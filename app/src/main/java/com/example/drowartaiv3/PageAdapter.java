package com.example.drowartaiv3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    int numCount;

    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public PageAdapter(@NonNull FragmentManager fm, int numCount) {
        super(fm, numCount);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                Anime anime = new Anime();
                return anime;
            case 1 :
                Animal animal = new Animal();
                return animal;
            case 2 :
                Nature nature = new Nature();
                return nature;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numCount;
    }
}
