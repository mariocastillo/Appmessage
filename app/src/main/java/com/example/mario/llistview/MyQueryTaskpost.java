package com.example.mario.llistview;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mario on 15/04/2016.
 */
public class MyQueryTaskpost extends AsyncTask<Void, Void,String > {
    UsersMessage usm;
    public MyQueryTaskpost(UsersMessage usm){
        this.usm=usm;
    }
    @Override
    protected String  doInBackground(Void... params) {
        JSONObject request = new JSONObject();
        String url="http://10.0.2.2:8191/rest/messages";
        try {
            request.put("from",usm.getFrom());
            request.put("to",usm.getTo());
            request.put("text", usm.getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(),headers);
        String answer = restTemplate.postForObject(url, entity,String.class);
        return answer;
    }
}
