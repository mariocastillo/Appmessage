package com.example.mario.llistview;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mario on 11/03/2016.
 */
public class myQueryTask extends AsyncTask<String,Void,ArrayList<Integer>> {

    @Override
    public ArrayList<Integer> doInBackground(String... params) {
        // Create a new RestTemplate instance
        ArrayList<Integer> listaa=new ArrayList<Integer>();
        String url="http://10.0.2.2:8191/rest/{contacts}/{contact}";

        RestTemplate restTemplate = new RestTemplate();


        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String

        String result = restTemplate.getForObject(url, String.class,params);

        ObjectMapper mapper = new ObjectMapper();
        List<ContactInfo> qres= null;
        try {
            qres = mapper.readValue(result,mapper.getTypeFactory().constructCollectionType(List.class, ContactInfo.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ContactInfo s:qres) {
            listaa.add(s.getUserId());
        }

        return listaa ;
    }
}
