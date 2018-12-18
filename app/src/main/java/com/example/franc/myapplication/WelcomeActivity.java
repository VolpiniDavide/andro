package com.example.franc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

        String mail;
        String faimail;
        static TextView tot;



    public void mailSend(){

            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"deiv-93@hotmail.it"});
            emailIntent.putExtra(Intent.EXTRA_TEXT, "body text");
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }


            List<View> righe = new ArrayList<>();

            RecyclerView recyclerView;
            LinearLayoutManager layoutManager;
            FoodListAdapter adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        EditText mail_passata = findViewById(R.id.mail_passata);
        Button buy_btn = findViewById(R.id.buy_btn);
        tot = findViewById(R.id.tot);


        mail = getIntent().getStringExtra("mailPassa");
        mail_passata.setOnClickListener(this);


        tot.setText("0");


        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (intent.getData() != null)
        faimail = Uri.decode(intent.getData().toString().substring(7));

        mail_passata.setText(faimail);


        layoutManager = new LinearLayoutManager(this);

        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(new Food( "bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food( "bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food( "bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food( "bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));


        adapter = new FoodListAdapter(this, foodList);


        recyclerView = findViewById(R.id.myrecyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        }





    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.mail_passata){
            mailSend();
        }
      }

    }




