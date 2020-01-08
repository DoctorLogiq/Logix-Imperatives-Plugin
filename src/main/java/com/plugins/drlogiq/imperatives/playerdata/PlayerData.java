package com.plugins.drlogiq.imperatives.playerdata;

public class PlayerData
{
    public String role;
    public String colour;

    // NOTE(LOGIQ): These should match the order they appear in the config!
    public PlayerData(String role, String colour)
    {
        this.role = role;
        this.colour = colour;
    }

    @Override
    public String toString()
    {
        return "PlayerData{" +
                "role='" + role + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}