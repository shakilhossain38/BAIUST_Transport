package com.example.zmns.baiusttransport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Suggestion extends AppCompatActivity {


    MessageDatabase messageDatabase;
    private EditText vSuggestion;
    private Button vSuggestionButton;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);


        messageDatabase = new MessageDatabase(this);
        vSuggestion = (EditText) findViewById(R.id.idSuggestion);
        vSuggestionButton = (Button) findViewById(R.id.idSuggestionButton);

        Bundle bundle = getIntent().getExtras();
        ID = bundle.getString("id");

        sendSuggestion();

    }

    public void sendSuggestion(){

        vSuggestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!vSuggestion.getText().toString().equals("") && messageDatabase.addSuggestion( ID, vSuggestion.getText().toString()))
                    Toast.makeText(Suggestion.this, "Messag Sent", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Suggestion.this, "Type Something", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
