package com.example.android.dezcook.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.dezcook.Database.DataSource;
import com.example.android.dezcook.Database.DbHelper;
import com.example.android.dezcook.Fragments.DesciptionFragment;
import com.example.android.dezcook.HelperClasses.UIHelper;
import com.example.android.dezcook.R;
import com.example.android.dezcook.Fragments.Imagefragment;
import com.example.android.dezcook.Fragments.MaterialFragment;

import java.util.ArrayList;
import java.util.List;

public class Foot_recipe extends AppCompatActivity {
    /*Layout Properties define*/
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Food send_info;
    private int[] tabIcons = {
            R.drawable.tab_details_imagealbum,
            R.drawable.tab_details_cookie,
            R.drawable.tab_details_camera
    };
    /*End layout propeties Definition*/
    /*Start Logic definition from here*/

    /*End Logic Definition*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_recipe);

        //get data from intent sended from cardview
        String txtID = "";
        Intent intent = getIntent();

        Food food = intent.getParcelableExtra("food_obj");
        send_info = food;

        //tool bar customization
        UIHelper.setTvText(this,R.id.toolbar_title,food.getTxtName());
        toolbarCustomization();
        //Tab customization
        tabCustomization();

        //Customize floatting button
        ActionButton_fav(food.getTxtid());

    }

    public void ActionButton_fav(final int id) {
        final DataSource dataSource=new DataSource(this);
        FloatingActionButton fav = (FloatingActionButton) findViewById(R.id.fav_btn_heart);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dataSource.UpdateDb(id,"1")>0)
                    Snackbar.make(v, "به علاقه مندی ها اضافه شد", Snackbar.LENGTH_SHORT).show();
                else
                    Snackbar.make(v, "در لیست علاقه مندی ها قرار دارد", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public Food sendFrag_info() {
        return send_info;
    }

    /*Layout Section
    * All Layout code about food details are down side this page
    * */
    private void toolbarCustomization() {
        toolbar = (Toolbar) findViewById(R.id.foodrestoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void tabCustomization() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        tabLayout.getTabAt(2).select();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
		
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DesciptionFragment(), "دستور");
        adapter.addFragment(new MaterialFragment(), "مواد");
        adapter.addFragment(new Imagefragment(), "عکس");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }



}
