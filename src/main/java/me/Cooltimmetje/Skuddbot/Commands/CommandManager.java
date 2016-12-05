package me.Cooltimmetje.Skuddbot.Commands;

import me.Cooltimmetje.Skuddbot.Commands.Admin.*;
import me.Cooltimmetje.Skuddbot.Commands.Admin.SuperAdmin.AdminManager;
import me.Cooltimmetje.Skuddbot.Commands.Admin.SuperAdmin.AwesomeManager;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import java.io.IOException;

/**
 * This class handles everything commands, and triggers the right bit of code to process the command!
 *
 * @author Tim (Cooltimmetje)
 * @version v0.3-ALPHA-DEV
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
                case "!settings":
                    Settings.run(event.getMessage());
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
            }
        }
    }

}
