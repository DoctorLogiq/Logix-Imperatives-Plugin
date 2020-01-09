package com.plugins.drlogiq.imperatives.events;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.playerdata.PlayerData;
import com.plugins.drlogiq.imperatives.utilities.Debug;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class DeathEvents implements Listener
{
    public static final DeathEvents Instance = new DeathEvents();

    private static LivingEntity lastEntityToDie;
    private static boolean entityIsPlayer;
    private static List<String> playersWhovePaidRespects = new ArrayList<>();

    private DeathEvents()
    {
    }

    @EventHandler
    void onPlayerDeath(final PlayerDeathEvent event)
    {
        final Player player = event.getEntity();
        setLastEntityToDie(player);

        if (event.getDeathMessage() != null)
        {
            PlayerData playerData = ImperativesConfig.getOrCreatePlayerData(player);
            final String originalMessage = event.getDeathMessage().replace(player.getDisplayName(), "").trim();

            String newMessage = ImperativesConfig.getString(ImperativesConfig.Keys.DeathMessageFormat, true);
            newMessage = newMessage.replace("%NAME%", player.getDisplayName());
            newMessage = newMessage.replace("%MESSAGE%", originalMessage);
            newMessage = newMessage.replace("§w1", playerData != null ? playerData.colour : ImperativesConfig.getString(ImperativesConfig.Keys.DefaultNameColour, true));

            event.setDeathMessage(newMessage);
        }
    }

    @EventHandler
    void onEntityDeath(final EntityDeathEvent event)
    {
        final LivingEntity entity = event.getEntity();

        if (entity.getCustomName() != null && entity.getCustomName().length() > 0)
        {
            Debug.logDebug("Entity '" + entity.getCustomName() + "' was slain");

            setLastEntityToDie(entity);
            entityIsPlayer = false;
            playersWhovePaidRespects.clear();

            // NOTE(LOGIQ): Broadcast death message.
            // TODO(LOGIQ): Find out why Minecraft isn't doing this
            String newMessage = ImperativesConfig.getString(ImperativesConfig.Keys.DeathMessageFormat, true);
            newMessage = newMessage.replace("%NAME%", entity.getCustomName());
            newMessage = newMessage.replace("%MESSAGE%", "was slain");
            newMessage = newMessage.replace("§w1", ImperativesConfig.getString(ImperativesConfig.Keys.DefaultNameColour, true));

            for (Player online : Imperatives.getInstance().getServer().getOnlinePlayers())
            {
                online.sendMessage(newMessage);
            }
        }
    }

    public void setLastEntityToDie(final LivingEntity entity)
    {
        lastEntityToDie = entity;
        entityIsPlayer = entity instanceof Player;
        playersWhovePaidRespects.clear();
    }

    public LivingEntity getLastEntityToDie()
    {
        return lastEntityToDie;
    }

    public boolean isEntityPlayer()
    {
        return entityIsPlayer;
    }

    public boolean hasPlayerPaidRespects(final Player player)
    {
        return playersWhovePaidRespects.contains(player.getName());
    }

    public void playerHasPaidRespects(final Player player)
    {
        playersWhovePaidRespects.add(player.getName());
    }
}
