package com.lab5.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "user", primaryKeys = {"name", "surname"})
public class User {
    @NonNull
    public String name;
    @NonNull
    public String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static User[] populateData() {
        return new User[] {
                new User("name1", "surname1"),
                new User("name2", "surname2"),
                new User("name3", "surname3")
        };
    }
}