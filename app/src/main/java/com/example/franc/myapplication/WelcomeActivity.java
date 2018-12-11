package com.example.franc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

        String mail;

        public void mailSend(){

            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"deiv-93@hotmail.it"});
            emailIntent.putExtra(Intent.EXTRA_TEXT, "body text");
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        EditText mail_passata = findViewById(R.id.mail_passata);

        mail = getIntent().getStringExtra("mailPassa");

        mail_passata.setText(mail);

        mail_passata.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mail_passata){
            mailSend();
        }

    }
}
