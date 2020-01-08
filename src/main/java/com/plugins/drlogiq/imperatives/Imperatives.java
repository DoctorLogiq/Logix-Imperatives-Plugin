package com.plugins.drlogiq.imperatives;

import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.events.PlayerChatEvent;
import com.plugins.drlogiq.imperatives.events.PlayerLoginEvent;
import com.plugins.drlogiq.imperatives.utilities.Debug;
import com.plugins.drlogiq.imperatives.utilities.VersionControl;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.ChatColor.*;

public class Imperatives extends JavaPlugin
{
    private static Imperatives Instance;

    @Override
    public void onEnable()
    {
        Instance = this;
        Debug.initialize(getLogger()); // NOTE(LOGIQ): Must be first, because all functions following this will be logging things.
        ImperativesConfig.load();
        // TODO(LOGIQ): Verify all PlayerData roles
        VersionControl.checkForUpdates(false);

        // Schedule periodic update check
        // TODO(LOGIQ): Configurable period?
        final int update_check_period_ticks = (20 * 60 * 5);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> VersionControl.checkForUpdates(true), update_check_period_ticks, update_check_period_ticks);

        // Register event handlers
        getServer().getPluginManager().registerEvents(PlayerLoginEvent.Instance, this);
        getServer().getPluginManager().registerEvents(PlayerChatEvent.Instance, this);
    }

    @Override
    public void onDisable()
    {

    }

    public void disable(String reason, boolean exceptionDetailsToFollow)
    {
        Debug.logError("Disabling Logix-Imperatives at the plugin's own request.", true);
        Debug.logError("Reason: " + reason, true);
        if (exceptionDetailsToFollow)
        {
            Debug.logError("Exception details to follow:");
        }
        getServer().getPluginManager().disablePlugin(this);
    }

    public static Imperatives getInstance()
    {
        assert Instance != null;
        return Instance;
    }

    // TODO(LOGIQ): Make "[Logix-Imperatives] tag toggleable in config
    public static void sendMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + DARK_GRAY + "[Logix-Imperatives] " + GRAY + message);
    }

    public static void sendDebugMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + DARK_GRAY + "[Logix-Imperatives] Debug: " + message);
    }

    public static void sendWarningMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + GOLD + "[Logix-Imperatives] Warning: " + message);
    }

    public static void sendErrorMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + RED + "[Logix-Imperatives] Error: " + DARK_RED + message);
    }

    public static void sendSuccessMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + GREEN + "[Logix-Imperatives] Success: " + DARK_GREEN + message);
    }

    public static String getReportMessage()
    {
        return "Please make sure to report this to the plugin developer (Dr. Logiq), or check the issues on the plugin's GitHub page. Use /website for a link.";
    }

    public static void sendReportMessage(final Player player)
    {
        sendWarningMessage(player, getReportMessage());
    }

    public static void sendOpOnlyMessage(final Player player)
    {
        sendErrorMessage(player, "This command is for server operators (OPs) only.");
    }
}