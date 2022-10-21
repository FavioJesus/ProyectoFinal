package com.fjgp.parcialguevara_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Usuario> listaregistrada;
    Button btnregresar;
    String userID;
    private FirebaseUser user;
    private DatabaseReference reference;
    TextView usuariologeado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnregresar=(Button)findViewById(R.id.btn_regresarprincipal);
        usuariologeado=(TextView)findViewById(R.id.txt_usuariologeado);

        btnregresar.setOnClickListener(this);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Usuario");
        userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuProfile=snapshot.getValue(Usuario.class);
                if(usuProfile!=null){
                    String nombres=usuProfile.nombres;
                    String apellidos=usuProfile.apellidos;
                    String correo=usuProfile.email;
                    String facultad=usuProfile.facultad;
                    String carrera=usuProfile.carrera;
                    String telefono=usuProfile.telefono;

                    usuariologeado.setText(nombres);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PrincipalActivity.this, "Algo inesperado ocurrio",Toast.LENGTH_SHORT).show();
            }
        });

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