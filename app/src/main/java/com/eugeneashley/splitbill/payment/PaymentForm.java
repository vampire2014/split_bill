package com.eugeneashley.splitbill.payment;

/**
 * Created by macbookpro on 3/21/15.
 */
public interface PaymentForm {
    public String getAmount();
    public String getCardNumber();
    public String getCvc();
    public Integer getExpMonth();
    public Integer getExpYear();
}
