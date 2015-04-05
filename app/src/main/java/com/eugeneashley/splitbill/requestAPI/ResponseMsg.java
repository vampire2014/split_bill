package com.eugeneashley.splitbill.requestAPI;

/**
 * Created by macbookpro on 3/7/15.
 */
/**
 * Created by Yixing on 3/6/2015.
 */
public class ResponseMsg {

    private int responseCode;
    private String msg;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}