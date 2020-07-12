package de.epeios.GlowTeams;

import org.bukkit.ChatColor;

public enum GlowColor {

    AQUA("AQUA"),
    BLACK("BLACK"),
    BLUE("BLUE"),
    DARK_AQUA("DARK_AQUA"),
    DARK_BLUE("DARK_BLUE"),
    DARK_GREEN("DARK_GREEN"),
    DARK_GREY("DARK_GREY"),
    DARK_PURPLE("DARK_PURPLE"),
    DARK_RED("DARK_RED"),
    GOLD("GOLD"),
    GRAY("GRAY"),
    GREEN("GREEN"),
    LIGHT_PURPLE("LIGHT_PURPLE"),
    RED("RED"),
    WHITE("WHITE"),
    YELLOW("YELLOW");

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
