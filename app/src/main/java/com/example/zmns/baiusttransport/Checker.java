package com.example.zmns.baiusttransport;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Checker extends AppCompatActivity {

    StudentDatabase studentDatabase;
    private EditText vUserNameChecker;
    private Button vCheckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);


        studentDatabase = new StudentDatabase(this);

        vUserNameChecker = (EditText) findViewById(R.id.idUserNameChecker);
        vCheckButton = (Button) findViewById(R.id.idLCheckButton);


        checker();


    }

    public void checker() {

        vCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor re = studentDatabase.checker(vUserNameChecker.getText().toString());

                if (re.getCount() == 0)
                    Toast.makeText(Checker.this, "Your Are Not Registered", Toast.LENGTH_SHORT).show();

                else {

                    StringBuffer buffer = new StringBuffer();

                    while (re.moveToNext()) {

                        buffer.append("ID          : " + re.getString(0) + "\n");
                        buffer.append("Name     : " + re.getString(1) + "\n");
                        buffer.append("Dept     : " + re.getString(3) + "\n");
                        buffer.append("Level    : " + re.getString(4) + "\n");
                        buffer.append("Term     : " + re.getString(5) + "\n");
                        buffer.append("Type     : " + re.getString(6) + "\n");
                        buffer.append("Status    : " + re.getString(7) + "\n\n\n");

                    }

                    viewSchedule("Student Info", buffer.toString());

                }

            }
        });
    }

    public void viewSchedule(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

}
