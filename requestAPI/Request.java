package httprequest;

import net.sf.json.JSONObject;

public class Request {
	
	// URL
	private static final String URL_LOGIN = "http://splitbill-2015spring.herokuapp.com/users/login";
	private static final String URL_SIGNUP = "http://splitbill-2015spring.herokuapp.com/users/sign_up";
	private static final String URL_FORGET_PSW = "http://splitbill-2015spring.herokuapp.com/users/forget_password";
	private static final String URL_RESET_PSW = "http://splitbill-2015spring.herokuapp.com/users/reset_password";
	
	// login
	public static void loginRequest(String email, String password, int login_type, Responce r){
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("email", email);
		jsonRequest.put("password", password);
		jsonRequest.put("login_type", login_type);
		String urlParameters = jsonRequest.toString();
		
		Thread exePost = new Thread(new ExecutePost(URL_LOGIN, urlParameters, r));
		exePost.start();
		
	}
	
	// sign up
	public static void signupRequest(String email, String password, Responce r){
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("email", email);
		jsonRequest.put("password", password);
		String urlParameters = jsonRequest.toString();

		Thread exePost = new Thread(new ExecutePost(URL_SIGNUP, urlParameters, r));
		exePost.start();
		
	}
	
	// forget password
	public static void forgetPasswordRequest(String email, Responce r){
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("email", email);
		String urlParameters = jsonRequest.toString();
		
		Thread exePost = new Thread(new ExecutePost(URL_FORGET_PSW, urlParameters, r));
		exePost.start();
		
	}
	
	// reset password
	public static void resetPasswordRequest(String email, String code, String new_password, Responce r){
		JSONObject jsonRequest = new JSONObject();
		jsonRequest.put("email", email);
		jsonRequest.put("code", code);
		jsonRequest.put("new_password", new_password);
		String urlParameters = jsonRequest.toString();
		
		Thread exePost = new Thread(new ExecutePost(URL_RESET_PSW, urlParameters, r));
		exePost.start();
		
	}
}
