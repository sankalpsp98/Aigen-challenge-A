package com.sankalp.aigen;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sankalp.aigen.data.carData;
import com.sankalp.aigen.data.dataWire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateAds extends AppCompatActivity {
    EditText sellerName;
    EditText carName;
    EditText carPrice;
    EditText carSeats;
    EditText carCompany;
    EditText sellerContact;
    EditText urlX;
    private OneTimeWorkRequest oneTimeWorkRequest2;

    List<carData> cars = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ads);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sellerName = findViewById(R.id.textView8);
        carName = findViewById(R.id.textView9);
        carCompany = findViewById(R.id.textView10);
        carSeats = findViewById(R.id.textView11);
        sellerContact = findViewById(R.id.textView12);
        carPrice = findViewById(R.id.textView13);
        urlX =findViewById(R.id.textView14);
        urlX.setText("https://imgd.aeplcdn.com/1056x594/cw/ec/28286/BMW-X7-Right-Front-Three-Quarter-164106.jpg?wm=0");
        oneTimeWorkRequest2 = new OneTimeWorkRequest.Builder(workManagerInsert.class).setInitialDelay(1, TimeUnit.SECONDS).addTag("newWorker2").build();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sellerName.getText().toString().equals(""))
                {
                    sellerName.setError("please Enter Details");
                }else if (carName.getText().toString().equals(""))
                {
                    carName.setError("please Enter Details");
                }else if(carCompany.getText().toString().equals(""))
                {
                    carCompany.setError("please Enter Details");
                }else if(carSeats.getText().toString().equals(""))
                {
                    carSeats.setError("please Enter Details");
                }else if(sellerContact.getText().toString().equals(""))
                {
                    sellerContact.setError("please Enter Details");
                }else if (urlX.getText().toString().equals(""))
                {
                    urlX.setError("please Enter Details");
                }else {

                    Snackbar.make(view, "We are inserting Data.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    carData carData = new carData(sellerName.getText().toString(), carName.getText().toString(), carPrice.getText().toString(), carSeats.getText().toString(), carCompany.getText().toString(), sellerContact.getText().toString(), urlX.getText().toString());
                    cars.add(carData);
                    dataWire.setResultsDataWire(cars);
                    WorkManager.getInstance().beginWith(oneTimeWorkRequest2).enqueue();
                }

            }
        });
        WorkManager.getInstance().getStatusById(oneTimeWorkRequest2.getId()).observe(this, new Observer<WorkStatus>() {
            @Override
            public void onChanged(@Nullable WorkStatus listLiveData) {
                if (listLiveData != null && listLiveData.getState().isFinished()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), genDataOnce.class));
                            finish();
                        }
                    }, 1000);

                }

            }
        });
    }

}
