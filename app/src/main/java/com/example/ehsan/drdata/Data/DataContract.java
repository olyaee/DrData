package com.example.ehsan.drdata.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ehsan-HP on 20/07/2017.
 */

public class DataContract {

    public static final String CONTENT_AUTHORITY = "com.example.ehsan.drdata";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DATA = "data";

    public static final class DataEnty implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DATA).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DATA;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DATA;

        public static final String TABLE_NAME = "data";
        public static final String COLUMN_GIVEN_NAME = "given_name";
        public static final String COLUMN_SURE_NAME = "sure_name";
        public static final String COLUMN_PHONE = "phone_number";
        public static final String COLUMN_SEX = "sex";
        public static final String COLUMN_DISEASE = "disease";


    }




}
