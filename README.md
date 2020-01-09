# Logix Imperatives
This is a Spigot 1.15.1 plugin which adds a bunch of useful features designed to not affect gameplay in any way, but to improve the overall user experience.

## Features:
 - Custom join messages for new and returning players, with extra messages to send directly to the player as they join
 - Custom death message formatting
 - Custom chat formatting
 - Chat roles (tags)
 - Ability to pay respects to fallen allies with the `/f` command

## Commands:
 - `/f`: pay respects to a recently deceased player or named entity
 - `/colour [Colour Name]`: set your name's colour in the chat (if enabled by the admin in the config). Possible values:
    - red
    - green
    - blue
    - magenta/light_purple
    - black
    - white
    - yellow
    - gold
    - aqua
    - gray/grey
    - dark_red
    - dark_green
    - dark_blue
    - dark_aqua
    - dark_purple
 - `/setRole [Player] [Role]`: OP only; sets the role for a given player from the selection available in the config (customizable)
 - `/refresh`: OP only; saves and then loads the config. This may override any changes made to the config since the last time the plugin was enabled. (an argument to not save may come in a later version) 

## Server owners
Please update as soon as possible to the new version (1.0.0). Remove your old config file and plugin from your server before adding the new one.

## Compatability
### EssentialsXChat
EssentialsXChat will intefere negatively with this plugin. In */plugins/Essentials/config.yml*, set the following values if you intend to continue using EssentialsXChat and wish this plugin to work:
 - `ops-name-color: 'none'`
 - `change-displayname: false`