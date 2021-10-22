package com.exa.examen1.ui;

import java.util.regex.Pattern;

import androidx.core.util.PatternsCompat;

public class Validacion {


    public String Correo(String correo){
        String resp_email;
        if (correo.isEmpty()){
            resp_email = "*Campo Requerido";
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(correo).matches()){
            resp_email = "No es un correo valido";
        }else{
            resp_email ="";
        }
        return resp_email;
    }


    public String RFC(String rfc){
        String resp_rfc;
        Pattern ExpRegRFC = Pattern.compile("^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$" );
        if (rfc.isEmpty()){
            resp_rfc = "*Campo Requerido";
        }else if (!ExpRegRFC.matcher(rfc).matches()){
            int spacios=0;
            for (int i = 0; i < rfc.length(); i++) {
                if (rfc.charAt(i) == ' ') spacios++;
            }
            if (spacios>=1){
                resp_rfc = "No debe contener espacios";
            }else  {resp_rfc = "RFC invalido";}

        }else {
            resp_rfc = "";
        }
        return resp_rfc;
    }

    public String Password(String pass){
        String resp_pas;
        Pattern ExpRegPass = Pattern.compile("^[0-9a-zA-Z]{4,}$");
        if (pass.isEmpty()){
            resp_pas = "*Campo Requerido";
        } else if (!ExpRegPass.matcher(pass).matches()){
            resp_pas = "Debe contener minimo 4 caracteres";
        }else {
            resp_pas = "";
        }
        return resp_pas;
    }

    public String Nombre(String name){
        String resp_nom;
        if (name.isEmpty()){
            resp_nom = "*Campo Requerido";
        }else {
            resp_nom = "";
        }
        return  resp_nom;
    }

}
