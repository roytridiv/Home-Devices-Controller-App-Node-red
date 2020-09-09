package com.example.mqttpublishnadsubscribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BathroonCommandActivity extends AppCompatActivity {
    Button reset , on , off ;
    MQTT mqtt ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroon_command);

        mqtt = new MQTT(BathroonCommandActivity.this, "bathroom_app_command");

        reset = findViewById(R.id.task4);
        on = findViewById(R.id.task5);
        off =findViewById(R.id.task6);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mqtt.publishMessage("bathroom_app_command","R");
                Toast.makeText(BathroonCommandActivity.this , "System has Reset", Toast.LENGTH_SHORT).show();
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mqtt.publishMessage("bathroom_app_command","T");
                Toast.makeText(BathroonCommandActivity.this , "System has set to ON", Toast.LENGTH_SHORT).show();
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bathroom_app_command","O");
                Toast.makeText(BathroonCommandActivity.this , "System has set to OFF", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BathroonCommandActivity.this , MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
