package com.angelsushi.angelbot.utils;

import java.awt.*;

/**
 * Created by Krypton the 1/21/2021 at 14:31
 *
 * @author Krypton
 * @copyright Spacelab Studio 2015-2021, under Apache 2.0 Licence
 */
public class Constants {

    public static String PREFIX = "$";
    public static String TOKEN = "OTAyMTc3NzIzNTUwNjc1MDI1.YXaoeg.O32nbpBPimrmcJL1RFnC3qH6Yqo";
    public static Color BOT_COLOR = new Color(50, 89, 151);

    public enum CommandCog {
        GENERAL("General"),
        HELP("Help"),
        MODERATION("Moderation"),
        OWNER("Owner");

        private final String commandCog;

        CommandCog(String commandCog) {
            this.commandCog = commandCog;
        }

        @Override
        public String toString() {
            return commandCog;
        }

    };

}
