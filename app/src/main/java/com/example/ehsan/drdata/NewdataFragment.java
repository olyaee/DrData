package com.example.ehsan.drdata;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ehsan.drdata.Data.DataContract;

/**
 * Created by Ehsan-HP on 22/07/2017.
 */

public class NewdataFragment extends Fragment {

    public String[] results;
    public String sexResult;
    public String diseaseResult;
    public String givenName;
    public String sureName;
    public String phoneNumber;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private RadioGroup radioDiseaseGroup;
    private RadioButton radioDiseaseButton;
    private EditText editGivenName;
    private EditText editSureName;
    private EditText editPhoneNumber;
    private Button saveButton;
    public View rootView;
    private FragmentActivity myContext;


    public NewdataFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.newdatafragment, container, false);
        handleRadioButtons();
        handleSaveButton();

        return rootView;
    }
    public void handleRadioButtons() {
        sexResult = "female";
        diseaseResult = "no";
        radioSexGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup_sex);
        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_female:
                        sexResult = "female";
                        break;
                    case R.id.radio_male:
                        sexResult = "male";

                        break;
                }

            }
        });
        radioDiseaseGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup_disease);
        radioDiseaseGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_heartDeasies_yes:
                        diseaseResult = "yes";
                        break;
                    case R.id.radio_heartDeasies_no:
                        diseaseResult = "no";
//                        Toast.makeText(NewDataActivity.this, "no", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

    }

    public void handleSaveButton(){
        saveButton = (Button) rootView.findViewById(R.id.btn_save);
        editGivenName = (EditText) rootView.findViewById(R.id.editText_givenName);
        editSureName = (EditText)rootView.findViewById(R.id.editText_sureName);
        editPhoneNumber = (EditText)rootView.findViewById(R.id.editText_phoneNumber);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                givenName = editGivenName.getText().toString();
                sureName = editSureName.getText().toString();
                phoneNumber = editPhoneNumber.getText().toString();

                if (givenName.equals("")| sureName.equals("")
                        | phoneNumber.equals("")) {
                    Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                }else if(Exists()){
                    Toast.makeText(getActivity(), "This Record Exists", Toast.LENGTH_LONG).show();
                }else{
                    results = new String[5];
                    results[0] = givenName.toString();
                    results[1] = sureName.toString();
                    results[2] = phoneNumber.toString();
                    results[3] = sexResult;
                    results[4] = diseaseResult;
                    addLocation(results[0], results[1], results[2], results[3], results[4]);
                    Toast.makeText(getActivity(), "New Record Saved", Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    public boolean Exists() {


        String[] columns =  new String[]{DataContract.DataEnty.COLUMN_GIVEN_NAME,
                DataContract.DataEnty.COLUMN_SURE_NAME, DataContract.DataEnty.COLUMN_PHONE};
        String selection = DataContract.DataEnty.COLUMN_GIVEN_NAME + " =?" + " AND " +
                DataContract.DataEnty.COLUMN_SURE_NAME + " =?" + " AND " +
                DataContract.DataEnty.COLUMN_PHONE + " =?" ;
        String[] selectionArgs = { givenName.toString(), sureName.toString(), phoneNumber.toString()  };
//        String limit = "1";

        Cursor cursor = myContext.getContentResolver().query(DataContract.DataEnty.CONTENT_URI,
                columns,
                selection,
                selectionArgs,
                null,
                null);

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public long addLocation(String givenName, String sureName, String phoneNumber, String sex , String Disease) {
        long locationId;

        // Now that the content provider is set up, inserting rows of data is pretty simple.
        // First create a ContentValues object to hold the data you want to insert.
        ContentValues addData = new ContentValues();

        // Then add the data, along with the corresponding name of the data type,
        // so the content provider knows what kind of value is being inserted.
        addData.put(DataContract.DataEnty.COLUMN_GIVEN_NAME, givenName);
        addData.put(DataContract.DataEnty.COLUMN_SURE_NAME, sureName);
        addData.put(DataContract.DataEnty.COLUMN_PHONE, phoneNumber);
        addData.put(DataContract.DataEnty.COLUMN_SEX, sex);
        addData.put(DataContract.DataEnty.COLUMN_DISEASE, Disease);

        Log.e("Ehsan", DataContract.DataEnty.CONTENT_URI.toString());

        // Finally, insert location data into the database.
        Uri insertedUri = myContext.getContentResolver().insert(
                DataContract.DataEnty.CONTENT_URI,
                addData
        );

        // The resulting URI contains the ID for the row.  Extract the locationId from the Uri.
        locationId = ContentUris.parseId(insertedUri);

        // Wait, that worked?  Yes!
        return locationId;
    }
}
