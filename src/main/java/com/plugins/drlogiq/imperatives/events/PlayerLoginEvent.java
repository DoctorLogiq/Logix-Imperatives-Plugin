package com.plugins.drlogiq.imperatives.events;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.playerdata.PlayerData;
import com.plugins.drlogiq.imperatives.utilities.PlayerHelper;
import com.plugins.drlogiq.imperatives.utilities.VersionControl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

import static com.plugins.drlogiq.imperatives.utilities.VersionControl.*;

public class PlayerLoginEvent implements Listener
{
    public static final PlayerLoginEvent Instance = new PlayerLoginEvent();

    private PlayerLoginEvent()
    {
    }

    @EventHandler
    void onPlayerLogin(PlayerJoinEvent event)
    {
        final Player player = event.getPlayer();

        if (VersionControl.IsUpdateAvailable && PlayerHelper.playerIsOpOrDeveloper(player))
        {
            Imperatives.sendMessage(player, "An update for Logix-Imperatives is available!");
            Imperatives.sendMessage(player, "Your version: " + CurrentVersion.toString() + " (" + CurrentVersionName + ") - " + CurrentVersionDescription);
            Imperatives.sendMessage(player, "Latest version: " + LatestVersion.toString() + " (" + LatestVersionName + ") - " + LatestVersionDescription);
            if (IsUpdateRecommended)
            {
                Imperatives.sendWarningMessage(player, "This update is strongly recommended!");
            }
        }

        PlayerData playerData = ImperativesConfig.getOrCreatePlayerData(player);

        List<String> joinMessages = ImperativesConfig.getStringList(player.hasPlayedBefore()
                                                                    ? ImperativesConfig.Keys.WelcomeMessagesReturningPlayers
                                                                    : ImperativesConfig.Keys.WelcomeMessagesNewPlayers,
                                                                    true);
        if (joinMessages.size() > 0)
        {
            String joinMessage = joinMessages.get(Imperatives.RNG.nextInt(joinMessages.size()));
            joinMessage = joinMessage.replace("%NAME%", player.getDisplayName());
            joinMessage = joinMessage.replace("§w1", playerData != null ? playerData.colour : ImperativesConfig.getString(ImperativesConfig.Keys.DefaultNameColour, true));

            event.setJoinMessage(joinMessage);
        }

        Imperatives.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Imperatives.getInstance(), () ->
        {
            List<String> personalJoinMessages = ImperativesConfig.getStringList(player.hasPlayedBefore()
                                                                                ? ImperativesConfig.Keys.PersonalWelcomeMessagesReturningPlayers
                                                                                : ImperativesConfig.Keys.PersonalWelcomeMessagesNewPlayers,
                                                                                true);
            if (personalJoinMessages.size() > 0)
            {
                player.sendMessage(personalJoinMessages.get(Imperatives.RNG.nextInt(personalJoinMessages.size())));
            }
        }, 3);
    }
}