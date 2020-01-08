package com.plugins.drlogiq.imperatives.utilities;

import com.plugins.drlogiq.imperatives.Imperatives;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Debug
{
    private static Logger Logger;

    public static void initialize(final Logger logger)
    {
        Logger = logger;
    }

    public static void log(String message)
    {
        log(message, false);
    }

    public static void logDebug(String message)
    {
        logDebug(message, false);
    }

    public static void logWarning(String message)
    {
        logWarning(message, false);
    }

    public static void logError(String message)
    {
        logError(message, false);
    }

    public static void log(String message, boolean broadcastToOPs)
    {
        Logger.info("[Logix-Imperatives] INFO >> " + message);
        if (broadcastToOPs)
        {
            for (final Player player : Bukkit.getServer().getOnlinePlayers())
            {
                if (player.isOp() || PlayerHelper.playerIsDeveloper(player))
                {
                    Imperatives.sendMessage(player, message);
                }
            }
        }
    }

    public static void logDebug(String message, boolean broadcastToOPs)
    {
        Logger.info("[Logix-Imperatives] INFO >> " + message);
        if (broadcastToOPs)
        {
            for (final Player player : Bukkit.getServer().getOnlinePlayers())
            {
                if (player.isOp() || PlayerHelper.playerIsDeveloper(player))
                {
                    Imperatives.sendDebugMessage(player, message);
                }
            }
        }
    }

    public static void logWarning(String message, boolean broadcastToOPs)
    {
        Logger.info("[Logix-Imperatives] WARNING >> " + message);
        if (broadcastToOPs)
        {
            for (final Player player : Bukkit.getServer().getOnlinePlayers())
            {
                if (player.isOp() || PlayerHelper.playerIsDeveloper(player))
                {
                    Imperatives.sendWarningMessage(player, message);
                }
            }
        }
    }

    public static void logError(String message, boolean broadcastToOPs)
    {
        Logger.info("[Logix-Imperatives] ERROR >> " + message);
        if (broadcastToOPs)
        {
            for (final Player player : Bukkit.getServer().getOnlinePlayers())
            {
                if (player.isOp() || PlayerHelper.playerIsDeveloper(player))
                {
                    Imperatives.sendErrorMessage(player, message);
                }
            }
        }
    }
}