package com.plugins.drlogiq.imperatives.commands;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.playerdata.PlayerData;
import com.plugins.drlogiq.imperatives.utilities.PlayerHelper;
import com.plugins.drlogiq.imperatives.utilities.StringHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class CommandSetRole implements CommandExecutor, TabCompleter
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args)
    {
        if (sender instanceof Player)
        {
            final Player player = (Player) sender;
            if (!PlayerHelper.playerIsOpOrDeveloper(player))
            {
                Imperatives.sendOpOnlyMessage(player, true);
                return true;
            }

            if (args.length > 2)
            {
                StringBuilder text = new StringBuilder();
                for (int i = 1; i < args.length; ++i)
                {
                    text.append(args[i]).append(" ");
                }
                text = new StringBuilder(text.toString().trim());

                Player target = Imperatives.getInstance().getServer().getPlayer(args[0]);
                if (target == null)
                {
                    Imperatives.sendErrorMessage(player, "The target player does not appear to exist, or has never been on this server.");
                    return true;
                }
                if (Imperatives.getInstance().getServer().getOnlinePlayers().contains(target)) // NOTE(LOGIQ): Require player to be online
                {
                    List<String> roles = ImperativesConfig.getStringList(ImperativesConfig.Keys.Roles, true);

                    int index;
                    try
                    {
                        index = Integer.parseInt(text.toString().replaceAll("[^0-9]", ""));
                    }
                    catch (final Exception exception)
                    {
                        Imperatives.sendErrorMessage(player, "Error parsing index to an integer. Are you sure it's a valid number?");
                        exception.printStackTrace();
                        return false;
                    }

                    if (index >= 0 && index <= roles.size())
                    {
                        PlayerData playerData = ImperativesConfig.getOrCreatePlayerData(target);
                        if (playerData == null)
                        {
                            Imperatives.sendErrorMessage(player, "Failed to retrieve or create PlayerData for the target player.");
                            return true;
                        }
                        playerData.role = roles.get(index);
                        if (ImperativesConfig.save())
                        {
                            Imperatives.sendSuccessMessage(player, "Config changes made and saved");
                        }
                        else
                        {
                            Imperatives.sendWarningMessage(player, "Config changes were made but the config did not save. Check the console for errors.");
                        }
                    }
                    else
                    {
                        Imperatives.sendErrorMessage(player, "This role index is out of bounds. Are you sure a role with that index exists?");
                    }

                    return true;
                }
                else
                {
                    Imperatives.sendErrorMessage(player, "The target player is not online.");
                    return true;
                }
            }
            else return false;
        }
        else return false;
    }

    private static final List<String> COMMANDS = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        if (command.getName().equals("setRole"))
        {
            COMMANDS.clear();
            List<String> roles = ImperativesConfig.getStringList(ImperativesConfig.Keys.Roles, true);
            for (int i = 0; i < roles.size(); ++i)
            {
                String suggestion = "\"" + StringHelper.removeColourCodes(roles.get(i)) + " (" + i + ")\"";
                COMMANDS.add(suggestion);
            }

            return (args.length == 2) ? StringUtil.copyPartialMatches(args[1], COMMANDS, new ArrayList<>()) : null;
        }
        return new ArrayList<>();
    }
}
