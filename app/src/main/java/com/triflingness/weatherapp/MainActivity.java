package com.triflingness.weatherapp;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    public final static int LOOPS = 1000;
    public CarouselPagerAdapter adapter;
    public ViewPager pager;
    public static int count = 10; //ViewPager items size
    /**
     * You shouldn't define first page = 0.
     * Let define firstpage = 'number viewpager size' to make endless carousel
     */
    public static int FIRST_PAGE = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.myviewpager);
        pagerActivity();
        pager = (ViewPager) findViewById(R.id.myviewpager2);
        pagerActivity();

    }

    private void pagerActivity() {
        //set page margin between pages for viewpager
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 4) * 2);
        pager.setPageMargin(-pageMargin);

        adapter = new CarouselPagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        pager.addOnPageChangeListener(adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(FIRST_PAGE);
        pager.setOffscreenPageLimit(3);
    }
}