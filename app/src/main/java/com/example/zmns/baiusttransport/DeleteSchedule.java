package com.example.zmns.baiusttransport;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class DeleteSchedule extends AppCompatActivity {

    ScheduleDatabase busDb;
    EditText vTime;
    Spinner vBusNo;
    Button vConfirm;
    String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_schedule);

        busDb = new ScheduleDatabase(this);
        vTime = (EditText) findViewById(R.id.idTimeDelete);
        vBusNo = (Spinner) findViewById(R.id.idBusNoDelete);
        vConfirm = (Button) findViewById(R.id.idConfirmDeleteButton);

        vTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(DeleteSchedule.this, new TimePickerDialog.OnTimeSetListener() {
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


        deleteSchedule();

    }

    private void deleteSchedule(){

        vConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deletedRows = busDb.deleteSchedule(vTime.getText().toString(), vBusNo.getSelectedItem().toString());

                if(deletedRows > 0)
                    Toast.makeText(DeleteSchedule.this, "Deleted", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(DeleteSchedule.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
