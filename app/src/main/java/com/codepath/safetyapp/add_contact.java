package com.codepath.safetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class add_contact extends AppCompatActivity {

    String name = " ", email = " ";
    TextView t1;

    EditText nameInput;
    EditText emailInput;

    Button submit, backAddc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        nameInput = findViewById(R.id.enterName);
        emailInput = findViewById(R.id.enterEmail);

        t1 = findViewById(R.id.textEnter);

        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                email = emailInput.getText().toString();

                    t1.setText("Prints name and email if added successfully\n" + name + "\n" + email + "\n");
                    t1.setVisibility(View.VISIBLE);
            }
        });

        backAddc = findViewById(R.id.back_add_contact);
        backAddc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(add_contact.this, ContactsPage.class));
            }
        });
    }

}
