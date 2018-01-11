package ua.kh.kryvko.entity;

/**
 * RoleName - the entity contains information from ROLE table in database.
 * Explaining: users role for access for some numbers futures in web application
 */
public class Role {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
