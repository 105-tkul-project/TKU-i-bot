package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Office extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private viewPagerAdapter adapter;

//add <activity android:name="你目前的頁面" android:parentActivityName="你要返回的主頁"></activity>
    // in AndroidMainfest.xml 返回建

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //添加返回建


        tabLayout =(TabLayout)findViewById(R.id.tablayout);
        viewPager =(ViewPager)findViewById(R.id.viewpaper);
        adapter= new viewPagerAdapter(getSupportFragmentManager());

        //add fragment Here
        adapter.AddFragment(new Office1(),"資創系");
        adapter.AddFragment(new Office2(),"語言系");
        adapter.AddFragment(new Office3(),"觀光系");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //這是把 tablayout 跟viewPager 綁在一起
    }
}
