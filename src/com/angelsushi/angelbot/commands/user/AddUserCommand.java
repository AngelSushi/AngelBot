package com.angelsushi.angelbot.commands.user;

import com.angelsushi.angelbot.commands.ICommand;
import com.angelsushi.angelbot.utils.Constants;
import com.angelsushi.angelbot.utils.MYSQLConnect;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddUserCommand implements ICommand {
    @Override
    public void execute(String[] args, MessageReceivedEvent messageReceivedEvent) throws IOException {
        if(args.length == 3) {
            try {
                PreparedStatement statement = MYSQLConnect.getConn().prepareStatement("INSERT INTO users VALUES (?,?,?)");
                statement.setString(1,args[0]);
                statement.setString(2,args[1]);
                statement.setString(3,args[2]);
                statement.execute();
                Statement state = MYSQLConnect.getConn().createStatement();
                state.executeQuery("SELECT * FROM users WHERE name=" + args[0]);
                ResultSet result = state.getResultSet();

                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("Nouveau utilisateur");
                embedBuilder.setColor(Constants.BOT_COLOR);
                embedBuilder.addField("id",result.getString("id"),false);
                embedBuilder.addField("name",result.getString("name"),false);
                embedBuilder.addField("discord_id",result.getString("discord_id"),false);
                embedBuilder.addField("discord_name",result.getString("discord_name"),false);

                messageReceivedEvent.getChannel().sendMessage(embedBuilder.build()).queue();
            }
            catch(Exception e) { e.printStackTrace(); }

        }
        else {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Erreur");
            embedBuilder.setColor(Color.RED);
            embedBuilder.addField("", "Merci de bien vouloir renseigner les trois éléments suivants: pseudo, id, @",false);

            messageReceivedEvent.getChannel().sendMessage(embedBuilder.build()).queue();
        }
    }

    @Override
    public Constants.CommandCog commandCog() {
        return null;
    }

    @Override
    public String commandUsage() {
        return "$add user <pseudo> <id> <@>";
    }

    @Override
    public String commandDescription() {
        return "Permet de créer un utilisateur dans la base de donnée";
    }

    @Override
    public int permissionRequired() {
        return 2;
    }
}
