package com.example.franc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import static com.example.franc.myapplication.R.color.dark;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = "MainActivity";


    EditText emailET;
    EditText passwordET;

    Button loginBtn;
    Button registerBtn;
    Switch switchPul;
    LinearLayout mainCont;
    //Utente ute;
    public boolean luce;

    SharedPreferences prefs;



    private boolean isValidEmail(){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailET.getText()).matches();
    }

    private boolean isValidPassword(){
        String password = passwordET.getText().toString();
        return (password.length() > 6);
    }

    private void showErrorMessage( String op){

        Context context = this;
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

    public void faiRegist(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void welcome(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("mailPassa", emailET.getText().toString());
        startActivity(intent);
    }


    public void cambiaCol(){
        if ( switchPul.isChecked()){
            mainCont.setBackgroundColor(getResources().getColor(R.color.dark));
            emailET.setTextColor(getResources().getColor(R.color.display));
            emailET.setHintTextColor(getResources().getColor(R.color.display));
            passwordET.setTextColor(getResources().getColor(R.color.display));
            passwordET.setHintTextColor(getResources().getColor(R.color.display));
            switchPul.setBackgroundColor(getResources().getColor(R.color.dark));
        } else if ( !switchPul.isChecked() ){
            mainCont.setBackgroundColor(getResources().getColor(R.color.display));
            emailET.setTextColor(getResources().getColor(R.color.dark));
            emailET.setHintTextColor(getResources().getColor(R.color.dark));
            passwordET.setTextColor(getResources().getColor(R.color.dark));
            passwordET.setHintTextColor(getResources().getColor(R.color.dark));
            switchPul.setBackgroundColor(getResources().getColor(R.color.display));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.email_et);         // col find view by id posso trovare un elemento del xml cui ho assegnato l'id specificato.
        passwordET = findViewById(R.id.password_et);   // questo viene ritrvato nel file R sotto l'attributo "id". viee così tornato un qualsiasi
        loginBtn = findViewById(R.id.login_btn);       // ogetto sottoclasse di view, n questo caso editText
        registerBtn = findViewById(R.id.reg_btn);
        switchPul = findViewById(R.id.simpleSwitch);
        mainCont = findViewById(R.id.mainCont);

        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        switchPul.setOnClickListener(this);

        emailET.getText();
        passwordET.getText();


        prefs = getPreferences(Context.MODE_PRIVATE);

        switchPul.setChecked( prefs.getBoolean("switchPul", false) );
        cambiaCol();


        Log.i(TAG, "activity created");




        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if ( isValidEmail() && isValidPassword())
                    loginBtn.setEnabled(true);
                else loginBtn.setEnabled(false);
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
                if ( isValidEmail() && isValidPassword())
                    loginBtn.setEnabled(true);
                else loginBtn.setEnabled(false);
            }
        } );

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
        if (view.getId() == R.id.login_btn) {
            if (!isValidEmail() || !isValidPassword())
                showErrorMessage("login");
            else showSuccessMessage("login");
            welcome(this.loginBtn);
            }

        else if (view.getId() == R.id.reg_btn){
            faiRegist(this.registerBtn);
        }

        else if (view.getId() == R.id.simpleSwitch) {
            if (!luce) {
                prefs.edit().putBoolean("switchPul", switchPul.isChecked()).apply();
                luce=true;
                cambiaCol();
            } else {
                prefs.edit().putBoolean("switchPul", switchPul.isChecked()).apply();
                luce=false;
                cambiaCol();
            }

        }

    }

    }