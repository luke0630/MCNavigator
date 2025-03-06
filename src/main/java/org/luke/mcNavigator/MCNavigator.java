package org.luke.mcNavigator;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCNavigator extends JavaPlugin {
    @Getter
    private static MCNavigator instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
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
