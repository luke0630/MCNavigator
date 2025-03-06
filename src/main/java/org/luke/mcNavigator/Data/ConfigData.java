package org.luke.mcNavigator.Data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConfigData {
    SQLData sqlData;
    @Getter
    @Builder
    public static class SQLData {
        String url;
        String username;
        String password;
        String databaseName;
    }
}
