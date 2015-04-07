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

import com.eugeneashley.splitbill.MainActivity;
import com.eugeneashley.splitbill.R;
import com.eugeneashley.splitbill.requestAPI.CallBackResponse;
import com.eugeneashley.splitbill.requestAPI.Request;
import com.eugeneashley.splitbill.requestAPI.RequestResult;

import org.json.JSONException;


public class ForgetActivity extends Activity {

    private String email;
    private Button send;
    private Request forgot = Request.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        email = ((EditText) this.findViewById(R.id.et_email)).getText().toString();
        send = (Button) this.findViewById(R.id.b_send);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //check email style
                //User exists
                //not:back to signup

                try {
                    forgot.forgetPasswordRequest(email,new CallBackResponse() {
                        @Override
                        public void cbrsp(RequestResult requestResult) {
                            if(requestResult.isSuccess){
                                Intent intent = new Intent(ForgetActivity.this,ResetActivity.class);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(ForgetActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Send Code to user's email(with expire time)
                //Input code with validation
                //Match the code
                //Load Reset Activity
                //failure
                //The user is not exist, guide him to signup view
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.forget, menu);
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
