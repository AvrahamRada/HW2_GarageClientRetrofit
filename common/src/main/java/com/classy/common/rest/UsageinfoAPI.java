package com.classy.common.rest;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.classy.common.entities.UsageInfo;

import java.util.List;

@Dao
public interface UsageinfoAPI {

    @Insert
    void SaveAppTime(UsageInfo time);

    @Query("SELECT * FROM usage_info WHERE appName LIKE :appName")
    List<UsageInfo> getScreenTime(String appName);
}
