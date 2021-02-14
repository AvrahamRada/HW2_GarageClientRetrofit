package com.classy.common.controllers;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

import com.classy.common.db.AppDataBase;
import com.classy.common.entities.UsageInfo;

import java.util.List;

public class AppUsageTime {

    private static AppUsageTime instance;
    private static AppDataBase appDatabase;

    private AppUsageTime(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                AppDataBase.class,
                "appTime.db").build();
    }

    public static AppUsageTime getInstance() {
        return instance;
    }

    public static AppUsageTime initHelper(Context context) {
        if (instance == null) {
            instance = new AppUsageTime(context);
        }
        return instance;
    }

    public interface CallBackTotalTime {
        void dataReady(long time);
    }

    public void timeSaved(long duration, String appName) {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                appDatabase.usageinfoAPI().SaveAppTime(new UsageInfo(duration,appName));
            }
        }).start();
    }

    public void finalTime(String appName, CallBackTotalTime callBack){
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                long totalScreenTime = 0;
                List<UsageInfo> screenTimeList = appDatabase.usageinfoAPI().getScreenTime(appName);
                for (UsageInfo time:screenTimeList) {
                    totalScreenTime += time.getDuration();
                }
                if (callBack!= null)
                    callBack.dataReady(totalScreenTime);
            }
        }).start();
    }
}
