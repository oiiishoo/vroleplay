package me.vahelce.vroleplay;

import me.vahelce.vroleplay.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class VRoleplay extends JavaPlugin implements CommandExecutor {
    private static VRoleplay instance;
    private static Configuration configuration;
    private static String[] helpMessages;

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
        List<String> rawHelp = (List<String>) configuration.getRawValue("message.help");
        helpMessages = new String[rawHelp.size()];
        for (int i = 0; i < rawHelp.size(); i++) {
            helpMessages[i] = ChatColor.translateAlternateColorCodes('&', rawHelp.get(i));
        }
        registerCommands();
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    private void registerCommands() {
        getServer().getPluginCommand("try").setExecutor(new TryCommand());
        getServer().getPluginCommand("me").setExecutor(new MeCommand());
        getServer().getPluginCommand("do").setExecutor(new DoCommand());
        getServer().getPluginCommand("b").setExecutor(new OocChatCommand());
        getServer().getPluginCommand("rpmask").setExecutor(new MaskCommand());
        getServer().getPluginCommand("todo").setExecutor(new TodoCommand());
        getServer().getPluginCommand("vroleplay").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§6V§eRolePlay §rv. " + getDescription().getVersion());
            sender.sendMessage("§eEnter /vroleplay §fhelp §7for help.");
        } else {
            sender.sendMessage(helpMessages);
        }
        return true;
    }

    public static VRoleplay getInstance() {
        return instance;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
