package com.example.hp.jsonparser;

/**
 * Created by HP on 08-Sep-18.
 */

public class Users {
    int id;
    String name,email,gender;

    public Users(int id, String name, String email, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}
