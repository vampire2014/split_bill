package com.eugeneashley.splitbill;

import org.json.JSONException;

/**
 * Created by Yixing on 3/8/2015.
 */
public interface CallBackRequest {
    public void cbreq(ResponseMsg responseMsg, CallBackResponse callBackResponse) throws JSONException;
}
