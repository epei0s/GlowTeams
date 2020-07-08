package de.epeios.GlowTeams;

import org.bukkit.ChatColor;

public enum GlowColor {

    BLUE("BLUE"),
    DARKRED("DARK_RED"),
    RED("RED");

    private final String myChatColor;

    GlowColor(String myChatColor) {
        this.myChatColor = myChatColor;
    }

    public ChatColor getMyChatColor() {
        return ChatColor.valueOf(myChatColor);
    }

    public static int getGlowColorLength() {
        return GlowColor.values().length;
    }

    public static boolean contains(String toTestFor) {
        for (int i = 0; i < getGlowColorLength(); i++) {
            if (GlowColor.values()[i].toString().equalsIgnoreCase(toTestFor)) {
                return true;
            }
        }
        return false;
    }
}
