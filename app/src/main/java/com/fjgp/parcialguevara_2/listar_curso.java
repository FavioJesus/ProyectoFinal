package com.fjgp.parcialguevara_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class listar_curso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_curso);
    }

    public void detalleCurso(View view){
        System.out.println("Click en detalle curso");
        //Intent intent = new Intent(listar_curso.this, registrar_curso.class);
        Bundle objBundle = new Bundle();
        //System.out.println(listaregistrada);
        //objBundle.putSerializable("datos",listaregistrada);
        //intent.putExtras(objBundle);
        //startActivity(intent);
        //finish();
    }
}