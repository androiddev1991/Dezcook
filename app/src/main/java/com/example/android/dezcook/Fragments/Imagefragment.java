package com.example.android.dezcook.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.dezcook.Activities.Food;
import com.example.android.dezcook.Activities.Foot_recipe;
import com.example.android.dezcook.R;
import com.squareup.picasso.Picasso;


public class Imagefragment extends Fragment {
    public Imagefragment()
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image, container, false);

        Foot_recipe foot_recipe=(Foot_recipe)getActivity();
        ImageView txt=(ImageView) v.findViewById(R.id.Image_pic);
        Food food=foot_recipe.sendFrag_info();
        Picasso.with(getActivity()).load(food.getConverted_image()).centerCrop()
                .resize(500,500).into(txt);
        //txt.setImageResource(food.getConverted_image());


        return v;
    }
}
