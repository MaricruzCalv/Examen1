package com.exa.examen1.db.dao;

import com.exa.examen1.db.entity.LogUser;
import com.exa.examen1.db.entity.Usuarios;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UsuariosDAO {
    @Insert
    void insert (Usuarios usuarios);

    @Query("SELECT * FROM usuarios WHERE idLogUser=:idLogUser")
    List<Usuarios> findUsuariosForLogUser(int idLogUser );

    @Query("SELECT * FROM usuarios where idUsuario LIKE :idUsuario")
    Usuarios findUsuarioByIdUsuario(int idUsuario);

    @Update
    void updateUsuariosById(Usuarios usuarios);

    @Delete
    void deleteUsuariosById(Usuarios usuarios);



}
