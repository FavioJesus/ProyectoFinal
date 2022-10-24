package com.fjgp.parcialguevara_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fjgp.parcialguevara_2.alumno.Alumno;
import com.fjgp.parcialguevara_2.curso.Curso;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registro_alumno extends AppCompatActivity implements View.OnClickListener {

    private EditText codigo, nombre, apellido, email;
    private Button btn_registrar_alumno;
    private String cod_curso;
    DatabaseReference referencia;
    private DatabaseReference reference;
    private String userID;
    private FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_alumno);

        codigo  = (EditText) findViewById(R.id.txt_CodigoAlumno);
        nombre = (EditText) findViewById(R.id.txt_NombreAlumno);
        apellido  = (EditText) findViewById(R.id.txt_ApellidoAlumno);
        email = (EditText) findViewById(R.id.txt_CorreoAlumno);
        btn_registrar_alumno = (Button) findViewById(R.id.btn_registrar_alumno);

        cod_curso = getIntent().getStringExtra("codigo_curso");


        referencia = FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Usuario");
        userID = user.getUid();
        btn_registrar_alumno.setOnClickListener(this);

    }

    public void registrarAlumno(){

        if(validarCampo(codigo)){ return;}
        if(validarCampo(nombre)){ return;}
        if(validarCampo(apellido)){ return;}
        if(validarCampo(email)){ return;}

        String codigo_alumno  = codigo.getText().toString();
        String nombre_alumno  = nombre.getText().toString();
        String apellido_alumno  = apellido.getText().toString();
        String email_alumno = email.getText().toString();

        Alumno alumno = new Alumno(
                codigo_alumno,
                nombre_alumno,
                apellido_alumno,
                email_alumno
        );

        referencia.child("Usuario").child(userID).child("Cursos").child(cod_curso).child("alumnos").child(codigo_alumno).setValue(alumno);
        regresar();

    }

    public boolean validarCampo(EditText campo){
        Boolean formularioIncorrecto = false;
        if(campo.getText().toString().equals("")){
            formularioIncorrecto = true;
        }
        return formularioIncorrecto;
    }

    @Override
    public void onClick(View v) {
        if  (v==btn_registrar_alumno){
            registrarAlumno();
        }
    }

    public void regresar(){
        Intent intent = new Intent(registro_alumno.this, perfil_curso.class);
        startActivity(intent);
        Bundle objBundle = new Bundle();
        //System.out.println(listaregistrada);
        //objBundle.putSerializable("datos",listaregistrada);
        objBundle.putString("codigo_curso",cod_curso);
        intent.putExtras(objBundle);
        startActivity(intent);
        finish();

    }
}