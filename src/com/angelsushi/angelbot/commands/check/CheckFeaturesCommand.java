package com.angelsushi.angelbot.commands.check;

import com.angelsushi.angelbot.commands.ICommand;
import com.angelsushi.angelbot.utils.Constants;
import com.angelsushi.angelbot.utils.MYSQLConnect;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckFeaturesCommand implements ICommand {

    @Override
    public void execute(String[] args, MessageReceivedEvent messageReceivedEvent) throws IOException {
        if(args.length == 1 || args.length == 2) {
            PreparedStatement statement;

            try {
                if (args.length == 1) {
                    statement = MYSQLConnect.getConn().prepareStatement("SELECT * FROM users_features WHERE command_id=?");
                    statement.setInt(1,Integer.parseInt(args[0]));
                    statement.executeQuery();
                }
                else {
                    statement = MYSQLConnect.getConn().prepareStatement("SELECT * FROM users_features WHERE command_id=? AND feature_name=?");
                    statement.setInt(1,Integer.parseInt(args[0]));
                    statement.setString(2,args[1]);
                    statement.executeQuery();
                }

                ResultSet result = statement.getResultSet();

                if(result.next()) {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setTitle("Fonctionnalités de la commande n°" + result.getInt("command_id"));
                    builder.setColor(Constants.BOT_COLOR);

                    while (result.next()) {
                        builder.addField("Nom",result.getString("feature_name"),false);
                        int state = result.getInt("state");
                        builder.addField("Etat", state == 0 ? ":x:" : state == 1 ? ":hourglass:" : ":white_check_mark:",false);

                        builder.addField("","",false);
                    }

                    messageReceivedEvent.getTextChannel().sendMessage(builder.build()).queue();
                }
                else
                    Constants.error(messageReceivedEvent,"Aucune commande n'existe avec ces données là.");
            }
            catch(Exception e) { e.printStackTrace(); }
        }
        else
            Constants.error(messageReceivedEvent,"Merci de bien vouloir renseigner les deux éléments suivants: command_id,feature_name (non obligatoire)");
    }

    @Override
    public Constants.CommandCog commandCog() {
        return null;
    }

    @Override
    public String commandUsage() {
        return "$check <command_id> <feature_name>";
    }

    @Override
    public String commandDescription() {
        return null;
    }

    @Override
    public int permissionRequired() {
        return 0;
    }
}
