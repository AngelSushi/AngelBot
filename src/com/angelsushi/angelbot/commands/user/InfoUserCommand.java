package com.angelsushi.angelbot.commands.user;

import com.angelsushi.angelbot.commands.ICommand;
import com.angelsushi.angelbot.utils.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;

public class InfoUserCommand implements ICommand {

    @Override
    public void execute(String[] args, MessageReceivedEvent messageReceivedEvent) throws IOException {
        if(args.length == 1) {

        }
        else {
            Constants.error(messageReceivedEvent,"Merci de bien vouloir renseigner l'élement suivant: pseudo");
        }
    }

    @Override
    public Constants.CommandCog commandCog() {
        return null;
    }

    @Override
    public String commandUsage() {
        return "$infouser <pseudo>";
    }

    @Override
    public String commandDescription() {
        return "";
    }

    @Override
    public int permissionRequired() {
        return 2;
    }
}
