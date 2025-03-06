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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
