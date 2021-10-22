package com.exa.examen1.db.database;

import android.content.Context;

import com.exa.examen1.constans.Constants;
import com.exa.examen1.db.dao.LogUserDAO;
import com.exa.examen1.db.dao.UsuariosDAO;
import com.exa.examen1.db.entity.LogUser;
import com.exa.examen1.db.entity.Usuarios;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {LogUser.class, Usuarios.class}, version = 2)
public abstract class AppDb extends RoomDatabase {
    private static AppDb INSTANCE;
    public abstract LogUserDAO logUserDAO();
    public abstract UsuariosDAO usuariosDAO();

    public static AppDb getAppDb(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDb.class, Constants.NAME_DATABASE)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;

    }


    public static void destroyInstance(){
        INSTANCE = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE usuarios(idUsuario INTEGER PRIMARY KEY NOT NULL, nombre TEXT, rfc TEXT, direccion TEXT, tel TEXT, web TEXT, idLogUser INTEGER NOT NULL, foreign key (idLogUser) references loguser(id) ON DELETE CASCADE)");
        }
    };
}
