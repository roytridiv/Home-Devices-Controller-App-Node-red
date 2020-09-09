package com.example.mqttpublishnadsubscribe;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

public class MQTT {
    private String TAG = "test";

    private MqttAndroidClient mqttAndroidClient;

    private final String serverUri = "tcp://45.248.149.235:1883";


    //generating random number within 9999
    private Random rand = new Random();
    private int n = rand.nextInt(9999);

    private String subscriptionTopic = "toandro";

    MQTT(Context context, String topicName){
        String clientId = "MQTT_ANDROID_CLIENT" + n;
        subscriptionTopic = topicName;
        mqttAndroidClient = new MqttAndroidClient(context, serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.w(TAG, s);
            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w(TAG, mqttMessage.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
        connect();
    }

    void setCallback(MqttCallbackExtended callback) {
        mqttAndroidClient.setCallback(callback);
    }

    private void connect(){
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);
        String username = "test_pass";
        mqttConnectOptions.setUserName(username);
        String password = "password";
        mqttConnectOptions.setPassword(password.toCharArray());

        try {

            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                    subscribeToTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.w(TAG, "Failed to connect to: " + serverUri + exception.toString());
                }
            });


        } catch (MqttException ex){
            ex.printStackTrace();
        }
    }


    private void subscribeToTopic() {
        try {
            mqttAndroidClient.subscribe(subscriptionTopic, 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.w(TAG,"Subscribed!");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.w(TAG, "Subscribed fail!");
                }
            });

        } catch (MqttException ex) {
            System.err.println("Exceptions subscribing");
            ex.printStackTrace();
        }
    }

    void unSubscribe(String topicName){
        try {
            mqttAndroidClient.unsubscribe(topicName);
            Log.w(TAG,"Unsubscribed!");
        } catch (MqttException ex) {
            System.err.println("Exceptions unsubscribe");
            ex.printStackTrace();
        }
    }

    void publishMessage(String topicName, String Message){
        MqttMessage message = new MqttMessage(Message.getBytes());
        message.setQos(2);
        message.setRetained(false);
        try {
            mqttAndroidClient.publish(topicName,message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unbindService(m_serviceConnection);
//        Toast.makeText(MainActivity.this, "Service Un-Binded", Toast.LENGTH_LONG).show();
//    };
}
