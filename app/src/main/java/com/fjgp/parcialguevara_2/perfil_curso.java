package com.fjgp.parcialguevara_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.fjgp.parcialguevara_2.curso.Curso;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class perfil_curso extends AppCompatActivity {
    String userID;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String codigo_curso;
    private TextView nombre, codigo, horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_curso);

        nombre = (TextView) findViewById(R.id.txt_nombrecurso);
        codigo = (TextView) findViewById(R.id.txt_codigocurso);
        horario = (TextView) findViewById(R.id.txt_horariocurso);

        user= FirebaseAuth.getInstance().getCurrentUser();
        codigo_curso =  getIntent().getStringExtra("codigo_curso");
        userID = user.getUid();
        reference= FirebaseDatabase.getInstance().getReference("Usuario");

        reference.child(userID).child("Cursos").child(codigo_curso).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Curso curso=snapshot.getValue(Curso.class);
                if(curso!=null){
                    String codigo_curso  =curso.getCodigo();
                    String nombre_curso  =curso.getNombre();
                    String silabus_curso =curso.getSilabus();
                    String horario_curso =curso.getHorario();


                    nombre.setText(nombre_curso);
                    codigo.setText(codigo_curso);
                    horario.setText(horario_curso);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(PrincipalActivity.this, "Algo inesperado ocurrio",Toast.LENGTH_SHORT).show();
            }
        });



    }
}