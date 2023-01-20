package com.example.estadia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    TextView temp, hum;

    //Button led1, led2;

    Button sig, sig2, reg, Extra;

    LottieAnimationView animationView, school;

    Boolean estado1 = false, estado2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference referenceEntrada1 = database.getReference("BOTONLED1");
        //DatabaseReference referenceEntrada2 = database.getReference("BOTONLED2");
        DatabaseReference referenceEntrada3 = database.getReference("LECTURAS");

        temp = findViewById(R.id.tem);

        hum = findViewById(R.id.hum);

        //led1 = findViewById(R.id.led1);

        //led2 = findViewById(R.id.led2);

        //animationView = findViewById(R.id.animationView);

        school = findViewById(R.id.school);


        sig = (Button)findViewById(R.id.sig);

        sig2 = (Button)findViewById(R.id.sig2);

        reg = (Button)findViewById(R.id.reg);

        Extra = (Button)findViewById(R.id.Extra);




        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Info.class);
                startActivity(i);
                school.playAnimation();


            }

            });




        sig2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent a = new Intent(MainActivity.this, Focos.class);
                startActivity(a);
                school.playAnimation();

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent b = new Intent(MainActivity.this, Regadero.class);
                startActivity(b);
                school.playAnimation();

            }
        });

        Extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent b = new Intent(MainActivity.this, Extra.class);
                startActivity(b);
                school.playAnimation();

            }
        });

/*
        referenceEntrada1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String estadoLed1 = snapshot.child("Valor").getValue().toString();
                if (estadoLed1.equals("true")){

                    led1.setBackground(getResources().getDrawable(R.drawable.botonpulsado));

                }else{

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

*/
/*
    led1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            HashMap<Object, Object> info = new HashMap<>();

            estado1 =! estado1;
            info.put("Valor", estado1);
            referenceEntrada1.setValue(info);

        }
    });
*/

        /*

        led2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<Object, Object> info = new HashMap<>();

                estado2 =! estado2;
                info.put("Valor", estado2);
                referenceEntrada2.setValue(info);

            }
        });

         */

        /*
        referenceEntrada3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String T = snapshot.child("temp").getValue().toString();
                temp.setText(T);

                String H = snapshot.child("hum").getValue().toString();
                hum.setText(H);

                animationView.playAnimation();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/
        school.playAnimation();


    }

}