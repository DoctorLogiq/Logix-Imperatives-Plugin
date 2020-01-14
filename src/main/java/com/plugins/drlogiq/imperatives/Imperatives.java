package com.plugins.drlogiq.imperatives;

import com.plugins.drlogiq.imperatives.commands.*;
import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.events.DeathEvents;
import com.plugins.drlogiq.imperatives.events.PlayerChatEvent;
import com.plugins.drlogiq.imperatives.events.PlayerLoginEvent;
import com.plugins.drlogiq.imperatives.utilities.Debug;
import com.plugins.drlogiq.imperatives.utilities.VersionControl;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Random;

import static org.bukkit.ChatColor.*;

// TODO(LOGIQ): Make config contain ALL comments (ugh, Spigot, WHYYY)
// TODO(LOGIQ): Implement /debug
public class Imperatives extends JavaPlugin
{
    public static final Random RNG = new Random();
    private static Imperatives Instance;

    @Override
    public void onEnable()
    {
        Instance = this;
        Debug.initialize(getLogger()); // NOTE(LOGIQ): Must be first, because all functions following this will be logging things.
        ImperativesConfig.load();
        // TODO(LOGIQ): Verify all PlayerData roles
        VersionControl.checkForUpdates(false, true); // TODO(LOGIQ): Set dev to false!

        // Schedule periodic update check
        // TODO(LOGIQ): Configurable period?
        final int update_check_period_ticks = (20 * 60 * 5);
        // TODO(LOGIQ): Set dev to false!
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> VersionControl.checkForUpdates(true, true), update_check_period_ticks, update_check_period_ticks);

        // Register event handlers
        getServer().getPluginManager().registerEvents(PlayerLoginEvent.Instance, this);
        getServer().getPluginManager().registerEvents(PlayerChatEvent.Instance, this);
        getServer().getPluginManager().registerEvents(DeathEvents.Instance, this);

        // Register command handlers
        Objects.requireNonNull(getCommand("f")).setExecutor(new CommandPayRespects());
        Objects.requireNonNull(getCommand("colour")).setExecutor(new CommandColour());
        Objects.requireNonNull(getCommand("setRole")).setExecutor(new CommandSetRole());
        Objects.requireNonNull(getCommand("refresh")).setExecutor(new CommandRefresh());
        Objects.requireNonNull(getCommand("debug")).setExecutor(new CommandDebug());
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

    public static void sendOpOnlyMessage(final Player player, boolean andDev)
    {
        sendErrorMessage(player, "This command is for server operators (OPs)" + (andDev ? " and the plugin developer" : "") + " only.");
    }
}