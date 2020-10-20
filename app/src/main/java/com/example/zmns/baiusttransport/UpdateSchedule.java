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

public class UpdateSchedule extends AppCompatActivity {

    ScheduleDatabase busDb;
    private EditText vTime;
    private Spinner vBusNo, vFrom, vTo;
    private Button vConfirm;
    private String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_schedule);

        busDb = new ScheduleDatabase(this);

        vTime = (EditText) findViewById(R.id.idTimeUpdate);
        vBusNo = (Spinner) findViewById(R.id.idBusNoUpdate);
        vFrom = (Spinner) findViewById(R.id.idFromUpdate);
        vTo = (Spinner) findViewById(R.id.idToUpdate);
        vConfirm = (Button) findViewById(R.id.idConfirmUpdateButton);


        vTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateSchedule.this, new TimePickerDialog.OnTimeSetListener() {
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


        vConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isUpdated = busDb.updateSchedule(vTime.getText().toString(), vFrom.getSelectedItem().toString(), vTo.getSelectedItem().toString(), vBusNo.getSelectedItem().toString());

                if(isUpdated > 0)
                    Toast.makeText(UpdateSchedule.this, "Data Updated", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(UpdateSchedule.this, "Data Not Updated ", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
