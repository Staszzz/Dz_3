package tables;

import objects.Curator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CuratorTable extends AbsTable {


    public CuratorTable() {
        super("Curator");
        columns = new HashMap<>();
        columns.put("id", "INT PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio", "varchar(150)");
        create();
    }

    public ArrayList<Curator> selectAll(){
        String sqlRequest = String.format("SELECT * FROM %s", tableName);
        return resultSetToArray(sqlRequest);
    }

    private ArrayList<Curator> resultSetToArray(String sqlRequest){
        ArrayList<Curator> result = new ArrayList<>();
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        try {
            while (rs.next()) {
                result.add(
                        new Curator(
                                rs.getInt("id"),
                                rs.getString("fio")
                        ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public void insert(Curator curator){
                String sqlRequest = String.format("INSERT INTO %s (fio) VALUES ('%s');",
                tableName, curator.getFio());
        db.executeRequest(sqlRequest);
    }
}