package com.plugins.drlogiq.imperatives.events;

import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.playerdata.PlayerData;
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

    @EventHandler(priority = EventPriority.HIGHEST) // NOTE(LOGIQ): This seems to be needed in order for this to work
    void onPlayerChat(AsyncPlayerChatEvent event)
    {
        final Player player = event.getPlayer();
        final String originalMessage = event.getMessage();

        // NOTE(LOGIQ): Original format = §r<%1$s> %2$s
        String newFormat = ImperativesConfig.getString(ImperativesConfig.Keys.ChatMessageFormat, true);

        PlayerData playerData = ImperativesConfig.getOrCreatePlayerData(player);
        newFormat = newFormat.replace("%ROLE%",
                                      playerData != null ? playerData.role : ImperativesConfig.getString(
                                              !player.isOp() ? ImperativesConfig.Keys.DefaultRole : ImperativesConfig.Keys.DefaultOperatorRole,
                                              true));
        newFormat = newFormat.replace("%NAME%", "%1$s");
        newFormat = newFormat.replace("%MESSAGE%", "%2$s");
        newFormat = newFormat.replace("§w1", playerData != null ? playerData.colour : ImperativesConfig.getString(ImperativesConfig.Keys.DefaultNameColour, true));

        event.setFormat(newFormat);
    }
}