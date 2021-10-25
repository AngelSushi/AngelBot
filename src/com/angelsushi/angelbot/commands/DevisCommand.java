package com.angelsushi.angelbot.commands;

import com.angelsushi.angelbot.utils.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;

public class DevisCommand implements ICommand{

    private boolean isDevis;

    public DevisCommand(boolean isDevis) {
        this.isDevis = isDevis;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent messageReceivedEvent) throws IOException {

        if(args.length == 3) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(isDevis ? "Devis" : "Facture");
            embedBuilder.setColor(Constants.BOT_COLOR);
            embedBuilder.addField("Lien" + (isDevis? " du devis" : " de la facture"), args[0],false);
            embedBuilder.addField("Prix" + (isDevis ?  "(hors frais)" : ""), args[1],false);
            embedBuilder.addField("Date limite", args[2],false);
            embedBuilder.addField("Signature", isDevis ? "Requise" : "Non Requise",false);

            messageReceivedEvent.getChannel().sendMessage(embedBuilder.build()).queue();
        }
        else {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Erreur");
            embedBuilder.setColor(Color.RED);
            embedBuilder.addField("", "Merci de bien vouloir renseigner les trois éléments suivants: lien du devis,prix, date de fin",false);

            messageReceivedEvent.getChannel().sendMessage(embedBuilder.build()).queue();
        }
    }

    @Override
    public Constants.CommandCog commandCog() {
        return null;
    }

    @Override
    public String commandUsage() {
        return "$devis";
    }

    @Override
    public String commandDescription() {
        return "Permet de générer un message de devis";
    }

    @Override
    public int permissionRequired() {
        return 0;
    }
}
