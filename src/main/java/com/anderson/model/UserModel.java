package com.anderson.model;

public class UserModel {
    private Long id;
    private String name;
    private int age;
    private boolean status;

    public UserModel(String name, int age, boolean status) {
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public UserModel(Long id, String name, int age, boolean status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
