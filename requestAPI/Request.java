
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
    private static final String URL_LOGIN = "http://splitbill-2015spring.herokuapp.com/users/login";
    private static final String URL_SIGNUP = "http://splitbill-2015spring.herokuapp.com/users/sign_up";
    private static final String URL_FORGET_PSW = "http://splitbill-2015spring.herokuapp.com/users/forget_password";
    private static final String URL_RESET_PSW = "http://splitbill-2015spring.herokuapp.com/users/reset_password";
    private static final String URL_DEPOSIT = "http://private-1875e-splitbillspring2015.apiary-mock.com/deposit";
    private static final String URL_TRANSFER = "http://private-1875e-splitbillspring2015.apiary-mock.com/transfer";
    private static final String URL_WITHDRAW = "http://private-1875e-splitbillspring2015.apiary-mock.com/withdraw";

    // login
    public RequestResult loginRequest(String email, String password, int login_type) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("password", password);
        jsonRequest.put("login_type", login_type);
        String urlParameters = jsonRequest.toString();

        // post request
        ResponseMsg msg = executePost(URL_LOGIN, urlParameters);

        // handle response
        JSONObject jsonResponse = new JSONObject(msg.getMsg());
        requestResult = new RequestResult();
        if(msg!=null) {
            switch(msg.getResponseCode()){
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
        return requestResult;
    }

    // social_login
    public RequestResult loginRequest(String email, int login_type) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("login_type", login_type);
        String urlParameters = jsonRequest.toString();

        // post request
        ResponseMsg msg = executePost(URL_LOGIN, urlParameters);

        // handle response
        JSONObject jsonResponse = new JSONObject(msg.getMsg());
        requestResult = new RequestResult();
        if(msg!=null) {
            switch(msg.getResponseCode()){
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
        return requestResult;

    }

    // sign up
    public RequestResult signupRequest(String email, String password) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("password", password);
        String urlParameters = jsonRequest.toString();

        // post request
        ResponseMsg msg = executePost(URL_SIGNUP, urlParameters);

        // handle response
        JSONObject jsonResponse = new JSONObject(msg.getMsg());
        requestResult = new RequestResult();
        if(msg!=null) {
            switch(msg.getResponseCode()){
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
        return requestResult;

    }

    // forget password
    public RequestResult forgetPasswordRequest(String email) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        String urlParameters = jsonRequest.toString();

        // post request
        ResponseMsg msg = executePost(URL_FORGET_PSW, urlParameters);

        // handle response
        JSONObject jsonResponse = new JSONObject(msg.getMsg());
        requestResult = new RequestResult();
        if(msg!=null) {
            switch(msg.getResponseCode()){
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
        return requestResult;

    }

    // reset password
    public RequestResult resetPasswordRequest(String email, String code, String new_password) throws JSONException{

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("email", email);
        jsonRequest.put("code", code);
        jsonRequest.put("new_password", new_password);
        String urlParameters = jsonRequest.toString();

        // post request
        ResponseMsg msg = executePost(URL_RESET_PSW, urlParameters);

        // handle response
        JSONObject jsonResponse = new JSONObject(msg.getMsg());
        requestResult = new RequestResult();
        if(msg!=null) {
            switch(msg.getResponseCode()){
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
        return requestResult;

    }

    // deposit
    public RequestResult depositRequest(String stripe_token, int amount) throws JSONException {

        // login or not
        if( id == -1 || token.equals("") ){
            requestResult = new RequestResult();
            requestResult.isSuccess = false;
            requestResult.setMessage("Haven't login yet");
            return requestResult;
        }

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("id", id);
        jsonRequest.put("token", token);
        jsonRequest.put("stripe_token", stripe_token);
        jsonRequest.put("amount", amount);
        String urlParameters = jsonRequest.toString();

        // post request
        ResponseMsg msg = executePost(URL_DEPOSIT, urlParameters);

        // handle response
        JSONObject jsonResponse = new JSONObject(msg.getMsg());
        requestResult = new RequestResult();
        if(msg!=null) {
            switch(msg.getResponseCode()){
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
        return requestResult;
    }

    // transfer
    public RequestResult transferRequest(String target_user, int amount) throws JSONException {

        // login or not
        if( id == -1 || token.equals("") ){
            requestResult = new RequestResult();
            requestResult.isSuccess = false;
            requestResult.setMessage("Haven't login yet");
            return requestResult;
        }

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("target_user", target_user);
        jsonRequest.put("amount", amount);
        String urlParameters = jsonRequest.toString();

        // post request
        ResponseMsg msg = executePost(URL_TRANSFER, urlParameters);

        // handle response
        JSONObject jsonResponse = new JSONObject(msg.getMsg());
        requestResult = new RequestResult();
        if(msg!=null) {
            switch(msg.getResponseCode()){
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
        return requestResult;
    }

    // withdraw
    public RequestResult withdrawRequest(String full_name, int amount, String bank_email, String stripe_token) throws JSONException {

        // login or not
        if( id == -1 || token.equals("") ){
            requestResult = new RequestResult();
            requestResult.isSuccess = false;
            requestResult.setMessage("Haven't login yet");
            return requestResult;
        }

        // build the json object of parameters
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("full_name", full_name);
        jsonRequest.put("amount", amount);
        jsonRequest.put("bank_email", bank_email);
        jsonRequest.put("stripe_token", stripe_token);
        String urlParameters = jsonRequest.toString();

        // post request
        ResponseMsg msg = executePost(URL_WITHDRAW, urlParameters);

        // handle response
        JSONObject jsonResponse = new JSONObject(msg.getMsg());
        requestResult = new RequestResult();
        if(msg!=null) {
            switch(msg.getResponseCode()){
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
        return requestResult;
    }

    // execute post
    public ResponseMsg executePost(String targetURL, String urlParameters){
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
 	        return msg;

        } catch (Exception e) {

            e.printStackTrace();
	      return null;

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    public RequestResult getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(RequestResult requestResult) {
        this.requestResult = requestResult;
    }
}
