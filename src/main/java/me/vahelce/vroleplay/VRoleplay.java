package me.vahelce.vroleplay;

import org.bukkit.plugin.java.JavaPlugin;

public class VRoleplay extends JavaPlugin {
    private static VRoleplay instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
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
}
