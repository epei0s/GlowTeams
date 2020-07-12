package de.epeios.GlowTeams;

import de.epeios.GlowTeams.commands.GlowCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class Main extends JavaPlugin {
    private static Main plugin;
    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("glow").setExecutor(new GlowCommand());
    }
}
