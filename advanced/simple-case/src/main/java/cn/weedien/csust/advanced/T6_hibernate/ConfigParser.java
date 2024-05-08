package cn.weedien.csust.advanced.T6_hibernate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigParser {

    private String tableName;

    private String primaryKey;

    private List<String> columns;

    public ConfigParser(String configPath) {
        columns = new ArrayList<>();
        parseConfigFile(configPath);
    }

    private void parseConfigFile(String configFilePath) {

        String realFilePath = this.getClass().getClassLoader().getResource(configFilePath).getFile();
        if (realFilePath == null) {
            throw new IllegalArgumentException("File not found");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(realFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("cn.weedien.csust")) {
                    tableName = line.split("=")[1];
                } else if (line.startsWith("key-")) {
                    primaryKey = line.split("=")[1];
                } else if (line.startsWith("property-")) {
                    columns.add(line.split("=")[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateQuery(String column) {
        String base = "select " + primaryKey + ", " +String.join(", ", columns) + " from " + tableName + " where ";
        if (column.equals(primaryKey)) {
            return base + primaryKey + " = ?";
        }

        if (columns.contains(column)) {
            return base + column + " = ?";
        } else {
            throw new IllegalArgumentException("Column not found");
        }
    }

    public static void main(String[] args) {
        ConfigParser config = new ConfigParser("Student.properties");
        System.out.println(config);
        System.out.println(config.generateQuery("id"));
    }
}