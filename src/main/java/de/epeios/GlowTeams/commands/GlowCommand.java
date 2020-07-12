package de.epeios.GlowTeams.commands;

import de.epeios.GlowTeams.GlowManager;
import de.epeios.GlowTeams.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.NoSuchElementException;

public class GlowCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GlowManager glowManager = new GlowManager();
        Player target;
        final String chatIdentifier = "§8[§1GlowPlugin§8] ";


        if (args.length == 0) {
            if (sender instanceof Player) {
                glowManager.setupTeams();
                target = (Player) sender;
                try {
                    glowManager.orderPlayer(target);
                } catch (RuntimeException e) {
                    target.sendMessage(chatIdentifier + "§cPlayer has no Role assigned!");
                    return false;
                }
                glowManager.toggleLight(target, true);
            } else {
                sender.sendMessage(chatIdentifier + "§cYou are not a Player, please use §6/glow <Player>");
            }
        }
        if (args.length == 1) {
            target = Main.getPlugin().getServer().getPlayer(args[0]);
            if (target.isOnline()) {
                glowManager.orderPlayer(target);
                glowManager.toggleLight(target, false);
                sender.sendMessage(chatIdentifier + "§6" + target.getName() + "§a is glowing now!");

            } else {
                sender.sendMessage(chatIdentifier + "§cTarget §6" + target.getName() + "§c is not online!");
            }
        }


        if (args.length == 2) {//assign Team to self
            if (args[0].equalsIgnoreCase("set")) {//SET COMMAND

                target = (Player) sender;
                String targetGlowRole = args[1];
                try {
                    glowManager.setTeam(target, targetGlowRole.toUpperCase());
                } catch (NoSuchElementException e) {
                    sender.sendMessage(chatIdentifier + "§cDiese Rolle gibt es nicht!");
                    return false;
                }
                glowManager.setupTeams();
                glowManager.orderPlayer(target);
                glowManager.reloadGlow(target);
                target.sendMessage(chatIdentifier + "§aYour Role got set to §6" + targetGlowRole.toUpperCase());


            }


        }

        if (args.length == 3) {
            if (args[0].equals("set")) {
                if (Bukkit.getPlayer(args[1]).isOnline()) {
                    target = Bukkit.getPlayer(args[1]);
                    String targetGlowRole = args[2];
                    try {
                        glowManager.setTeam(target, targetGlowRole.toUpperCase());
                    } catch (NoSuchElementException e) {
                        sender.sendMessage(chatIdentifier + "§cDiese Rolle gibt es nicht!");
                        return false;
                    }
                    glowManager.setupTeams();
                    glowManager.reloadGlow(target);
                    glowManager.orderPlayer(target);
                    sender.sendMessage(chatIdentifier + "§aChanged §6" + target.getName() + "§a's Role to §6" + targetGlowRole);
                }
            }
        }


        return false;
    }


}