package com.plugins.drlogiq.imperatives.commands;

import com.plugins.drlogiq.imperatives.Imperatives;
import com.plugins.drlogiq.imperatives.config.ImperativesConfig;
import com.plugins.drlogiq.imperatives.playerdata.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandColour implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args)
    {
        if (sender instanceof Player)
        {
            final Player player = (Player)sender;

            if (ImperativesConfig.getBoolean(ImperativesConfig.Keys.AllowNameColourCustomisation, true))
            {
                PlayerData playerData = ImperativesConfig.getOrCreatePlayerData(player);
                if (playerData == null)
                {
                    Imperatives.sendErrorMessage(player, "Could not retrieve or create your PlayerData, sorry!");
                    return true;
                }
                if (args.length == 1)
                {
                    args[0] = args[0].toLowerCase();
                    switch (args[0])
                    {
                        // TODO(LOGIQ): Check nothing has been missed
                        default:
                        {
                            Imperatives.sendErrorMessage(player, "Unknown colour.");
                            Imperatives.sendMessage(player, ChatColor.YELLOW + "Available colours:");
                            Imperatives.sendMessage(player, ChatColor.YELLOW + "red, green, blue, magenta/light_purple, black, white, yellow, gold, ");
                            Imperatives.sendMessage(player, ChatColor.YELLOW + "aqua, gray/grey, dark_red, dark_green, dark_blue, dark_aqua, dark_purple");
                            return false;
                        }
                        case "red":
                        {
                            playerData.colour = "§" + ChatColor.RED.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "green":
                        {
                            playerData.colour = "§" + ChatColor.GREEN.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "blue":
                        {
                            playerData.colour = "§" + ChatColor.BLUE.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "magenta": case "light_purple":
                        {
                            playerData.colour = "§" + ChatColor.LIGHT_PURPLE.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "white":
                        {
                            playerData.colour = "§" + ChatColor.WHITE.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "black":
                        {
                            playerData.colour = "§" + ChatColor.BLACK.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "yellow":
                        {
                            playerData.colour = "§" + ChatColor.YELLOW.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "gold":
                        {
                            playerData.colour = "§" + ChatColor.GOLD.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "aqua":
                        {
                            playerData.colour = "§" + ChatColor.AQUA.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "gray": case "grey":
                        {
                            playerData.colour = "§" + ChatColor.GRAY.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "dark_gray": case "dark_grey":
                        {
                            playerData.colour = "§" + ChatColor.DARK_GRAY.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "dark_red":
                        {
                            playerData.colour = "§" + ChatColor.DARK_RED.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "dark_green":
                        {
                            playerData.colour = "§" + ChatColor.DARK_GREEN.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "dark_aqua":
                        {
                            playerData.colour = "§" + ChatColor.DARK_AQUA.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "dark_purple":
                        {
                            playerData.colour = "§" + ChatColor.DARK_PURPLE.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                        case "dark_blue":
                        {
                            playerData.colour = "§" + ChatColor.DARK_BLUE.getChar();
                            if (ImperativesConfig.save())
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0]);
                            }
                            else
                            {
                                Imperatives.sendMessage(player, "Your colour has been set to " + playerData.colour + args[0] + ChatColor.GRAY + " but the config did not save. Changes may be lost upon server reload.");
                            }
                            return true;
                        }
                    }
                }
                return false;
            }
            else
            {
                Imperatives.sendErrorMessage(player, "This command is currently disabled by the admin.");
                return true;
            }
        }
        return false;
    }
}