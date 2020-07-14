package me.vahelce.vroleplay;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

public class Configuration {
    private final HashMap<String, Object> config = new HashMap<>();
    private final String path;

    public Configuration(String path, JavaPlugin plugin) {
        this.path = path;
        final YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try (Reader fileReader = new FileReader(path)) {
            yamlConfiguration.load(fileReader);
            final Set<String> keys = yamlConfiguration.getKeys(true);
            if (keys.size() > 0) {
                for (final String key : keys) {
                    Object common = yamlConfiguration.get(key);
                    if (common != null) {
                        if (common instanceof String)
                            common = ChatColor.translateAlternateColorCodes('&', String.valueOf(common));
                        config.put(key, common);
                    }
                }
            }
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Configuration failed to load.");
            e.printStackTrace();
        }
    }

    public String getPath() {
        return this.path;
    }

    public String getString(String key) {
        return String.valueOf(config.get(key));
    }

    public int getInt(String key) {
        return (Integer) config.get(key);
    }

    public boolean getBoolean(String key) {
        return (Boolean) config.get(key);
    }

    public Object getRawValue(String key) {
        return config.get(key);
    }
}
