package com.eugeneashley.splitbill;

/**
 * Created by Yixing on 3/6/2015.
 */

// Ashley can get the result info from this class
public class RequestResult {

    public Boolean isSuccess;   // success or not
    private String message;     // responce message

    private int balance;        // attribute of login response, only get if isSuccess is "true"

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
