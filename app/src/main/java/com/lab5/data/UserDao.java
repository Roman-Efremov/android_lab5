package com.lab5.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE name LIKE :name || '%' OR surname LIKE :name || '%'")
    LiveData<List<User>> findByNameOrSurname(String name);

    @Insert
    void insert(User user);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
