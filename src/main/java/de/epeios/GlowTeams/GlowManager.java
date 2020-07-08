package de.epeios.GlowTeams;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class GlowManager {
    //-----------------finals-----------------
    private final PotionEffect myGlow = new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, false, false);
    private final String chatIdentifier = "§8[§1GlowPlugin§8] ";
    private final FileConfiguration config = Main.getPlugin().getConfig();
    private final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
    private final HashMap<GlowColor, Team> teamLink = new HashMap<GlowColor, Team>();

    public GlowManager() {
    }

    public void orderPlayer(Player target) {    //put Player target into the right Team
        String targetGlowColor = config.getString("GlowRole." + target.getName());
        try {
            Team targetTeam = teamLink.get(GlowColor.valueOf(targetGlowColor));
            targetTeam.addEntry(target.getName());
            target.setScoreboard(board);
        } catch (NullPointerException e) {
            //e.printStackTrace();
            throw new RuntimeException("TeamIsNull");
        }
    }

    public void setupTeams() {     //sets all Teams up, and Link them in teamLink
        int i = 0;
        Team[] teams = new Team[GlowColor.getGlowColorLength()];
        try {
            for (GlowColor glowColor : GlowColor.values()) {
                teams[i] = board.registerNewTeam(glowColor.toString());
                teams[i].setColor(glowColor.getMyChatColor());
                teamLink.put(glowColor, teams[i]);
                i++;
            }
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    public void reloadGlow(Player target) {     //reloads Players glow Effect
        toggleLight(target, true);
        toggleLight(target, true);
    }

    public void toggleLight(Player target, boolean noMessage) {    //toggles the glowing effect of the player
        if (target.hasPotionEffect(PotionEffectType.GLOWING)) {
            target.removePotionEffect(PotionEffectType.GLOWING);
            if (!noMessage) {
                target.sendMessage(chatIdentifier + "§aGlowing §c[OFF]");
            }
        } else {
            target.addPotionEffect(myGlow);
            if (!noMessage) {
                target.sendMessage(chatIdentifier + "§aGlowing §a[ON]");
            }
        }
    }

    public void setTeam(Player target, String glowColor) {     //puts Player's Team in config.yml
        if (target != null) {
            if (GlowColor.contains(glowColor.toUpperCase())) {    //Assign Team in config
                config.set("GlowRole." + target.getPlayer().getName(), glowColor);
                Main.getPlugin().saveConfig();
            } else {
                throw new NoSuchElementException();
            }
        }
    }


}
