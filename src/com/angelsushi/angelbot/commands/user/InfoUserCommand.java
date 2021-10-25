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

public class InfoUserCommand implements ICommand {

    @Override
    public void execute(String[] args, MessageReceivedEvent messageReceivedEvent) throws IOException {
        if(args.length == 1) {
            try {
                PreparedStatement preparedStatement = MYSQLConnect.getConn().prepareStatement("SELECT * FROM users WHERE name=?");
                preparedStatement.setString(1,args[0]);
                preparedStatement.executeQuery();
                ResultSet result = preparedStatement.getResultSet();

                if(result.next()) {
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setTitle("Informations utilisateur");
                    embedBuilder.setColor(Constants.BOT_COLOR);

                    embedBuilder.addField("id","" + result.getInt("id"),true);
                    embedBuilder.addField("name",result.getString("name"),true);
                    embedBuilder.addField("discord_id",result.getString("discord_id"),true);
                    embedBuilder.addField("discord_name",result.getString("discord_name"),true);

                    embedBuilder.addField("","",false);

                    PreparedStatement commandStatement = MYSQLConnect.getConn().prepareStatement("SELECT * FROM users_commands WHERE user_id=?");
                    commandStatement.setInt(1,result.getInt("id"));
                    commandStatement.executeQuery();
                    ResultSet commandResult = commandStatement.getResultSet();

                    if(commandResult.next()) {
                        StringBuilder command_id = new StringBuilder();
                        while(commandResult.next()) {
                            command_id.append(commandResult.getString("command_id"));
                        }

                        embedBuilder.addField("Commandes",command_id.toString(),true);
                    }

                    messageReceivedEvent.getChannel().sendMessage(embedBuilder.build()).queue();
                }
                else
                    Constants.error(messageReceivedEvent,"Aucun utilisateur n'a été trouvé avec ce pseudo");
            }
            catch (Exception e) { e.printStackTrace(); }
        }
        else {
            Constants.error(messageReceivedEvent,"Merci de bien vouloir renseigner l'élement suivant: pseudo"); // embed builder with red color and error title
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
