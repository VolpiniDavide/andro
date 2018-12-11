package com.example.franc.myapplication;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements OnClickListener{   // TODO implementare textWatcher

    private static final String TAG = "RegisterActivity";

    EditText emailET;
    EditText passwordET;
    EditText numberET;

    Button registerBtn;

    private int flag = 0 ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);
        numberET = findViewById(R.id.number_et);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(this);



        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if ( isValidEmail() && isValidNumber() && isValidPassword())
                registerBtn.setEnabled(true);
                else registerBtn.setEnabled(false);
            }
        } );
        numberET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if ( isValidEmail() && isValidNumber() && isValidPassword())
                    registerBtn.setEnabled(true);
                else registerBtn.setEnabled(false);
            }
        } );
        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if ( isValidEmail() && isValidNumber() && isValidPassword())
                    registerBtn.setEnabled(true);
                else registerBtn.setEnabled(false);
            }
        } );


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


    private boolean isValidEmail() {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailET.getText()).matches();
    }

    private boolean isValidPassword() {
        String password = passwordET.getText().toString();
        return (password.length() > 6);
    }

    private boolean isValidNumber() {

        return (numberET.getText().toString().length() == 10);
    }

    public void tornaLog(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void showErrorMessage(String op) {

        Context context = getApplicationContext();
        CharSequence text = op + " error!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void showSuccessMessage(String op) {
        Context context = getApplicationContext();
        CharSequence text = op + " successful!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }





    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register_btn) {
            if (isValidEmail()) {
                if (isValidPassword()) {
                    if (isValidNumber()) {
                        showSuccessMessage("registration");
                        tornaLog(this.registerBtn);
                        Utente.aggiungiUtente(new Utente(this.toString(),this.toString(),this.toString()));
                    } else showErrorMessage("invalid number");
                } else if (!isValidPassword()) {
                    if (isValidNumber()) showErrorMessage("invalid password");
                    else showErrorMessage("invalid password and number");
                } else if (!isValidEmail()) {
                    if (isValidPassword()) {
                        if (isValidNumber())
                            showErrorMessage("mail");
                        else showErrorMessage("invalid mail and number");
                    } else if (!isValidPassword()) {
                        if (isValidNumber()) showErrorMessage("invalid password and mail");
                        else showErrorMessage("invalid password, mail and number");
                    }
                }

            }


        }
    }


}
