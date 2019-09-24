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

public class Interests extends AppCompatActivity implements GestureDetector.OnGestureListener{
    ImageView back,home;
    GestureDetector gestureDetector=new GestureDetector(this);
    EditText interests,heading;
    Button save;
    SharePreferance sharePreferance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        findViewById();
        sharePreferance = new SharePreferance(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePreferance.saveHeadingInterests(heading.getText().toString());
                sharePreferance.saveInterests(interests.getText().toString());
                Intent intent=new Intent(Interests.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Interests.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Interests.this, Home.class);
                startActivity(intent); finish();
            }
        });
    }
    public void findViewById(){
        back=findViewById(R.id.back);
        interests=findViewById(R.id.interests);
        save=findViewById(R.id.saveInt);
        heading=findViewById(R.id.headindInterests);
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
