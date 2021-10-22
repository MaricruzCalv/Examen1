package com.exa.examen1.ui;

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
import com.exa.examen1.db.entity.Usuarios;
import com.google.android.material.textfield.TextInputLayout;


public class RU_usuarioFragment extends Fragment {
    Button boton;

    TextInputLayout nombre,rfc,dir,tel,web;
    String Snombre,Srfc,Sdir,Stel,Sweb;
    Validacion validar = new Validacion();
    private Usuarios usuarios;
    private static final String ARG_PARAM1="param1";
    private int mParam1;
    private Usuarios usuario;

    public RU_usuarioFragment() {
        // Required empty public constructor
    }
    public static RU_usuarioFragment newInstance(int param1){
        RU_usuarioFragment fragment = new RU_usuarioFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_r_u_usuario, container, false);
        TextView textView = view.findViewById(R.id.fragRUTvTitle);
        TextView textView2 = view.findViewById(R.id.fragRUdelete);
        //recibo e imprimo valor
        if(mParam1>0) {
            usuario = AppDb.getAppDb(getContext()).usuariosDAO().findUsuarioByIdUsuario(mParam1);
            //TextView textView = view.findViewById(R.id.fragRUTvTitle);
            //textView.setText(mParam1);

            textView2.setVisibility(view.VISIBLE);
            textView.setText("Actualizar Datos");
            nombre = view.findViewById(R.id.fragRUEtNombre);
            rfc = view.findViewById(R.id.fragRUEtRfc);
            dir = view.findViewById(R.id.fragRUEtDireccion);
            tel = view.findViewById(R.id.fragRUEtTel);
            web = view.findViewById(R.id.fragRUEtWebs);



            nombre.getEditText().setText(usuario.getNombre().toString());
            rfc.getEditText().setText(usuario.getRfc().toString());
            dir.getEditText().setText(usuario.getDireccion().toString());
            tel.getEditText().setText(usuario.getTel());
            web.getEditText().setText(usuario.getWeb());
        }

        return view;
    }

    @Override
    public void onViewCreated(View v,Bundle b){
        super.onViewCreated(v,b);
        TextView textView2 = v.findViewById(R.id.fragRUdelete);
        boton = v.findViewById(R.id.fragRUBtBoton);
        nombre = v.findViewById(R.id.fragRUEtNombre);
        rfc = v.findViewById(R.id.fragRUEtRfc);
        dir = v.findViewById(R.id.fragRUEtDireccion);
        tel = v.findViewById(R.id.fragRUEtTel);
        web = v.findViewById(R.id.fragRUEtWebs);
        usuarios = new Usuarios();
        if(mParam1<=0){
            TextView textView = v.findViewById(R.id.fragRUTvTitle);
            textView.setText("Crear Usuario");
            boton.setText(getArguments().getString("boton"));
            usuarios = new Usuarios();
        }
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getArguments().getString("boton")!=null && getArguments().getString("boton").equals("Crear")){
                    if(validar.Nombre(nombre.getEditText().getText().toString())=="" && validar.RFC(rfc.getEditText().getText().toString())==""){
                        usuarios.setNombre(nombre.getEditText().getText().toString());
                        usuarios.setRfc(rfc.getEditText().getText().toString());
                        usuarios.setDireccion(dir.getEditText().getText().toString());
                        usuarios.setTel(tel.getEditText().getText().toString());
                        usuarios.setWeb(web.getEditText().getText().toString());
                        usuarios.setIdLogUser(getArguments().getInt("loguserId"));
                        AppDb.getAppDb(getContext()).usuariosDAO().insert(usuarios);
                        Bundle usuario = new Bundle();
                        usuario.putInt("id",getArguments().getInt("loguserId"));
                        Fragment fragment = new UriariosFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragment.setArguments(usuario);
                        fragmentTransaction.replace(R.id.frameLayout1,fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }else {
                        nombre.setError(validar.Nombre(nombre.getEditText().getText().toString()));
                        rfc.setError(validar.RFC(rfc.getEditText().getText().toString()));
                    }
                }else {

                    usuario.setNombre(nombre.getEditText().getText().toString());
                    usuario.setRfc(rfc.getEditText().getText().toString());
                    usuario.setDireccion(dir.getEditText().getText().toString());
                    usuario.setTel(tel.getEditText().getText().toString());
                    usuario.setWeb(web.getEditText().getText().toString());
                    AppDb.getAppDb(getContext()).usuariosDAO().updateUsuariosById(usuario);
                    Bundle usuar = new Bundle();
                    usuar.putInt("id",usuario.getIdLogUser());
                    Fragment fragment = new UriariosFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragment.setArguments(usuar);
                    fragmentTransaction.replace(R.id.frameLayout1,fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();


                }
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle usu = new Bundle();
                usu.putInt("id",usuario.getIdLogUser());
                Fragment fragment = new UriariosFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(usu);
                fragmentTransaction.replace(R.id.frameLayout1,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                AppDb.getAppDb(getContext()).usuariosDAO().deleteUsuariosById(usuario);
            }
        });

    }
}