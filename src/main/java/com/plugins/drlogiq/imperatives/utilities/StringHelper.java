package com.plugins.drlogiq.imperatives.utilities;

import org.bukkit.ChatColor;

import java.io.File;

public class StringHelper
{
    public static String makeFilePath(String path)
    {
        if (File.separatorChar == '/')
        {
            path = path.replace('\\', File.separatorChar);
        }
        else if (File.separatorChar == '\\')
        {
            path = path.replace('/', File.separatorChar);
        }
        return path;
    }

    public static String removeColourCodes(String str)
    {
        for (ChatColor colour : ChatColor.values())
        {
            str = str.replace("§" + colour.getChar(), "");
        }
        return str;
    }
}