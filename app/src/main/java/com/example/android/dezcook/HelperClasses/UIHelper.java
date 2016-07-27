package com.example.android.dezcook.HelperClasses;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.dezcook.R;

/**
 * Created by Android on 6/28/2016.
 */
public class UIHelper  {
    private static  Typeface typeface;
    private static  void settypeFace(Activity activity)
    {
        typeface = Typeface.createFromAsset(activity.getAssets(), "font/byekan.ttf");
    }



    public static void SetActionBar(int id, ActionBar actionBar)
    {
        actionBar.setHomeAsUpIndicator(id);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    public static void CollapsingToolbarLayout(Activity activity,int id,String title)
    {
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)activity.findViewById(id);
        collapsingToolbarLayout.setCollapsedTitleTypeface(typeface);
        collapsingToolbarLayout.setExpandedTitleTypeface(typeface);
        collapsingToolbarLayout.setTitle(title);
    }
//TextView Methods
    public static String tvgetText(Activity activity, int id) {
        TextView tv = (TextView) activity.findViewById(id);
        tv.setTypeface(typeface);
        return tv.getText().toString();
    }
    public static String tvgetText(Activity activity, int id,View view) {
        TextView tv = (TextView) view.findViewById(id);
        tv.setTypeface(typeface);
        return tv.getText().toString();
    }
    public static void setTvText(Activity activity, int id, String text) {
        TextView tv = (TextView) activity.findViewById(id);
        tv.setText(text);
        settypeFace(activity);
        tv.setTypeface(typeface);
    }
    public static void setTvText(Activity activity, int id, String text,View v) {
        TextView tv = (TextView) v.findViewById(id);
        tv.setText(text);
        settypeFace(activity);
        tv.setTypeface(typeface);
    }

    public static void txtTypeFace(Activity activity, int id) {
        TextView tv = (TextView) activity.findViewById(id);
        settypeFace(activity);
        tv.setTypeface(typeface);
    }
//Edit Text Methods
    public static String etgetText(Activity activity, int id) {
        EditText et = (EditText) activity.findViewById(id);
        et.setTypeface(typeface);
        return et.getText().toString();
    }
    public static void etTypeFace(Activity activity, int id) {
        EditText tv = (EditText) activity.findViewById(id);
        settypeFace(activity);
        tv.setTypeface(typeface);
    }


    public static boolean getCBChecked(Activity activity, int id) {
        CheckBox cb = (CheckBox) activity.findViewById(id);
        return cb.isChecked();
    }

    public static void setCBChecked(Activity activity, int id, boolean value) {
        CheckBox cb = (CheckBox) activity.findViewById(id);
        cb.setChecked(value);
    }

}
