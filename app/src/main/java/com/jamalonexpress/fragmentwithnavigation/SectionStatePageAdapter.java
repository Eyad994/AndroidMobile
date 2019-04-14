package com.jamalonexpress.fragmentwithnavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionStatePageAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mfragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SectionStatePageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title){
        mfragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    public String getFragmentTitle(int i){
        return mFragmentTitleList.get(i);
    }
    @Override
    public Fragment getItem(int i) {
        return mfragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }
}