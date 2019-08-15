package com.example.haimer.proyectoconcienciaemocionalfup.Actividad_1;

/**
 * Created by HAIMER on 19/10/2018.
 */
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Personal on 01/10/2018.
 */

public class Adaptador extends FragmentStatePagerAdapter {
    public Adaptador(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new page2();
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
