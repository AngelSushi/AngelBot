package com.angelsushi.angelbot.commands.help;

import com.angelsushi.angelbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import com.angelsushi.angelbot.commands.ICommand;
import com.angelsushi.angelbot.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Krypton the 1/21/2021 at 17:24
 *
 * @author Krypton
 * @copyright Spacelab Studio 2015-2021, under Apache 2.0 Licence
 */
public class Help implements ICommand {

    @Override
    public void execute(String[] args, MessageReceivedEvent messageReceivedEvent) throws IOException {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Bot Help");
        for (Constants.CommandCog commandCog : Constants.CommandCog.values()) {
            ArrayList<String> commandsList = new ArrayList<>();
            for (Map.Entry<String, ICommand> stringICommandEntry : Main.commands.entrySet()) {
                if ((stringICommandEntry.getValue().commandCog() == commandCog)) {
                    commandsList.add(stringICommandEntry.getKey());
                }
            }
            embedBuilder.setColor(Constants.BOT_COLOR);
            StringBuilder commands = new StringBuilder();
            commands.append(String.join(" • ", commandsList));
            embedBuilder.addField(commandCog.toString() + " Commands", String.valueOf(commands), false);
        }
        messageReceivedEvent.getChannel().sendMessage(embedBuilder.build()).queue();
    }

    @Override
    public Constants.CommandCog commandCog() {
        return Constants.CommandCog.HELP;
    }

    @Override
    public String commandUsage() {
        return "!help";
    }

    @Override
    public String commandDescription() {
        return "Need some help?";
    }

    @Override
    public int permissionRequired() {
        return 3;
    }

}
