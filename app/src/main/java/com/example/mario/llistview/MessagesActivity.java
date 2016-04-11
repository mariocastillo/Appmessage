package com.example.mario.llistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i =getIntent();
        UsersMessage uIds= (UsersMessage) i.getSerializableExtra("UsersIds");
        TextView textousers= (TextView) findViewById(R.id.textousuarios);
        textousers.setText("from: "+uIds.getFrom().toString()+" to: "+uIds.getTo().toString());


    }

}
