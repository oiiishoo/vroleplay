package me.vahelce.vroleplay.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class LocationUtils {

    public static List<Player> getPlayersAt(Location location, int range) {
        return Bukkit.getOnlinePlayers()
                .parallelStream()
                .filter(p -> p.getLocation().distance(location) >= range)
                .collect(Collectors.toList());
    }
}

