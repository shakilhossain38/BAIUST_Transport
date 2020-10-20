package com.example.zmns.baiusttransport;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentList extends AppCompatActivity {

    String jsonString;
    JSONObject jsonObject;
    JSONArray jsonArray;
    StudentAdapter studentAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        listView = (ListView)findViewById(R.id.idStudentListView);
        studentAdapter = new StudentAdapter(this, R.layout.student_list_view_id);
        listView.setAdapter(studentAdapter);

        jsonString = getIntent().getExtras().getString("jsonData");

        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count = 0;
            String id, name, dept, level, term, type, busFee;

            while (count < jsonArray.length()){

                JSONObject jo = jsonArray.getJSONObject(count);

                id = jo.getString("id");
                name = jo.getString("name");
                dept = jo.getString("dept");
                level = jo.getString("level");
                term = jo.getString("term");
                type = jo.getString("type");
                busFee = jo.getString("busFee");


                StudentModelClass studentModelClass = new StudentModelClass(id, name, dept, level, term, type, busFee);

                studentAdapter.add(studentModelClass);

                count++;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}