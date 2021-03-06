package com.plugins.drlogiq.imperatives.utilities;

import java.security.InvalidParameterException;

public class Version implements Comparable<Version>
{
    private String version;

    public final String get()
    {
        return this.version;
    }

    public Version(String version)
    {
        if (version == null)
        {
            throw new IllegalArgumentException("Version can not be null");
        }
        if (!version.matches("[0-9]+(\\.[0-9]+)*"))
        {
            throw new IllegalArgumentException("Invalid version format");
        }
        this.version = version;
    }

    @Override
    public int compareTo(Version that)
    {
        if (that == null)
        {
            return 1;
        }
        String[] thisParts = this.get().split("\\.");
        String[] thatParts = that.get().split("\\.");
        int length = Math.max(thisParts.length, thatParts.length);
        for (int i = 0; i < length; i++)
        {
            int thisPart = i < thisParts.length ?
                           Integer.parseInt(thisParts[i]) : 0;
            int thatPart = i < thatParts.length ?
                           Integer.parseInt(thatParts[i]) : 0;
            if (thisPart < thatPart)
            {
                return -1;
            }
            if (thisPart > thatPart)
            {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object that)
    {
        if (!(that instanceof Version))
        {
            throw new InvalidParameterException("Cannot check if Version#equals([" + that.getClass().getSimpleName() + "])");
        }
        if (this == that)
        {
            return true;
        }
        return this.compareTo((Version) that) == 0;
    }

    @Override
    public String toString()
    {
        return version;
    }
}