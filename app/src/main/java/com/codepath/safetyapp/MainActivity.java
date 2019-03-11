package com.codepath.safetyapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    TextView textLOL;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ContactBtn = findViewById(R.id.button_contacts_page);
        Button btn1 = findViewById(R.id.button);
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

}
