package com.exa.examen1.db.dao;

import com.exa.examen1.db.entity.LogUser;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LogUserDAO {
    @Insert
    void insertLogUser(LogUser loguser);

    @Query("SELECT * FROM loguser")
    List<LogUser> findAllLogUser();

    @Query("SELECT * FROM loguser where nombre LIKE :name")
    LogUser findLogUserByName(String name);

    @Query("SELECT * FROM loguser where correo LIKE :correo")
    LogUser findLogUserByCorreo(String correo);

    @Query("SELECT * FROM loguser where rfc LIKE :rfc")
    LogUser findLogUserByRfc(String rfc);

    @Query("SELECT * FROM loguser where id LIKE :id")
    LogUser findLogUserById(int id);

    @Update
    void updateLogUserById(LogUser loguser);

    @Query("DELETE FROM loguser")
    void deleteAllLogUser();

    @Delete
    void deleteLogUserById(LogUser loguser);


}
