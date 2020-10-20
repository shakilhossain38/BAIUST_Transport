package com.example.zmns.baiusttransport;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    MessageDatabase messageDatabase;
    ScheduleDatabase busDb;
    Button vMaintainSchedule, vViewSchedule, vFindBusAdmin, vMaintainStudent, vMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        messageDatabase = new MessageDatabase(this);
        busDb = new ScheduleDatabase(this);

        vMaintainSchedule = (Button) findViewById(R.id.idMaintainSchedule);
        vViewSchedule = (Button) findViewById(R.id.idViewScheduleAdmin1);
        vFindBusAdmin = (Button) findViewById(R.id.idFindBusAdmin);
        vMaintainStudent = (Button) findViewById(R.id.idMaintainStudent);
        vMessage = (Button) findViewById(R.id.idMessage);

        vFindBusAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Admin.this, FindBus.class);
                startActivity(intent);
            }
        });

        vMaintainSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Admin.this, MaintainSchedule.class);
                startActivity(intent);
            }
        });

        vMaintainStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, MaintainStudent.class);
                startActivity(intent);
            }
        });

        vMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor re = messageDatabase.viewMessage();

                if(re.getCount() == 0)
                    Toast.makeText(Admin.this, "No Message Found", Toast.LENGTH_SHORT).show();

                else{

                    StringBuffer buffer = new StringBuffer();

                    while(re.moveToNext()){

                        buffer.append("ID       :  " + re.getString(0) + "\n");
                        buffer.append(re.getString(1) + "\n\n\n");

                    }

                    viewSchedule("Messages", buffer.toString());

                }

            }
        });

        vViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor re = busDb.viewSchedule();

                if(re.getCount() == 0)
                    Toast.makeText(Admin.this, "No Schedule Found", Toast.LENGTH_SHORT).show();

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
