package com.example.android.dezcook.Activities;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PointF;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.android.dezcook.Database.DataSource;
import com.example.android.dezcook.Database.DbHelper;
import com.example.android.dezcook.HelperClasses.UIHelper;
import com.example.android.dezcook.R;
import com.example.android.dezcook.SimpleDividerItemDecoration;
import com.example.android.dezcook.Adapter.fav_recAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//define variable
    private SQLiteDatabase sqLiteDatabase;
    private DrawerLayout mDrawerLayout;
    private DbHelper dbHelper;
    private DataSource dsource;
    private fav_recAdapter fa;
    private ItemTouchHelper itemTouchHelper;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavTypeFace();
        dsource=new DataSource(this);
        //Toolbar customization
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarCustomization(toolbar);
        //-------------------

        //----ColapseToolbar customization
        UIHelper.CollapsingToolbarLayout(this,R.id.collapsingToolbar,"غذاهای محلی دزفول ");

        //Modify drawer
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerId);

        //RecyclerView configuration
        dbHelper = new DbHelper(this);
        try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        show_card();
    }

    public void NavTypeFace()
    {
        UIHelper.txtTypeFace(this,R.id.tvlstName);
        UIHelper.txtTypeFace(this,R.id.tvlstName1);
        UIHelper.txtTypeFace(this,R.id.tvlstName2);
        UIHelper.txtTypeFace(this,R.id.tvlstName3);
        UIHelper.txtTypeFace(this,R.id.tvlstName4);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    private void toolbarCustomization(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        UIHelper.SetActionBar(R.drawable.actionbar_icon_ic_menu,actionBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item != null && item.getItemId() == R.id.btnMyMenu) {
            if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
            } else {
                mDrawerLayout.openDrawer(Gravity.RIGHT);
            }
            return true;
        }
        return false;
    }

    public void show_card()
    {
        List<Food> lstFood;
        TextView empty=(TextView)findViewById(R.id.empty_fav_list);
        UIHelper.txtTypeFace(this,R.id.empty_fav_list);

        ItemTouchHelper itemTouchHelper =new ItemTouchHelper(simpleCallbackItemTouchHelper);
        RecyclerView reclist=(RecyclerView)findViewById(R.id.fav_cardList);
        reclist.addItemDecoration(new SimpleDividerItemDecoration(this));
        reclist.setHasFixedSize(true);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        reclist.setLayoutManager(llm);
        reclist.smoothScrollToPosition(1000);
        Log.i("finish1","LayoutManager seted correctly");

        lstFood=dsource.GetListOfFav();
        if(lstFood.size()<=0) {
            empty.setVisibility(View.VISIBLE);

        }
        else
            empty.setVisibility(View.GONE);

        fa=new fav_recAdapter(lstFood,this);
        reclist.setAdapter(fa);
        itemTouchHelper.attachToRecyclerView(reclist);
        Log.i("finish1","fav_recAdapter seted correctly");
    }




    public Cursor SQLCommand(SQLiteDatabase sqLiteDatabase, String sql) {
        return   sqLiteDatabase.rawQuery(sql,null);
    }
  public void show_card (View view)
   {

       Food food=new Food();
food=dsource.RetunFood(Integer.parseInt( UIHelper.tvgetText(this,R.id.main_txtid,view).toString()));

       Intent intent=new Intent(this,Foot_recipe.class);
       intent.putExtra("food_obj",food);
       startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        show_card();
    }
    ItemTouchHelper.SimpleCallback simpleCallbackItemTouchHelper=new
            ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | itemTouchHelper.DOWN,
                    ItemTouchHelper.RIGHT){
                private List<Food> lstFood=new ArrayList<Food>();
                @Override
                public boolean onMove(RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder,RecyclerView.ViewHolder target)
                {

                    final int fromPosition=viewHolder.getAdapterPosition();
                    final int toPosition=target.getAdapterPosition();

                    Food prev=lstFood.remove(fromPosition);
                    lstFood.add(toPosition>fromPosition?toPosition-1:toPosition,prev);
                    fa.notifyItemMoved(fromPosition,toPosition);

                    fa.notifyItemMoved(fromPosition,toPosition);
                    return true;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    int position=viewHolder.getAdapterPosition();
                   // lstFood=Configure_bank();
                    lstFood=dsource.GetListOfFav();
                    Food foods=lstFood.get(position);

                    dsource.UpdateDb(foods.getTxtid(),"0");
                    show_card();
                    lstFood.remove(position);
                    fa.notifyDataSetChanged();
                }
            };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FrameLayout frameLayout=(FrameLayout)
                findViewById(R.id.header_pic);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            frameLayout.setVisibility(View.GONE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            frameLayout.setVisibility(View.VISIBLE);
        }
    }
    public void openSecondActivity(View view)
    {
        Intent intent=new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    public void openDezfulActivity(View view)
    {
        Intent intent=new Intent(this,Dezful.class);
        startActivity(intent);
    }
    public void openTouristActivity(View view)
    {
        Intent intent=new Intent(this,tourism.class);
        startActivity(intent);
    }
    public void openContactActivity(View view)
    {
        Intent intent=new Intent(this,ContactUs.class);
        startActivity(intent);
    }
    public void exitApp(View view)
    {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }



}
