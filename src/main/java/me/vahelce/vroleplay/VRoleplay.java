package me.vahelce.vroleplay;

import me.vahelce.vroleplay.commands.DoCommand;
import me.vahelce.vroleplay.commands.MeCommand;
import me.vahelce.vroleplay.commands.OocChatCommand;
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
    }

    private void registerCommands() {
        getServer().getPluginCommand("try").setExecutor(new TryCommand());
        getServer().getPluginCommand("me").setExecutor(new MeCommand());
        getServer().getPluginCommand("do").setExecutor(new DoCommand());
        getServer().getPluginCommand("b").setExecutor(new OocChatCommand());
    }

    public static VRoleplay getInstance() {
        return instance;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
