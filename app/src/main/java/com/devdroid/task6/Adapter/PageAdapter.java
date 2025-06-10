package com.devdroid.task6.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.devdroid.task6.Fragment.LoginFragment;
import com.devdroid.task6.Fragment.RegisterFragment;

public class PageAdapter extends FragmentPagerAdapter {

    int tabcount;
    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return new LoginFragment();
            case 1:
                return new RegisterFragment();

            default:return null;

        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
