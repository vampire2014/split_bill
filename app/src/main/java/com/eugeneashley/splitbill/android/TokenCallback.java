package com.eugeneashley.splitbill.android;

import com.eugeneashley.splitbill.android.model.Token;

import org.json.JSONException;


public abstract class TokenCallback {
    public abstract void onError(Exception error);
    public void onSuccess(Token token) throws JSONException {
        // Send token to your server
    }

}
