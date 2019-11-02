package com.sankalp.aigen.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {carData.class},version = 1)
public abstract class CarsDatabase extends RoomDatabase {
    public abstract carsDAO carsDAO();
}
