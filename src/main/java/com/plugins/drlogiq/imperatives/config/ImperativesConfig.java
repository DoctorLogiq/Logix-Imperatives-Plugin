package com.plugins.drlogiq.imperatives.config;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.playerdata.PlayerData;
import com.plugins.drlogiq.imperatives.utilities.Debug;
import com.plugins.drlogiq.imperatives.utilities.StringHelper;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ImperativesConfig
{
    private static HashMap<String, Integer> ConfigIntegers = new HashMap<>();
    private static HashMap<String, Boolean> ConfigBooleans = new HashMap<>();
    private static HashMap<String, String> ConfigStrings = new HashMap<>();
    private static HashMap<String, List<String>> ConfigStringLists = new HashMap<>();
    private static HashMap<String, PlayerData> ConfigPlayerData = new HashMap<>();

    private static final File File = new File(StringHelper.makeFilePath(Imperatives.getInstance().getDataFolder() + "/config.yml"));
    private static FileConfiguration config;

    public static class Keys
    {
        public static final String EnableDebugMessages = "EnableDebugMessages";
        public static final String ChatMessageFormat = "ChatMessageFormat";
        public static final String DeathMessageFormat = "DeathMessageFormat";
        public static final String RespectsMessageFormat = "RespectsMessageFormat";
        public static final String WelcomeMessagesNewPlayers = "WelcomeMessagesNewPlayers";
        public static final String WelcomeMessagesReturningPlayers = "WelcomeMessagesReturningPlayers";
        public static final String PersonalWelcomeMessagesNewPlayers = "PersonalWelcomeMessagesNewPlayers";
        public static final String PersonalWelcomeMessagesReturningPlayers = "PersonalWelcomeMessagesReturningPlayers";
        public static final String DefaultNameColour = "DefaultNameColour";
        public static final String Roles = "Roles";
        public static final String DefaultRole = "DefaultRole";
        public static final String DefaultOperatorRole = "DefaultOperatorRole";
        public static final String AllowNameColourCustomisation = "AllowNameColourCustomisation";
        public static final String PlayerData = "PlayerData";
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void load()
    {
        // Verify the file exists
        if (!File.exists())
        {
            Debug.logDebug("Plugin config does not appear to exist... attempting to create it.");
            Debug.logDebug("Target filename: " + File.getAbsolutePath());

            File.getParentFile().mkdirs();

            Imperatives.getInstance().saveResource("config.yml", false);
            if (File.exists())
            {
                Debug.logDebug("Successfully created the config file");
            }
            else
            {
                Imperatives.getInstance().disable("Failed to create the config file.", false);
                return;
            }
        }

        // If the config is already in memory, save it and clear lists
        if (config != null)
        {
            try
            {
                config.save(File);
                Debug.log("Saved config from memory to the file");
            }
            catch (final IOException exception)
            {
                Imperatives.getInstance().disable("Failed to save the config. Any changes made using commands since the last time the plugin was enabled will likely be lost. Sorry about that!", true);
                exception.printStackTrace();
                return;
            }

            config = null;
            ConfigIntegers.clear();
            ConfigBooleans.clear();
            ConfigStrings.clear();
            ConfigStringLists.clear();
            ConfigPlayerData.clear();
        }

        // Load the config
        config = YamlConfiguration.loadConfiguration(File);
        Debug.logDebug("Loaded config");

        // Load config values
        loadBoolean(Keys.EnableDebugMessages);
        loadString(Keys.ChatMessageFormat);
        loadString(Keys.DeathMessageFormat);
        loadString(Keys.RespectsMessageFormat);
        loadStringList(Keys.WelcomeMessagesNewPlayers);
        loadStringList(Keys.WelcomeMessagesReturningPlayers);
        loadStringList(Keys.PersonalWelcomeMessagesNewPlayers);
        loadStringList(Keys.PersonalWelcomeMessagesReturningPlayers);
        loadString(Keys.DefaultNameColour);
        loadStringList(Keys.Roles);
        loadString(Keys.DefaultRole);
        loadString(Keys.DefaultOperatorRole);
        loadBoolean(Keys.AllowNameColourCustomisation);
        loadAllPlayerData();
    }

    //region Loading

    private static void loadInteger(String key)
    {
        if (ConfigIntegers.containsKey(key))
        {
            Imperatives.getInstance().disable("Cannot load integer '" + key + "' from config because it is already present in the ConfigIntegers HashMap.", false);
            return;
        }
        if (config.contains(key))
        {
            ConfigIntegers.put(key, config.getInt(key));
            Debug.log("Loaded integer '" + key + "' from config. Value: " + ConfigIntegers.get(key));
        }
        else
        {
            Imperatives.getInstance().disable("Cannot load integer '" + key + "' from config. Is it set?", false);
        }
    }

    private static void loadBoolean(String key)
    {
        if (ConfigBooleans.containsKey(key))
        {
            Imperatives.getInstance().disable("Cannot load boolean '" + key + "' from config because it is already present in the ConfigBooleans HashMap.", false);
            return;
        }
        if (config.contains(key))
        {
            ConfigBooleans.put(key, config.getBoolean(key));
            Debug.log("Loaded boolean '" + key + "' from config. Value: " + ConfigBooleans.get(key));
        }
        else
        {
            Imperatives.getInstance().disable("Cannot load boolean '" + key + "' from config. Is it set?", false);
        }
    }

    private static void loadString(String key)
    {
        if (ConfigStrings.containsKey(key))
        {
            Imperatives.getInstance().disable("Cannot load string '" + key + "' from config because it is already present in the ConfigStrings HashMap.", false);
            return;
        }
        if (config.contains(key))
        {
            ConfigStrings.put(key, config.getString(key));
            Debug.log("Loaded string '" + key + "' from config. Value: " + ConfigStrings.get(key));
        }
        else
        {
            Imperatives.getInstance().disable("Cannot load string '" + key + "' from config. Is it set?", false);
        }
    }

    private static void loadStringList(String key)
    {
        if (ConfigStringLists.containsKey(key))
        {
            Imperatives.getInstance().disable("Cannot load string list '" + key + "' from config because it is already present in the ConfigStringLists HashMap.", false);
            return;
        }
        if (config.contains(key))
        {
            ConfigStringLists.put(key, config.getStringList(key));
            Debug.log("Loaded string list '" + key + "' from config. Values: ");
            for (String value : ConfigStringLists.get(key))
            {
                Debug.log("  - " + value);
            }
        }
        else
        {
            Imperatives.getInstance().disable("Cannot load string list '" + key + "' from config. Is it set?", false);
        }
    }

    //endregion

    //region Getters

    public static Integer getInteger(String key, boolean absolutelyRequired)
    {
        if (ConfigIntegers.containsKey(key))
        {
            return ConfigIntegers.get(key);
        }
        else if (absolutelyRequired)
        {
            Imperatives.getInstance().disable("Could not retrieve integer '" + key + "' from the loaded config values, and it was required.", false);
        }
        return 0;
    }

    public static Boolean getBoolean(String key, boolean absolutelyRequired)
    {
        if (ConfigBooleans.containsKey(key))
        {
            return ConfigBooleans.get(key);
        }
        else if (absolutelyRequired)
        {
            Imperatives.getInstance().disable("Could not retrieve boolean '" + key + "' from the loaded config values, and it was required.", false);
        }
        return false;
    }

    public static String getString(String key, boolean absolutelyRequired)
    {
        if (ConfigStrings.containsKey(key))
        {
            return ConfigStrings.get(key);
        }
        else if (absolutelyRequired)
        {
            Imperatives.getInstance().disable("Could not retrieve string '" + key + "' from the loaded config values, and it was required.", false);
        }
        return "";
    }

    public static List<String> getStringList(String key, boolean absolutelyRequired)
    {
        if (ConfigStringLists.containsKey(key))
        {
            return ConfigStringLists.get(key);
        }
        else if (absolutelyRequired)
        {
            Imperatives.getInstance().disable("Could not retrieve string list '" + key + "' from the loaded config values, and it was required.", false);
        }
        return new ArrayList<>();
    }

    //endregion

    private static void loadAllPlayerData()
    {
        ConfigurationSection section = config.getConfigurationSection(Keys.PlayerData);
        Set<String> keys;
        if (section != null)
        {
            keys = section.getKeys(false);
            for (String key : keys)
            {
                List<String> list = config.getStringList(Keys.PlayerData + "." + key);
                if (list.size() == 2) // NOTE: Update 1.0.1: add note
                {
                    list.add("");
                }
                else if (list.size() == 3) // NOTE: Number of items in PlayerData
                {
                    ConfigPlayerData.put(key, new PlayerData(list.get(0), list.get(1), list.get(2)));
                    Debug.log("Loaded player data for '" + key + "': " + ConfigPlayerData.get(key).toString());
                }
                else
                {
                    Debug.logError("Cannot load player data for " + key + "; some items may be missing from the list.", true);
                }
            }
        }
    }

    public static boolean isPlayerDataLoaded(final Player player)
    {
        return ConfigPlayerData.containsKey(player.getName().toLowerCase());
    }

    public static PlayerData getOrCreatePlayerData(final Player player)
    {
        final String key = player.getName().toLowerCase();

        if (isPlayerDataLoaded(player))
            return ConfigPlayerData.get(key);

        ConfigurationSection section = config.getConfigurationSection(Keys.PlayerData);
        List<String> newPlayerData = new ArrayList<>();
        newPlayerData.add(0, player.isOp() ? getString(Keys.DefaultOperatorRole, true) : getString(Keys.DefaultRole, true));
        newPlayerData.add(1, getString(Keys.DefaultNameColour, true));
        newPlayerData.add(2, "");
        Objects.requireNonNull(section).set(key, newPlayerData);
        ConfigPlayerData.put(key, new PlayerData(newPlayerData.get(0), newPlayerData.get(1), newPlayerData.get(2)));

        try
        {
            config.save(File);
            Debug.log("Created player data for '" + key + "': " + ConfigPlayerData.get(key).toString(), true);
        }
        catch (final IOException exception)
        {
            Imperatives.getInstance().disable("Failed to save config after making changes (regarding PlayerData)", true);
            exception.printStackTrace();
            return null;
        }

        return ConfigPlayerData.get(key);
    }

    public static boolean save()
    {
        try
        {
            config.save(File);
            Debug.logDebug("Config saved");
            return true;
        }
        catch (final IOException exception)
        {
            Debug.logError("Config save failed!");
            exception.printStackTrace();
            return false;
        }
    }

    //region Debug Command

    public static void Debug(Player player)
    {
        for (String key : ConfigIntegers.keySet())
        {
            player.sendMessage(ChatColor.GRAY + "Integer " + ChatColor.GOLD + "'" + key + "'" + ChatColor.GRAY + ": " + ChatColor.AQUA + ConfigIntegers.get(key));
        }
        for (String key : ConfigBooleans.keySet())
        {
            player.sendMessage(ChatColor.GRAY + "Bool " + ChatColor.GOLD + "'" + key + "'" + ChatColor.GRAY + ": " + ChatColor.BLUE + (ConfigBooleans.get(key) ? "True" : "False"));
        }
        for (String key : ConfigStrings.keySet())
        {
            player.sendMessage(ChatColor.GRAY + "String " + ChatColor.GOLD + "'" + key + "'" + ChatColor.GRAY + ": \"" + ChatColor.YELLOW + ConfigStrings.get(key).replace('§', '$') + "\"");
        }
        for (String key : ConfigStringLists.keySet())
        {
            player.sendMessage(ChatColor.GRAY + "StringList " + ChatColor.GOLD + "'" + key + "'" + ChatColor.GRAY + ":");
            List<String> values = ConfigStringLists.get(key);
            for (String value : values)
            {
                player.sendMessage(ChatColor.GRAY + "  - \"" + ChatColor.YELLOW + value.replace('§', '$') + "\"");
            }
        }
        for (String key : ConfigPlayerData.keySet())
        {
            player.sendMessage(ChatColor.GRAY + "PlayerData for " + ChatColor.BLUE + "'" + key + "'" + ChatColor.GRAY + ":");
            PlayerData values = ConfigPlayerData.get(key);
            player.sendMessage(ChatColor.GRAY + "  - Role: " + values.role);
            String colour = values.colour.replace("§", "");
            player.sendMessage(ChatColor.GRAY + "  - Colour: " + colour + " (" + Objects.requireNonNull(ChatColor.getByChar(colour)).name() + ")");
            player.sendMessage(ChatColor.GRAY + "  - Note: " + values.note);
        }
    }

    //endregion
}