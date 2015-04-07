package com.eugeneashley.splitbill.authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eugeneashley.splitbill.R;
import com.eugeneashley.splitbill.requestAPI.CallBackResponse;
import com.eugeneashley.splitbill.requestAPI.Request;
import com.eugeneashley.splitbill.requestAPI.RequestResult;

import org.json.JSONException;

//reset:success/failure: toast, check password if it is the same

public class ResetActivity extends Activity {

    private EditText newPassword,newCfPassword,code;
    private Button resetButton;
    private String email,password,emailcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        Intent intent = getIntent();
        if(intent.hasExtra("email")){
            email = intent.getStringExtra("email");
        }
        initControls();
    }

    private void initControls(){
        newPassword = (EditText) findViewById(R.id.et_newpassword);
        password = newPassword.getText().toString();
        newCfPassword = (EditText) findViewById(R.id.et_cfpassword);
        code = (EditText) findViewById(R.id.et_passwordcode);
        emailcode = code.getText().toString();
        resetButton = (Button) findViewById(R.id.b_reset);

        resetButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                try {
                    BackToLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    //save its new password
    private void BackToLogin() throws JSONException {
        //Check Text
        //Save to database
        //Load LoginView
        Request resetPasswordRequest = Request.getInstance();
        resetPasswordRequest.resetPasswordRequest(email, password, emailcode, new CallBackResponse() {
            @Override
            public void cbrsp(RequestResult requestResult) {
                if (requestResult.isSuccess) {
                    Intent intent = new Intent(ResetActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    resetFailed();
                }
            }


        });
    }
    private void resetFailed(){
        Toast.makeText(this,getString(R.string.signupmsg),Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reset, menu);
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
