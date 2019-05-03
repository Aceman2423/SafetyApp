package com.codepath.safetyapp;

import android.Manifest;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.*;

import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.KeyEvent;
import android.view.Menu;

import android.media.MediaPlayer;
import android.media.MediaRecorder;

import android.os.Environment;


import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
//import java.util.Random;
import java.util.UUID;

//import static android.Manifest.permission.RECORD_AUDIO;
//import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import android.widget.TextView;

import android.content.Intent;
//import android.content.Context;
//import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    //Experimenting with buttons for recording
    Button btnStartRecord, btnPlayRecording, btnStopPlaying;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    private Button button3;
    private boolean onceOver = true;

    //timer variables
    long maxTime = 5000;   //time in milliseconds for our delay

    //gps variables
    private Button button, button4;
    private TextView textView;
    private LocationManager locationManager;
    private LocationListener listener;
    public String sendableLocation;

    final int REQUEST_PERMISSION_CODE = 1000;

    //public static final int RequestPermissionCode = 1;
    private ListView obj;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-----------------------DataBase Code------------------------------
        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllContacts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);

/*
        //public static final int RequestPermissionCode = 1;
        ListView obj = (ListView) findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), ContactsPage.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
*/
        //-------------------EXPERIMENTING WITH AUDIO FILES-------------------------------
        if (!checkPermissionFromDevice())
            requestPermissions();

        //inital view
        btnStartRecord = (Button) findViewById(R.id.btnStartRecord);
        //btnStopRecord = (Button) findViewById(R.id.btnStopRecord);
        btnPlayRecording = (Button) findViewById(R.id.btnPlayRecording);
        //btnStopPlaying = (Button) findViewById(R.id.btnStopPlaying);

//        btnStopRecord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaRecorder.stop();
//                btnStopRecord.setEnabled(false);
//                btnPlayRecording.setEnabled(true);
//                btnStartRecord.setEnabled(true);
//                btnStopPlaying.setEnabled(false);
//            }
//        });

        btnPlayRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                btnStopPlaying.setEnabled(true);
//                btnStopRecord.setEnabled(false);
//                btnStartRecord.setEnabled(false);
//                btnPlayRecording.setEnabled(false);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(pathSave);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //this is performed after the delay
                        if (mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                            setupMediaRecorder();
                        }//end if
                    }
                }, maxTime);   //maxTime = our delay time

                Toast.makeText(MainActivity.this, "Playing...", Toast.LENGTH_LONG).show();
            }
        });


//        btnStopPlaying.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                btnStopRecord.setEnabled(false);
////                btnStartRecord.setEnabled(true);
////                btnStopPlaying.setEnabled(false);
////                btnPlayRecording.setEnabled(true);
//
//                if (mediaPlayer != null) {
//                    mediaPlayer.stop();
//                    mediaPlayer.release();
//                    mediaPlayer = null;
//                    setupMediaRecorder();
//
//                }
//            }
//        });

        //-----------------------------GPS functionality------------------------------

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if ( onceOver ) {
                    double latitude;
                    double longitude;
                    char NorthOrSouth;
                    char EastOrWest;
                    if (location.getLatitude() > 0) {
                        latitude = Math.abs(location.getLatitude());
                        NorthOrSouth = 'n';
                    } else {
                        latitude = Math.abs(location.getLatitude());
                        NorthOrSouth = 's';
                    }

                    if (location.getLongitude() > 0) {
                        longitude = Math.abs(location.getLongitude());
                        EastOrWest = 'e';
                    } else {
                        longitude = Math.abs(location.getLongitude());
                        EastOrWest = 'w';
                    }
                    //this txt view if for debugging purposes
                    textView.append("   http://maps.google.com/?q=" + latitude + NorthOrSouth + "," + longitude + EastOrWest);
                    onceOver = false;
                    sendableLocation = "   http://maps.google.com/?q=" + latitude + NorthOrSouth + "," + longitude + EastOrWest;
                }
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }
            @Override
            public void onProviderEnabled(String s) {

            }
            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        configure_button();


    }//end OnCreate


    //-----------------------EXPERIMENTING WITH AUDIO FILES---------------------------
    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }//setupMediaRecorder

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }//end requestPermission

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }//end case
            break;
        }//end swithch
    }//end onRequestPermissionResulte

    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;
    }//ench checkPermissionFrom Device

    //-------------------------------DataBase Code----------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(), ContactsPage.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }


    private TextView mTextMessage;
    TextView textLOL;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    //-----------------------------GPS methods--------------------------------
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        btnStartRecord.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (checkPermissionFromDevice()) {

                    pathSave = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                            + UUID.randomUUID().toString() + "_audio_record.3gp";
                    setupMediaRecorder();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();

                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        locationManager.requestLocationUpdates("gps", 1000, 0, listener);
                    } catch (SecurityException ex){

                    }//end catch
                    //btnPlayRecording.setEnabled(true);
                    //btnStopPlaying.setEnabled(true);
                    Toast.makeText(MainActivity.this, "Recording...", Toast.LENGTH_LONG).show();

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            //this is performed after the delay
                            mediaRecorder.stop();
                        }
                    }, maxTime);   //maxTime = our delay time

                } else {
                    requestPermissions();
                }//end else
            }//end on click
        });
    }


}
