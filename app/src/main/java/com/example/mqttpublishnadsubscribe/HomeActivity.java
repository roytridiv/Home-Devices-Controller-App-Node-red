package com.example.mqttpublishnadsubscribe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class HomeActivity extends AppCompatActivity {

    Button set_light1_on, set_light2_on , set_light3_on , set_light4_on ,set_light1_off , set_light2_off , set_light3_off , set_light4_off , set_lights_on , set_lights_off , reset_lights;
    MQTT mqtt;

    TextView light1_feedback_status , light2_feedback_status , light3_feedback_status , light4_feedback_status ;
    String st = "";

    boolean light_1_flag =  false;
    boolean light_2_flag =  false;
    boolean light_3_flag =  false;
    boolean light_4_flag =  false;
    boolean light_on_flag =  false;
    boolean light_off_flag =  false;

    String l1= "", l2= "" , l3= "" , l4 = "" ;

    boolean s =false;

    boolean b = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mqtt = new MQTT(HomeActivity.this, "bedroom_app_command");
        set_light1_on = findViewById(R.id.light1_on);
        set_light2_on = findViewById(R.id.light2_on);
        set_light3_on = findViewById(R.id.light3_on);
        set_light4_on = findViewById(R.id.light4_on);
        set_lights_on = findViewById(R.id.lights_on);

        set_light1_off = findViewById(R.id.light1_off);
        set_light2_off = findViewById(R.id.light2_off);
        set_light3_off = findViewById(R.id.light3_off);
        set_light4_off = findViewById(R.id.light4_off);
        set_lights_off = findViewById(R.id.lights_off);

        reset_lights = findViewById(R.id.lights_reset);

        light1_feedback_status = findViewById(R.id.light1_status);
        light2_feedback_status = findViewById(R.id.light2_status);
        light3_feedback_status = findViewById(R.id.light3_status);
        light4_feedback_status = findViewById(R.id.light4_status);


        Light1();
        Light2();
        Light3();
        Light4();






        set_light1_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","W");
                Toast.makeText(HomeActivity.this , "Light 1 is ON", Toast.LENGTH_SHORT).show();


            }
        });

        set_light2_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","X");
                Toast.makeText(HomeActivity.this , "Light 2 is ON", Toast.LENGTH_SHORT).show();



            }
        });

        set_light3_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","Y");
                Toast.makeText(HomeActivity.this , "Light 3 is ON", Toast.LENGTH_SHORT).show();



            }
        });
        set_light4_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","Z");
                Toast.makeText(HomeActivity.this , "Light 4 is ON", Toast.LENGTH_SHORT).show();


            }
        });




        set_light1_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","w");
                Toast.makeText(HomeActivity.this , "Light 1 is OFF", Toast.LENGTH_SHORT).show();



            }
        });

        set_light2_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","x");
                Toast.makeText(HomeActivity.this , "Light 2 is OFF", Toast.LENGTH_SHORT).show();

            }
        });

        set_light3_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","y");
                Toast.makeText(HomeActivity.this , "Light 3 is OFF", Toast.LENGTH_SHORT).show();

            }
        });

        set_light4_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","z");
                Toast.makeText(HomeActivity.this , "Light 4 is OFF", Toast.LENGTH_SHORT).show();

            }

        });
        set_lights_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","T");
                Toast.makeText(HomeActivity.this , "All lights are ON", Toast.LENGTH_SHORT).show();

            }
        });

        set_lights_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","O");
                Toast.makeText(HomeActivity.this , "All lights are OFF", Toast.LENGTH_SHORT).show();

            }
        });

        reset_lights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqtt.publishMessage("bedroom_app_command","R");
                Toast.makeText(HomeActivity.this , "All lights are RESET", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void Light1(){
        mqtt = new MQTT(HomeActivity.this,"light1");
        mqtt.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
            }

            @Override
            public void connectionLost(Throwable throwable) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

                Log.d("test","this is the msg : "+ mqttMessage.toString());

                String data = mqttMessage.toString();

                if(data.equals("1")){
                    light1_feedback_status.setText("Light 1 : ON");

                }else{
                    light1_feedback_status.setText("Light 1 : OFF");
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }
    public void Light2(){
        mqtt = new MQTT(HomeActivity.this,"light2");
        mqtt.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
            }

            @Override
            public void connectionLost(Throwable throwable) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

                Log.d("test","this is the msg : "+ mqttMessage.toString());

                String data = mqttMessage.toString();

                if(data.equals("1")){
                    light2_feedback_status.setText("Light 2 : ON");

                }else{
                    light2_feedback_status.setText("Light 2 : OFF");
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }
    public void Light3(){
        mqtt = new MQTT(HomeActivity.this,"light3");
        mqtt.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
            }

            @Override
            public void connectionLost(Throwable throwable) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

                Log.d("test","this is the msg : "+ mqttMessage.toString());

                String data = mqttMessage.toString();

                if(data.equals("1")){
                    light3_feedback_status.setText("Light 3 : ON");

                }else{
                    light3_feedback_status.setText("Light 3 : OFF");
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }
    public void Light4(){
        mqtt = new MQTT(HomeActivity.this,"light4");
        mqtt.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
            }

            @Override
            public void connectionLost(Throwable throwable) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

                Log.d("test","this is the msg : "+ mqttMessage.toString());

                String data = mqttMessage.toString();

                if(data.equals("1")){
                    light4_feedback_status.setText("Light 4 : ON");

                }else{
                    light4_feedback_status.setText("Light 4 : OFF");
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }






    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}
