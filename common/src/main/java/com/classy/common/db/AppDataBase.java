package com.classy.common.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.classy.common.entities.UsageInfo;
import com.classy.common.rest.UsageinfoAPI;

    @Database(entities = {UsageInfo.class}, version = 1)
    public abstract class AppDataBase extends RoomDatabase {
        public abstract UsageinfoAPI usageinfoAPI();

}
