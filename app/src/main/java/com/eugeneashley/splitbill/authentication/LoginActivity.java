package com.eugeneashley.splitbill.authentication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eugeneashley.splitbill.R;

/**
 * Created by macbookpro on 2/26/15.
 */
public class LoginActivity extends Activity{

    private Button signup,login,fblogin;
    private String username, password;
    private TextView forgotpassword;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        signup = (Button) this.findViewById(R.id.b_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.signup_activity);
                Button dialogButton = (Button) dialog.findViewById(R.id.b_signup);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        login = (Button) this.findViewById(R.id.b_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.login_activity);
                Button dialogButton = (Button) dialog.findViewById(R.id.b_login);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        username = ((AutoCompleteTextView) v.findViewById(R.id.et_email)).getText().toString();
                        password = ((EditText) v.findViewById(R.id.et_password)).getText().toString();
                        dialog.dismiss();
                    }
                });
                dialog.show();
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
    private void openSignupViewer() {
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.signup_activity,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);

        builder.setTitle(getString(R.string.signup));
        builder.setView(dialoglayout);
        builder.setCancelable(false).setPositiveButton(getString(R.string.label_sign_up),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                Dialog f = (Dialog) dialogInterface;
                username = ((AutoCompleteTextView) f.findViewById(R.id.et_email)).getText().toString();
                password = ((EditText) f.findViewById(R.id.et_password)).getText().toString();
                //success
                //failure
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void openLoginViewer() {
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.login_activity,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setView(dialoglayout);
        builder.setCancelable(false).setPositiveButton(getString(R.string.label_sign_up), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                Dialog f = (Dialog) dialogInterface;
                username = ((AutoCompleteTextView) f.findViewById(R.id.et_email)).getText().toString();
                password = ((EditText) f.findViewById(R.id.et_password)).getText().toString();
                //success
                //failure
            }
        });
        forgotpassword = (TextView) this.findViewById(R.id.et_forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
}
