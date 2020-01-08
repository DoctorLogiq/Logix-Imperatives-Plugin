package com.plugins.drlogiq.imperatives.utilities;

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
}