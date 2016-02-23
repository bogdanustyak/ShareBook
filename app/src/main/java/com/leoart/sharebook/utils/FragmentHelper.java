package com.leoart.sharebook.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentHelper {

    private static FragmentHelper instance;

    private FragmentHelper() {
    }

    public static FragmentHelper getInstance() {
        if (instance == null) {
            instance = new FragmentHelper();
        }
        return instance;
    }

    public void showFragment(FragmentManager fm, Fragment fragmentToShow, int containerID) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerID, fragmentToShow);
        ft.addToBackStack(fragmentToShow.getClass().getSimpleName());
        ft.commit();
    }

    public void showAnimFragment(FragmentManager fm, Fragment fragmentToShow, int containerID, int enter, int exit) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(enter, exit);
        ft.replace(containerID, fragmentToShow);
        ft.commit();
    }

    public void addAnimFragment(FragmentManager fm, Fragment fragmentToShow, int containerID, int enter, int exit) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(enter, exit);
        ft.add(containerID, fragmentToShow);
        ft.commit();
    }

    public void removeFragment(FragmentManager fm, int containerID) {
        try {
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragmentToRemove = fm.findFragmentById(containerID);
            ft.remove(fragmentToRemove);
            ft.commit();
        } catch (IllegalStateException ignored) {
        }
    }

    public void removeAnimFragment(FragmentManager fm, int containerID, int enter, int exit) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(enter, exit);
        Fragment fragmentToRemove = fm.findFragmentById(containerID);
        ft.remove(fragmentToRemove);
        ft.commit();
    }
}