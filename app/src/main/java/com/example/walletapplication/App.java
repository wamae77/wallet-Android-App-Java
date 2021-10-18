package com.example.walletapplication;

import android.app.Application;
import android.app.Application;
import android.content.Context;

import com.example.walletapplication.models.DaoMaster;
import com.example.walletapplication.models.DaoSession;

import org.greenrobot.greendao.database.Database;


public class App extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "mainDatabase", null);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
