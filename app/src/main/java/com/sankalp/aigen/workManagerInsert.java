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

public class workManagerInsert extends Worker {

    private static final String TAG = "MyPeriodicWork 2";
    List<carData> carDataX;
    dataWire dataWire = new dataWire();

    @NonNull
    @Override
    public Result doWork() {

        carDataX = new ArrayList<>();
        carDataX = dataWire.getResultsDataWire();

        Log.e(TAG, "doWork: Work  "+carDataX.size()+" ");
        if (setData())
        {

            Log.e(TAG, "doWork: Work is done. inserted  "+carDataX.size()+" ");
            return Result.SUCCESS;

        }else
        {
            return Result.RETRY;
        }

    }

    private boolean setData() {
        CarsDatabase db = Room.databaseBuilder(getApplicationContext(),CarsDatabase.class,"datahouse")
                .build();
        for (int i=0;i<carDataX.size();i++) {
            db.carsDAO().insertAll(carDataX.get(i));
        }
        try {
            Thread.sleep(400);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (carDataX!=null)
            {

                Log.e("we got it","we got in workm insert");
                return true;
            }
        }


        return false;
    }
}
