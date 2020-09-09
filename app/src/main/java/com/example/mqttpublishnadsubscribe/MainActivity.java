package com.example.mqttpublishnadsubscribe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

//    String topic ;
//    TextView t ;
//    EditText e ;
//    MQTT mqtt ;
//    Button s ;
//    String data , name , size , color;

    Button gas , water , kitchen , washroom , bedroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gas = findViewById(R.id.recieve_gas);
        water = findViewById(R.id.recieve_water);
        kitchen = findViewById(R.id.send_kitchen);
        washroom = findViewById(R.id.send_washroom);
        bedroom = findViewById(R.id.send_bedroom);

        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(internetOn()){
                    Intent intent = new Intent(MainActivity.this , GasActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Warning !")
                            .setMessage("Your internet connection is off , Please turn on mobile data or wifi to proceed.")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    internetOn();
                                }
                            })
                            .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }

            }
        });

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(internetOn()){
                    Intent intent = new Intent(MainActivity.this , WaterActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Warning !")
                            .setMessage("Your internet connection is off , Please turn on mobile data or wifi to proceed.")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    internetOn();
                                }
                            })
                            .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }



            }
        });

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(internetOn()){
                    Intent intent = new Intent(MainActivity.this , KitchenCommandActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Warning !")
                            .setMessage("Your internet connection is off , Please turn on mobile data or wifi to proceed.")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    internetOn();
                                }
                            })
                            .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }

            }
        });

        washroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(internetOn()){
                    Intent intent = new Intent(MainActivity.this , BathroonCommandActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Warning !")
                            .setMessage("Your internet connection is off , Please turn on mobile data or wifi to proceed.")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    internetOn();
                                }
                            })
                            .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }

            }
        });
        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(internetOn()){
                    Intent intent = new Intent(MainActivity.this , HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Warning !")
                            .setMessage("Your internet connection is off , Please turn on mobile data or wifi to proceed.")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    internetOn();
                                }
                            })
                            .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }

            }
        });


//
//
//        t = findViewById(R.id.output);
//        s = findViewById(R.id.send);
//        e = findViewById(R.id.msg);
//
//        topic = "HELLO";
//
//        mqtt = new MQTT(MainActivity.this,topic);
//        mqtt.setCallback(new MqttCallbackExtended() {
//            @Override
//            public void connectComplete(boolean b, String s) {
//            }
//
//            @Override
//            public void connectionLost(Throwable throwable) {
//            }
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
//
//                Log.d("test","this is the msg : "+ mqttMessage.toString());
//
//                data = mqttMessage.toString();
//
//                t.setText(data);
//
//                // JSONObject jsonObject = new JSONObject(data);
//
////                name = jsonObject.getString("fruit");
////                size = jsonObject.getString("size");
////                color = jsonObject.getString("color");
////
////                t.setText("Name : "+name+"\nSize : "+size+"\nColor : "+color);
//
//
//
//            }
//
//            @Override
//            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
//            }
//        });
//
//        s.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mqtt.publishMessage("tridiv_data_receive",e.getText().toString());
//                e.setText(" ");
//            }
//        });






    }


    private boolean internetOn(){
        boolean have_wifi = false ;
        boolean have_data = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for(NetworkInfo info:networkInfos){
            if (info.getTypeName().equalsIgnoreCase("WIFI")){
                if(info.isConnected()){
                    have_wifi=true;
                }

            }
            if (info.getTypeName().equalsIgnoreCase("MOBILE")){

                if (info.isConnected()){
                    have_data = true;
                }


            }
        }
        return have_wifi || have_data ;
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//       // unbindService(m_serviceConnection);
//        Toast.makeText(MainActivity.this, "Service Un-Binded", Toast.LENGTH_LONG).show();
//    };



}
