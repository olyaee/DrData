package com.example.ehsan.drdata;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ehsan.drdata.Data.DataContract;

/**
 * Created by Ehsan-HP on 20/07/2017.
 */

public class Fragment_Top extends Fragment {

    Button button_search;
    EditText editText_value;
    String value;
    String spinner_item;
    long spinner_id;
    String selection;
    String[] selectionArgs;


    public Fragment_Top() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top, null);
        handleSpinner();

        editText_value = (EditText) rootView.findViewById(R.id.editext_value);

        button_search = (Button) rootView.findViewById(R.id.btn_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = editText_value.getText().toString();

//                Toast.makeText(getActivity(), spinner_item, Toast.LENGTH_SHORT).show();

                switch ((int) spinner_id) {
                    case 0:
                        selection = DataContract.DataEnty.COLUMN_GIVEN_NAME + " = ?";
                        selectionArgs = new String[]{value};
                        break;
                    case 1:
                        selection = DataContract.DataEnty.COLUMN_SURE_NAME + " = ?";
                        selectionArgs = new String[]{value};
                        break;
                    case 2:
                        selection = DataContract.DataEnty.COLUMN_PHONE + " = ?";
                        selectionArgs = new String[]{value};
                        break;
                }

                Cursor cursor = getActivity().getContentResolver().query(DataContract.DataEnty.CONTENT_URI,
                        new String[]{DataContract.DataEnty.COLUMN_GIVEN_NAME, DataContract.DataEnty.COLUMN_SURE_NAME, DataContract.DataEnty.COLUMN_PHONE},
                        selection,
                        selectionArgs,
                        null);

                Fragment_Down.mForecastAdapter.clear();

                if (cursor.moveToFirst()) {
                    do {
                        int sureNameIndex = cursor.getColumnIndex(DataContract.DataEnty.COLUMN_SURE_NAME);
                        String sureName = cursor.getString(sureNameIndex);

                        int givenNameIndex = cursor.getColumnIndex(DataContract.DataEnty.COLUMN_GIVEN_NAME);
                        String givenName = cursor.getString(givenNameIndex);

                        int phoneIndex = cursor.getColumnIndex(DataContract.DataEnty.COLUMN_PHONE);
                        String phone = cursor.getString(phoneIndex);

                        String es = givenName + " " + sureName + " :" + phone;
                        Fragment_Down.mForecastAdapter.add(es);
                    } while (cursor.moveToNext());
                }else{
                    Toast.makeText(getActivity(),"Value is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.search_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(),(String) Long.toString(id), Toast.LENGTH_SHORT).show();
                spinner_item = (String) parent.getItemAtPosition(position);
                spinner_id = id;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return rootView;
    }
    public void handleSpinner() {
    }
}
