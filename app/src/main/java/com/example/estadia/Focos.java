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

public class Focos extends AppCompatActivity {


    Button led1, led2, climab;

    TextView Foco1, Foco2, climat;


    Boolean estado1 = false, estado2 = false, estado3 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focos);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referenceEntrada1 = database.getReference("BOTONLED1");
        DatabaseReference referenceEntrada2 = database.getReference("BOTONLED2");

        DatabaseReference referenceEntrada4 = database.getReference("BOTONLED1");

        DatabaseReference referenceEntrada5= database.getReference("BOTONLED2");

        DatabaseReference referenceEntrada6= database.getReference("CLIMA");

        DatabaseReference referenceEntrada7= database.getReference("CLIMA");



        led1 = findViewById(R.id.led1);

        led2 = findViewById(R.id.led2);

        Foco1 = findViewById(R.id.Foco1);

        Foco2 = findViewById(R.id.Foco2);

        climab = findViewById(R.id.climab);

        climat = findViewById(R.id.climat);



        led1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<Object, Object> info = new HashMap<>();

                estado1 =! estado1;
                info.put("Valor", estado1);
                referenceEntrada1.setValue(info);

            }
        });

        led2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<Object, Object> info = new HashMap<>();

                estado2 =! estado2;
                info.put("Valor", estado2);
                referenceEntrada2.setValue(info);

            }
        });


        referenceEntrada4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String F = snapshot.child("Valor").getValue().toString();

                if(F == "true") {
                    Foco1.setText("Foco 1 Encendido");
                } else{
                    Foco1.setText("Foco 1 Apagado");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        referenceEntrada5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String D = snapshot.child("Valor").getValue().toString();

                if(D == "true") {
                    Foco2.setText("Foco 2 Encendido");
                } else{
                    Foco2.setText("Foco 2 Apagado");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        climab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<Object, Object> info = new HashMap<>();

                estado3 =! estado3;
                info.put("C1", estado3);
                referenceEntrada6.setValue(info);

            }
        });


        referenceEntrada7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String W = snapshot.child("C1").getValue().toString();

                if(W == "true") {
                    climat.setText("Clima 1 Encendido");
                } else{
                    climat.setText("Clima 1 Apagado");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}