package com.fjgp.parcialguevara_2.curso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fjgp.parcialguevara_2.R;

import java.util.ArrayList;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

    private ArrayList<Curso> cursos;
    private int resources;
    Context context;

    public CursoAdapter(ArrayList<Curso> cursos, int resources, Context context) {
        this.cursos = cursos;
        this.resources = resources;
        this.context = context;
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(
                        parent.getContext()
                )
                .inflate(
                    resources,
                        parent,
                    false
                );
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = cursos.get(position);
        holder
                .nombre
                .setText(
                        curso.getNombre()
                );
        holder
                .carrera
                .setText(
                        curso.getCarrera()
                );
        holder
                .codigo
                .setText(
                        curso.getCodigo()
                );
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder{

    TextView nombre, carrera, codigo;
    View view;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            nombre  = (TextView) itemView.findViewById(R.id.nombre_curso);
            carrera = (TextView) itemView.findViewById(R.id.carrera_curso);
            codigo  = (TextView) itemView.findViewById(R.id.codigo_curso);
        }
    }
}
