package com.example.estadia;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Stack;

public class Info extends AppCompatActivity {

    TextView temp, hum, Alarma;

    Button led1, led2, B10;

    Button B11;

    Boolean estado3 = false;

    Switch S1;

    Boolean estado5 = false;


    private static final String CHANNEL_ID = "canal";

    private PendingIntent pendingIntent;

    //Boolean estado1 = false, estado2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referenceEntrada1 = database.getReference("BOTONLED1");
        DatabaseReference referenceEntrada2 = database.getReference("BOTONLED2");
        DatabaseReference referenceEntrada3 = database.getReference("LECTURAS");

        DatabaseReference referenceEntrada10 = database.getReference("ALARMA");

        DatabaseReference referenceEntrada11 = database.getReference("ESTADO");

        DatabaseReference referenceEntrada12 = database.getReference("ESTADO");

        DatabaseReference referenceEntrada13 = database.getReference("ESTADO");



        temp = findViewById(R.id.tem);

        hum = findViewById(R.id.hum);

        led1 = findViewById(R.id.led1);

        led2 = findViewById(R.id.led2);

        Alarma = findViewById(R.id.Alarma);


        B10 = findViewById(R.id.B10);


        B11 = findViewById(R.id.B11);

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
        referenceEntrada3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String T = snapshot.child("temp").getValue().toString();
                temp.setText(T);






               // String H = snapshot.child("hum").getValue().toString();
               // hum.setText(H);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        B10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<Object, Object> info = new HashMap<>();
                estado5 =! estado5;
                info.put("Estado", estado5);
                referenceEntrada11.setValue(info);


            }
        });


        referenceEntrada12.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String F = snapshot.child("Estado").getValue().toString();

                if(F == "true") {

                    B10.setText("Sensores encendidos");






                } else{
                    B10.setText("Sensores apagados");

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        referenceEntrada10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String U = snapshot.child("Distancia").getValue().toString();


                String n = " \"true\" " ;

                if (U == "true") {

                    //Alarma.setText("funciona");

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        ShowNotifications();
                    } else {
                        ShowNewNotifications();
                    }



                } else {
                    //Alarma.setText("no funciona");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        B11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                    ShowNotifications();

                }else{
                    ShowNewNotifications();
                }

            }

        });




}

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ShowNotifications() {

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "NEW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.createNotificationChannel(channel);
        ShowNewNotifications();

    }

    private void ShowNewNotifications() {
     setPendingIntent(Info.class);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
        CHANNEL_ID)
            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal)
            .setContentTitle("-----ALERTA-----")
            .setContentText("La alarma detecto movimiento")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(1, builder.build());

    }

    public void setPendingIntent(Class<Info> infoClass) {

        Intent intent = new Intent(this, infoClass);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(infoClass);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, pendingIntent.FLAG_UPDATE_CURRENT);

    }



}



