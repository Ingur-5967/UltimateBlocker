package ru.solomka.blocker.config;

import ru.solomka.blocker.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {

    private static final Main plugin = Main.getInstance();

    public static void createConfig() {
        if (new File(plugin.getDataFolder(), "config.yml").exists())
            return;
        plugin.getLogger().info("Creating new configuration file...");
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
        plugin.getLogger().info("Configuration file success created!");
    }

    public static void reload() {
        plugin.reloadConfig();
    }

    public static String getString(String string) {
        return plugin.getConfig().getString(string);
    }

    public static List<String> getStringList(String string) {
        return plugin.getConfig().getStringList(string);
    }

    public static boolean getBoolean(String string) {
        return plugin.getConfig().getBoolean(string);
    }
}
