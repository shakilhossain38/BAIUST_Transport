package com.example.zmns.baiusttransport;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MaintainSchedule extends AppCompatActivity {

    ScheduleDatabase busDb;
    Button vViewSchedule, vAddSchedule, vUpdateSchedule, vDeleteSchedule;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_schedule);

        busDb = new ScheduleDatabase(this);

        vViewSchedule = (Button) findViewById(R.id.idViewScheduleAdmin2);
        vAddSchedule = (Button) findViewById(R.id.idAddSchedule);
        vUpdateSchedule = (Button) findViewById(R.id.idUpdateSchedule);
        vDeleteSchedule = (Button) findViewById(R.id.idDeleteSchedule);

        vAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MaintainSchedule.this, AddSchedule.class);
                startActivity(intent);

            }
        });

        vUpdateSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintainSchedule.this, UpdateSchedule.class);
                startActivity(intent);
            }
        });

        vDeleteSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintainSchedule.this, DeleteSchedule.class);
                startActivity(intent);
            }
        });

        vViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor re = busDb.viewSchedule();

                if(re.getCount() == 0)
                    Toast.makeText(MaintainSchedule.this, "No Schedule Found", Toast.LENGTH_SHORT).show();

                else{

                    StringBuffer buffer = new StringBuffer();

                    while(re.moveToNext()){

                        buffer.append("Time         : " + re.getString(0) + "\n");
                        buffer.append("From         : " + re.getString(1) + "\n");
                        buffer.append("To              : " + re.getString(2) + "\n");
                        buffer.append("Bus No      : " + re.getString(3) + "\n\n\n");

                    }

                    viewSchedule("Bus Schedule", buffer.toString());

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
