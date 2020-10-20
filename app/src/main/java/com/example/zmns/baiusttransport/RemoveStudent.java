package com.example.zmns.baiusttransport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveStudent extends AppCompatActivity {

    StudentDatabase studentDatabase;
    EditText vRemoveStudentId;
    Button vRemoveStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_student);

        studentDatabase = new StudentDatabase(this);

        vRemoveStudentId = (EditText) findViewById(R.id.idRemoveStudentId);
        vRemoveStudentButton = (Button) findViewById(R.id.idRemoveStudentButton);

        deleteStudent();

    }

    private void deleteStudent(){

        vRemoveStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer removedRows = studentDatabase.removeStudent(vRemoveStudentId.getText().toString());

                if(vRemoveStudentId.getText().toString().length() != 7)
                    Toast.makeText(RemoveStudent.this, "Wrong ID", Toast.LENGTH_SHORT).show();

                else if (removedRows > 0)
                    Toast.makeText(RemoveStudent.this, "Removed", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(RemoveStudent.this, "ID Not Found", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
