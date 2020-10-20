package com.example.zmns.baiusttransport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {


    StudentDatabase studentDatabase;
    private EditText vChangePasswordUserName, vChangePasswordUserPassword1, vChangePasswordUserPassword2;
    private Button vChangePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        studentDatabase = new StudentDatabase(this);

        vChangePasswordUserName = (EditText) findViewById(R.id.idChangePasswordUserName);
        vChangePasswordUserPassword1 = (EditText) findViewById(R.id.idChangePasswordUserPassword1);
        vChangePasswordUserPassword2 = (EditText) findViewById(R.id.idChangePasswordUserPassword2);
        vChangePasswordButton = (Button) findViewById(R.id.idChangePasswordButton);

        changePassword();

    }

    private void changePassword() {

        vChangePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vChangePasswordUserName.getText().toString().length() != 7)
                    Toast.makeText(ChangePassword.this, "Wrong ID", Toast.LENGTH_SHORT).show();

                else if (vChangePasswordUserPassword1.getText().toString().equals(vChangePasswordUserPassword2.getText().toString())) {

                    if (vChangePasswordUserPassword1.getText().toString().length() >= 4) {
                        Integer cp = studentDatabase.changePassword(vChangePasswordUserName.getText().toString(), vChangePasswordUserPassword1.getText().toString());
                        Toast.makeText(ChangePassword.this, "Password Changed", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(ChangePassword.this, "Too Short Password", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(ChangePassword.this, "Password Not Matched", Toast.LENGTH_SHORT).show();
            }

        });

    }
}

