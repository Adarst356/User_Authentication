package com.devdroid.task6.Database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.devdroid.task6.Model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);
    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    User getUserByEmail(String email);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    User login(String email, String password);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();
}
