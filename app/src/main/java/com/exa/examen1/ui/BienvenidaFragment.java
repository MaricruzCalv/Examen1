package com.exa.examen1.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exa.examen1.R;
import com.exa.examen1.databinding.FragmentBienvenidaBinding;


public class BienvenidaFragment extends Fragment {
    TextView welcom;


    public BienvenidaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bienvenida, container, false);

    }

    @Override
    public void onViewCreated(View v, Bundle b){
        super.onViewCreated(v,b );
        welcom = v.findViewById(R.id.fracTvBienv);
        String texto = getArguments().getString("usuario");
        welcom.setText("INICIO \nUsuario:  "+ texto);

    }
}