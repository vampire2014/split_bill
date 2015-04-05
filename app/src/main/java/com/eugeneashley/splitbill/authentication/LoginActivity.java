package com.eugeneashley.splitbill.authentication;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eugeneashley.splitbill.MainActivity;
import com.eugeneashley.splitbill.R;
import com.eugeneashley.splitbill.requestAPI.*;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;

import com.eugeneashley.splitbill.requestAPI.Request;

import org.json.JSONException;

import java.util.Arrays;




/**
 * Created by macbookpro on 2/26/15.
 */
public class LoginActivity extends FragmentActivity {

    private Button signup,login,fblogin;
    private String username, password;
    private EditText mPassword;
    private TextView forgotpassword;
    private AutoCompleteTextView mEmailView;
    private UiLifecycleHelper uiHelper;
    private Boolean saveLogin;
    public static final String PREFS_NAME = "LoginPrefs";

    private Request authrequest = Request.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiHelper = new UiLifecycleHelper(this, statusCallback);
        uiHelper.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_main);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        initControls();
    }

    private void initControls(){

        signup = (Button) this.findViewById(R.id.b_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignup();
            }
        });

        login = (Button) this.findViewById(R.id.b_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        LoginButton authButton = (LoginButton) findViewById(R.id.authButton);
        authButton.setReadPermissions(Arrays.asList("public_profile"));
        authButton.setUserInfoChangedCallback(new UserInfoChangedCallback() {
          @Override
          public void onUserInfoFetched(GraphUser user){
              if (user != null) {
                  try {
                    authrequest.loginRequest((String.valueOf(user.asMap().get("email"))),2,new CallBackResponse(){
                          @Override
                          public void cbrsp(RequestResult requestResult) {
                              if(requestResult.isSuccess){
                                  Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                  startActivity(intent);
                              }else{
                                    invaildnotes();
                              }
                          }

                      });

                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }
          }
        });
    }

    private void attemptSignup(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.signup_activity);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        final AutoCompleteTextView mEmailView = (AutoCompleteTextView) dialog.findViewById(R.id.et_email);
        final EditText mPassword = (EditText) dialog.findViewById(R.id.et_password);
        Button dialogButton = (Button) dialog.findViewById(R.id.b_signup);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mEmailView.getText().toString();
                password = mPassword.getText().toString();
                dialog.dismiss();
                try {
                    doSignup(username,password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.show();
    }

    private void doSignup(String email, String password) throws JSONException {

        boolean cancel = false;

        if(TextUtils.isEmpty(password)){
            mPassword.setError(getString(R.string.error_invalid_password));
            cancel = true;
        }
        if(TextUtils.isEmpty(username)){
            mEmailView.setError(getString(R.string.error_field_required));
            cancel = true;
        }else if(!isEmailVaild(username)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            cancel = true;
        }
        if(cancel) {

        }else {
            final SharedPreferences preferences = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_WORLD_WRITEABLE);
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Authentication_Id",username);
            editor.putString("Authentication_Password",password);
            editor.putString("Authentication_Status","false");
            editor.apply();
            authrequest.signupRequest(username, password,new CallBackResponse() {
                @Override
                public void cbrsp(RequestResult requestResult) {
                    if(requestResult.isSuccess){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        editor.putString("Authentication_Status","true");
                    }else{
                        invaildnotes();
                    }
                }
            });
        }
    }


    private void attemptLogin() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.login_activity);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        forgotpassword = (TextView) dialog.findViewById(R.id.et_forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetActivity.class);
                startActivity(intent);
            }
        });

        final AutoCompleteTextView mEmailView = (AutoCompleteTextView) dialog.findViewById(R.id.et_email);
        final EditText mPassword = (EditText) dialog.findViewById(R.id.et_password);
        Button dialogButton = (Button) dialog.findViewById(R.id.b_login);

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mEmailView.getText().toString();
                password = mPassword.getText().toString();
                dialog.dismiss();
                try {
                    doLogin(username, password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.show();
    }

    private void doLogin(String username, String password) throws JSONException {
        boolean cancel = false;
        if(TextUtils.isEmpty(password)){
            mPassword.setError(getString(R.string.error_invalid_password));
            cancel = true;
        }

        if(TextUtils.isEmpty(username)){
            mEmailView.setError(getString(R.string.error_field_required));

            cancel = true;
        }else if(!isEmailVaild(username)) {
            mEmailView.setError(getString(R.string.error_invalid_email));

            cancel = true;
        }

        if(cancel){

        }else{

            authrequest.loginRequest(username,password,1,new CallBackResponse() {
                @Override
                public void cbrsp(RequestResult requestResult) {
                    if(requestResult.isSuccess){
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("logged", "logged");
                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        invaildnotes();
                    }
                }
            });

        }
    }

    private boolean isEmailVaild(String email){
        return email.contains("@");
    }

    private void invaildnotes(){
        Toast.makeText(this,"Your username or password is invaild, please try again.",Toast.LENGTH_LONG).show();
    }


    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            if (state.isOpened()) {
//                buttonsEnabled(true);
                Log.d("FacebookSampleActivity", "Facebook session opened");
            } else if (state.isClosed()) {
//                buttonsEnabled(false);
                Log.d("FacebookSampleActivity", "Facebook session closed");
            }
        }
    };

//    public void buttonsEnabled(boolean isEnabled) {
//        postImageBtn.setEnabled(isEnabled);
//        updateStatusBtn.setEnabled(isEnabled);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
//http://javatechig.com/android/using-facebook-sdk-in-android-example