package com.exa.examen1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exa.examen1.R;

import com.exa.examen1.databinding.ActivityRegistroBinding;
import com.exa.examen1.db.database.AppDb;
import com.exa.examen1.db.entity.LogUser;

import java.util.ArrayList;
import java.util.List;

public class RegistroActivity extends AppCompatActivity {
    ActivityRegistroBinding regAct;
    Validacion validar = new Validacion();
    String nombre, correo, rfc, pass, passc;

    private LogUser loguser, loguser2, loguser3;
    private RegistrarLogUserTask registrarLogUserTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        regAct = ActivityRegistroBinding.inflate(getLayoutInflater());
        View vR = regAct.getRoot();
        setContentView(vR);

        configView();
    }
    private void configView(){
        loguser = new LogUser();

        // Boton registrar
        regAct.registroActivityBtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validar datos
                correo =regAct.registroActivityEtCorreo.getEditText().getText().toString();
                rfc = regAct.registroActivityEtRfc.getEditText().getText().toString();
                nombre = regAct.registroActivityEtNombre.getEditText().getText().toString();
                pass = regAct.registroActivityEtPass.getEditText().getText().toString();
                passc = regAct.registroActivityEtConfirmPass.getEditText().getText().toString();

                if (validar.Nombre(nombre)=="" && validar.Correo(correo) =="" && validar.RFC(rfc) =="" && validar.Password(pass) =="" && validar.Password(passc)=="" ){
                    if (!pass.equals(passc)){
                        regAct.registroActivityEtConfirmPass.setError("La Contraseña no coincide");

                    }else {
                        regAct.registroActivityEtNombre.setError(validar.Nombre(nombre));
                        regAct.registroActivityEtCorreo.setError(validar.Correo(correo));
                        regAct.registroActivityEtRfc.setError(validar.RFC(rfc));
                        regAct.registroActivityEtPass.setError(validar.Password(pass));
                        regAct.registroActivityEtConfirmPass.setError(validar.Password(passc));
                        consultaBD();

                    }
                }else {
                    regAct.registroActivityEtNombre.setError(validar.Nombre(nombre));
                    regAct.registroActivityEtCorreo.setError(validar.Correo(correo));
                    regAct.registroActivityEtRfc.setError(validar.RFC(rfc));
                    regAct.registroActivityEtPass.setError(validar.Password(pass));
                    regAct.registroActivityEtConfirmPass.setError(validar.Password(passc));
                }

            }
        });

    }

    private void consultaBD(){
        // Consultar de la BD que no Existan
        loguser3 = AppDb.getAppDb(getApplicationContext()).logUserDAO().findLogUserByRfc(rfc);
        loguser2 = AppDb.getAppDb(getApplicationContext()).logUserDAO().findLogUserByCorreo(correo);

        if( loguser2==null && loguser3==null )
        {   //Guardar nuevo registro

            loguser.setNombre(nombre);
            loguser.setCorreo(correo);
            loguser.setRfc(rfc);
            loguser.setPassword(pass);
            registrarLogUserTask =new RegistrarLogUserTask();
            registrarLogUserTask.execute(loguser);
            Toast.makeText(getApplicationContext(),"Registro Exitoso", Toast.LENGTH_LONG).show();
            Intent inten = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(inten) ;
            finish();

            Toast.makeText(getApplicationContext(),"Registro Exitoso", Toast.LENGTH_LONG).show();
        }else {
            if(loguser2 !=null){
                regAct.registroActivityEtCorreo.setError("El correo ya esta registrado");
                //Toast.makeText(getApplicationContext(),"El correo ya esta registrado", Toast.LENGTH_LONG).show();
                Log.d("Tag", loguser2.getCorreo());
            }else if(loguser3 != null){
                regAct.registroActivityEtRfc.setError("El RFC ya esta registrado");
                //Toast.makeText(getApplicationContext(),"El RFC ya esta registrado", Toast.LENGTH_LONG).show();
                Log.d("Tag", loguser3.getRfc());
            }

        }
    }



    private class RegistrarLogUserTask extends AsyncTask<LogUser, Void , Void>{

        @Override
        protected Void doInBackground(LogUser... logUsers) {
            AppDb.getAppDb(getApplicationContext()).logUserDAO().insertLogUser(logUsers[0]);
            return null;
        }
    }



}