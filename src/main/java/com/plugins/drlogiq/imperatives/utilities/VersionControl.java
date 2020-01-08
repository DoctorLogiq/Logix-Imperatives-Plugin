package com.plugins.drlogiq.imperatives.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class VersionControl
{
    public static final Version CurrentVersion = new Version("1.0.0"); // NOTE(LOGIQ): Make sure this is reflected in pom.xml, plugin.yml and latest.txt!
    public static final String CurrentVersionName = "Foundation";
    public static final String CurrentVersionDescription = "A brand new plugin, wink";

    public static Version LatestVersion;
    public static String LatestVersionName;
    public static String LatestVersionDescription;

    public static boolean IsUpdateAvailable;
    public static boolean IsUpdateRecommended;

    public static void checkForUpdates(boolean silent)
    {
        // Attempt to load the latest version from GitHub
        try
        {
            final URL url = new URL("https://raw.githubusercontent.com/DoctorLogiq/Logix-Imperatives-Plugin/master/latest.txt");
            final URLConnection connection = url.openConnection();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            /* NOTE(LOGIQ): This assumes 'latest.txt' on GitHub contains precicely two lines pertaining to the values represented here (chronologically). It is therefore non-optional to ensure 'latest.txt' is always written properly! Layout:
                   - Version string
                   - Strongly recommended? (true/false)
                   - Version name
                   - Version description
             */

            String latest_version_string = reader.readLine();
            String strongly_recommended_string = reader.readLine();
            String latest_version_name_string = reader.readLine();
            String latest_version_description_string = reader.readLine();
            reader.close();

            LatestVersion = new Version(latest_version_string);

            IsUpdateAvailable = LatestVersion.compareTo(CurrentVersion) > 0;
            IsUpdateRecommended = strongly_recommended_string == null || strongly_recommended_string.equals("true");

            LatestVersionName = latest_version_name_string == null ?
                                LatestVersion.toString().equals("0.1.0") ? "Initial" :
                                LatestVersion.toString().equals("0.1.1") ? "Blotch" :
                                LatestVersion.toString().equals("0.2.0") ? "Cement" :
                                "Unknown" : latest_version_name_string;

            LatestVersionDescription = latest_version_description_string == null ?
                                       LatestVersion.toString().equals("0.1.0") ? "The initial release" :
                                       LatestVersion.toString().equals("0.1.1") ? "A failed attempt at fixing bugs" :
                                       LatestVersion.toString().equals("0.2.0") ? "A complete plugin re-write" :
                                       "Unknown" : latest_version_description_string;
        }
        catch (final IOException exception)
        {
            exception.printStackTrace();
        }

        // Log to server console
        if (IsUpdateAvailable)
        {
            Debug.log("An update for Logix-Imperatives is available!", true);
            Debug.log("Your version: " + CurrentVersion.toString() + " (" + CurrentVersionName + ") - " + CurrentVersionDescription, true);
            Debug.log("Latest version: " + LatestVersion.toString() + " (" + LatestVersionName + ") - " + LatestVersionDescription, true);
            if (IsUpdateRecommended)
            {
                Debug.logWarning("This update is strongly recommended!", true);
            }
        }
        else if (!silent)
        {
            Debug.log("Current version: " + CurrentVersion.toString() + " (latest). No updates are available currently. Happy Minecrafting!");
        }
    }
}