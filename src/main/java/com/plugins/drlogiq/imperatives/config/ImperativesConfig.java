package com.plugins.drlogiq.imperatives.config;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.utilities.Debug;
import com.plugins.drlogiq.imperatives.utilities.StringHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ImperativesConfig
{
    private static FileConfiguration config;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void load()
    {
        final File target_file = new File(StringHelper.makeFilePath(Imperatives.getInstance().getDataFolder() + "/config.yml"));

        // Verify the file exists
        if (!target_file.exists())
        {
            Debug.logDebug("Plugin config does not appear to exist... attempting to create it.");
            Debug.logDebug("Target filename: " + target_file.getAbsolutePath());

            target_file.getParentFile().mkdirs();

            Imperatives.getInstance().saveResource("config.yml", false);
            if (target_file.exists())
            {
                Debug.logDebug("Successfully created the config file");
            }
            else
            {
                Imperatives.getInstance().disable("Failed to create the config file.");
            }
        }

        // Load the config
        config = YamlConfiguration.loadConfiguration(target_file);
        Debug.logDebug("Loaded config");
    }
}