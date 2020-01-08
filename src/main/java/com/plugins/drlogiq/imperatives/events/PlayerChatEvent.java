package com.plugins.drlogiq.imperatives.events;

import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.playerdata.PlayerData;
import com.plugins.drlogiq.imperatives.utilities.Debug;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener
{
    public static final PlayerChatEvent Instance = new PlayerChatEvent();

    private PlayerChatEvent()
    {
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    void onPlayerChat(AsyncPlayerChatEvent event)
    {
        final Player player = event.getPlayer();
        final String originalMessage = event.getMessage();

        // NOTE(LOGIQ): Original format = §r<%1$s> %2$s

        String format = ImperativesConfig.getString(ImperativesConfig.Keys.ChatMessageFormat, true);

        PlayerData playerData = ImperativesConfig.getOrCreatePlayerData(player);
        if (playerData != null)
        {
            format = format.replace("%ROLE%", playerData.role);
            format = format.replace("%NAME%", "%1$s");
            format = format.replace("%MESSAGE%", "%2$s");
            format = format.replace("§w1", playerData.colour);
            // TODO(LOGIQ): Replace wildcard

            event.setFormat(format);
        }
        else
        {
            Debug.logDebug("No PlayerData", true); // TEMPORARY
            // TODO(LOGIQ): No player data
        }
    }
}