package com.exa.examen1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.exa.examen1.databinding.ActivityMainBinding;
import com.exa.examen1.db.database.AppDb;
import com.exa.examen1.db.entity.LogUser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainAct;
    Validacion validar = new Validacion();
    String User, Pass;

    private LogUser loguser;
    //private List<LogUser> usuario = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainAct = ActivityMainBinding.inflate(getLayoutInflater());
        View v = mainAct.getRoot();
        setContentView(v);

        configView();
    }

    private void configView(){

        mainAct.mainActivityBtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //BOTON REGISTRAR
                startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
                finish();
            }
        });

        mainAct.mainActivityTvRecuperarPassw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OPCION DE RECUPERACION DE CONTRASEÑA
                startActivity(new Intent(getApplicationContext(), RecuperarPassActivity.class));
                finish();
            }
        });

        mainAct.mainActivityBtInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //BOTON INICIAR SESSION
                User = mainAct.mainActivityEtEmail.getEditText().getText().toString();
                Pass= mainAct.mainActivityEtPassw.getEditText().getText().toString();

                if (validar.Password(Pass) == "" && validar.Correo(User) == "") {
                    mainAct.mainActivityEtEmail.setError(validar.Correo(User));
                    mainAct.mainActivityEtPassw.setError(validar.Password((Pass)));
                    // Consultar en Base de Datos
                    loguser = AppDb.getAppDb(getApplicationContext()).logUserDAO().findLogUserByCorreo(User);

                    if(loguser!= null && loguser.getPassword().equals(Pass)){
                        // Ir a la siguiente activity
                        startActivity(new Intent(getApplicationContext(), OpcionesActivity.class).putExtra("correo", loguser.getCorreo()));
                        finish();
                    }else if (loguser!= null && !loguser.getPassword().equals(Pass)){
                        //Actualizar pass

                        mainAct.mainActivityTvRecuperarPassw.setVisibility(View.VISIBLE);
                        mainAct.mainActivityEtPassw.setError("Contraseña incorrecta");

                    }else {
                        // El usuario no existe
                        mainAct.mainActivityEtEmail.setError("No existe usuario");
                        //Toast.makeText(getApplicationContext(),"No existe usuario", Toast.LENGTH_LONG).show();
                    }
                } else {
                     mainAct.mainActivityEtEmail.setError(validar.Correo(User));
                     mainAct.mainActivityEtPassw.setError(validar.Password(Pass));
                }

            }
        });


    }
}