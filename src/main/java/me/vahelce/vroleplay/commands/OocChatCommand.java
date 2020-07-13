package me.vahelce.vroleplay.commands;

import me.vahelce.vroleplay.Configuration;
import me.vahelce.vroleplay.VRoleplay;
import me.vahelce.vroleplay.utils.LocationUtils;
import me.vahelce.vroleplay.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OocChatCommand implements CommandExecutor {
    private final Configuration configuration = VRoleplay.getConfiguration();
    private final String rawMessage = configuration.getString("message.commands.ooc");
    private final String errorMessage = configuration.getString("message.general.error");
    private final int range = configuration.getInt("range");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("vroleplay.ooc")) {
                if (args.length > 0) {
                    String message = rawMessage
                            .replaceFirst("%player%", player.getName())
                            .replaceFirst("%message%", StringUtils.concatMessage(args));
                    for (Player p : LocationUtils.getPlayersAt(player.getLocation(), range)) {
                        p.sendMessage(message);
                    }
                    return true;
                }
                player.sendMessage(errorMessage);
                return false;
            }
            sender.sendMessage(configuration.getString("message.general.nopermissions"));
            return false;
        }
        sender.sendMessage(configuration.getString("message.general.notplayer"));
        return false;
    }
}
