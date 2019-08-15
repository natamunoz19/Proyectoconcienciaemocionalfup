package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HAIMER on 29/11/2018.
 */

public class Adaptador  extends FragmentStatePagerAdapter {
    public Adaptador(FragmentManager fm){ super(fm);}


    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Page();
        Bundle bundle = new Bundle();
        bundle.putInt("count", position + 1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
