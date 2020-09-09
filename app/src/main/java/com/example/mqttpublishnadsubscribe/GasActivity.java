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

public class GasActivity extends AppCompatActivity {

    TextView fetch_lpg , fetch_smoke , fetch_distance , fetch_kitchen;
    MQTT mqtt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        fetch_lpg = findViewById(R.id.lpg);
        fetch_smoke = findViewById(R.id.smoke);
        fetch_distance= findViewById(R.id.distance);
        fetch_kitchen= findViewById(R.id.kitchen);

        lpg();
        smoke();
        distance();
        kitchen();

    }

    public void lpg(){
        mqtt = new MQTT(GasActivity.this,"lpg");
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

                fetch_lpg.setText("LPG : "+data+" ppm");


            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }

    public void smoke(){
        mqtt = new MQTT(GasActivity.this,"smoke");
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

                fetch_smoke.setText("Smoke : "+data+" ppm");


            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }

    public void distance(){
        mqtt = new MQTT(GasActivity.this,"distance");
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

                fetch_distance.setText("Distance : "+data+" cm");


            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }

    public void kitchen(){
        mqtt = new MQTT(GasActivity.this,"kitchen_trigger");
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
                    fetch_kitchen.setText("Gas Stove ON");
                }else if(data.equals("0")){
                    fetch_kitchen.setText("Gas Stove OFF");
                }

               // fetch_kitchen.setText(data);


            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GasActivity.this , MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
