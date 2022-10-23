package com.fjgp.parcialguevara_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fjgp.parcialguevara_2.alumno.Alumno;
import com.fjgp.parcialguevara_2.alumno.AlumnoAdapter;
import com.fjgp.parcialguevara_2.curso.Curso;
import com.fjgp.parcialguevara_2.curso.CursoAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class perfil_curso extends AppCompatActivity {
    String userID;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String codigo_curso;
    private TextView nombre, codigo, horario;

    Button btn_agregar_alumno;

    //Lista de alumnos
    private RecyclerView recyclerView;
    private AlumnoAdapter alumnoAdapter;
    private ArrayList<Alumno> alumnos = new ArrayList<>();

    private AlumnoAdapter.RecyclerViewClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_curso);

        btn_agregar_alumno = (Button) findViewById(R.id.btn_agregar_alumno);
        recyclerView = (RecyclerView) findViewById(R.id.listaalumnos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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

        //Lista de alumnos
        reference
                .child(userID)
                .child("Cursos")
                .child(codigo_curso)
                .child("alumnos")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                String codigo  = dataSnapshot.child("codigo").getValue().toString();
                                String nombre = dataSnapshot.child("nombre").getValue().toString();
                                String apellido  = dataSnapshot.child("apellido").getValue().toString();
                                String email = dataSnapshot.child("email").getValue().toString();

                                alumnos.add(
                                        new Alumno(
                                                codigo,
                                                nombre,
                                                apellido,
                                                email
                                        )
                                );
                                System.out.println(alumnos);
                            }
                            setOnClickListener();
                            alumnoAdapter = new AlumnoAdapter(alumnos, R.layout.activity_listar_alumno, perfil_curso.this, listener );
                            recyclerView.setAdapter(alumnoAdapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



    }

    //Boton para ir al registro de alumno

    public void irRegistroAlumno(View view){
        //System.out.println("Click en agregar");
        Intent intent = new Intent(perfil_curso.this, registro_alumno.class);
        Bundle objBundle = new Bundle();
        //System.out.println(listaregistrada);
        //objBundle.putSerializable("datos",listaregistrada);
        objBundle.putString("codigo_curso",codigo_curso);
        intent.putExtras(objBundle);
        startActivity(intent);
        finish();
    }

    private void setOnClickListener(){
        listener = new AlumnoAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                System.out.println("Click en detalle curso");
                Intent intent = new Intent(perfil_curso.this, registro_nota_alumno.class);
                Bundle objBundle = new Bundle();
                //System.out.println(listaregistrada);
                objBundle.putString("codigo_alumno",alumnos.get(position).getCodigo());
                intent.putExtras(objBundle);
                startActivity(intent);
                finish();
            }
        };
    }




}