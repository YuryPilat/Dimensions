package com.example.managernew;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private final DBHelper dbHelper;
    private final SQLiteDatabase sqldb;

    public DBManager (Context context, String dbName) {
        dbHelper = new DBHelper(context, dbName);
        dbHelper.create_db(dbName);
        sqldb = dbHelper.open();
    }

    public Cursor getAllData(int tableName) {
        return sqldb.query(Root.getStringRes(tableName), null, null, null, null, null, null);
    }

    public Cursor getDataByParams(int tableName, int column_name, int id) {
        Cursor c = sqldb.query(Root.getStringRes(tableName), null, Root.getStringRes(column_name) + " = " + id, null, null, null, null);
        if (c != null) c.moveToFirst();
        return c;
    }
}