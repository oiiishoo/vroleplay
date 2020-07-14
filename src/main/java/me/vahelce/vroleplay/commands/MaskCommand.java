package me.vahelce.vroleplay.commands;

import me.vahelce.vroleplay.ChatListener;
import me.vahelce.vroleplay.Configuration;
import me.vahelce.vroleplay.VRoleplay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaskCommand implements CommandExecutor {
    private final Configuration configuration = VRoleplay.getConfiguration();
    private final String enabledMessage = configuration.getString("message.commands.mask.activate");
    private final String disabledMessage = configuration.getString("message.commands.mask.deactivate");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("vroleplay.mask")) {
                if (ChatListener.isPlayerMasked(player)) {
                    ChatListener.removePlayerFromMasks(player);
                    player.sendMessage(disabledMessage);
                } else {
                    ChatListener.addPlayerToMasks(player);
                    player.sendMessage(enabledMessage);
                }
                return true;
            }
            sender.sendMessage(configuration.getString("message.general.nopermissions"));
            return false;
        }
        sender.sendMessage(configuration.getString("message.general.notplayer"));
        return false;
    }
}
