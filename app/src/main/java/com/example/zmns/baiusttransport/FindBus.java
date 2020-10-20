package com.example.zmns.baiusttransport;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class FindBus extends AppCompatActivity {

    ScheduleDatabase scheduleDatabase;
    private Spinner vFromFindBus, vToFindBus;
    private Button vConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bus);

        scheduleDatabase = new ScheduleDatabase(this);
        vFromFindBus = (Spinner) findViewById(R.id.idFromFindBus);
        vToFindBus = (Spinner) findViewById(R.id.idToFindBus);
        vConfirm = (Button) findViewById(R.id.idFindBusButton);

        findBus();

    }

    public void findBus(){

        vConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor re = scheduleDatabase.findBus(vFromFindBus.getSelectedItem().toString(), vToFindBus.getSelectedItem().toString());

                if(re.getCount() == 0)
                    Toast.makeText(FindBus.this, "No Bus Found", Toast.LENGTH_SHORT).show();

                else{

                    StringBuffer buffer = new StringBuffer();

                    while(re.moveToNext()){

                        buffer.append("Time         : " + re.getString(0) + "\n");
                        buffer.append("From         : " + re.getString(1) + "\n");
                        buffer.append("To              : " + re.getString(2) + "\n");
                        buffer.append("Bus No      : " + re.getString(3) + "\n\n\n");

                    }

                    viewSchedule("Available Buses", buffer.toString());

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
