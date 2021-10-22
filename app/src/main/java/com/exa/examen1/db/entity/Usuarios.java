package com.exa.examen1.db.entity;

import com.exa.examen1.constans.Constants;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (tableName = Constants.NAME_TABLE_USUARIOS,
        foreignKeys = @ForeignKey(entity = LogUser.class,
                parentColumns = "id",
                childColumns = "idLogUser",
                onDelete = CASCADE))
public class Usuarios {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idUsuario")
    public int idUsuario;
    @ColumnInfo(name="nombre")
    public String nombre;
    @ColumnInfo(name="rfc")
    public String rfc;
    @ColumnInfo(name="direccion")
    public String direccion;
    @ColumnInfo(name="tel")
    public String tel;
    @ColumnInfo(name="web")
    public String web;
    @ColumnInfo(name="idLogUser")
    public int idLogUser;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getIdLogUser() {
        return idLogUser;
    }

    public void setIdLogUser(int idLogUser) {
        this.idLogUser = idLogUser;
    }
}
