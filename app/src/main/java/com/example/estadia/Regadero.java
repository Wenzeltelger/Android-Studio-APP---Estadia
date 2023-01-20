package com.example.estadia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Regadero extends AppCompatActivity {

    TextView hum;

    Button B21;

    Boolean estado10 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regadero);

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        hum = findViewById(R.id.hum);

        B21 = findViewById(R.id.B21);


        DatabaseReference referenceEntrada21 = database.getReference("LECTURAS2");
        DatabaseReference referenceEntrada22 = database.getReference("REGADERO");




        referenceEntrada21.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String T = snapshot.child("hum").getValue().toString();
                hum.setText("Humedad: "+T);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        B21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<Object, Object> info = new HashMap<>();
                estado10 =! estado10;
                info.put("R1", estado10);
                referenceEntrada22.setValue(info);

                if(estado10 == true){
                    B21.setText("Regadero ON");
                }else{
                    B21.setText("Regadero OFF");
                }

            }
        });

    }
}