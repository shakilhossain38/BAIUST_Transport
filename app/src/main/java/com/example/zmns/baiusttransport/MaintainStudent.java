package com.example.zmns.baiusttransport;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MaintainStudent extends AppCompatActivity {

    String JSON_String;
    private Button vAddStudent, vStudentList, vRemoveStudent, vUpdateStudent, vResetStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain_student);


        vAddStudent = (Button) findViewById(R.id.idAddStudent);
        vStudentList = (Button) findViewById(R.id.idStudentList);
        vRemoveStudent = (Button) findViewById(R.id.idRemoveStudent);
        vUpdateStudent = (Button) findViewById(R.id.idUpdateStudent);
        vResetStatus = (Button) findViewById(R.id.idResetStatus);

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute();

        vStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BackgroundTask backgroundTask = new BackgroundTask();
                backgroundTask.execute();

                if(JSON_String == null){

                    Toast.makeText(getApplicationContext(), "First Get JSON", Toast.LENGTH_SHORT).show();

                }

                else {

                    Intent intent = new Intent(MaintainStudent.this, StudentList.class);
                    intent.putExtra("jsonData", JSON_String);
                    startActivity(intent);

                }
            }
        });

        vAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MaintainStudent.this, AddStudent.class);
                startActivity(intent);

            }
        });

        vRemoveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MaintainStudent.this, RemoveStudent.class);
                startActivity(intent);

            }
        });

        vUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintainStudent.this, UpdateStudent.class);
                startActivity(intent);
            }
        });

        vResetStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status = "Not Updated";

                Toast.makeText(MaintainStudent.this, "Status Reset", Toast.LENGTH_SHORT).show();
            }
        });

    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String viewStudentJsonUrl, viewStudentJsonString;

        @Override
        protected void onPreExecute() {

            viewStudentJsonUrl = "http://baiustshurjo.com/viewStudent.php";

        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(viewStudentJsonUrl);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((viewStudentJsonString = bufferedReader.readLine()) != null ){

                    stringBuilder.append(viewStudentJsonString + "\n");

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            JSON_String = result;
        }

    }

}
