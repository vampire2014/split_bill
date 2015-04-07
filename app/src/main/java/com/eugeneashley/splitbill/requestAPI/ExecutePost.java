package com.eugeneashley.splitbill.requestAPI;

import org.apache.http.HttpStatus;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by macbookpro on 3/9/15.
 */
public class ExecutePost implements Runnable {

    private String targetURL;
    private String urlParameters;
    private CallBackRequest callBackRequest;
    private CallBackResponse callBackResponse;

    public ExecutePost(String targetURL, String urlParameters, CallBackRequest callBackRequest, CallBackResponse callBackResponse){
        this.targetURL = targetURL;
        this.urlParameters = urlParameters;
        this.callBackRequest = callBackRequest;
        this.callBackResponse = callBackResponse;
    }

    @Override
    public void run() {
        URL url;
        HttpURLConnection connection = null;
        try {

            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json");

//            connection.setRequestProperty("Content-Length", "" +
//                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Connection", "close");
//            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();


            InputStream is;
            int status = connection.getResponseCode();
            if(status >= HttpStatus.SC_BAD_REQUEST)
                is = connection.getErrorStream();
            else
                is = connection.getInputStream();

            //Get Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            ResponseMsg msg = new ResponseMsg();
            msg.setResponseCode(connection.getResponseCode());
            msg.setMsg(response.toString());
            callBackRequest.cbreq(msg, callBackResponse);
//            return msg;

        } catch (Exception e) {

            e.printStackTrace();
            try {
                callBackRequest.cbreq(null, callBackResponse);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
//            return null;

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}
