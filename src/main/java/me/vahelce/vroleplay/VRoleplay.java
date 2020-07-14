package me.vahelce.vroleplay;

import me.vahelce.vroleplay.commands.*;
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
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    private void registerCommands() {
        getServer().getPluginCommand("try").setExecutor(new TryCommand());
        getServer().getPluginCommand("me").setExecutor(new MeCommand());
        getServer().getPluginCommand("do").setExecutor(new DoCommand());
        getServer().getPluginCommand("b").setExecutor(new OocChatCommand());
        getServer().getPluginCommand("mask").setExecutor(new MaskCommand());
    }

    public static VRoleplay getInstance() {
        return instance;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
