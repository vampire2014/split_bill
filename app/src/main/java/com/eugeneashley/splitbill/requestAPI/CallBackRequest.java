package com.eugeneashley.splitbill.requestAPI;

import org.json.JSONException;

/**
 * Created by macbookpro on 3/9/15.
 */
public interface CallBackRequest {
    public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException;
}
