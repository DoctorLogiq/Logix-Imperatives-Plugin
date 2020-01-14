package com.plugins.drlogiq.imperatives.commands;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRefresh implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args)
    {
        if (sender instanceof Player)
        {
            final Player player = (Player) sender;
            if (!player.isOp())
            {
                Imperatives.sendOpOnlyMessage(player, false);
                return true;
            }

            boolean save = ImperativesConfig.save();
            if (!save)
            {
                Imperatives.sendErrorMessage(player, "Failed to save the config. Changes to PlayerData may be lost.");
            }
            ImperativesConfig.load();
            Imperatives.sendMessage(player, ChatColor.YELLOW + "Config reloaded.");
            return true;
        }
        else return false;
    }
}
