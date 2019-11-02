package com.sankalp.aigen;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.sankalp.aigen.data.carData;
import com.sankalp.aigen.data.dataWire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class genDataOnce extends AppCompatActivity {
    OneTimeWorkRequest oneTimeWorkRequest1;
    OneTimeWorkRequest oneTimeWorkRequest2;
    Random random;
    carData carDataX;
    dataWire dataWire = new dataWire();
    int price=0;
    String[] name = {"san P", "raj B", "Aaradhya A", "Alisha M", "Aarav V", "Kabir D", "Saisha B", "Laksh T", "Ranbir R", "Krisha P"};


    String[] urlList = {"https://imgd.aeplcdn.com/1056x594/cw/ec/37067/BMW-3-Series-Exterior-167583.jpg?wm=0", "https://imgd.aeplcdn.com/1056x594/cw/ec/26911/BMW-5-Series-Right-Front-Three-Quarter-172250.jpg?wm=0", "https://imgd.aeplcdn.com/1056x594/cw/ec/28286/BMW-X7-Right-Front-Three-Quarter-164106.jpg?wm=0", "https://imgd.aeplcdn.com/1056x594/cw/ec/32854/BMW-6-Series-GT-Right-Front-Three-Quarter-154025.jpg?wm=0", "https://imgd.aeplcdn.com/1056x594/cw/ec/41375/BMW-New-X6-Exterior-170088.jpg?wm=0", "https://imgd.aeplcdn.com/1056x594/cw/ec/41406/BMW-8-Series-Exterior-170077.jpg?wm=0", "https://imgd.aeplcdn.com/1056x594/cw/ec/37092/BMW-M2-Exterior-141232.jpg?wm=0", "https://imgd.aeplcdn.com/1056x594/cw/ec/26911/BMW-5-Series-Right-Front-Three-Quarter-172250.jpg?wm=0", "https://imgd.aeplcdn.com/1056x594/cw/ec/41406/BMW-8-Series-Exterior-170077.jpg?wm=0", "https://imgd.aeplcdn.com/1056x594/cw/ec/37092/BMW-M2-Exterior-141232.jpg?wm=0"};



    List<carData> cars = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_data_once);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        random = new Random();
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


            ExecuteTask();

        }
        else {
            Toast.makeText(getApplicationContext(),"Make Sure Internet/WIFI is ON To Load Car Img",Toast.LENGTH_LONG).show();
            ExecuteTask();
        }
    }

    private void ExecuteTask() {
        final SharedPreferences sharedPreferences12 = getSharedPreferences("data2", Context.MODE_PRIVATE);
        if (sharedPreferences12.getString("Email", "NA").equals("NA")) {
            SharedPreferences sharedPreferences1 = getSharedPreferences("data2", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putString("Email", "1");
            editor.apply();
            editor.commit();
            String a = sharedPreferences1.getString("Email", "NA");
            oneTimeWorkRequest2 = new OneTimeWorkRequest.Builder(workManagerInsert.class).setInitialDelay(1, TimeUnit.SECONDS).addTag("newWorker2").build();

            for (int i = 0; i < 10; i++) {

                price = random.nextInt(15);
                if (price == 0) {
                    price = 1;
                }
                Log.e("price ", "" + price);
                carDataX = new carData(name[i], "BMW V" + i, price + ",00,000$", 4 + "", "BMW", "9" + i + "28" + i + "89" + i + "9", urlList[i]);
                cars.add(carDataX);
            }

            dataWire.setResultsDataWire(cars);
            WorkManager.getInstance().beginWith(oneTimeWorkRequest2).enqueue();

            WorkManager.getInstance().getStatusById(oneTimeWorkRequest2.getId()).observe(this, new Observer<WorkStatus>() {
                @Override
                public void onChanged(@Nullable WorkStatus listLiveData) {
                    if (listLiveData != null && listLiveData.getState().isFinished()) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"this ",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), carsMainUI.class));
                                finish();
                            }
                        }, 1000);

                    }

                }
            });

            Toast.makeText(getApplicationContext(), "" + a, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "" + sharedPreferences12.getString("Email", "NA"), Toast.LENGTH_LONG).show();


        } else {
            oneTimeWorkRequest1 = new OneTimeWorkRequest.Builder(workManager.class).setInitialDelay(1, TimeUnit.SECONDS).addTag("newsWorker").build();
            WorkManager.getInstance().beginWith(oneTimeWorkRequest1).enqueue();

            WorkManager.getInstance().getStatusById(oneTimeWorkRequest1.getId()).observe(this, new Observer<WorkStatus>() {
                @Override
                public void onChanged(@Nullable WorkStatus listLiveData) {
                    if (listLiveData != null && listLiveData.getState().isFinished()) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), carsMainUI.class));
                            }
                        }, 1000);

                    }

                }
            });
        }

    }
}
