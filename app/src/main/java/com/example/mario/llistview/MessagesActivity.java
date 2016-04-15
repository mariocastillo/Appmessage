package com.example.mario.llistview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MessagesActivity extends AppCompatActivity implements FragmentMessages.OnFragmentInteractionListener,FragmentSharing.OnFragmentInteractionListener{
    String mensaje="";
    String temp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager pager= (ViewPager) findViewById(R.id.viewpager);

        pager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(pager);





    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentMessages();
                case 1:
                default:
                    return new FragmentSharing();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Mensajes";
                case 1:
                default:
                    return "Compartidos";
            }
        }
    }

}
