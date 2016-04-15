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
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mario on 15/04/2016.
 */
public class MyQueryTaskpost extends AsyncTask<Void, Void, String> {
    UsersMessage usm;
    public MyQueryTaskpost(UsersMessage usm){
        this.usm=usm;
    }
    @Override
    protected String doInBackground(Void... params) {
        JSONObject request = new JSONObject();
        try {
            request.put("from",usm.getFrom());
            request.put("to",usm.getTo());
            request.put("text", usm.getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {            System.out.println(request.get("text"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url="http://10.0.2.2:8191/rest/messages";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(request.toString());
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(),headers);
        String answer = restTemplate.postForObject(url, entity, String.class);


        return answer;
    }
}
