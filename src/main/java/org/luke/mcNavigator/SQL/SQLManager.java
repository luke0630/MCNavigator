package org.luke.mcNavigator.SQL;

import org.luke.mcNavigator.Data.ConfigData;
import org.luke.mcNavigator.MCNavigator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLManager {

    private static Connection connection = null;
    static final String tableName = "linked_data";
    static final String column_discordID = "discord_id";
    static final String column_linked_data = "link_data";

    public static void ConnectionToDatabase() {
        ConfigData.SQLData sqlData = MCNavigator.getConfigData().getSqlData();
        try {
            // MySQLドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");
            // データベースへの接続
            connection = DriverManager.getConnection(sqlData.getUrl(), sqlData.getUsername(), sqlData.getPassword());
            MCNavigator.getInstance().getLogger().info("データベースに接続しました。");
            MCNavigator.getInstance().getLogger().info("データベース名: " + sqlData.getDatabaseName() );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()) {
                ConnectionToDatabase();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void CreateDatabase() {
        final String dbName = MCNavigator.getConfigData().getSqlData().getDatabaseName();
        List<String> executes = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {
            // データベース作成を実行
            executes.add("CREATE DATABASE IF NOT EXISTS " + dbName);

            executes.add("USE " + dbName);

            String createTable = "CREATE TABLE IF NOT EXISTS " + tableName + " ( " +
                    column_discordID + " VARCHAR(60) NOT NULL," +
                    column_linked_data + " JSON NOT NULL, " +
                    " PRIMARY KEY ( " + column_discordID + " )" +
                    " );";

            executes.add(createTable);
            //<---------------------------------->
            MCNavigator.getInstance().getLogger().info(
                    String.format(
                            "テーブル%sが作成されました。",
                            tableName
                    ));

            for (String execute : executes) {
                statement.executeUpdate(execute);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
