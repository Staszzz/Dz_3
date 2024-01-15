package tables;

import objects.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupTable extends AbsTable {

    public GroupTable() {
        super("Groupa");
        columns = new HashMap<>();
        columns.put("id", "INT PRIMARY KEY AUTO_INCREMENT");
        columns.put("name", "varchar(100)");
        columns.put("id_curator", "INT");
        create();
    }
    private ArrayList<Group> selectByQuery(String sqlQuery){
        ArrayList<Group> groups = new ArrayList<>();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                groups.add(new Group(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("id_curator")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groups;
    }
    public void insert(Group group){
          final String sqlRequest = String.format("INSERT INTO %s (name, id_curator)" + "VALUES ('%s', %d);",
                tableName, group.getName(), group.getId_curator());
        db.executeRequest(sqlRequest);
    }

    public ArrayList<Group> selectAll(){
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }
    public void update(Group group){
               String sqlQuery = String.format("UPDATE Groupa SET " +
                "id_curator= 4  WHERE id = '3' ");
        db.executeRequest(sqlQuery);

    }
    public void selectAllGroupCurator()  {
             try {
        String sqlRequest = String.format("SELECT Groupa.id, Groupa.name, Groupa.id_curator, Curator.fio \n" +
                "FROM Groupa \n" +
                "JOIN Curator ON Groupa.id_curator = Curator.id");
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        while (rs.next()) {
            System.out.println("Список групп с кураторами: /" +
                    rs.getString(1) + "/" +
                    rs.getString(2) + "/" +
                    rs.getString(3) + "/" +
                    rs.getString(4));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        }
    }
}