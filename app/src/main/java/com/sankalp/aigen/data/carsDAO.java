package com.sankalp.aigen.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface carsDAO {
    @Query("SELECT *FROM carData")
    List<carData>  getAllCars();
    @Insert
    void insertAll(carData...carData);

}
