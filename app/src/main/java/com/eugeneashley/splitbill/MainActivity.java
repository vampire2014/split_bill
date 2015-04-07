package com.eugeneashley.splitbill;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;
//import org.json.JSONException;

public class MainActivity extends Activity {

    public Request request = Request.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void commTest(View view){

        try {
            request.signupRequest("ssdddsd@126.com","123321", new CallBackResponse() {
                @Override
                public void cbrsp(RequestResult requestResult) {
                    System.out.println(requestResult.isSuccess);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
