import db.MySQLConnector;
import objects.Curator;
import objects.Group;
import objects.Student;
import tables.CuratorTable;
import tables.GroupTable;
import tables.StudentsTable;

import java.util.ArrayList;


public class Main {


    public static void main(String[] args) {
        try {

            StudentsTable studentsTable = new StudentsTable();
            GroupTable groupTable = new GroupTable();
            CuratorTable curatorTable = new CuratorTable();

            ArrayList<Student> students = studentsTable.selectAll();
            if (students.isEmpty()) {
            studentsTable.insert(new Student( "Шариков Полиграф Полиграфович", "муж", 1));
            studentsTable.insert(new Student("Преображенский Филипп Филиппович", "муж", 2));
            studentsTable.insert(new Student("Борменталь Иван Арнольдович", "муж", 3));
            studentsTable.insert(new Student("Иванов Иван Иванович", "муж", 1));
            studentsTable.insert(new Student("Петров Петр Петрович", "муж", 2));
            studentsTable.insert(new Student("Сидоров Петр Иванович", "муж", 3));
            studentsTable.insert(new Student("Петрова Мария Петровна", "жен", 1));
            studentsTable.insert(new Student("Иванова Алла Ивановна", "жен", 2));
            studentsTable.insert(new Student("Федоров Федор Петрович", "муж", 3));
            studentsTable.insert(new Student("Поликарпов Михаил Петрович", "муж", 1));
            studentsTable.insert(new Student("Антонов Антон Антонович", "муж", 2));
            studentsTable.insert(new Student("Жданов Роман Петрович", "муж", 3));
            studentsTable.insert(new Student("Викторов Петр Викторович", "муж", 2));
            studentsTable.insert(new Student("Ежова Анастасия Петровна", "жен", 2));
            studentsTable.insert(new Student("Титов Виктор Викторович", "муж", 3));
        }


            ArrayList<Group> groups = groupTable.selectAll();
             if (groups.isEmpty()) {
            groupTable.insert(new Group("Философия", 1));
            groupTable.insert(new Group("Физика", 2));
            groupTable.insert(new Group("Химия", 3));
           groups = groupTable.selectAll();
        }



        ArrayList<Curator> curators = curatorTable.selectAll();
        if (curators.isEmpty()) {
            curatorTable.insert(new Curator("Павлов Виктор Петрович"));
            curatorTable.insert(new Curator("Сидоров Александр Васильевич"));
            curatorTable.insert(new Curator("Лызлов Михаил Петрович"));
            curatorTable.insert(new Curator("Шмаков Дмитрий Сергеевич"));
        }


            studentsTable.selectAllStudentGroupCurator();
            System.out.println("-------------------------------------------------");

            studentsTable.selectCount();
            System.out.println("-------------------------------------------------");

            studentsTable.selectAllWomen();
            System.out.println("-------------------------------------------------");

            groupTable.selectAllGroupCurator();
            System.out.println("-------------------------------------------------");

            studentsTable.selectAllStudentsFromGroup();
            System.out.println("-------------------------------------------------");

            groups.get(2).setId_curator(4);
            groupTable.update(groups.get(2));


          } finally {
              MySQLConnector.close();
          }
    }
}
