package com.example.android.dezcook.Activities;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.example.android.dezcook.HelperClasses.UIHelper;
import com.example.android.dezcook.R;

import net.cachapa.expandablelayout.ExpandableLinearLayout;

public class tourism extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tourtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        customize_textFont();
    }

public void customize_textFont()
{

    UIHelper.txtTypeFace(this,R.id.tourHeader);
    UIHelper.txtTypeFace(this,R.id.txttour_nature);
    UIHelper.txtTypeFace(this,R.id.content_Nature);
    UIHelper.txtTypeFace(this,R.id.txtHistory_title);
    UIHelper.txtTypeFace(this,R.id.txtmon_title);
    UIHelper.txtTypeFace(this,R.id.content_Monument);
}
public void Nature_toggle(View view)
{
    ExpandableLinearLayout expandableLinearLayout;
    expandableLinearLayout=(ExpandableLinearLayout)findViewById(R.id.Nature_expand);
    expandableLinearLayout.toggle();
}
public  void History_toggle(View view)
{
    ExpandableLinearLayout expandableLinearLayout;
    expandableLinearLayout=(ExpandableLinearLayout)findViewById(R.id.History_expand);
    expandableLinearLayout.toggle();
}
    public void Monument_toggle(View view)
    {
        ExpandableLinearLayout expandableLinearLayout;
        expandableLinearLayout=(ExpandableLinearLayout)findViewById(R.id.Monument_expand);
        expandableLinearLayout.toggle();
    }
}
