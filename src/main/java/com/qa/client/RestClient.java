package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {


    //1. GET Method without Headers:
    public void get(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url); //http get request
        CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget); //hit the GET URL

        //verify status code
        int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
        System.out.println("statusCodeis" + statusCode);

        //verify and to convert the entire reponse body to JSON
        String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJSON = new JSONObject(responseString);
        System.out.println("Response JSON from API===>" + responseJSON);

        Header[] headersArray = closebaleHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("Headers Array" + allHeaders);

    }
}

//    2. GET Method with Headers:
//    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpget = new HttpGet(url); //http get request
//
//        for(Map.Entry<String,String> entry : headerMap.entrySet()){
//            httpget.addHeader(entry.getKey(), entry.getValue());
//        }
//        CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
//        return closebaleHttpResponse;
//
//    }}
