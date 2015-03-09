package com.eugeneashley.splitbill;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    // Singleton
    private static Request instance;
    private Request (){}
    public static synchronized Request getInstance() {
        if (instance == null) {
            instance = new Request();
        }
        return instance;
    }

    private RequestResult requestResult = new RequestResult(); // request result
    private int id = -1; // user id
    private String token = "";   // token


    // URL
//    private static final String URL_LOGIN = "http://splitbill-2015spring.herokuapp.com/users/login";
//    private static final String URL_SIGNUP = "http://splitbill-2015spring.herokuapp.com/users/sign_up";
//    private static final String URL_FORGET_PSW = "http://splitbill-2015spring.herokuapp.com/users/forget_password";
//    private static final String URL_RESET_PSW = "http://splitbill-2015spring.herokuapp.com/users/reset_password";
    private static final String URL_LOGIN = "http://private-1875e-splitbillspring2015.apiary-mock.com/login";
    private static final String URL_SIGNUP = "http://private-1875e-splitbillspring2015.apiary-mock.com/signup";
    private static final String URL_FORGET_PSW = "http://private-1875e-splitbillspring2015.apiary-mock.com/forget_password";
    private static final String URL_RESET_PSW = "http://private-1875e-splitbillspring2015.apiary-mock.com/reset_password";
    private static final String URL_DEPOSIT = "http://private-1875e-splitbillspring2015.apiary-mock.com/deposit";
    private static final String URL_TRANSFER = "http://private-1875e-splitbillspring2015.apiary-mock.com/transfer";
    private static final String URL_WITHDRAW = "http://private-1875e-splitbillspring2015.apiary-mock.com/withdraw";

    // login
    public void loginRequest(String email, String password, int login_type, CallBackResponse callBackResponse) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("password", password);
        jsonRequest.put("login_type", login_type);
        String urlParameters = jsonRequest.toString();

        // Implement the call back func of Request.java
        CallBackRequest callBackRequest = new CallBackRequest() {
            @Override
            public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException {
                requestResult = new RequestResult();
                if(responseMsg!=null) {
                    JSONObject jsonResponse = new JSONObject(responseMsg.getMsg());
                    switch(responseMsg.getResponseCode()){
                        case 200:
                            requestResult.isSuccess = true;
                            requestResult.setMessage("Success");
                            requestResult.setBalance(jsonResponse.getInt("balance"));
                            id = jsonResponse.getInt("id");
                            token = jsonResponse.getString("token");
                            break;
                        case 401:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                            break;
                        default:
                            requestResult.isSuccess = false;
                            requestResult.setMessage("Other Error!");
                            break;
                    }
                }
                else{
                    requestResult.isSuccess = false;
                    requestResult.setMessage("Other Error!");
                }
                callBackResponse.cbrsp(requestResult);
            }
        };

        // Execute Post
        Thread exePost = new Thread(new ExecutePost(URL_LOGIN, urlParameters, callBackRequest, callBackResponse));
        exePost.start();
    }

    // social_login
    public void loginRequest(String email, int login_type, CallBackResponse callBackResponse) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("login_type", login_type);
        String urlParameters = jsonRequest.toString();

        // Implement the call back func of Request.java
        CallBackRequest callBackRequest = new CallBackRequest() {
            @Override
            public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException {
                requestResult = new RequestResult();
                if(responseMsg!=null) {
                    JSONObject jsonResponse = new JSONObject(responseMsg.getMsg());
                    switch(responseMsg.getResponseCode()){
                        case 200:
                            requestResult.isSuccess = true;
                            requestResult.setMessage("Success");
                            requestResult.setBalance(jsonResponse.getInt("balance"));
                            id = jsonResponse.getInt("id");
                            token = jsonResponse.getString("token");
                            break;
                        case 401:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                            break;
                        default:
                            requestResult.isSuccess = false;
                            requestResult.setMessage("Other Error!");
                            break;
                    }
                }
                else{
                    requestResult.isSuccess = false;
                    requestResult.setMessage("Other Error!");
                }
                callBackResponse.cbrsp(requestResult);
            }
        };

        // Execute Post
        Thread exePost = new Thread(new ExecutePost(URL_LOGIN, urlParameters, callBackRequest, callBackResponse));
        exePost.start();

    }

    // sign up
    public void signupRequest(String email, String password, CallBackResponse callBackResponse) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("password", password);
        String urlParameters = jsonRequest.toString();

        // Implement the call back func of Request.java
        CallBackRequest callBackRequest = new CallBackRequest() {
            @Override
            public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException {
                requestResult = new RequestResult();
                if(responseMsg!=null) {
                    JSONObject jsonResponse = new JSONObject(responseMsg.getMsg());
                    switch(responseMsg.getResponseCode()){
                        case 200:
                            requestResult.isSuccess = true;
                            requestResult.setMessage("Success");
                            id = jsonResponse.getInt("id");
                            token = jsonResponse.getString("token");
                            break;
                        case 401:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                            break;
                        default:
                            requestResult.isSuccess = false;
                            requestResult.setMessage("Other Error!");
                            break;
                    }
                }
                else{
                    requestResult.isSuccess = false;
                    requestResult.setMessage("Other Error!");
                }
                callBackResponse.cbrsp(requestResult);
            }
        };

        // Execute Post
        Thread exePost = new Thread(new ExecutePost(URL_SIGNUP, urlParameters, callBackRequest, callBackResponse));
        exePost.start();

    }

    // forget password
    public void forgetPasswordRequest(String email, CallBackResponse callBackResponse) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        String urlParameters = jsonRequest.toString();

        // Implement the call back func of Request.java
        CallBackRequest callBackRequest = new CallBackRequest() {
            @Override
            public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException {
                requestResult = new RequestResult();
                if(responseMsg!=null) {
                    JSONObject jsonResponse = new JSONObject(responseMsg.getMsg());
                    switch(responseMsg.getResponseCode()){
                        case 200:
                            requestResult.isSuccess = true;
                            requestResult.setMessage("Success");
                            break;
                        case 401:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                            break;
                        default:
                            requestResult.isSuccess = false;
                            requestResult.setMessage("Other Error!");
                            break;
                    }
                }
                else{
                    requestResult.isSuccess = false;
                    requestResult.setMessage("Other Error!");
                }
                callBackResponse.cbrsp(requestResult);
            }
        };

        // Execute Post
        Thread exePost = new Thread(new ExecutePost(URL_FORGET_PSW, urlParameters, callBackRequest, callBackResponse));
        exePost.start();

    }

    // reset password
    public void resetPasswordRequest(String email, String code, String new_password, CallBackResponse callBackResponse) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("code", code);
        jsonRequest.put("new_password", new_password);
        String urlParameters = jsonRequest.toString();

        // Implement the call back func of Request.java
        CallBackRequest callBackRequest = new CallBackRequest() {
            @Override
            public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException {
                requestResult = new RequestResult();
                if(responseMsg!=null) {
                    JSONObject jsonResponse = new JSONObject(responseMsg.getMsg());
                    switch(responseMsg.getResponseCode()){
                        case 200:
                            requestResult.isSuccess = true;
                            requestResult.setMessage("Success");
                            id = jsonResponse.getInt("id");
                            token = jsonResponse.getString("token");
                            break;
                        case 401:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                            break;
                        default:
                            requestResult.isSuccess = false;
                            requestResult.setMessage("Other Error!");
                            break;
                    }
                }
                else{
                    requestResult.isSuccess = false;
                    requestResult.setMessage("Other Error!");
                }
                callBackResponse.cbrsp(requestResult);
            }
        };

        // Execute Post
        Thread exePost = new Thread(new ExecutePost(URL_RESET_PSW, urlParameters, callBackRequest, callBackResponse));
        exePost.start();

    }

    // deposit
    public void depositRequest(String stripe_token, int amount, CallBackResponse callBackResponse) throws JSONException {

        // login or not
        if( id == -1 || token.equals("") ){
            requestResult = new RequestResult();
            requestResult.isSuccess = false;
            requestResult.setMessage("Haven't login yet");
            callBackResponse.cbrsp(requestResult);
        }

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("id", id);
        jsonRequest.put("token", token);
        jsonRequest.put("stripe_token", stripe_token);
        jsonRequest.put("amount", amount);
        String urlParameters = jsonRequest.toString();

        // Implement the call back func of Request.java
        CallBackRequest callBackRequest = new CallBackRequest() {
            @Override
            public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException {
                requestResult = new RequestResult();
                if(responseMsg!=null) {
                    JSONObject jsonResponse = new JSONObject(responseMsg.getMsg());
                    switch(responseMsg.getResponseCode()){
                        case 200:
                            requestResult.isSuccess = true;
                            requestResult.setMessage("Success");
                            break;
                        case 401:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                            break;
                        case 406:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                        default:
                            requestResult.isSuccess = false;
                            requestResult.setMessage("Other Error!");
                            break;
                    }
                }
                else{
                    requestResult.isSuccess = false;
                    requestResult.setMessage("Other Error!");
                }
                callBackResponse.cbrsp(requestResult);
            }
        };

        // Execute Post
        Thread exePost = new Thread(new ExecutePost(URL_DEPOSIT, urlParameters, callBackRequest, callBackResponse));
        exePost.start();

    }

    // transfer
    public void transferRequest(String target_user, int amount, CallBackResponse callBackResponse) throws JSONException {

        // login or not
        if( id == -1 || token.equals("") ){
            requestResult = new RequestResult();
            requestResult.isSuccess = false;
            requestResult.setMessage("Haven't login yet");
            callBackResponse.cbrsp(requestResult);
        }

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("target_user", target_user);
        jsonRequest.put("amount", amount);
        String urlParameters = jsonRequest.toString();

        // Implement the call back func of Request.java
        CallBackRequest callBackRequest = new CallBackRequest() {
            @Override
            public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException {
                requestResult = new RequestResult();
                if(responseMsg!=null) {
                    JSONObject jsonResponse = new JSONObject(responseMsg.getMsg());
                    switch(responseMsg.getResponseCode()){
                        case 200:
                            requestResult.isSuccess = true;
                            requestResult.setMessage("Success");
                            break;
                        case 401:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                            break;
                        case 406:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                        default:
                            requestResult.isSuccess = false;
                            requestResult.setMessage("Other Error!");
                            break;
                    }
                }
                else{
                    requestResult.isSuccess = false;
                    requestResult.setMessage("Other Error!");
                }
                callBackResponse.cbrsp(requestResult);
            }
        };

        // Execute Post
        Thread exePost = new Thread(new ExecutePost(URL_TRANSFER, urlParameters, callBackRequest, callBackResponse));
        exePost.start();

    }

    // withdraw
    public void withdrawRequest(String full_name, int amount, String bank_email, int bank_account, CallBackResponse callBackResponse) throws JSONException {

        // login or not
        if( id == -1 || token.equals("") ){
            requestResult = new RequestResult();
            requestResult.isSuccess = false;
            requestResult.setMessage("Haven't login yet");
            callBackResponse.cbrsp(requestResult);
        }

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("full_name", full_name);
        jsonRequest.put("amount", amount);
        jsonRequest.put("bank_email", bank_email);
        jsonRequest.put("bank_account", bank_account);
        String urlParameters = jsonRequest.toString();

        // Implement the call back func of Request.java
        CallBackRequest callBackRequest = new CallBackRequest() {
            @Override
            public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException {
                requestResult = new RequestResult();
                if(responseMsg!=null) {
                    JSONObject jsonResponse = new JSONObject(responseMsg.getMsg());
                    switch(responseMsg.getResponseCode()){
                        case 200:
                            requestResult.isSuccess = true;
                            requestResult.setMessage("Success");
                            break;
                        case 401:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                            break;
                        case 402:
                            requestResult.isSuccess = false;
                            requestResult.setMessage(jsonResponse.getString("msg"));
                        default:
                            requestResult.isSuccess = false;
                            requestResult.setMessage("Other Error!");
                            break;
                    }
                }
                else{
                    requestResult.isSuccess = false;
                    requestResult.setMessage("Other Error!");
                }
                callBackResponse.cbrsp(requestResult);
            }
        };

        // Execute Post
        Thread exePost = new Thread(new ExecutePost(URL_WITHDRAW, urlParameters, callBackRequest, callBackResponse));
        exePost.start();

    }


    public RequestResult getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(RequestResult requestResult) {
        this.requestResult = requestResult;
    }
}
