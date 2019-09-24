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
import android.widget.RelativeLayout;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.Home;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.ResumeHome;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class Education extends AppCompatActivity implements GestureDetector.OnGestureListener{
    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5;
Button master,bachelor,twelth,tenth;
    SharePreferance sharedPreference;
GestureDetector gestureDetector=new GestureDetector(this);

EditText heading,courseM,streamM,instituteM,cgpaM,yearM,courseB,streamB,instituteB,cgpaB,yearB,courseT,streamT,instituteT,cgpaT,yearT,courseH,streamH,instituteH,cgpaH,yearH;
    ImageView back,home,edit;
    Button saveid,saveidB,saveidT,saveidH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        findViewById();
        expandableLayout1.collapse();
        expandableLayout2.collapse();
        expandableLayout3.collapse();
        expandableLayout4.collapse();
        sharedPreference = new SharePreferance(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.saveHeadingEducation(heading.getText().toString());
                Intent intent = new Intent(Education.this, ResumeHome.class);
                startActivity(intent);
                finish();
            }
        });
        saveid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreference.saveMaster(courseM.getText().toString(),streamM.getText().toString(),instituteM.getText().toString(),cgpaM.getText().toString(),yearM.getText().toString());
                expandableButton1(view);
            }
        });
        saveidB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.saveBachelor(courseB.getText().toString(),streamB.getText().toString(),instituteB.getText().toString(),cgpaB.getText().toString(),yearB.getText().toString());
                expandableButton2(view);

            }
        });
        saveidT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.saveTwelth(courseT.getText().toString(),streamT.getText().toString(),instituteT.getText().toString(),cgpaT.getText().toString(),yearT.getText().toString());
                expandableButton3(view);
            }
        });
        saveidH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.saveTenth(courseH.getText().toString(),instituteH.getText().toString(),cgpaH.getText().toString(),yearH.getText().toString());
                expandableButton4(view);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setEnabled(true);
                heading.setSelection(heading.getText().length());
            }
        });        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Education.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void expandableButton1(View view) {

        expandableLayout1.toggle();
        expandableLayout2.collapse();
        expandableLayout3.collapse();
        expandableLayout4.collapse();
        if (!expandableLayout1.isExpanded()){
            master.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_up,0);
            bachelor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
            twelth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
            tenth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
        }
        else if (expandableLayout1.isExpanded()){
            master.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
        }
    }

    public void expandableButton2(View view) {

        expandableLayout2.toggle();
        expandableLayout1.collapse();
        expandableLayout3.collapse();
        expandableLayout4.collapse();
        if (!expandableLayout2.isExpanded()){
            master.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
            bachelor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_up,0);
            twelth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
            tenth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
        }
        else if (expandableLayout2.isExpanded()){
            bachelor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
        }
    }

    public void expandableButton3(View view) {

        expandableLayout3.toggle();
        expandableLayout2.collapse();
        expandableLayout1.collapse();
        expandableLayout4.collapse();
        if (!expandableLayout3.isExpanded()){
            master.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
            twelth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_up,0);
            bachelor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
            tenth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
        }
        else if (expandableLayout3.isExpanded()){
            twelth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
        }
    }

    public void expandableButton4(View view) {

        expandableLayout4.toggle();
        expandableLayout2.collapse();
        expandableLayout3.collapse();
        expandableLayout1.collapse();
        if (!expandableLayout4.isExpanded()){
            master.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
            tenth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_up,0);
            twelth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
            bachelor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
        }
        else if (expandableLayout4.isExpanded()){
            tenth.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_drop_down_black_24dp,0);
        }
    }
    public void findViewById(){
        expandableLayout1 =  findViewById(R.id.expandableLayout1);
        expandableLayout2 = findViewById(R.id.expandableLayout2);
        expandableLayout3 =  findViewById(R.id.expandableLayout3);
        expandableLayout4 =  findViewById(R.id.expandableLayout4);
        master=findViewById(R.id.Masters);
        bachelor=findViewById(R.id.Bachelors);
        twelth=findViewById(R.id.Twelth);
        tenth=findViewById(R.id.Tenth);
        back=findViewById(R.id.back);
        courseM=findViewById(R.id.course);
        courseB=findViewById(R.id.courseB);
        courseT=findViewById(R.id.courseT);
        courseH=findViewById(R.id.courseH);
        streamM=findViewById(R.id.stream);
        streamB=findViewById(R.id.streamB);
        streamT=findViewById(R.id.streamT);
        instituteM=findViewById(R.id.institute);
        instituteB=findViewById(R.id.instituteB);
        instituteT=findViewById(R.id.instituteT);
        instituteH=findViewById(R.id.instituteH);
        cgpaM=findViewById(R.id.cgpa);
        cgpaB=findViewById(R.id.cgpaB);
        cgpaT=findViewById(R.id.cgpaT);
        cgpaH=findViewById(R.id.cgpaH);
        yearM=findViewById(R.id.year);
        yearB=findViewById(R.id.yearB);
        yearT=findViewById(R.id.yearT);
        yearH=findViewById(R.id.yearH);
        saveid=findViewById(R.id.saveIt);
        saveidB=findViewById(R.id.saveItB);
        saveidT=findViewById(R.id.saveItT);
        saveidH=findViewById(R.id.saveItH);
        heading=findViewById(R.id.headingEdu);
        edit=findViewById(R.id.edit3);
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
