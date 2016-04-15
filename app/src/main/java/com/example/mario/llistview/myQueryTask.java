package com.example.mario.llistview;

import android.content.Context;
import android.os.AsyncTask;
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
        String url1="https://ajax.googleapis.com/ajax/" +
                "services/search/web?v=1.0&q={query}";
        //String url="https://global.api.pvp.net/api/lol/static-data/lan/v1.2/champion?champData=all&api_key=404e03a9-b38e-4f0d-a6aa-acf04022575e";
        //String url="https://blazing-heat-1733.firebaseio.com/";
        String url2="http://10.0.2.2:8191/rest/{contacts}/{contact}";
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        //String result1 = restTemplate.getForObject(url1, String.class, params);
        String result2 = restTemplate.getForObject(url2, String.class,params);

        ObjectMapper mapper = new ObjectMapper();
        List<ContactInfo> qres= null;
        try {
            qres = mapper.readValue(result2,mapper.getTypeFactory().constructCollectionType(List.class, ContactInfo.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ContactInfo s:qres) {
            listaa.add(s.getUserId());
        }


            //List<ContactInfo> qres = mapper.readValue(result1, mapper.getTypeFactory().constructCollectionType(List.class, ContactInfo.class));
            //for (ContactInfo s:qres) {
            //    listaa.add(s.getUserName());
            //}
            //QueryResult queryResult =new ObjectMapper().readValue(result1, QueryResult.class);
            //for(ResultItems s:queryResult.getResponseData().getResults()){

            //    listaa.add(s.getTitle());

            //}



        return listaa ;
    }
}
