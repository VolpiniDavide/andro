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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, OnQuantityChanged {

    String mail;
    String faimail;
    TextView tot;
    float total;
    Button buy_btn;
    int totForBar;


    public void mailSend() {

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
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        EditText mail_passata = findViewById(R.id.mail_passata);
        buy_btn = findViewById(R.id.buy_btn);
        tot = findViewById(R.id.tot);
        progressBar = findViewById(R.id.progress_bar);


        progressBar.setMax(5);
        progressBar.setProgress(0);

        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));

        getProducts();




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




        adapter = new FoodListAdapter(this, foodList);
        adapter.setOnQuantityChange(this);


        recyclerView = findViewById(R.id.myrecyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);






    }

    ArrayList<Food> foodList = new ArrayList<>();

        /*foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));
        foodList.add(new Food("bread", "1", "0"));
        foodList.add(new Food("milk", "2", "0"));
        foodList.add(new Food("meat", "3.50", "0"));*/


    private void getProducts(){

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="https://5ba19290ee710f0014dd764c.mockapi.io/Food";

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Success",response);

                            try {
                                JSONObject responseJSON = new JSONObject(response);
                                JSONArray jsonArray = responseJSON.getJSONArray("foods");
                                for(int i = 0; i<jsonArray.length(); i++){
                                        foodList.add(new Food(jsonArray.getJSONObject(i)));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // Display the first 500 characters of the response string.

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error", error.getMessage());
                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);
        }



    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.mail_passata) {
            mailSend();
        }
    }

    @Override
    public void onItemAdded(float price) {

        Float newQuantity = (Float.parseFloat(tot.getText().toString()));
        newQuantity += price;
        tot.setText(String.valueOf(newQuantity));
        totForBar += price;
        progressBar.setProgress(totForBar);
        if ( newQuantity >= 5)
            buy_btn.setEnabled(true);
    }

    @Override
    public void onItemRemoved(float price) {


        Float newQuantity = (Float.parseFloat(tot.getText().toString()));
        if (newQuantity > 0.99) {
            newQuantity -= price;
            tot.setText(String.valueOf(newQuantity));
            totForBar -= price;
            progressBar.setProgress(totForBar);
            if ( newQuantity < 5)
                buy_btn.setEnabled(false);
        }
    }
}
