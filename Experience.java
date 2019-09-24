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
import com.example.myapplication05.ResumePackage.Activity.ModelResume.ExperienceModel;
import com.google.gson.Gson;
import java.util.ArrayList;


public class Experience extends AppCompatActivity implements GestureDetector.OnGestureListener{
    EditText organization,designation,duration,technology,headingExp;
    ImageView back,edit,home;
    Button save,add;
    GestureDetector gestureDetector=new GestureDetector(this);
ArrayList<ExperienceModel> arrayList;

SharePreferance sharePreferance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        findViewById();
        sharePreferance = new SharePreferance(this);
        arrayList=new ArrayList<>();
        back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
                Intent intent=new Intent(Experience.this, ResumeHome.class);
                startActivity(intent);
        finish();
    }
});

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExperienceModel experienceModel=new ExperienceModel();
                experienceModel.setOrganization(organization.getText().toString());
                experienceModel.setDesignation(designation.getText().toString());
                experienceModel.setDuration(duration.getText().toString());
                experienceModel.setTechnology(technology.getText().toString());
                arrayList.add(experienceModel);
                organization.setText("");
                designation.setText("");
                duration.setText("");
                technology.setText("");
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Experience.this, Home.class);
                startActivity(intent);
                finish();

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExperienceModel experienceModel=new ExperienceModel();
                experienceModel.setOrganization(organization.getText().toString());
                experienceModel.setDesignation(designation.getText().toString());
                experienceModel.setDuration(duration.getText().toString());
                experienceModel.setTechnology(technology.getText().toString());
                arrayList.add(experienceModel);
                Gson gson=new Gson();
                String json=gson.toJson(arrayList);
                sharePreferance.saveHeadingExperience(headingExp.getText().toString());
                sharePreferance.saveExperienceList(json);
                Intent intent=new Intent(Experience.this, ResumeHome.class);
                startActivity(intent);
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                headingExp.setEnabled(true);
                headingExp.setSelection(headingExp.getText().length()-1);
            }
        });
    }
    public void findViewById(){
        organization=findViewById(R.id.organization);
        designation=findViewById(R.id.designation);
        duration=findViewById(R.id.duration);
        technology=findViewById(R.id.technology);
        save=findViewById(R.id.saveEx);
        add=findViewById(R.id.add);
        headingExp=findViewById(R.id.headingExp);
        back=findViewById(R.id.back);
        edit=findViewById(R.id.edit4);
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
