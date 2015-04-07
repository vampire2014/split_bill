package com.eugeneashley.splitbill.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.eugeneashley.splitbill.MainActivity;
import com.eugeneashley.splitbill.R;
import com.eugeneashley.splitbill.android.Stripe;
import com.eugeneashley.splitbill.android.TokenCallback;
import com.eugeneashley.splitbill.android.model.Card;
import com.eugeneashley.splitbill.android.model.Token;
import com.eugeneashley.splitbill.requestAPI.CallBackResponse;
import com.eugeneashley.splitbill.requestAPI.Request;
import com.eugeneashley.splitbill.requestAPI.RequestResult;

import org.json.JSONException;

public class CreditActivity extends FragmentActivity {

    public static final String PUBLISHABLE_KEY = "pk_test_kEbuoHxEkU9TXcLxMiqbO2wC";

    private ProgressDialogFragment progressFragment;

    private Request authrequest = Request.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditactivity);
        progressFragment = ProgressDialogFragment.newInstance(R.string.progressMessage);
    }

    public void saveCreditCard(final PaymentForm form) {

        Card card = new Card(
                form.getCardNumber(),
                form.getExpMonth(),
                form.getExpYear(),
                form.getCvc());

        boolean validation = card.validateCard();
        if (validation) {
            startProgress();
            new Stripe().createToken(
                    card,
                    PUBLISHABLE_KEY,
                    new TokenCallback() {
                        public void onSuccess(Token token) throws JSONException {
                            getTokenList().addToList(token);
                            Integer amount = Integer.valueOf(form.getAmount());
                            String stripe_token = token.toString();
                            authrequest.depositRequest(stripe_token,amount, new CallBackResponse(

                            ) {
                                @Override
                                public void cbrsp(RequestResult requestResult) {
                                    if(requestResult.isSuccess){
                                        Intent intent = new Intent(CreditActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }else{
                                        invaildnotes();
                                        System.out.println(requestResult.getMessage());
                                    }
                                }
                            });
                            finishProgress();
                        }
                        public void onError(Exception error) {
                            handleError(error.getLocalizedMessage());
                            finishProgress();
                        }
                    });
        } else if (!card.validateNumber()) {
            handleError("The card number that you entered is invalid");
        } else if (!card.validateExpiryDate()) {
            handleError("The expiration date that you entered is invalid");
        } else if (!card.validateCVC()) {
            handleError("The CVC code that you entered is invalid");
        } else {
            handleError("The card details that you entered are invalid");
        }
    }

    private void startProgress() {
        progressFragment.show(getSupportFragmentManager(), "progress");
    }

    private void finishProgress() {
        progressFragment.dismiss();
    }

    private void handleError(String error) {
        DialogFragment fragment = ErrorDialogFragment.newInstance(R.string.validationErrors, error);
        fragment.show(getSupportFragmentManager(), "error");
    }

    private TokenList getTokenList() {
        return (TokenList)(getSupportFragmentManager().findFragmentById(R.id.token_list));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_creditactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void invaildnotes(){
        Toast.makeText(this, "Something went wrong, please try again.", Toast.LENGTH_LONG).show();
    }

}
