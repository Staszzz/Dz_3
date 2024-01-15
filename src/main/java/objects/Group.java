package objects;

public class Group {
    private int id;
    private String name;
    private int id_curator;


    public Group(String name, int id_curator) {
        this.name = name;
        this.id_curator = id_curator;
    }

    public Group(int id, String name, int id_curator) {
        this.id = id;
        this.name = name;
        this.id_curator = id_curator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_curator() {
        return id_curator;
    }

    public void setId_curator(int id_curator) {
        this.id_curator = id_curator;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", id_curator=" + id_curator +
                '}';
    }
}