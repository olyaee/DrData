package com.example.ehsan.drdata.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Ehsan-HP on 20/07/2017.
 */

public class DataProvider extends ContentProvider {

    static final int data = 100;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DbHelper mOpenHelper;

    static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DataContract.CONTENT_AUTHORITY;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, DataContract.PATH_DATA, data);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {

            case data: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        DataContract.DataEnty.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

        @Nullable
        @Override
        public String getType (Uri uri){
            return null;
        }

        @Nullable
        @Override
        public Uri insert (Uri uri, ContentValues values){

            final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            final int match = sUriMatcher.match(uri);
            Uri returnUri;

            switch (match) {
                case data: {
                    long _id = db.insert(DataContract.DataEnty.TABLE_NAME, null, values);

                    if (_id > 0)
                        returnUri = ContentUris.withAppendedId(DataContract.DataEnty.CONTENT_URI, _id);

                    else
                        throw new android.database.SQLException("Failed to insert row into " + uri);
                    break;
                }
                default:
                    throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return returnUri;
        }

        @Override
        public int delete (Uri uri, String selection, String[]selectionArgs){
            return 0;
        }

        @Override
        public int update (Uri uri, ContentValues values, String selection, String[]selectionArgs){
            return 0;
        }
    }
