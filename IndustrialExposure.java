package com.example.myapplication05.ResumePackage.Activity.Tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.Home;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.ResumeHome;

public class IndustrialExposure extends AppCompatActivity implements GestureDetector.OnGestureListener{
    ImageView back,edit,home;
    EditText industry,heading;
    GestureDetector gestureDetector=new GestureDetector(this);
    Button save;
    SharePreferance sharePreferance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industrial_exposure);
        findViewById();
        sharePreferance = new SharePreferance(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePreferance.saveHeadingIndustry(heading.getText().toString());
                sharePreferance.saveIndustrial(industry.getText().toString());
                Intent intent=new Intent(IndustrialExposure.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IndustrialExposure.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setEnabled(true);
                heading.setSelection(heading.getText().length());
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IndustrialExposure.this, Home.class);
                startActivity(intent); finish();
            }
        });
    }
    public void findViewById(){
        back=findViewById(R.id.back);
        edit=findViewById(R.id.edit23);
        industry=findViewById(R.id.industryName);
        save=findViewById(R.id.saveInd);
        heading=findViewById(R.id.headingInd);
        home=findViewById(R.id.homeIcon);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        boolean result = false;
        float diffY=motionEvent1.getY()-motionEvent.getY();
        float diifX=motionEvent1.getX()-motionEvent.getX();
        if (Math.abs(diifX)>Math.abs(diffY)){
            if (Math.abs(diifX)>100&&Math.abs(v)>100){
                if (diifX>0){
                    back.callOnClick();

                }
                else {
                }
            }
        }
        else{
            if (Math.abs(diffY)>100&&Math.abs(v1)>100){
                if (diffY>0){
                }
                else{
                }
            }
        }
        return result;
    }
}
