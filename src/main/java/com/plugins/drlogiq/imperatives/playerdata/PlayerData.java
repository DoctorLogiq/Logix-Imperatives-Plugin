package com.plugins.drlogiq.imperatives.playerdata;

public class PlayerData
{
    public String role;
    public String colour;
    public String note;

    // NOTE(LOGIQ): These should match the order they appear in the config!
    // NOTE(LOGIQ): Any changes must be reflected in ImperativesConfig#Debug()
    public PlayerData(String role, String colour, String note)
    {
        this.role = role;
        this.colour = colour;
        this.note = note;
    }

    @Override
    public String toString()
    {
        return "PlayerData -> [role: " + role + ", colour: " + colour.replace("§", "") + "]";
    }
}