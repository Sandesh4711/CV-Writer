package com.example.myapplication05.ResumePackage.Activity.ActivityResume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.Adapter.CustomViewPager;
import com.example.myapplication05.ResumePackage.Activity.Adapter.PrefManager;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class intro extends AppCompatActivity {
ViewPager viewPager;
DotsIndicator dotsIndicator;
TextView next,skip;
CustomViewPager customViewPager;
int [] resources={
        R.drawable.intro1,
        R.drawable.intro2,
        R.drawable.intro3
};
PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        prefManager =new PrefManager(this);
        findViewById();

        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        customViewPager=new CustomViewPager(this,resources);
        viewPager.setAdapter(customViewPager);
        dotsIndicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if (current < resources.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHomeScreen();
            }
        });
    }
    public void findViewById(){
        viewPager=findViewById(R.id.pagerIntro);
        dotsIndicator=findViewById(R.id.dots_indicator_Intro);
        next=findViewById(R.id.nextBtn);
        skip=findViewById(R.id.skipBtn);
    }
    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == resources.length - 1) {
                // last page. make button text to GOT IT
                next.setText("Start");
                skip.setVisibility(View.GONE);
            } else {
                // still pages are left
                next.setText("Next");
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(intro.this, Home.class));
        finish();
    }

}
