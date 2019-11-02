package com.sankalp.aigen;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sankalp.aigen.data.carData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class carsAdapter extends RecyclerView.Adapter<carsAdapter.ViewHolder> {
    List<carData> carDataList;
    Context context;


    public carsAdapter(List<carData> carDataList,Context context) {
        this.carDataList = carDataList;
        this.context =context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cars,parent,false);
        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        carData carData = carDataList.get(position);
        holder.sellerName.setText("Model: "+carData.getSellerName());
        holder.carName.setText("Model: "+carData.getCarName());
        holder.carCompany.setText("Phone: "+carData.getCarCompany());
        holder.carSeats.setText("Seats: "+carData.getCarSeats());
        holder.sellerContact.setText("Phone: "+carData.getSellerContact());
        holder.carPrice.setText(carData.carPrice);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+holder.sellerName.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,carInfoAndContact.class);
                intent.putExtra("pos",position+"");
                context.startActivity(intent);

            }
        });


        Picasso.with(context).load(carData.carPicUrl).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageView);
        new Thread(new Runnable() {
            @Override
            public void run() {

                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView sellerName;
         TextView carName;
         TextView carPrice;
         TextView carSeats;
         TextView carCompany;
         TextView sellerContact;
         ImageView imageView;
         CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sellerName = itemView.findViewById(R.id.textView);
            carName = itemView.findViewById(R.id.textView2);
            carCompany = itemView.findViewById(R.id.textView3);
            carSeats = itemView.findViewById(R.id.textView4);
            sellerContact = itemView.findViewById(R.id.textView5);
            carPrice = itemView.findViewById(R.id.textView6);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
