package com.example.zmns.baiusttransport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Login extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private StudentDatabase studentDb;
    private EditText vUserName, vPassword;
    private Spinner vSelectUser;
    private Button vLogin;
    private CheckBox vCheckbox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String PREF_NAME = "prefs";
    private static final String KEY_CHECKBOX = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        

        studentDb = new StudentDatabase(this);

        vUserName = (EditText) findViewById(R.id.idUserName);
        vPassword = (EditText) findViewById(R.id.idUserPassword);
        vLogin = (Button) findViewById(R.id.idLoginButton);
        vSelectUser = (Spinner) findViewById(R.id.idSelectUser);
        vCheckbox = (CheckBox) findViewById(R.id.idCheckBox) ;

        if(sharedPreferences.getBoolean(KEY_CHECKBOX, false))
            vCheckbox.setChecked(true);
        else
            vCheckbox.setChecked(false);

        vUserName.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        vPassword.setText(sharedPreferences.getString(KEY_PASS, ""));

        vLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoginValid(vSelectUser.getSelectedItem().toString(), vUserName.getText().toString(), vPassword.getText().toString());
            }
        });

        vCheckbox.setOnCheckedChangeListener(this);

    }


    private void isLoginValid(String selectUser, String userName, String userPassword){

        if(selectUser.equals("Admin")){

            String username = vUserName.getText().toString();
            String password = vPassword.getText().toString();
            String type = "login";

            AsyncTaskAdmin asyncTaskAdmin = new AsyncTaskAdmin(this);
            asyncTaskAdmin.execute(type, username, password);
            //String isLogin = backgroundWorker.doInBackground();

            //Toast.makeText(Login.this, isLogin, Toast.LENGTH_LONG).show();

        }

        else if(selectUser.equals("Student")){

            Cursor result = studentDb.studentLogin(userName);
            String password = null;

            while (result.moveToNext()){
                password = result.getString(2);
            }

            if( result.getCount() == 0 )
                Toast.makeText(Login.this, "Wrong ID", Toast.LENGTH_SHORT).show();

            else if (userPassword.equals(password)){

                Intent intent = new Intent(Login.this, Student.class);
                intent.putExtra("id", userName);
                startActivity(intent);

                Toast.makeText(Login.this, "Correct", Toast.LENGTH_SHORT).show();

            }

            else
                Toast.makeText(Login.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
        }

        else if(selectUser.equals("Checker")){

            if( (userName.equals("helper")) && (userPassword.equals("0000"))){

                Intent intent = new Intent(Login.this, Checker.class);
                startActivity(intent);

                Toast.makeText(Login.this, "Correct", Toast.LENGTH_LONG).show();

            }

            else
                Toast.makeText(Login.this, "Incorrect", Toast.LENGTH_LONG).show();

        }

    }


    public void onCheckedChanged (CompoundButton compoundButton, boolean b){
        managePrefs();
    }

    private void managePrefs() {
        if(vCheckbox.isChecked()){
            editor.putString(KEY_USERNAME, vUserName.getText().toString().trim());
            editor.putString(KEY_PASS, vPassword.getText().toString().trim());
            editor.putBoolean(KEY_CHECKBOX, true);
            editor.apply();
        }
        else
        {
            editor.putBoolean(KEY_CHECKBOX, false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAME);
            editor.apply();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
