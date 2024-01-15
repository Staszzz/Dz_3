package tables;

import db.IDBConnector;
import db.MySQLConnector;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbsTable {
    protected String tableName;
    protected Map<String, String> columns;
    protected IDBConnector db = new MySQLConnector();

    public AbsTable(String tableName) {
        this.tableName = tableName;
    }

    public void create() {
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, convertMapColumnsToString());
        db.executeRequest(sqlRequest);
    }

    private String convertMapColumnsToString() {
        final String result = columns.entrySet().stream()
                .map((Map.Entry entry) -> String.format("%s %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
        return result;
    }
}