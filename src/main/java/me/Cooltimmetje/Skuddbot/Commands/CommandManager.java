package me.Cooltimmetje.Skuddbot.Commands;

import me.Cooltimmetje.Skuddbot.Commands.Admin.*;
import me.Cooltimmetje.Skuddbot.Commands.Admin.SuperAdmin.AdminManager;
import me.Cooltimmetje.Skuddbot.Commands.Admin.SuperAdmin.AwesomeManager;
import me.Cooltimmetje.Skuddbot.Commands.Admin.SuperAdmin.LoadAuth;
import me.Cooltimmetje.Skuddbot.Commands.Admin.SuperAdmin.SayCommand;
import me.Cooltimmetje.Skuddbot.Enums.EmojiEnum;
import me.Cooltimmetje.Skuddbot.Profiles.Server;
import me.Cooltimmetje.Skuddbot.Profiles.ServerManager;
import me.Cooltimmetje.Skuddbot.Utilities.MessagesUtils;
import org.json.JSONObject;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.io.IOException;

/**
 * This class handles everything commands, and triggers the right bit of code to process the command!
 *
 * @author Tim (Cooltimmetje)
 * @version v0.5-ALPHA-DEV
 * @since v0.1-ALPHA
 */
public class CommandManager {

    /**
     * EVENT: This event gets triggered when a message gets posted, it will check for a command and then run the code to process that command.
     *
     * @param event The event that the message triggered.
     */
    @EventSubscriber
    public void onMessage(MessageReceivedEvent event){
        if(!event.getMessage().getChannel().isPrivate()) {
            switch (event.getMessage().getContent().split(" ")[0].toLowerCase()) {
                case "!game":
                    GameCommand.run(event.getMessage());
                    break;
                case "!xp":
                    XpCommand.run(event.getMessage());
                    break;
                case "!saveprofile":
                    SaveProfile.run(event.getMessage());
                    break;
                case "!twitch":
                    TwitchLinkCommand.run(event.getMessage());
                    break;
                case "!xplb":
                    Leaderboard.run(event.getMessage());
                    break;
                case "!initialize":
                    InitializeCommand.run(event.getMessage());
                    break;
                case "!dumpdata":
                    DumpData.run(event.getMessage());
                    break;
                case "!serversettings":
                    ServerSettingsCommand.run(event.getMessage());
                    break;
                case "!reloadglobal":
                    ReloadGlobal.run(event.getMessage());
                    break;
                case "!about":
                    AboutCommand.run(event.getMessage());
                    break;
                case "!loadauth":
                    LoadAuth.run(event.getMessage());
                    break;
                case "!flip":
                    FlipTextCommand.run(event.getMessage());
                    break;
                case "!import":
                    try {
                        ImportCommand.run(event.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "!ping":
                    PingCommand.run(event.getMessage());
                    break;
                case "!addawesome":
                    AwesomeManager.add(event.getMessage());
                    break;
                case "!removeawesome":
                    AwesomeManager.remove(event.getMessage());
                    break;
                case "!addadmin":
                    AdminManager.add(event.getMessage());
                    break;
                case "!removeadmin":
                    AdminManager.remove(event.getMessage());
                    break;
                case "!userinfo":
                    UserInfo.run(event.getMessage());
                    break;
                case "!riot":
                    MessagesUtils.sendPlain("(╯°□°）╯︵ ┻━┻", event.getMessage().getChannel(), false);
                    break;
                case "!reverse":
                    ReverseCommand.run(event.getMessage());
                    break;
                case "!usersettings":
                    UserSettingsCommand.run(event.getMessage());
                    break;
                case "o7":
                    SaluteCommand.run(event.getMessage());
                    break;
                case "!command":
                    discordEdit(event.getMessage());
                break;
            }
        } else {
            switch (event.getMessage().getContent().split(" ")[0].toLowerCase()) {
                case "!confirm":
                    TwitchLinkCommand.confirm(event.getMessage());
                    break;
                case "!cancel":
                    TwitchLinkCommand.cancel(event.getMessage());
                    break;
                case "!setchannel":
                    SayCommand.setChannel(event.getMessage());
                    break;
                case "!say":
                    SayCommand.sayMessage(event.getMessage());
                    break;
                case "!reloadawesome":
                    AwesomeManager.reload(event.getMessage());
                    break;
                case "!flip":
                    FlipTextCommand.run(event.getMessage());
                    break;
                case "!setping":
                    SetPing.run(event.getMessage());
                    break;
                case "!ping":
                    PingCommand.run(event.getMessage());
                    break;
                case "!addmsg":
                    AddMessageCommand.run(event.getMessage());
                    break;
                case "!reverse":
                    ReverseCommand.run(event.getMessage());
                    break;
            }
        }
    }

    public static void discordEdit(IMessage message){
        String result = commandEditor(message.getContent(), ServerManager.getServer(message.getStringID()));
        if(result.startsWith("ERR ")){
            MessagesUtils.addReaction(message, result.substring(4).replace("$name", message.getAuthor().mention()), EmojiEnum.X);
        } else if(result.startsWith("SUC ")) {
            MessagesUtils.addReaction(message, result.substring(4).replace("$name", message.getAuthor().mention()), EmojiEnum.WHITE_CHECK_MARK);
        }
    }

    /**
     * This is used for adding/editing/removing custom commands.
     *
     * @param input The input from the command.
     * @return The result.
     */
    public static String commandEditor(String input, Server server){ //!command add !memes Memes are dank.
        String[] args = input.split(" ");
        if(args.length > 3){
            switch (args[1].toLowerCase()){
                case "add":
                    if(server.getCommands().containsKey(args[2].toLowerCase())){
                        return "ERR This command already exists.";
                    }

                    StringBuilder sbResponse = new StringBuilder();
                    for(int i = 3; i < args.length; i++){
                        sbResponse.append(args[i]).append(" ");
                    }
                    String response = sbResponse.toString().trim();

                    JSONObject object = new JSONObject();
                    object.put("response", response);

                    server.getCommands().put(args[2].toLowerCase(), object.toString());

                    return "SUC $name, the command " + args[2].toLowerCase() + " has been added. Response: " + response;
                case "remove":

                    break;
                case "edit":

                    break;
                default:
                    return "ERR Invalid operation: Use add, remove or edit.";
            }
        }

        return "ERR Not enough arguments: !command <add/remove/edit> <command> <response/settings>";
    }

}
