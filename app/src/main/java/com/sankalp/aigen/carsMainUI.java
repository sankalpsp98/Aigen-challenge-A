package com.sankalp.aigen;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sankalp.aigen.data.CarsDatabase;
import com.sankalp.aigen.data.carData;
import com.sankalp.aigen.data.dataWire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class carsMainUI extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<carData> carDataList;
    carData carDataX;
    dataWire dataWire = new dataWire();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_main_ui);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rc1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carDataList = new ArrayList<>();
//        for (int i =0;i<10;i++)
//////        {
//////            carDataX = new carData("san"+i,"","","","","");
//////
//////            carDataList.add(carDataX);
//////        }
        carDataList = dataWire.getResultsDataWire();
        adapter= new carsAdapter(carDataList,this);
        recyclerView.setAdapter(adapter);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                carData carData = new carData("sankalp","BMW V5","100000$","4","BMW","88795779","http://sfwallpaper.com/images/car-pic-9.jpg");


              startActivity(new Intent(getApplicationContext(),CreateAds.class));
            }
        });
    }

}
