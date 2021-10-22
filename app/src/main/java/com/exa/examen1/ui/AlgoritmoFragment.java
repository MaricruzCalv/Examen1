package com.exa.examen1.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exa.examen1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;


public class AlgoritmoFragment extends Fragment {
    Button analizar;
    TextInputLayout cadenas;


    public AlgoritmoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_algoritmo, container, false);

    }

    @Override
    public void onViewCreated(View v, Bundle b){
        super.onViewCreated(v,b );
        cadenas = v.findViewById(R.id.fracTvEntrada);
        analizar = v.findViewById(R.id.fracAlgAnalizar);


        analizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String analizar = cadenas.getEditText().getText().toString();
                String[] parts = analizar.split("\n");
                String result ="" ;
                if (parts!=null){
                    for (String part : parts){
                        //result += part+Palindrom(part)+"/n";
                        if(Palindrom(part)){
                            result += part+"  ->  Es Palíndroma \n";
                        }else{
                            result += part+"  ->  No es Palíndroma\n";
                        }
                    }
                    cadenas.getEditText().setText(result);
                }

            }
        });
    }
    public boolean Palindrom(String palabra){

        palabra = palabra.toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o")
        				.replace("ú", "u").replace(" ", "").replace(".", "").replace(",", "");
        String palreves = new StringBuilder(palabra).reverse().toString();
        return palabra.equals(palreves);
    }
}