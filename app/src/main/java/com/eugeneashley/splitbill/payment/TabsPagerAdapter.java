package com.eugeneashley.splitbill.payment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import com.eugeneashley.splitbill.payment.friendstransaction;
import com.eugeneashley.splitbill.payment.mytransaction;

/**
 * Created by macbookpro on 3/18/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       switch(position){
           case 0 : return new mytransaction();
           case 1 : return new friendstransaction();
       }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
