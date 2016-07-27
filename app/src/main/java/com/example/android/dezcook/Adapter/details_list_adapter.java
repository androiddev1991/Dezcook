package com.example.android.dezcook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.dezcook.HelperClasses.UIHelper;
import com.example.android.dezcook.R;

/**
 * Created by Android on 6/13/2016.
 */
public class details_list_adapter extends BaseAdapter {
    String[] result;
   Typeface face;
    private Context mcontext;
    private static LayoutInflater inflater=null;
    public details_list_adapter(Context context,String[] prgmNameList)
    {
        result=prgmNameList;
        face = Typeface.createFromAsset(context.getAssets(), "font/byekan.ttf");
        this.mcontext =context;
        inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount()
    {
        return result.length;
    }
    @Override
    public Object getItem(int position)
    {
        return position;
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        Holder holder=new Holder();
        View rowView;
        rowView=inflater.inflate(R.layout.list_items_frag,null);
        holder.tv=(TextView)rowView.findViewById(R.id.tvItem_tag);
        holder.tv.setTypeface(face);

        holder.img=(ImageView)rowView.findViewById(R.id.item_img_tag);
        holder.tv.setText(result[position]);
        //  holder.tv.setTypeface(MainActivity.face);

        return rowView;
    }
}
