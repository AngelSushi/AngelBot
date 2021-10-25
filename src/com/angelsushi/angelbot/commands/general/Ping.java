package com.angelsushi.angelbot.commands.general;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import com.angelsushi.angelbot.commands.ICommand;
import com.angelsushi.angelbot.utils.Constants;

import java.io.IOException;

/**
 * Created by Krypton the 1/21/2021 at 18:19
 *
 * @author Krypton
 * @copyright Spacelab Studio 2015-2021, under Apache 2.0 Licence
 */
public class Ping implements ICommand {

    @Override
    public void execute(String[] args, MessageReceivedEvent messageReceivedEvent) throws IOException {
        messageReceivedEvent.getChannel().sendMessage("I'm alive :D").queue();
    }

    @Override
    public Constants.CommandCog commandCog() {
        return Constants.CommandCog.GENERAL;
    }

    @Override
    public String commandUsage() {
        return "!ping";
    }

    @Override
    public String commandDescription() {
        return "Make sure the bot is online";
    }

    @Override
    public int permissionRequired() {
        return 3;
    }

}
