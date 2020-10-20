package com.example.zmns.baiusttransport;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Student extends AppCompatActivity {

    ScheduleDatabase scheduleDatabase;
    private Button vViewSchedule, vFindBussStudent, vSuggestion, vChangePassword;
    String suggestionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        scheduleDatabase = new ScheduleDatabase(this);

        vViewSchedule = (Button) findViewById(R.id.idViewScheduleStudent);
        vFindBussStudent = (Button) findViewById(R.id.idFindBusStudent);
        vSuggestion = (Button) findViewById(R.id.idSuggestion);
        vChangePassword = (Button) findViewById(R.id.idChangePassword);

        Bundle bundle = getIntent().getExtras();
        suggestionId = bundle.getString("id");


        vFindBussStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Student.this, FindBus.class);
                startActivity(intent);
            }
        });

        vSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Student.this, Suggestion.class);
                intent.putExtra("id", suggestionId);
                startActivity(intent);

            }
        });


        vChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student.this, ChangePassword.class);
                startActivity(intent);
            }
        });

        viewSchedule();

    }

    public void viewSchedule(){

        vViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor re = scheduleDatabase.viewSchedule();

                if(re.getCount() == 0)
                    Toast.makeText(Student.this, "Nothing Found", Toast.LENGTH_SHORT).show();

                else{

                    StringBuffer buffer = new StringBuffer();

                    while(re.moveToNext()){

                        buffer.append("Time         : " + re.getString(0) + "\n");
                        buffer.append("From         : " + re.getString(1) + "\n");
                        buffer.append("To              : " + re.getString(2) + "\n");
                        buffer.append("Bus No      : " + re.getString(3) + "\n\n\n");

                    }

                    viewScheduleAlertDialog("Bus Schedule", buffer.toString());

                }

            }
        });

    }

    public void viewScheduleAlertDialog(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
