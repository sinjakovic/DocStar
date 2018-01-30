package com.sinjakovic.services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by Brandon on 4/21/2017.
 */
@Service
public class FederalGovService {

    public String findFiltered( String title, String location, String type, String signif) {

        String result = null;
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https");
        builder.setHost("www.federalregister.gov");
        builder.setPath("/api/v1/documents.json");


        if( title != null && !title.equals("") && title.length() > 1 ){
            builder.addParameter("conditions[term]",title);
        }
        if( location != null && !location.equals("") && location.length() == 5 ){
            builder.addParameter("conditions[near][location]",location);
        }
        if( type != null && !type.equals("") ){
            builder.addParameter("conditions[type][]",type);
        }
        if( signif != null && !signif.equals("") ){
            builder.addParameter("conditions[significant]",signif);
        }

        try{
            result = getDocJSON( builder.build() );
        }catch(Exception e){
        }
        return  result;
    }

    public String findById(String docNumber){
        String result = null;
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https");
        builder.setHost("www.federalregister.gov");
        builder.setPath("/api/v1/documents/"+docNumber+".json" );
        try{
            result = getDocJSON( builder.build() );
        }catch(Exception e){
        }
        return  result;
    }

    public String findMultiple(List<String> docNumbers) {
        if(docNumbers.size() > 0){
            StringBuilder strBuilder = new StringBuilder();
            String result = null;
            URIBuilder builder = new URIBuilder();
            builder.setScheme("https");
            builder.setHost("www.federalregister.gov");

            for(String numb : docNumbers){
                strBuilder.append(numb);
                strBuilder.append(",");
            }
            builder.setPath("/api/v1/documents/"+strBuilder.substring(0,strBuilder.length()-1)+".json" );
            try{
                result = getDocJSON( builder.build() );
            }catch(Exception e){
            }
            return  result;
        }else{
            return "";
        }
    }

    public String getDocJSON( URI uri) throws Exception{
        String responseBody;
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(uri);

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }
        return responseBody;
    }
}