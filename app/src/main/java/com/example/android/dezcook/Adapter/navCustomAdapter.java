package com.example.android.dezcook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.dezcook.Activities.ContactUs;
import com.example.android.dezcook.Activities.Dezful;
import com.example.android.dezcook.Activities.MainActivity;
import com.example.android.dezcook.Activities.SecondActivity;
import com.example.android.dezcook.Activities.tourism;
import com.example.android.dezcook.R;

/**
 * Useless Class Just for fun
 */
public class navCustomAdapter extends BaseAdapter {

    String[] result;
    Context context;
    int[] imageId;
    public static Typeface face;
    private static LayoutInflater inflater=null;
    public navCustomAdapter(MainActivity mainActivity, String[] prgmNameList, int[] prgImages)
    {
        face = Typeface.createFromAsset(mainActivity.getAssets(), "font/byekan.ttf");
        result=prgmNameList;
        imageId=prgImages;
        context =mainActivity;
        inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
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
        //I want to change nav_navItem to nav header because i want to remove error
        rowView=inflater.inflate(R.layout.nav_header,null);
        holder.tv=(TextView)rowView.findViewById(R.id.tvlstName);

        holder.tv.setTypeface(face);
        holder.img=(ImageView)rowView.findViewById(R.id.img);
        holder.tv.setText(result[position]);

      //  holder.tv.setTypeface(MainActivity.face);
        holder.img.setImageResource(imageId[position]);
       rowView.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           if(result[position]=="فهرست غذاها".trim().toString())
                                           {

                                               context.startActivity(new Intent(context, SecondActivity.class));
                                           }
                                            else if(result[position]=="دزفول".trim().toString()){
                                               context.startActivity(new Intent(context,Dezful.class));
                                           }
                                           else if(result[position]=="گردشگری".trim().toString()){
                                               context.startActivity(new Intent(context,tourism.class));
                                           }
                                           else if(result[position]=="تماس با ما".trim().toString()){
                                               context.startActivity(new Intent(context,ContactUs.class));
                                           }
                                           else if(result[position]=="خروج".trim().toString()){

                                               android.os.Process.killProcess(android.os.Process.myPid());
                                               System.exit(1);
                                           }
                                       }
                                  }


        );
        return rowView;
    }

}
