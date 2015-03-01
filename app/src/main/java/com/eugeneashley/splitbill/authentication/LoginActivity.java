package com.eugeneashley.splitbill.authentication;


import android.app.Activity;
import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eugeneashley.splitbill.MainActivity;
import com.eugeneashley.splitbill.R;
import com.eugeneashley.splitbill.requestAPI.Response;


import static com.eugeneashley.splitbill.requestAPI.Request.signupRequest;
import static com.eugeneashley.splitbill.requestAPI.Request.loginRequest;



/**
 * Created by macbookpro on 2/26/15.
 */
public class LoginActivity extends Activity {

    private Button signup,login,fblogin;
    private String username, password;
    private EditText mPassword;
    private TextView forgotpassword;
    private AutoCompleteTextView mEmailView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
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
        fblogin = (Button) this.findViewById(R.id.b_fblogin);
        fblogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openfbLoginViewer();
            }
        });
    }
    private void attemptSignup(){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.signup_activity);
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
                doSignup(username,password);
            }
        });
        dialog.show();
    }

    private void doSignup(String email, String password){

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
            /* Check username and password */
            signupRequest(username,password,new Response() {
                @Override
                public void response(String responseMsg) {
                    System.out.println(responseMsg);
                }
            });

        }
    }


    private void attemptLogin() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.login_activity);
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
                doLogin(username, password);
            }
        });
        dialog.show();
    }

    private void doLogin(String email, String password){
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
            loginRequest(username,password,1,new Response() {
                @Override
                public void response(String responseMsg) {

                }
            });
        }
    }

    private boolean isEmailVaild(String email){
        return email.contains("@");
    }

    private void openfbLoginViewer() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    protected void loginFailed() {

    }


}
