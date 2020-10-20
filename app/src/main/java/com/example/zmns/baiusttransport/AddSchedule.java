package com.example.zmns.baiusttransport;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddSchedule extends AppCompatActivity {


    private ScheduleDatabase busDb;
    private EditText vTime;
    private Spinner vFrom, vTo, vBusNo;
    private Button vConfirm;
    private String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        busDb = new ScheduleDatabase(this);

        vTime = (EditText) findViewById(R.id.idTimeAdd);
        vFrom = (Spinner) findViewById(R.id.idFromAdd);
        vTo = (Spinner) findViewById(R.id.idToAdd);
        vBusNo = (Spinner) findViewById(R.id.idBusNoAdd);
        vConfirm = (Button) findViewById(R.id.idConfirmAddScheduleButton);


        vTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddSchedule.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if(hourOfDay >= 12) {
                            amPm = "PM";
                            if(hourOfDay>12)
                                hourOfDay=hourOfDay-12;
                        }
                        else {
                            amPm = "AM";
                            if(hourOfDay==0)
                                hourOfDay=hourOfDay+12;
                        }

                        vTime.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + amPm);

                    }
                },0 ,0 , false);

                timePickerDialog.show();

            }
        });

        addSchedule();

    }

    public void addSchedule(){

        vConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = busDb.addSchedule(
                        vTime.getText().toString(),
                        vFrom.getSelectedItem().toString(),
                        vTo.getSelectedItem().toString(),
                        vBusNo.getSelectedItem().toString()
                );

                if(isInserted == true)
                    Toast.makeText(AddSchedule.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddSchedule.this, "Data not Inserted", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
