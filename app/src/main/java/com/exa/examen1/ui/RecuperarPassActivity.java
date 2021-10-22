package com.exa.examen1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exa.examen1.R;
import com.exa.examen1.databinding.ActivityRecuperarPassBinding;
import com.exa.examen1.databinding.ActivityRegistroBinding;
import com.exa.examen1.db.database.AppDb;
import com.exa.examen1.db.entity.LogUser;

public class RecuperarPassActivity extends AppCompatActivity {
    ActivityRecuperarPassBinding recPassAct;
    Validacion Var = new Validacion();
    public String correo, rfc;
    private LogUser logUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recPassAct = ActivityRecuperarPassBinding.inflate(getLayoutInflater());
        View vR = recPassAct.getRoot();
        setContentView(vR);

        configView();
    }

    private void configView(){
        recPassAct.recuperarPassActivityBtActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validar();
                // Llamada al metodo consultar en BD para Verificar si los datos existen

            }
        });

    }

    private void Validar(){
        correo = recPassAct.recuperarPassActivityEtCorreo.getEditText().getText().toString();
        rfc = recPassAct.recuperarPassActivityEtRfc.getEditText().getText().toString();

        if (Var.Correo(correo)=="" && Var.RFC(rfc)==""){
            recPassAct.recuperarPassActivityEtCorreo.setError(Var.Correo(correo));
            recPassAct.recuperarPassActivityEtRfc.setError(Var.RFC(rfc));
            // consulta si esta en bd
            consultaBD();
       }else {
            recPassAct.recuperarPassActivityEtCorreo.setError(Var.Correo(correo));
            recPassAct.recuperarPassActivityEtRfc.setError(Var.RFC(rfc));
        }

    }
    private void consultaBD(){

        logUser = AppDb.getAppDb(getApplicationContext()).logUserDAO().findLogUserByCorreo(correo);

        if (logUser==null){
            Toast.makeText(getApplicationContext(),"Usuario incorrecto", Toast.LENGTH_LONG).show();
            recPassAct.recuperarPassActivityEtCorreo.setError("Usuario no Existe");

        }else if (logUser != null && logUser.getRfc().equals(rfc)){
            Intent inten = new Intent(getApplicationContext(), NuevoPassActivity.class);
            inten.putExtra("correo", logUser.getCorreo());
            startActivity(inten) ;
            finish();

        }else {

            recPassAct.recuperarPassActivityEtRfc.setError("El RFC no Coincide");
        }

    }

}