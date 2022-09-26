package com.fjgp.parcialguevara_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener{
    EditText txtnombres,txtapellidos,txtemail,txtpassword;
    Spinner spestado,spsexo;
    Button btnregistrar,btnregresar;
    ArrayList<Usuario> listaregistrada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        txtnombres=(EditText) findViewById(R.id.txt_nombres);
        txtapellidos=(EditText) findViewById(R.id.txt_apellidos);
        txtemail=(EditText) findViewById(R.id.txt_email);
        txtpassword=(EditText) findViewById(R.id.txt_password);
        spestado=(Spinner)findViewById(R.id.sp_estado);
        spsexo=(Spinner)findViewById(R.id.sp_sexo);
        btnregistrar=(Button)findViewById(R.id.btn_registrar);
        btnregresar=(Button)findViewById(R.id.btn_regresarregistro);
        btnregistrar.setOnClickListener(this);
        btnregresar.setOnClickListener(this);

        Intent objIntent = getIntent();
        Bundle objBundle = objIntent.getExtras();
        if(objBundle != null){
            ArrayList<Usuario> listarecibida = (ArrayList<Usuario>) objBundle.getSerializable("datos");
            listaregistrada = listarecibida;
        }else{
            listaregistrada = new ArrayList<Usuario>();
        }
    }
    public void registrar(){
        String email=txtemail.getText().toString();
        String nombres=txtnombres.getText().toString();
        String apellidos=txtapellidos.getText().toString();
        String password=txtpassword.getText().toString();

        String sexostr=spsexo.getSelectedItem().toString();
        String estadostr=spestado.getSelectedItem().toString();
        Usuario objcalculado=new Usuario();
        objcalculado.setEmail(email);
        objcalculado.setNombres(nombres);
        objcalculado.setApellidos(apellidos);
        objcalculado.setSexo(sexostr);
        objcalculado.setEstadocivil(estadostr);
        objcalculado.setContrasena(password);
        this.listaregistrada.add(objcalculado);
        txtnombres.setText("");
        txtemail.setText("");
        txtapellidos.setText("");
        txtpassword.setText("");


    }
    public void regresar(){
        Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
        Bundle objBundle = new Bundle();
        //System.out.println(listaregistrada);
        objBundle.putSerializable("datos",listaregistrada);
        intent.putExtras(objBundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v==btnregistrar){
            registrar();
        } else if  (v==btnregresar){
            regresar();
        }
    }
}