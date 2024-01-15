package tables;

import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentsTable extends AbsTable {


    public StudentsTable() {
        super("Student");
        columns = new HashMap<>();
        columns.put("id", "INT PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio", "varchar(100)");
        columns.put("sex", "varchar(10)");
        columns.put("id_group", "INT");
        create();
    }

    public ArrayList<Student> selectAll(){
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return resultSetToArray(sqlQuery);
    }
    public void selectCount()  {
        String sqlQuery = String.format("SELECT COUNT(*) FROM %s ", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {

            while (rs.next()) {
                System.out.println("Кол-во студентов: " + rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectAllWomen()  {
        String sqlQuery = String.format("SELECT * FROM %s WHERE sex = 'жен' ", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                System.out.println("Студентки: " +
                        rs.getString(1) + "/" +
                        rs.getString(2) + "/" +
                        rs.getString(3) + "/"+
                        rs.getString(4)
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private ArrayList<Student> resultSetToArray(String sqlQuery) {
        ArrayList<Student> result = new ArrayList<>();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                result.add(
                        new Student(
                                rs.getInt("id"),
                                rs.getString("fio"),
                                rs.getString("sex"),
                                rs.getInt("id_group")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public void selectAllStudentGroupCurator()  {
        String sqlQuery = String.format("SELECT Student.id, Student.fio, Student.sex, Student.id_group, Groupa.name, Curator.fio" +
                " FROM Student JOIN Groupa ON Groupa.id = Student.id_group JOIN Curator ON Curator.id = Groupa.id_curator");
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                System.out.println("Информация о всех студентах включая название группы и имя куратора: /" +

                        rs.getString(1) + "/" +
                        rs.getString(2) + "/" +
                        rs.getString(3) + "/" +
                        rs.getString(4) + "/" +
                        rs.getString(5) + "/" +
                        rs.getString(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void selectAllStudentsFromGroup ()  {
        String sqlQuery = String.format("SELECT Student.fio, Student.id, Student.sex, Student.id_group " +
                "FROM Student INNER JOIN Groupa ON Student.id_group  = Groupa.id WHERE Groupa.name = 'Философия'");
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                System.out.println("Студенты из группы Философия: /" +
                        rs.getString(1) + "/" +
                        rs.getString(2) + "/" +
                        rs.getString(3) + "/" +
                        rs.getString(4))
                ;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void insert(Student student){
         String sqlRequest = String.format("INSERT INTO %s (fio, sex, id_group) VALUES ('%s', '%s', %d);",
                tableName, student.getFio(), student.getSex(), student.getId_group());
        db.executeRequest(sqlRequest);
    }
}