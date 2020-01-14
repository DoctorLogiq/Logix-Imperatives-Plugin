package com.plugins.drlogiq.imperatives.commands;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.utilities.PlayerHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDebug implements CommandExecutor
{
    @Override
    public boolean onCommand (CommandSender sender, Command command, String alias, String[]args)
    {
        if (sender instanceof Player)
        {
            final Player player = (Player) sender;
            if (!PlayerHelper.playerIsOpOrDeveloper(player))
            {
                Imperatives.sendOpOnlyMessage(player, true);
                return true;
            }

            ImperativesConfig.Debug(player);
            return true;
        }
        else return false;
    }
}