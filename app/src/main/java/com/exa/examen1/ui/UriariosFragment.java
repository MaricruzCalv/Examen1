package com.exa.examen1.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.exa.examen1.R;
import com.exa.examen1.adaptadores.ListaUsiariosAdapter;
import com.exa.examen1.db.database.AppDb;
import com.exa.examen1.db.entity.Usuarios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UriariosFragment extends Fragment implements ListaUsiariosAdapter.ItemClickListener {
    FloatingActionButton newUsuarioBt;
    //private List<Usuarios> usuarios;
    ArrayList<Usuarios> usuarios;
    RecyclerView listuser;
    public UriariosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uriarios, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle b){
        super.onViewCreated(v,b );
        newUsuarioBt = v.findViewById(R.id.fragUfloatingActionButton);
        String id = Integer.toString(getArguments().getInt("id"));
        //Toast.makeText(getContext(),id, Toast.LENGTH_LONG).show();
        listuser = v.findViewById(R.id.fragULista);
        listuser.setLayoutManager(new LinearLayoutManager(getContext()));
        usuarios = new ArrayList<>();
        usuarios = (ArrayList<Usuarios>) AppDb.getAppDb(getContext()).usuariosDAO().findUsuariosForLogUser(getArguments().getInt("id"));

        ListaUsiariosAdapter adapter=new ListaUsiariosAdapter(usuarios,this);
        listuser.setAdapter(adapter);

        /*for (Usuarios usuario: usuarios){
            Log.d( "TAG", "Nombre: "+usuario.getNombre().toString()+ " RFC:  "+usuario.getRfc().toString() );
        }*/


        newUsuarioBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle usuario = new Bundle();
                usuario.putInt("loguserId",getArguments().getInt("id"));
                usuario.putString("boton","Crear");
                Fragment fragment = new RU_usuarioFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(usuario);
                fragmentTransaction.replace(R.id.frameLayout1,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void onItemClick(Usuarios usuarios) {
        Fragment fragment = RU_usuarioFragment.newInstance(usuarios.idUsuario);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout1,fragment,"RU_usuario_fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}