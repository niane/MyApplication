package com.yzg.myapplication.model.orm.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yzg.myapplication.model.bean.DaoMaster;
import com.yzg.myapplication.model.bean.DaoSession;

import javax.inject.Inject;

/**
 * Created by yzg on 2017/5/25.
 */

public class GDataBaseHelper {
    private final static String DB_NAME = "area.db";
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    @Inject
    public GDataBaseHelper(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
    }

    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    public DaoSession getReadableSession(){
        return new DaoMaster(getReadableDatabase()).newSession();
    }

    public DaoSession getWritableSession(){
        return new DaoMaster(getWritableDatabase()).newSession();
    }
}
