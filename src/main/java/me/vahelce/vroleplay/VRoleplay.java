package me.vahelce.vroleplay;

import me.vahelce.vroleplay.commands.TryCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class VRoleplay extends JavaPlugin {
    private static VRoleplay instance;
    private static Configuration configuration;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        configuration = new Configuration(getDataFolder() + File.separator + "config.yml", this);
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {

    }

    private void registerListeners() {

    }

    public static VRoleplay getInstance() {
        return instance;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
