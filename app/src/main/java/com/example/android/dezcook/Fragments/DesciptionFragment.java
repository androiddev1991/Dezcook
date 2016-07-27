package com.example.android.dezcook.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.dezcook.Activities.Food;
import com.example.android.dezcook.Activities.Foot_recipe;
import com.example.android.dezcook.HelperClasses.UIHelper;
import com.example.android.dezcook.R;


public class DesciptionFragment extends Fragment {
    public DesciptionFragment()
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_desciption, container, false);

        Foot_recipe foot_recipe=(Foot_recipe)getActivity();

        Food food=foot_recipe.sendFrag_info();
        TextView textView=(TextView)v.findViewById(R.id.frag_id);

        UIHelper.setTvText(getActivity(),R.id.frag_id,food.getTxtDesc(),v);


        return v;
    }
}
