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
        String filePath = getDataFolder() + File.separator + "config.yml";
        if (!new File(filePath).exists())
            saveResource("config.yml", true);
        configuration = new Configuration(filePath, this);
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getServer().getPluginCommand("try").setExecutor(new TryCommand());
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
