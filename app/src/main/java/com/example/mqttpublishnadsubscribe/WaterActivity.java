package com.example.mqttpublishnadsubscribe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class WaterActivity extends AppCompatActivity {
    TextView fetch_water_tap, fetch_bucket , fetch_water;
    MQTT mqtt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        fetch_water_tap = findViewById(R.id.water_tap);
        fetch_bucket= findViewById(R.id.bucket);
        fetch_water= findViewById(R.id.water);


        water_tap();
        bucket();
        water();

    }


    public void water_tap(){
        mqtt = new MQTT(WaterActivity.this,"water_tap_trigger");
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
                    fetch_water_tap.setText("Water tap ON");
                }else{
                    fetch_water_tap.setText("Water tap OFF");
                }


                //fetch_water_tap.setText(data);


            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }

    public void bucket(){
        mqtt = new MQTT(WaterActivity.this,"bucket");
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
                if(data.equals("0")){
                    fetch_bucket.setText("There is bucket");
                }else{
                    fetch_bucket.setText("There is no bucket");
                }




            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }

    public void water(){
        mqtt = new MQTT(WaterActivity.this,"water");
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

                fetch_water.setText("Level of water : "+data+ " cm");


            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WaterActivity.this , MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    }

