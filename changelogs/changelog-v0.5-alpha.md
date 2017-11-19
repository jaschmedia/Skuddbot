# The CMDR Update (v0.5-ALPHA)

## Added

### The headlines
* **CUSTOMIZABLE COMMANDS** - yes, it's real
  * You can now add your own commands using `!command` on Discord or `s!command` on Twitch.
    * You use it like this: `(s)!command <add/remove/edit> <command> <response/settings>`
  * Added commands work on both platforms, regardless of where you added them.
  * Variables - Add some flavour to your commands.
    * `$user`
      * Twitch: Get's replaced with the name of the user that sent the command.
      * Discord: Get's replaced with a mention of the user that sent the command.
    * `$arguments`
      * Get's replaced with everything that is after the command itself.
    * `$randomuser`
      * Twitch: Get's replaced with a random user that is present in chat.
      * Discord: *WIP*

### The other things
* You can now attach images to welcome and goodbye messages!
  * You can do this using the `WELCOME_MSG_ATTACH` and `GOODBYE_MSG_ATTACH` settings under `!serversettings`.
* You can now make your XP private.
  * You can do this by setting `XP_PRIVATE` to `true` using the `!usersettings` command.

## Changed
* Dashes (`-`) are now allowed to be used in `!usersettings` and `!serversettings`.
* Discord4J updated: `2.8.3` -> `2.9.2`
* Welcome and goodbye messages are now embeds.
* Restructured the `!xp` command in the backend.
  * This comes with increased clarity when a error occurs.
* We now send `CAP REQ :twitch.tv/membership` upon connection to Twitch IRC.
* We now keep track of which users are present in Twitch Chat.

## Fixed

#### Twitch Issues
* A security issue, if the bot was modded people could ban other people, using the `!reverse` command, without the need of being a mod themselves.
  * `!reverse` now uses a whitelist of commands that may be triggered by the bot via reversing text.
* `!riot` is no longer case sensitive.