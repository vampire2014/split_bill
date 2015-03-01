package com.eugeneashley.splitbill.requestAPI;

import org.apache.http.HttpStatus;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExecutePost implements Runnable{
	
	private String targetURL; 
	private String urlParameters;
	private Response r;
	
	ExecutePost(String targetURL, String urlParameters, Response r){
		this.targetURL = targetURL;
		this.urlParameters = urlParameters;
		this.r = r;
	}
	
	public void run(){
		URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/json");

//	      connection.setRequestProperty("Content-Length", "" +
//	               Integer.toString(urlParameters.getBytes().length));
          connection.setRequestProperty("Connection", "close");
//	      connection.setRequestProperty("Content-Language", "en-US");

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
//	      return response.toString();
	      r.response(response.toString());

	    } catch (Exception e) {

	      e.printStackTrace();
//	      return null;
	      r.response(null);

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	}

}
