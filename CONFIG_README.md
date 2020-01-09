#Logix Imperatives Config Readme

Due to Spigot's inability to keep comments when saving a resource file, until I find the time to write a custom file-writer, please use this reference to understand the config file and its usage.

If you feel any information here is missing or incorrect, or warrants further explanation, please open a new issue on the plugin's GitHub page: https://github.com/DoctorLogiq/Logix-Imperatives-Plugin/issues, and be sure to use the `documentation` label.

---
`EnableDebugMessages`: Enable debug messages. These are extra pieces of information useful if you think the plugin may not be working correctly.
  - Type: ` boolean`
  - Plugin's default: `true`
---
`ChatMessageFormat`: The format to use when a player writes a message in chat.
 - Type: `string`
 - Plugin's default: `"§8[%ROLE%§8] §w1%NAME%§8 says §7%MESSAGE%"`
 - Minecraft's default: `"§r<%NAME%> %MESSAGE%"`
 - Accepts formatting codes: `yes`
 - Variables:
    - `%ROLE%`: the role of the player who sent the message
    - `%NAME%` - the name of the player who sent the message
    - `%MESSAGE%` - the message the player sent
 - Wildcards:
    - `§w1` - the player's customised name colour
---
`DeathMessageFormat`: The format to use in the broadcast that is sent when a player dies.
 - Type: `string`
 - Plugin's default: `"§w1§o%NAME%§4§o %MESSAGE%"`
 - Minecraft's default: `"§r%NAME% %MESSAGE%"`
 - Accepts formatting codes: `yes`
 - Variables:
    - `%NAME%` - the name of the player who died
    - `%MESSAGE%` - the death message (has the player's name removed so that you can format the name separetely if desired)
 - Wildcards:
    - `§w1` - the player's customised name colour
---
`RespectsMessageFormat`: The format to use in the broadcast when a player uses /f to pay respects to a fallen comrade.
 - Type: `string`
 - Plugin's default: `"§w1§o%NAME1%§d§o pays their respects to §w2§o%NAME2%§d!"`
 - Accepts formatting codes: `yes`
 - Variables:
     - `%NAME1%` - the name of the player who is paying their respects
     - `%NAME2%` - the name of the player who persished (is having respects paid to)
 - Wildcards:
    - `§w1` - the customised name colour of the player who is paying their respects
    - `§w2` - the customised name colour of the player who perished (is having respects paid to)
---
`WelcomeMessagesNewPlayers`: A list of possible messages to be used in the broadcast when a new player logs in.
 - Type: `string list`
 - Plugin's default: 

          - "§r§o§w1%NAME%§a is new here, be sure to give them a warm welcome!"
 - Accepts formatting codes: `yes`
 - Variables:
      - `%NAME%` - the name of the player who joined
---
`WelcomeMessagesReturningPlayers`: A list of possible messages to be used in the broadcast when a returning player logs in.
 - Type: `string list`
 - Plugin's default: 

          - "§a§oOh noes, §w1§o%NAME%§a§o is here!"
          - "§w1§o%NAME%§a§o is here to wreak havoc!"
          - "§w1§o%NAME%§a§o chose the §cred §apill!"
          - "§a§oQuick! Everybody hide, §w1§o%NAME%§a§o has appeared!"
 - Accepts formatting codes: `yes`
 - Variables:
      - `%NAME%` - the name of the player who joined
---
`PersonalWelcomeMessagesNewPlayers`: A list of possible messages to send to new players when they log in.
 - Type: `string list`
 - Plugin's default: 

          - "§2§oPlease follow the rules, and most importantly, have fun!"
 - Accepts formatting codes: `yes`
---
`PersonalWelcomeMessagesReturningPlayers`: A list of possible messages to send to returning players when they log in.
 - Type: `string list`
 - Plugin's default: 

          - "§2§oWelcome back, so much has happened while you were gone!"
          - "§2§oYou should have taken the §9blue §2pill!"
          - "§2§oThis server is powered by hamsters, please be sure to feed them!"
          - "§2§oWell well well... what do we have here?"
 - Accepts formatting codes: `yes`
---
`Roles`: A list of roles (essentially tags that go next to the name in chat, if used in ChatMessageFormat).
 - Type: `string list`
 - Accepts formatting codes: `yes`
 - Plugin's default:
 
          - "§cOwner"
          - "§aAdmin"
          - "§eMod"
          - "§7User"
          - "§4Dev"
---
`DefaultRole`: The default role to give non-OP players when they first join
 - Type: `string`
 - Plugin's default: `"§7User"`
---
`DefaultOperatorRole`: The default role to give OP players when they first join
 - Type: `string`
 - Plugin's default: `"§aAdmin"`
---
`DefaultNameColour`: The default colour to give player's names in chat, until they customise it (if you allow this).
 - Type: `string`
 - Plugin's default: `"§9"`
---
`AllowNameColourCustomisation`: Whether or not to allow players to use the /colour command.
 - Type: `boolean`
 - Plugin's default: `true`
 
---
Formatting codes:

 -  BLACK: `§0`
 -  DARK_BLUE: `§1`
 -  DARK_GREEN: `§2`
 -  DARK_AQUA: `§3`
 -  DARK_RED: `§4`
 -  DARK_PURPLE: `§5`
 -  GOLD: `§6`
 -  GRAY: `§7`
 -  DARK_GRAY: `§8`
 -  BLUE: `§9`
 -  GREEN: `§a`
 -  AQUA: `§b`
 -  RED: `§c`
 -  LIGHT_PURPLE: `§d`
 -  YELLOW: `§e`
 -  WHITE: `§f`
 -  MAGIC: `§k`
 -  BOLD: `§l`
 -  STRIKETHROUGH: `§m`
 -  UNDERLINE: `§n`
 -  ITALIC: `§o`
 -  RESET: `§r`