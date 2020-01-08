package com.plugins.drlogiq.imperatives.events;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.utilities.PlayerHelper;
import com.plugins.drlogiq.imperatives.utilities.VersionControl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
    }
}