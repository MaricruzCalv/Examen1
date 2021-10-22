package com.exa.examen1.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exa.examen1.R;
import com.exa.examen1.db.database.AppDb;
import com.exa.examen1.db.entity.LogUser;
import com.google.android.material.textfield.TextInputLayout;


public class ConfigcFragment extends Fragment {
    Button actualizar;
    TextInputLayout nombre,rfc,dir,tel,web;
    String Snombre,Srfc,Sdir,Stel,Sweb;
    TextView CambPass;
    Validacion validar = new Validacion();
    private LogUser loguser;


    public ConfigcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configc, container, false);
    }
    @Override
    public void onViewCreated(View v,Bundle b){
        super.onViewCreated(v,b);
        actualizar =v.findViewById(R.id.fracUpdateCBtActualizar);
        nombre= v.findViewById(R.id.fracUpdateCEtNombre);
        rfc=v.findViewById(R.id.fracUpdateCEtRfc);
        dir =v.findViewById(R.id.fracUpdateCEtDireccion);
        tel=v.findViewById(R.id.fracUpdateCEtTel);
        web=v.findViewById(R.id.fracUpdateCEtWebs);
        CambPass =v.findViewById(R.id.fracUpdateCTvCambPass);
        loguser = AppDb.getAppDb(getContext()).logUserDAO().findLogUserById(getArguments().getInt("id"));
        //nombre.getEditText().setText(getArguments().getString("nombre"));
        //rfc.getEditText().setText(getArguments().getString("Rfc"));
        nombre.getEditText().setText(loguser.getNombre());
        rfc.getEditText().setText(loguser.getRfc());
        dir.getEditText().setText(loguser.getDireccion());
        tel.getEditText().setText(loguser.getTelefono());
        web.getEditText().setText(loguser.getWebsite());



        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snombre=nombre.getEditText().getText().toString();
                Srfc    = rfc.getEditText().getText().toString();
                Sdir    =dir.getEditText().getText().toString();
                Stel   = tel.getEditText().getText().toString();
                Sweb    =web.getEditText().getText().toString();
                if(validar.Nombre(Snombre)=="" && validar.RFC(Srfc)==""){
                    if(validar.Nombre(Sdir)==""|validar.Nombre(Stel)==""|validar.Nombre(Sweb)==""){
                        loguser.setNombre(Snombre);
                        loguser.setRfc(Srfc);
                        loguser.setDireccion(Sdir);
                        loguser.setTelefono(Stel);
                        loguser.setWebsite(Sweb);
                        AppDb.getAppDb(getContext()).logUserDAO().updateLogUserById(loguser);
                        String id= Integer.toString(getArguments().getInt("id"))  ;
                        //Toast.makeText(getContext(),id, Toast.LENGTH_LONG).show();

                        Bundle usuario = new Bundle();
                        usuario.putString("usuario",loguser.getNombre().toString());
                        usuario.putString("Rfc",loguser.getRfc().toString());


                        Fragment fragment = new BienvenidaFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragment.setArguments(usuario);
                        fragmentTransaction.replace(R.id.frameLayout1,fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        //getSupportFragmentManager().beginTransaction().replace(opcAct.frameLayout1.getId(), Algoritmos).addToBackStack(null).commit();
                    }else{
                        Toast.makeText(getContext(),"No hay datos para Actualizar", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        CambPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(getContext(), NuevoPassActivity.class);
                inten.putExtra("correo", loguser.getCorreo());
                startActivity(inten) ;
               // finish();
            }
        });


    }
}