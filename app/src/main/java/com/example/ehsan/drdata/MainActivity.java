package com.example.ehsan.drdata;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }


    public static class PlaceholderFragment extends Fragment {
        private FragmentActivity myContext;
        public PlaceholderFragment() {
        }

        @Override
        public void onAttach(Activity activity) {
            myContext=(FragmentActivity) activity;
            super.onAttach(activity);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.fragmentmain, container, false);

//            Button btn_newData = (Button) rootView.findViewById(R.id.btn_newData);
//            btn_newData.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity( new Intent(getActivity(), NewDataActivity.class));
//
//                }
//            });
//            Button btn_search = (Button) rootView.findViewById(R.id.btn_search);
//            btn_search.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity( new Intent(getActivity(),SearchActivity.class));
//
//                }
//            });
            switchToFragment1();

            BottomNavigationView bottomNavigationView = (BottomNavigationView)
                    rootView.findViewById(R.id.bottom_navigation);

            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_add:
                                    switchToFragment1();
                                    break;
                                case R.id.action_search:
                                    switchToFragment2();
                                    break;
                            }
                            return false;
                        }
                    });
            return rootView;
        }

        public void switchToFragment1() {
            myContext.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new NewdataFragment()).commit();
        }
        public void switchToFragment2() {
            myContext.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new SearchdataFragment()).commit();
        }

    }

}

