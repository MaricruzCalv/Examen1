package com.exa.examen1.db.entity;
//import android.arch.persistence.room.Entity;
import com.exa.examen1.constans.Constants;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity (tableName = Constants.NAME_TABLE_LOGUSER)
public class LogUser {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name="correo")
    private String correo;
    @ColumnInfo(name = "rfc")
    private String rfc;
    @ColumnInfo (name = "password")
    private String password;
    @ColumnInfo (name = "direccion")
    private String direccion;
    @ColumnInfo (name = "telefono")
    private String telefono;
    @ColumnInfo(name = "website")
    private String website;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
