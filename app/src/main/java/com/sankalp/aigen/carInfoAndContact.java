package com.sankalp.aigen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sankalp.aigen.data.carData;
import com.sankalp.aigen.data.dataWire;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class carInfoAndContact extends AppCompatActivity {

    List<carData> dataList = new ArrayList<>();
    TextView sellerName;
    TextView carName;
    TextView carPrice;
    TextView carSeats;
    TextView carCompany;
    TextView sellerContact;
    ImageView imageView;
    carData carData;
    Button button;
    static  int  pos=0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info_and_contact);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .6));
        dataList = dataWire.getResultsDataWire();
        pos=Integer.parseInt(getIntent().getStringExtra("pos"));
        carData = dataList.get(pos);

        sellerName = findViewById(R.id.textView);
        carName = findViewById(R.id.textView2);
        carCompany = findViewById(R.id.textView3);
        carSeats = findViewById(R.id.textView4);
        sellerContact = findViewById(R.id.textView5);
        carPrice = findViewById(R.id.textView6);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button3);

        sellerName.setText("Name: "+carData.getSellerName());
        carName.setText("Model: "+carData.getCarName());
        carCompany.setText("Comp: "+carData.getCarCompany());
        carSeats.setText("Seats: "+carData.getCarSeats());
        sellerContact.setText("Phone: "+carData.sellerContact);
        carPrice.setText(carData.getCarPrice());
        Picasso.with(getApplicationContext()).load(carData.carPicUrl).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent mIntent = new Intent(getApplicationContext(), emailSeller.class);
                View view = null;
                ActivityOptions activityOptions = null;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view=button;
                    activityOptions =ActivityOptions.makeSceneTransitionAnimation(carInfoAndContact.this,
                            Pair.create(view,"b1"));
                }
                startActivity(mIntent,activityOptions.toBundle());

            }
        });


    }
}
