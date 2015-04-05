package com.eugeneashley.splitbill.userprofile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eugeneashley.splitbill.R;

/**
 * Created by macbookpro on 3/7/15.
 */
public class InviteFragment extends Fragment {
    public InviteFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_invite, container, false);

        return rootView;
    }
}
