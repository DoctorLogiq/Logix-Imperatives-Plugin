package com.plugins.drlogiq.imperatives;

import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
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
        VersionControl.checkForUpdates();

        // Register event handlers
        getServer().getPluginManager().registerEvents(PlayerLoginEvent.Instance, this);
    }

    @Override
    public void onDisable()
    {

    }

    public void disable(String reason)
    {
        Debug.logError("Disabling Logix-Imperatives at the plugin's own request.", true);
        Debug.logError("Reason: " + reason, true);
        getServer().getPluginManager().disablePlugin(this);
    }

    public static Imperatives getInstance()
    {
        assert Instance != null;
        return Instance;
    }

    // TODO(LOGIQ): Verify formatting on all of these
    // TODO(LOGIQ): Make "[Logix-Imperatives] tag toggleable in config
    public static void sendMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + DARK_GRAY + "[Logix-Imperatives] " + GRAY + message);
    }

    public static void sendDebugMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + DARK_GRAY + "[Logix-Imperatives // DEBUG] " + message);
    }

    public static void sendWarningMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + GOLD + "[Logix-Imperatives // WARNING] " + message);
    }

    public static void sendErrorMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + RED + "[Logix-Imperatives // ERROR] : " + DARK_RED + message);
    }

    public static void sendSuccessMessage(final Player player, final String message)
    {
        player.sendMessage(ITALIC + "" + GREEN + "[Logix-Imperatives // SUCCESS] : " + DARK_GREEN + message);
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