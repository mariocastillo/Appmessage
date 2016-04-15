package com.example.mario.llistview;


import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ListActivity {
    ListView listv;
    Intent ia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ia= getIntent();
        String contacto= (String) ia.getSerializableExtra("UserId");


        try {

            ArrayList<Integer> result= new myQueryTask().execute("contacts",contacto).get();
            
            ArrayAdapter<Integer> adapter=new ArrayAdapter<Integer>(this,R.layout.rowlayout,result);
            this.setListAdapter(adapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // ListView Clicked item value
        ia=getIntent();
        String contacto= (String) ia.getSerializableExtra("UserId");
        int  itemValue    = (int) l.getItemAtPosition(position);
        UsersMessage usersid= new UsersMessage(Integer.parseInt(contacto),itemValue, "");
        Intent i= new Intent(getApplicationContext(),MessagesActivity.class);
        i.putExtra("UsersIds", usersid);
        startActivity(i);
    }

    /**
     * Created by mario on 11/03/2016.
     */

}
