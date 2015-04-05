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
public class FriendsFragment extends Fragment {
    public FriendsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        return rootView;
    }
}
