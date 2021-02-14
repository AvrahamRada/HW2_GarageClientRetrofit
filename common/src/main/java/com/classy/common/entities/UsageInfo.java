package com.classy.common.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "usage_info")
public class UsageInfo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String appName;
    @ColumnInfo
    public long duration;

    @Ignore
    public UsageInfo(long duration, String appName) {
        this.appName = appName;
        this.duration = duration;
    }

    public UsageInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}

