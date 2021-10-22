package com.exa.examen1.adaptadores;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exa.examen1.R;
import com.exa.examen1.db.entity.Usuarios;
import com.exa.examen1.ui.BienvenidaFragment;
import com.exa.examen1.ui.RU_usuarioFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;



public class ListaUsiariosAdapter extends RecyclerView.Adapter<ListaUsiariosAdapter.UsuarioViewHolder>{
    ArrayList<Usuarios> listUsuarios;
    private ItemClickListener clickListener;

    public ListaUsiariosAdapter(ArrayList<Usuarios> listUsuarios,ItemClickListener clickListener){
        this.listUsuarios = listUsuarios;
        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_user,null, false );
       return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.nombre.setText(listUsuarios.get(position).getNombre());
        holder.tel.setText(listUsuarios.get(position).getTel());
        holder.rfc.setText(listUsuarios.get(position).rfc);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(listUsuarios.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, rfc, tel;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.listaitemNombre);
            rfc = itemView.findViewById(R.id.listaitemRfc);
            tel = itemView.findViewById(R.id.listaitemTel);

        }
    }
        public interface ItemClickListener{
            public void onItemClick(Usuarios usuarios);
        }
}
