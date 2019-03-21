package com.codepath.safetyapp;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
//import java.util.List;

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
    Button btnStartRecord,btnStopRecord, btnPlayRecording, btnStopPlaying;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer ;
    final int REQUEST_PERMISSION_CODE = 1000;

    //public static final int RequestPermissionCode = 1;
    //private ListView obj;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllContacts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);


        //public static final int RequestPermissionCode = 1;
        ListView obj = (ListView) findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new OnItemClickListener(){
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

        //-------------------EXPERIMENTING WITH AUDIO FILES-------------------------------
        if (!checkPermissionFromDevice())
            requestPermissions();

        //inital view
        btnStartRecord =  findViewById(R.id.btnStartRecord);
        btnStopRecord =  findViewById(R.id.btnStopRecord);
        btnPlayRecording =  findViewById(R.id.btnPlayRecording);
        btnStopPlaying = findViewById(R.id.btnStopPlaying);


        //need to request Run-time permission from Android M

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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    btnPlayRecording.setEnabled(false);
                    btnStopPlaying.setEnabled(false);

                    Toast.makeText(MainActivity.this, "Recording...", Toast.LENGTH_LONG).show();
                } else {
                    requestPermissions();
                }//end else
            }
        });

        btnStopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                btnStopRecord.setEnabled(false);
                btnPlayRecording.setEnabled(true);
                btnStartRecord.setEnabled(true);
                btnStopPlaying.setEnabled(false);
            }
        });
        btnPlayRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStopPlaying.setEnabled(true);
                btnStopRecord.setEnabled(false);
                btnStartRecord.setEnabled(false);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(pathSave);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                Toast.makeText(MainActivity.this, "Playing...", Toast.LENGTH_LONG).show();
            }
        });


        btnStopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStopRecord.setEnabled(false);
                btnStartRecord.setEnabled(true);
                btnStopPlaying.setEnabled(false);
                btnPlayRecording.setEnabled(true);

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    setupMediaRecorder();

                }
            }
        });
    }



    //----------------EXPERIMENTING WITH AUDIO FILES "CHECKPERMISSION CLASS"
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
        },REQUEST_PERMISSION_CODE);
    }//end requestPermission

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int [] grantResults){
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
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
    //------------------------------------------------------------------------------


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

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllContacts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);

        obj = (ListView) findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), ContactsPage.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });


        Button ContactBtn = findViewById(R.id.Contacts_page);
        Button btn1 = findViewById(R.id.button1);
        textLOL = findViewById(R.id.textView3);

        textLOL.setVisibility(View.INVISIBLE);

        ContactBtn.setOnClickListener(   new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, ContactsPage.class));
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( textLOL.getVisibility() == View.VISIBLE ){
                    textLOL.setVisibility(View.INVISIBLE);
                }
                else {
                    textLOL.setVisibility(View.VISIBLE);
                }
            }
        });


        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    */

}
