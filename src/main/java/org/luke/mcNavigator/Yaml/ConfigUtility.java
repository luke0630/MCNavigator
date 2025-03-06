package org.luke.mcNavigator.Yaml;

import com.google.common.base.Charsets;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.luke.mcNavigator.Data.ConfigData;
import org.luke.mcNavigator.MCNavigator;

import java.io.InputStream;
import java.io.InputStreamReader;

@UtilityClass
public class ConfigUtility {

    private final String NAME_SQL_CONFIG = "sql-config.yml";

    public ConfigData loadConfig() {
        return loadConfigData();
    }

    private ConfigData loadConfigData() {
        FileConfiguration config = MCNavigator.getInstance().getConfig();

        return ConfigData.builder()
                .sqlData(loadSQLData())
                .build();
    }

    private ConfigData.SQLData loadSQLData() {
        MCNavigator.getInstance().saveResource(NAME_SQL_CONFIG, false);
        InputStream defConfigStream = MCNavigator.getInstance().getResource(NAME_SQL_CONFIG);

        if (defConfigStream != null) {
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8));

            return ConfigData.SQLData.builder()
                    .url(yamlConfiguration.getString("url"))
                    .username(yamlConfiguration.getString("username"))
                    .password(yamlConfiguration.getString("password"))
                    .databaseName(yamlConfiguration.getString("database-name"))
                    .build();
        }
        return null;
    }
}
