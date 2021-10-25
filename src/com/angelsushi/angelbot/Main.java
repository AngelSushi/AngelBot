package com.angelsushi.angelbot;

import com.angelsushi.angelbot.commands.DevisCommand;
import com.angelsushi.angelbot.commands.user.AddUserCommand;
import com.angelsushi.angelbot.utils.MYSQLConnect;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import com.angelsushi.angelbot.commands.CommandDataParser;
import com.angelsushi.angelbot.commands.ICommand;
import com.angelsushi.angelbot.commands.help.Help;
import com.angelsushi.angelbot.listeners.OnMessageListener;
import com.angelsushi.angelbot.listeners.OnReadyListener;
import com.angelsushi.angelbot.utils.Constants;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Krypton the 1/21/2021 at 13:45
 *
 * @author Krypton
 * @copyright Spacelab Studio 2015-2021, under Apache 2.0 Licence
 */
public class Main {

    private static JDA jda;

    public static HashMap<String, ICommand> commands = new HashMap<>();
    public static CommandDataParser commandDataParser = new CommandDataParser();

    public static void main(String[] args) {

        try {
            jda = JDABuilder.createDefault(Constants.TOKEN).build();
        } catch (LoginException loginException) {
            loginException.printStackTrace();
        }
        setupCommands();
        setupListeners();

        MYSQLConnect.connectToDB();
    }

    private static void setupCommands() {
        commands.put("help", new Help());
        commands.put("devis",new DevisCommand(true));
        commands.put("facture",new DevisCommand(false));
        commands.put("adduser",new AddUserCommand());

    }

    private static void setupListeners() {
        jda.addEventListener(new OnReadyListener());
        jda.addEventListener(new OnMessageListener());
    }

    public static void executeCommand(CommandDataParser.CommandData commandData) throws IOException {
        if (commands.containsKey(commandData.name)) {
            commands.get(commandData.name.toLowerCase()).execute(commandData.args, commandData.messageReceivedEvent);
        }
    }

}
