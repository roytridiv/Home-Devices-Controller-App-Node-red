package com.example.mqttpublishnadsubscribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class KitchenCommandActivity extends AppCompatActivity {

    Button reset, on, off;
    MQTT mqtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_command);

        mqtt = new MQTT(KitchenCommandActivity.this, "kitchen_app_command");

        reset = findViewById(R.id.task1);
        on = findViewById(R.id.task2);
        off = findViewById(R.id.task3);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "R";
                String topic = "kitchen_app_command";

                mqtt.publishMessage(topic, s);
                Toast.makeText(KitchenCommandActivity.this, "System has Reset", Toast.LENGTH_SHORT).show();

            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mqtt.publishMessage("kitchen_app_command", "T");
                Toast.makeText(KitchenCommandActivity.this, "System has set to ON", Toast.LENGTH_SHORT).show();
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("kitchen_app_command", "O");
                Toast.makeText(KitchenCommandActivity.this, "System has set to OFF", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(KitchenCommandActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }



//    public void resetPressed(){
//
//        String s ="R" ;
//        String topic = "kitchen_app_command";
//
//        mqtt = new MQTT(KitchenCommandActivity.this,topic);
//        mqtt.publishMessage(topic,s);
//        Toast.makeText(KitchenCommandActivity.this , "System has Reset", Toast.LENGTH_SHORT).show();
//
//    }


}
