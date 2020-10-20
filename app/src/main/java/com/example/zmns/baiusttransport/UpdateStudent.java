package com.example.zmns.baiusttransport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateStudent extends AppCompatActivity {


    StudentDatabase studentDatabase;
    private EditText vUpdateStudentId;
    private Spinner vUpdateStudentLevel, vUpdateStudentTerm, vUpdateStudentType, vUpdateStudentStatus;
    private Button vUpdateStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        studentDatabase = new StudentDatabase(this);

        vUpdateStudentId = (EditText) findViewById(R.id.idUpdateStudentId);
        vUpdateStudentLevel = (Spinner) findViewById(R.id.idUpdateStudentLevel);
        vUpdateStudentTerm = (Spinner) findViewById(R.id.idUpdateStudentTerm);
        vUpdateStudentType = (Spinner) findViewById(R.id.idUpdateStudentType);
        vUpdateStudentStatus = (Spinner) findViewById(R.id.idUpdateStudentStatus);
        vUpdateStudentButton = (Button) findViewById(R.id.idUpdateStudentButton);

        updateStudent();

    }

    private void updateStudent(){

        vUpdateStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer updatedRows = studentDatabase.updateStudent(vUpdateStudentId.getText().toString(),
                        vUpdateStudentLevel.getSelectedItem().toString(), vUpdateStudentTerm.getSelectedItem().toString(),
                        vUpdateStudentType.getSelectedItem().toString(), vUpdateStudentStatus.getSelectedItem().toString());

                if(vUpdateStudentId.getText().toString().length() != 7)
                    Toast.makeText(UpdateStudent.this, "Wrong ID", Toast.LENGTH_SHORT).show();

                else if (updatedRows > 0)
                    Toast.makeText(UpdateStudent.this, "Updated", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(UpdateStudent.this, "ID Not Found", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
