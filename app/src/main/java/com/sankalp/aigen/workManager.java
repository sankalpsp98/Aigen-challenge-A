package com.sankalp.aigen;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.work.Worker;

import com.sankalp.aigen.data.CarsDatabase;
import com.sankalp.aigen.data.carData;
import com.sankalp.aigen.data.dataWire;

import java.util.ArrayList;
import java.util.List;

public class workManager extends Worker {

    private static final String TAG = "MyPeriodicWork";
    List<carData>  carDataX;
    dataWire dataWire = new dataWire();


    @NonNull
    @Override
    public Result doWork() {

        carDataX = new ArrayList<>();

        Log.e(TAG, "doWork: Work is done.  "+carDataX.size()+" ");
        if (getData())
        {
            dataWire.setResultsDataWire(carDataX);
            return Result.SUCCESS;

        }else
        {
            return Result.RETRY;
        }
    }


    private boolean getData() {


        CarsDatabase db = Room.databaseBuilder(getApplicationContext(),CarsDatabase.class,"datahouse")
                .build();
        carDataX = db.carsDAO().getAllCars();
        try {
            Thread.sleep(400);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (carDataX!=null)
            {

                Log.e("we got it","we got in workM");
                return true;
            }
        }
        return false;
    }
}
