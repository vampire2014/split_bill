package com.eugeneashley.splitbill.requestAPI;

//import net.sf.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.logging.Log;




public class Request {
	
	// URL
	private static final String URL_LOGIN = "http://splitbill-2015spring.herokuapp.com/users/login";
	private static final String URL_SIGNUP = "http://splitbill-2015spring.herokuapp.com/users/sign_up";
	private static final String URL_FORGET_PSW = "http://splitbill-2015spring.herokuapp.com/users/forget_password";
	private static final String URL_RESET_PSW = "http://splitbill-2015spring.herokuapp.com/users/reset_password";
	
	// login
	public static void loginRequest(String email, String password, int login_type, Response r){
        try {
            org.json.JSONObject jsonRequest = new org.json.JSONObject();
            jsonRequest.put("email", email);
            jsonRequest.put("password", password);
            jsonRequest.put("login_type", login_type);
            String urlParameters = jsonRequest.toString();

            Thread exePost = new Thread(new ExecutePost(URL_LOGIN, urlParameters, r));
            exePost.start();
        }catch(JSONException e){
            e.printStackTrace();
        }

		
	}
	
	// sign up
	public static void signupRequest(String email, String password, Response r){
		org.json.JSONObject jsonRequest = new org.json.JSONObject();
        try {
            jsonRequest.put("email", email);
            jsonRequest.put("password", password);
            String urlParameters = jsonRequest.toString();

            Thread exePost = new Thread(new ExecutePost(URL_SIGNUP, urlParameters, r));
            exePost.start();
        }catch (JSONException e){
            e.printStackTrace();
        }
	}
	
	// forget password
	public static void forgetPasswordRequest(String email, Response r){
		org.json.JSONObject jsonRequest = new org.json.JSONObject();
        try {
		jsonRequest.put("email", email);
		String urlParameters = jsonRequest.toString();
		
		Thread exePost = new Thread(new ExecutePost(URL_FORGET_PSW, urlParameters, r));
		exePost.start();
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
	
	// reset password
	public static void resetPasswordRequest(String email, String code, String new_password, Response r){
		JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("email", email);
            jsonRequest.put("code", code);
            jsonRequest.put("new_password", new_password);
            String urlParameters = jsonRequest.toString();

            Thread exePost = new Thread(new ExecutePost(URL_RESET_PSW, urlParameters, r));
            exePost.start();
        }catch(JSONException e){
            e.printStackTrace();
        }
		
	}
}
