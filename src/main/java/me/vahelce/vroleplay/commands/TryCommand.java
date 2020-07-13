package me.vahelce.vroleplay.commands;

import me.vahelce.vroleplay.Configuration;
import me.vahelce.vroleplay.VRoleplay;
import me.vahelce.vroleplay.utils.LocationUtils;
import me.vahelce.vroleplay.utils.RandomHelper;
import me.vahelce.vroleplay.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TryCommand implements CommandExecutor {
    private final Configuration configuration = VRoleplay.getConfiguration();
    private final String rawMessage = configuration.getString("commands.message.try");
    private final String success = configuration.getString("commands.message.try.success");
    private final String fail = configuration.getString("commands.message.try.fail");
    private final String errorMessage = configuration.getString("commands.general.error");
    private final int range = configuration.getInt("range");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("vroleplay.try")) {
                if (args.length > 0) {
                    String result = RandomHelper.getRandom(1) == 0 ? success : fail;
                    String message = rawMessage
                            .replaceFirst("%player%", player.getName())
                            .replaceFirst("%action%", StringUtils.concatMessage(args))
                            .replaceFirst("%result%", result);
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
