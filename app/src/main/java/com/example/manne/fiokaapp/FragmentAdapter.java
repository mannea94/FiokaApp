package com.example.manne.fiokaapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by manne on 11.1.2018.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }
//    public ArrayList<String> titles = new ArrayList<>();
    public ArrayList<Fragment> fragments = new ArrayList<>();

    public void addFragment(Fragment fragment){
//        titles.add(title);
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles.get(position);
//    }
}
