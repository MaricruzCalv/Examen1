package com.exa.examen1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exa.examen1.R;
import com.exa.examen1.databinding.ActivityNuevopassBinding;
import com.exa.examen1.databinding.ActivityRegistroBinding;
import com.exa.examen1.db.database.AppDb;
import com.exa.examen1.db.entity.LogUser;

public class NuevoPassActivity extends AppCompatActivity {
    ActivityNuevopassBinding newpAct;
    String pass, cpass;
    Validacion val = new Validacion();
    private LogUser logUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String correo= getIntent().getStringExtra("correo");
        newpAct = ActivityNuevopassBinding.inflate(getLayoutInflater());
        newpAct.nuevopassActivityUsuario.setText(correo);
        View vR = newpAct.getRoot();
        setContentView(vR);
        logUser = AppDb.getAppDb(getApplicationContext()).logUserDAO().findLogUserByCorreo(correo);
        configView();
    }

    private void configView(){

        // Visualizar en este TextView el nombre del LogUser


        newpAct.nuevopassActivityBtActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass = newpAct.nuevopassActivityEtPass.getEditText().getText().toString();
                cpass = newpAct.nuevopassActivityEtConfPass.getEditText().getText().toString();

                if(val.Password(pass)=="" && val.Password(cpass)=="")
                {
                    if(!pass.equals(cpass) ){
                        newpAct.nuevopassActivityEtConfPass.setError("La Contraseña no coincide");

                    }else{
                        newpAct.nuevopassActivityEtConfPass.setError("");
                        logUser.setPassword(pass);
                        Toast.makeText(getApplicationContext(),logUser.getNombre()+"   Tu contraseña se actualizo", Toast.LENGTH_LONG).show();
                        AppDb.getAppDb(getApplicationContext()).logUserDAO().updateLogUserById(logUser);
                       Intent inten = new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(inten) ;
                       finish();
                    }


                }else {
                    newpAct.nuevopassActivityEtPass.setError(val.Password(pass));
                    newpAct.nuevopassActivityEtConfPass.setError((val.Password(cpass)));
                }


            }
        });
    }


}