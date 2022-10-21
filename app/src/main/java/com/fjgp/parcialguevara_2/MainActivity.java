package com.fjgp.parcialguevara_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText txt_usuario;
    EditText txt_password;
    ArrayList<Usuario> listaregistrada;
    Button btnregistrar,btningresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_usuario=(EditText) findViewById(R.id.txt_usuario);

        txt_password=(EditText) findViewById(R.id.txt_pass);
        btnregistrar=(Button)findViewById(R.id.btn_registrar_principal);
        btningresar=(Button)findViewById(R.id.btn_ingresar);
        btnregistrar.setOnClickListener(this);
        btningresar.setOnClickListener(this);




    }

    public void registrar(){
        Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
        Bundle objBundle = new Bundle();
        //System.out.println(listaregistrada);
        startActivity(intent);
        finish();
    }

    public void ingresar(){
       int val;
        String email=txt_usuario.getText().toString();
        String passwordusuario=txt_password.getText().toString();
        Usuario usuariovalidar = new Usuario();
        usuariovalidar.setEmail(email);
        usuariovalidar.setContrasena(passwordusuario);
        val=validar(usuariovalidar,this.listaregistrada);
        if(val==1){
            Intent intent=new Intent(MainActivity.this,PrincipalActivity.class);
            txt_usuario.setText("");
            txt_password.setText("");
            Bundle objBundle = new Bundle();
            //System.out.println(listaregistrada);
            objBundle.putSerializable("datos",listaregistrada);
            intent.putExtras(objBundle);
            startActivity(intent);
            finish();
        }else if(val==2){

            Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            txt_password.setText("");
            txt_password.requestFocus();
        }else if(val==3) {

            Toast.makeText(MainActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
            txt_password.setText("");
            txt_usuario.requestFocus();
        }

    }

    public void listar(){
        Intent intent = new Intent(MainActivity.this, ListarActivity.class);
        Bundle objBundle = new Bundle();
        //System.out.println(listaregistrada);
        objBundle.putSerializable("datos",listaregistrada);
        intent.putExtras(objBundle);
        startActivity(intent);
        finish();
    }
    public int validar(Usuario usuariovalidar,ArrayList<Usuario> lista2){

        Integer val=3;
        for (Usuario u: lista2
        ) {
            if(u.getEmail().equals(usuariovalidar.getEmail()) && u.getContrasena().equals(usuariovalidar.getContrasena())){
                val=1;
                break;
            }else if(u.getEmail().equals(usuariovalidar.getEmail()) && !u.getContrasena().equals(usuariovalidar.getContrasena())){
                val=2;
                break;
            }
        }

        return val;
    }

    @Override
    public void onClick(View v) {
        if (v==btnregistrar){
            registrar();
        } else if  (v==btningresar){
            ingresar();
        }
    }
}