package com.plugins.drlogiq.imperatives.commands;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.events.DeathEvents;
import com.plugins.drlogiq.imperatives.playerdata.PlayerData;
import com.plugins.drlogiq.imperatives.utilities.Debug;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPayRespects implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args)
    {
        if (sender instanceof Player)
        {
            final Player player = (Player)sender;
            if (DeathEvents.Instance.getLastEntityToDie() == null)
            {
                Imperatives.sendMessage(player, "No-one has died recently.");
                return true;
            }
            if (!DeathEvents.Instance.hasPlayerPaidRespects(player))
            {
                PlayerData playerData = ImperativesConfig.getOrCreatePlayerData(player);
                String message = ImperativesConfig.getString(ImperativesConfig.Keys.RespectsMessageFormat, true);
                String nameOfRespectee = DeathEvents.Instance.isEntityPlayer() ? ((Player) DeathEvents.Instance.getLastEntityToDie()).getDisplayName() : DeathEvents.Instance.getLastEntityToDie().getCustomName();
                if (nameOfRespectee == null)
                {
                    Imperatives.sendErrorMessage(player, "Unknown error; could not get the name of the entity who died recently");
                    Debug.logError("Unknown error; could not get the name of the entity who died recently");
                    return true;
                }
                if (nameOfRespectee.equals(player.getDisplayName()))
                {
                    // TODO(LOGIQ): Custom message ("...themself?!")
                }

                message = message.replace("%NAME1%", player.getDisplayName());
                message = message.replace("%NAME2%", nameOfRespectee);
                message = message.replace("§w1", playerData != null ? playerData.colour : ImperativesConfig.getString(ImperativesConfig.Keys.DefaultNameColour, true));
                if (DeathEvents.Instance.isEntityPlayer())
                {
                    PlayerData targetPlayerData = ImperativesConfig.getOrCreatePlayerData(((Player)DeathEvents.Instance.getLastEntityToDie()));
                    message = message.replace("§w2", targetPlayerData != null ? targetPlayerData.colour : ImperativesConfig.getString(ImperativesConfig.Keys.DefaultNameColour, true));
                }
                else
                {
                    message = message.replace("§w2", ImperativesConfig.getString(ImperativesConfig.Keys.DefaultNameColour, true));
                }

                // Broadcast
                for (Player online : Imperatives.getInstance().getServer().getOnlinePlayers())
                {
                    online.sendMessage(message);;
                }

                DeathEvents.Instance.playerHasPaidRespects(player);

                return true;
            }
            else
            {
                String nameOfRespectee = DeathEvents.Instance.isEntityPlayer() ? ((Player)DeathEvents.Instance.getLastEntityToDie()).getDisplayName() : DeathEvents.Instance.getLastEntityToDie().getCustomName();
                if (nameOfRespectee == null)
                {
                    Imperatives.sendErrorMessage(player, "Unknown error; could not get the name of the entity who died recently");
                    Debug.logError("Unknown error; could not get the name of the entity who died recently");
                    return true;
                }

                Imperatives.sendMessage(player, "You have already paid your respects to " + nameOfRespectee + ".");
                return true;
            }
        }
        return false;
    }
}