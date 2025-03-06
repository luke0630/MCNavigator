package org.luke.mcNavigator;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.luke.mcNavigator.Data.ConfigData;
import org.luke.mcNavigator.TutorialNavigator.Listener.CatchEvent;
import org.luke.mcNavigator.SQL.SQLManager;
import org.luke.mcNavigator.Yaml.ConfigUtility;

import java.util.List;

public final class MCNavigator extends JavaPlugin {
    @Getter
    private static MCNavigator instance;

    @Getter
    private static ConfigData configData;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Register events
        final List<Listener> listeners = List.of(
                new CatchEvent()
        );
        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        // Load Data
        configData = ConfigUtility.loadConfig();

        SQLManager.CreateDatabase();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
