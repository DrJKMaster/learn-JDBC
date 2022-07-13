package e_db_utils.domain;

public class Test {
    private long id;
    private String name;
    private java.sql.Timestamp birthday;
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public java.sql.Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Timestamp birthday) {
        this.birthday = birthday;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
