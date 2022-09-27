package com.fjgp.parcialguevara_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Usuario> listaregistrada;
    Button btnregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnregresar=(Button)findViewById(R.id.btn_regresarprincipal);
        btnregresar.setOnClickListener(this);
        Intent objIntent = getIntent();
        Bundle objBundle = objIntent.getExtras();
        if(objBundle != null){
            ArrayList<Usuario> listarecibida = (ArrayList<Usuario>) objBundle.getSerializable("datos");
            listaregistrada = listarecibida;
        }else{
            listaregistrada = new ArrayList<Usuario>();
        }

        //esto es un comentario
    }

    public void regresar(){
        Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
        Bundle objBundle = new Bundle();
        //System.out.println(listaregistrada);
        objBundle.putSerializable("datos",listaregistrada);
        intent.putExtras(objBundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if  (v==btnregresar){
            regresar();
        }
    }
}