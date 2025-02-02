package com.xu.yan.employee_management.model;
//import javax.persistence.Entity;
//import javax.persistence.Id;

//如果不用mybatis,而使用JPA的话，就要用@Entity
public class Dept {
    //如果使用JPA的话， @Id
    private int id;
    private String name;

    public Dept() {
    }

    public Dept(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dept(int id) {
        this.id = id;
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
}
