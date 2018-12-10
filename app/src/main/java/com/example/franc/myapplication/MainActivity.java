package com.example.franc.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = "MainActivity";

    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;


    private boolean isValidEmail(){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailET.getText()).matches();
    }

    private boolean isValidPassword(){
        String password = passwordET.getText().toString();
        return (password.length() > 6);
    }

    private void showErrorMessage( String op){

        Context context = getApplicationContext();
        CharSequence text = op+" error!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void showSuccessMessage(String op){
        Context context = getApplicationContext();
        CharSequence text = op+" successful!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.email_et);         // col find view by id posso trovare un elemento del xml cui ho assegnato l'id specificato.
        passwordET = findViewById(R.id.password_et);   // questo viene ritrvato nel file R sotto l'attributo "id". viee così tornato un qualsiasi
        loginBtn = findViewById(R.id.login_btn);       // ogetto sottoclasse di view, n questo caso editText
        registerBtn = findViewById(R.id.reg_btn);


        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        emailET.getText();
        passwordET.getText();



        Log.i(TAG, "activity created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "activity started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "activity resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "activity paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "activity stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "activity destroyed");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.reg_btn) {
            if (isValidEmail()) {
                if (isValidPassword())
                    showSuccessMessage("login");
                else showErrorMessage("invalid password");
            } else if (!isValidEmail()) {
                if (isValidPassword()) showErrorMessage("invalid email");
                else showErrorMessage("invalid email and password");
            }
        } else if (view.getId() == R.id.login_btn) {
            if (!isValidEmail() || !isValidPassword())
                showErrorMessage("registration");
            else showSuccessMessage("registration");
        }
    }

    }