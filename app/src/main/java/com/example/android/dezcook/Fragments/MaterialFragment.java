package com.example.android.dezcook.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.dezcook.Activities.Food;
import com.example.android.dezcook.Activities.Foot_recipe;
import com.example.android.dezcook.Adapter.details_list_adapter;
import com.example.android.dezcook.R;


public class MaterialFragment extends Fragment {
    public MaterialFragment()
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_material, container, false);

        Foot_recipe foot_recipe=(Foot_recipe)getActivity();

        Food food=foot_recipe.sendFrag_info();

        String[] arr=food.getItems().split("،");
        
        Log.i("LogArr",arr[0]);
      //  ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_items_frag, arr);
        ListView listView = (ListView) v.findViewById(R.id.show_items);
        listView.setAdapter(new details_list_adapter(getActivity(),arr));

        return v;
    }

}
