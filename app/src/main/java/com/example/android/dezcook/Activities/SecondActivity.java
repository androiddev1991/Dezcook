package com.example.android.dezcook.Activities;

import android.content.Intent;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.dezcook.Database.DataSource;
import com.example.android.dezcook.Database.DbHelper;
import com.example.android.dezcook.Adapter.Food_Adapter;
import com.example.android.dezcook.HelperClasses.UIHelper;
import com.example.android.dezcook.R;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private SQLiteDatabase sqLiteDatabase;
    private DataSource dsource;
   // public static Typeface face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        dsource=new DataSource(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.sectoolbar);
       setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UIHelper.CollapsingToolbarLayout(this,R.id.collapsing_toolbar,"لیست غذاها");
        //showing into card section
        show_card();


    }
    public void show_card()
    {
        RecyclerView reclist=(RecyclerView)findViewById(R.id.cardList);
        reclist.setHasFixedSize(true);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        reclist.setLayoutManager(llm);
        reclist.smoothScrollToPosition(1000);
        Food_Adapter fa=new Food_Adapter(dsource.GetListOfAll(),this);
        reclist.setAdapter(fa);


    }





    public void show_details(View view)
    {

        Food food=new Food();
        food=dsource.RetunFood(Integer.parseInt(UIHelper.tvgetText(this,R.id.txtid,view).toString()));
        Intent intent=new Intent(this,Foot_recipe.class);
        intent.putExtra("food_obj",food);
        startActivity(intent);
    }


 
}
