package com.example.android.dezcook.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.dezcook.Activities.Food;
import com.example.android.dezcook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Android on 6/11/2016.
 */

public class Food_Adapter extends RecyclerView.Adapter<Food_Adapter.foodViewHolder>{
    private List<Food> foodList;
    private Context context;
    public static Typeface face;
    public Food_Adapter(List<Food> foodList,Context context)
    {
        this.foodList=foodList;
        this.context=context;
    }
    @Override
    public foodViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.card_layout,viewGroup,false);
        face = Typeface.createFromAsset(context.getAssets(), "font/byekan.ttf");
        return new foodViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    @Override
    public void onBindViewHolder(foodViewHolder foodViewHolder, int i) {
        Food food=foodList.get(i);
        foodViewHolder.txtName.setText(food.getTxtName());
        foodViewHolder.txtName.setTypeface(face);
        foodViewHolder.txtItems.setText(food.getItems());
        foodViewHolder.txtItems.setTypeface(face);
        foodViewHolder.txtId.setText(Integer.toString(food.getTxtid()));
        Picasso.with(context).load(food.getConverted_image())
                .resize(100,100).centerCrop().into(foodViewHolder.txtImage);

     //  foodViewHolder.txtImage.setImageResource(food.getConverted_image());




    }
//food.getTxtImage()


    public static class foodViewHolder extends RecyclerView.ViewHolder {

        protected TextView txtName;
        protected TextView txtItems;
        protected ImageView txtImage;
        protected TextView  txtId;

        public foodViewHolder(View v) {
            super(v);
            txtName=(TextView)v.findViewById(R.id.txtName);
            txtItems=(TextView)v.findViewById(R.id.txtItems);
            txtImage=(ImageView)v.findViewById(R.id.card_thumbnail);
            txtId=(TextView)v.findViewById(R.id.txtid);

        }
    }


}