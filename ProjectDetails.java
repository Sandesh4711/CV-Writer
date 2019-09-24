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

public class ProjectDetails extends AppCompatActivity implements GestureDetector.OnGestureListener{
    ImageView back,edit,home;
    GestureDetector gestureDetector=new GestureDetector(this);
    EditText heading,title,description,duration;
    Button save;
    SharePreferance sharePreferance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        findViewById();
        sharePreferance = new SharePreferance(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePreferance.saveHeadingProjects(heading.getText().toString());
                sharePreferance.saveProjectDetails(title.getText().toString(),description.getText().toString(),duration.getText().toString());
                Intent intent=new Intent(ProjectDetails.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setEnabled(true);
                heading.setSelection(heading.getText().length()-1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProjectDetails.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProjectDetails.this, Home.class);
                startActivity(intent); finish();
            }
        });
    }
    public void findViewById(){
        back =findViewById(R.id.back);
        heading=findViewById(R.id.headingPro);
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        duration=findViewById(R.id.duration2);
        save=findViewById(R.id.savepr);
        edit=findViewById(R.id.edit5);
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
