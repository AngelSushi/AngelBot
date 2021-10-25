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

public class DeleteUserCommand implements ICommand {
    @Override
    public void execute(String[] args, MessageReceivedEvent messageReceivedEvent) throws IOException {
        if(args.length == 1) {
            try {
                PreparedStatement selectStatement = MYSQLConnect.getConn().prepareStatement("SELECT * FROM users WHERE name=?");
                selectStatement.setString(1,args[0]);
                selectStatement.executeQuery();
                ResultSet result = selectStatement.getResultSet();

                if(result.next()) {
                    PreparedStatement statement = MYSQLConnect.getConn().prepareStatement("DELETE FROM users WHERE name=?");
                    statement.setString(1,args[0]);
                    statement.executeQuery();


                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setTitle("Utilisateur supprimé ");
                    embedBuilder.setColor(Constants.BOT_COLOR);
                    embedBuilder.addField("id","L'utilisateur " + args[0] + " vient d'être supprimé de la base de données. ",false);

                    messageReceivedEvent.getChannel().sendMessage(embedBuilder.build()).queue();
                }
                else {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setTitle("Erreur");
                    embedBuilder.setColor(Color.RED);
                    embedBuilder.addField("", "Aucun utilisateur n'a été trouvé avec ce pseudo",false);

                    messageReceivedEvent.getChannel().sendMessage(embedBuilder.build()).queue();
                }
            }
            catch(Exception e) { e.printStackTrace(); }
        }
        else {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Erreur");
            embedBuilder.setColor(Color.RED);
            embedBuilder.addField("", "Merci de bien vouloir renseigner l'élement suivant: pseudo",false);

            messageReceivedEvent.getChannel().sendMessage(embedBuilder.build()).queue();
        }
    }

    @Override
    public Constants.CommandCog commandCog() {
        return null;
    }

    @Override
    public String commandUsage() {
        return "$removeuser <pseudo>";
    }

    @Override
    public String commandDescription() {
        return null;
    }

    @Override
    public int permissionRequired() {
        return 2;
    }
}
