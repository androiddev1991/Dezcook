package com.example.android.dezcook.Adapter;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class fav_recAdapter extends RecyclerView.Adapter<fav_recAdapter.foodViewHolder>
{
    private List<Food> foodList;
    private Context myContext;
    public static Typeface face;
    public fav_recAdapter(List<Food> foodList,Context context)
    {
        this.foodList=foodList;
        face = Typeface.createFromAsset(context.getAssets(), "font/byekan.ttf");
        this.myContext=context;
    }
    @Override
    public foodViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.scroll_view_item,viewGroup,false);
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
        foodViewHolder.txtId.setText(Integer.toString(food.getTxtid())+"");
        Log.i("FIND","got it: " +Integer.toString(food.getConverted_image()));
        try {
            //foodViewHolder.txtImage.setImageResource(food.getConverted_image());
            Picasso.with(myContext).load(food.getConverted_image()).centerCrop().resize(100,100).
                    into(foodViewHolder.txtImage);
        }catch (Exception ex)
        {
            Log.i("FINDError:","got it: " +Integer.toString(food.getConverted_image()));
            Log.i("FINDError:","got error"+ex.getMessage().toString());
        }
    }
//food.getTxtImage()


    public static class foodViewHolder extends RecyclerView.ViewHolder {

        protected TextView txtName;
        protected TextView txtItems;
        protected ImageView txtImage;
        protected TextView  txtId;

        public foodViewHolder(View v) {
            super(v);
            txtName=(TextView)v.findViewById(R.id.main_txtName);
            txtItems=(TextView)v.findViewById(R.id.main_txtItems);
            txtImage=(ImageView)v.findViewById(R.id.main_card_thumbnail);
            txtId=(TextView)v.findViewById(R.id.main_txtid);

        }

    }



}
