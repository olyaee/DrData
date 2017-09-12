package com.example.ehsan.drdata;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ehsan-HP on 22/07/2017.
 */

public class SearchdataFragment extends Fragment {

    private FragmentActivity myContext;
    public View rootView;
    public Fragment_Down mFragment_Down;
    public Fragment_Top mFragment_Top;


    public SearchdataFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.searchdatafragment, container, false);

        mFragment_Down = new Fragment_Down();
        mFragment_Top = new Fragment_Top();

        FragmentTransaction transaction =
                myContext.getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.top_fragment, mFragment_Top);
        transaction.add(R.id.down_fragment, mFragment_Down);

        transaction.commit();

        return rootView;
    }
}
