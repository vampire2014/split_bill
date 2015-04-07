package com.eugeneashley.splitbill.payment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.eugeneashley.splitbill.R;

/**
 * Created by macbookpro on 3/21/15.
 */

    public class PaymentFormFragment extends Fragment implements PaymentForm {

        Button saveButton;
        EditText amount;
        EditText cardNumber;
        EditText cvc;
        Spinner monthSpinner;
        Spinner yearSpinner;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.payment_form_fragment, container, false);

            this.saveButton = (Button) view.findViewById(R.id.save);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveForm(view);
                }
            });
            this.amount = (EditText) view.findViewById(R.id.amount);
            this.cardNumber = (EditText) view.findViewById(R.id.number);
            this.cvc = (EditText) view.findViewById(R.id.cvc);
            this.monthSpinner = (Spinner) view.findViewById(R.id.expMonth);
            this.yearSpinner = (Spinner) view.findViewById(R.id.expYear);

            return view;
        }

        @Override
        public String getAmount(){ return this.amount.getText().toString(); }

        @Override
        public String getCardNumber() {
            return this.cardNumber.getText().toString();
        }

        @Override
        public String getCvc() {
            return this.cvc.getText().toString();
        }

        @Override
        public Integer getExpMonth() {
            return getInteger(this.monthSpinner);
        }

        @Override
        public Integer getExpYear() {
            return getInteger(this.yearSpinner);
        }

        public void saveForm(View button) {
            ((CreditActivity)getActivity()).saveCreditCard(this);
        }

        private Integer getInteger(Spinner spinner) {
            try {
                return Integer.parseInt(spinner.getSelectedItem().toString());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
}

