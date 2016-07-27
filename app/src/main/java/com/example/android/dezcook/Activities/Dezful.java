package com.example.android.dezcook.Activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.dezcook.HelperClasses.UIHelper;
import com.example.android.dezcook.R;

public class Dezful extends AppCompatActivity {
    TextView txtName_content;
    TextView txtHistory_content;
    TextView txtHistory_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dezful);
        Toolbar toolbar = (Toolbar) findViewById(R.id.deztoolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtName_content=(TextView) findViewById(R.id.txtNamehistory_content);
        txtName_content.setVisibility(View.GONE);
        UIHelper.txtTypeFace(this,R.id.txtNamehistory_content);

        txtHistory_content=(TextView) findViewById(R.id.txtgravityhistory_content);
        txtHistory_content.setVisibility(View.GONE);
        UIHelper.txtTypeFace(this,R.id.txtgravityhistory_content);

        txtHistory_back=(TextView) findViewById(R.id.txtmonument_content);
        txtHistory_back.setVisibility(View.GONE);
        UIHelper.txtTypeFace(this,R.id.txtmonument_content);


    }
    public  void slide_down(Context ctx, View v)
    {
        Animation a= AnimationUtils.loadAnimation(ctx,R.anim.slide_down);
        if(a!=null) {
            a.reset();
            if(v!=null){
                v.clearAnimation();
                v.startAnimation(a);
            }

        }
    }
    private void slide_up(Context ctx,View v) {
        Animation a= AnimationUtils.loadAnimation(ctx,R.anim.slide_up);
        if(a!=null) {
            a.reset();
            if(v!=null){
                v.clearAnimation();
                v.startAnimation(a);
            }

        }
    }
    public void toggle_contents(View v){
        if(txtName_content.isShown()){
            slide_up(this,txtName_content);
            txtName_content.setVisibility(View.GONE);
        }
        else{
            txtName_content.setVisibility(View.VISIBLE);
            slide_down(this,txtName_content);
        }
    }
    public void toggle_contents1(View v){

        if(txtHistory_content.isShown()){
            slide_up(this,txtHistory_content);
            txtHistory_content.setVisibility(View.GONE);
        }
        else{
            txtHistory_content.setVisibility(View.VISIBLE);
            slide_down(this,txtHistory_content);
        }
    }
    public void toggle_contents2(View v){

        if(txtHistory_back.isShown()){
            slide_up(this,txtHistory_back);
            txtHistory_back.setVisibility(View.GONE);
        }
        else{
            txtHistory_back.setVisibility(View.VISIBLE);
            slide_down(this,txtHistory_back);
        }
    }
}
