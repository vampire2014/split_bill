package com.eugeneashley.splitbill.authentication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eugeneashley.splitbill.R;

//reset:success/failure: toast, check password if it is the same

public class ResetActivity extends Activity {

    private EditText newPassword,newCfPassword;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        initControls();
    }

    private void initControls(){
        newPassword = (EditText) findViewById(R.id.et_newpassword);
        newCfPassword = (EditText) findViewById(R.id.et_cfpassword);
        resetButton = (Button) findViewById(R.id.b_reset);

        resetButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                BackToLogin();
            }
        } );
    }

    //save its new password
    private void BackToLogin(){
        //Check Text
        //Save to database
        //Load LoginView

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
