package com.plugins.drlogiq.imperatives.commands;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.playerdata.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class CommandNote implements CommandExecutor, TabCompleter
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args)
    {
        if (sender instanceof Player)
        {
            final Player player = (Player) sender;

            PlayerData playerData = ImperativesConfig.getOrCreatePlayerData(player);
            if (playerData == null)
            {
                Imperatives.sendErrorMessage(player, "Could not retrieve or create your PlayerData, sorry!");
                return true;
            }
            if (args.length == 1)
            {
                if (args[0].equalsIgnoreCase("show"))
                {
                    if (playerData.note.length() > 0)
                    {
                        player.sendMessage(ChatColor.GRAY + "Your note: " + ChatColor.WHITE + "" + ChatColor.ITALIC + playerData.note);
                    }
                    else
                    {
                        Imperatives.sendWarningMessage(player, "You do not have a note set. Use /note set \"(note text)\" to set one.");
                    }
                    return true;
                }
                else if (args[0].equalsIgnoreCase("clear"))
                {
                    playerData.note = "";
                    player.sendMessage(ChatColor.DARK_GREEN + "Note cleared.");
                    if (ImperativesConfig.save())
                    {
                        Imperatives.sendSuccessMessage(player, "Config changes made and saved");
                    }
                    else
                    {
                        Imperatives.sendWarningMessage(player, "Config changes were made but the config did not save. Check the console for errors.");
                    }
                    return true;
                }
                else
                {
                    Imperatives.sendErrorMessage(player, "Unknown parameter '" + args[0] + "'");
                    return false;
                }
            }
            else if (args.length >= 2)
            {
                if (args[0].equalsIgnoreCase("set"))
                {
                    StringBuilder text = new StringBuilder();
                    for (int i = 1; i < args.length; ++i)
                    {
                        text.append(args[i]).append(" ");
                    }
                    text = new StringBuilder(text.toString().trim());

                    playerData.note = text.toString();
                    player.sendMessage(ChatColor.DARK_GREEN + "Note set.");
                    if (ImperativesConfig.save())
                    {
                        Imperatives.sendSuccessMessage(player, "Config changes made and saved");
                    }
                    else
                    {
                        Imperatives.sendWarningMessage(player, "Config changes were made but the config did not save. Check the console for errors.");
                    }
                    return true;
                }
                else
                {
                    Imperatives.sendErrorMessage(player, "Unknown parameter '" + args[0] + "'");
                    return false;
                }
            }
            else return false;
        }
        else return false;
    }

    private static final List<String> COMMANDS = new ArrayList<String>()
    {{
        add("show");
        add("set");
        add("clear");
    }};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        if (command.getName().equals("note"))
        {
            return args.length == 1
                   ? StringUtil.copyPartialMatches(args[0], COMMANDS, new ArrayList<>())
                   : null;
        }
        return new ArrayList<>();
    }
}
