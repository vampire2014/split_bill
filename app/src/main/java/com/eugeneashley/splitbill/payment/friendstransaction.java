package com.eugeneashley.splitbill.payment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eugeneashley.splitbill.R;

/**
 * Created by macbookpro on 3/18/15.
 */
public class friendstransaction extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transaction, container, false);

        return rootView;
    }
}
