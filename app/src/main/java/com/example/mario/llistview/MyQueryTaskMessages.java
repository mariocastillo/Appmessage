package com.example.mario.llistview;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 16/04/2016.
 */
public class MyQueryTaskMessages extends AsyncTask<String,Void,ArrayList<String>> {

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        // Create a new RestTemplate instance
        ArrayList<String> listaa=new ArrayList<String>();
        String url="http://10.0.2.2:8191/rest/{Messages}/{contact1}/{contact2}";

        RestTemplate restTemplate = new RestTemplate();


        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String

        String result = restTemplate.getForObject(url, String.class,params);

        ObjectMapper mapper = new ObjectMapper();
        List<Message> qres= null;
        try {
            qres = mapper.readValue(result,mapper.getTypeFactory().constructCollectionType(List.class, Message.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Message s:qres) {
            listaa.add(s.getText());
        }

        return listaa ;
    }
}
