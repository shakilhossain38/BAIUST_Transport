package com.example.zmns.baiusttransport;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class AddStudent extends AppCompatActivity {

    private EditText vStudentId, vStudentName;
    private Spinner vStudentDept, vStudentLevel, vStudentTerm, vStudentType;
    private Button vConfirmAddStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        vStudentId = (EditText) findViewById(R.id.idAddStudentId);
        vStudentName = (EditText) findViewById(R.id.idAddStudentName);
        vStudentDept = (Spinner) findViewById(R.id.idAddStudentDept);
        vStudentLevel = (Spinner) findViewById(R.id.idAddStudentLevel);
        vStudentTerm = (Spinner) findViewById(R.id.idAddStudentTerm);
        vStudentType = (Spinner) findViewById(R.id.idAddStudentType);
        vConfirmAddStudentButton =  (Button) findViewById(R.id.idAddStudentButton);

        addStudent();

    }

    public void addStudent(){

        vConfirmAddStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id, name, dept, level, term, type;

                id = vStudentId.getText().toString();
                name = vStudentName.getText().toString();
                dept = vStudentDept.getSelectedItem().toString();
                level = vStudentLevel.getSelectedItem().toString();
                term = vStudentTerm.getSelectedItem().toString();
                type = vStudentType.getSelectedItem().toString();

                BackgroundTask backgroundTask = new BackgroundTask();
                backgroundTask.execute(id, name, dept, level, term, type);


            }
        });

    }


    class BackgroundTask extends AsyncTask<String, Void, String> {

        String addStudentUrl;

        @Override
        protected void onPreExecute() {

            addStudentUrl = "http://baiustshurjo.com/addStudent.php";

        }

        @Override
        protected String doInBackground(String... args) {

            String id, name, dept, level, term, type;

            id = args[0];
            name = args[1];
            dept = args[2];
            level = args[3];
            term = args[4];
            type = args[5];


            try {
                URL url = new URL(addStudentUrl);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"+
                                    URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"+
                                    URLEncoder.encode("dept", "UTF-8")+"="+URLEncoder.encode(dept, "UTF-8")+"&+"+
                                    URLEncoder.encode("level", "UTF-8")+"="+URLEncoder.encode(level, "UTF-8")+"&"+
                                    URLEncoder.encode("term", "UTF-8")+"="+URLEncoder.encode(term, "UTF-8")+"&"+
                                    URLEncoder.encode("type", "UTF-8")+"="+URLEncoder.encode(type, "UTF-8")+"&"+
                                    URLEncoder.encode("busFee", "UTF-8")+"="+URLEncoder.encode("Paid", "UTF-8")+"&"+
                                    URLEncoder.encode("passWord", "UTF-8")+"="+URLEncoder.encode("8888", "UTF-8");

                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="", line="";
                while ((line = bufferedReader.readLine()) != null ){

                    result += line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

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
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }

    }

}
