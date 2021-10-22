package com.exa.examen1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.FragmentTransitionSupport;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.exa.examen1.R;
import com.exa.examen1.databinding.ActivityMainBinding;
import com.exa.examen1.databinding.ActivityOpcionesBinding;
import com.exa.examen1.databinding.ActivityRegistroBinding;
import com.exa.examen1.db.database.AppDb;
import com.exa.examen1.db.entity.LogUser;

public class OpcionesActivity extends AppCompatActivity {
    private LogUser logUser;
    FragmentTransaction transaction;
    Fragment Configc, Algoritmos, Usuarios, Bienvenida;
    ActivityOpcionesBinding opcAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_opciones);
        String correo= getIntent().getStringExtra("correo");
        opcAct = ActivityOpcionesBinding.inflate(getLayoutInflater());
        View vR = opcAct.getRoot();
        setContentView(vR);
        logUser = AppDb.getAppDb(getApplicationContext()).logUserDAO().findLogUserByCorreo(correo);

        Bienvenida = new BienvenidaFragment();
        Configc = new ConfigcFragment();
        Algoritmos = new AlgoritmoFragment();
        Usuarios = new UriariosFragment();

        Bundle args = new Bundle();
        args.putString("usuario", logUser.getNombre().toString());
        Bienvenida.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(opcAct.frameLayout1.getId(), Bienvenida).commit();

        transaction =getSupportFragmentManager().beginTransaction();
        //opcion Configuracion de Cuenta
        opcAct.fragmentConfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle usuario = new Bundle();
                usuario.putInt("id",logUser.getId());
                usuario.putString("nombre",logUser.getNombre().toString());
                usuario.putString("Rfc",logUser.getRfc().toString());
                Configc.setArguments(usuario);
                getSupportFragmentManager().beginTransaction().replace(opcAct.frameLayout1.getId(), Configc).addToBackStack(null).commit();

            }
        });
        // opcion de Algoritmo
        opcAct.fragmentAlgorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(opcAct.frameLayout1.getId(), Algoritmos).addToBackStack(null).commit();
            }
        });
        //opcion de Usuarios
        opcAct.fragmentUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle usuario = new Bundle();
                usuario.putInt("id",logUser.getId());
                Usuarios.setArguments(usuario);
                getSupportFragmentManager().beginTransaction().replace(opcAct.frameLayout1.getId(), Usuarios).addToBackStack(null).commit();

            }
        });

    }
}