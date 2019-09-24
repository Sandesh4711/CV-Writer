package com.example.myapplication05.ResumePackage.Activity.Tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.Home;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.ResumeHome;

public class Reference extends AppCompatActivity implements GestureDetector.OnGestureListener{
    EditText name,designation,organization,email,phone,heading;
    ImageView back,edit,home;
    Button save;
    GestureDetector gestureDetector=new GestureDetector(this);
SharePreferance sharePreferance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        sharePreferance=new SharePreferance(this);
        findViewById();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePreferance.saveHeadingReference(heading.getText().toString());
                sharePreferance.saveReferenceDetails(name.getText().toString(),designation.getText().toString(),organization.getText().toString(),email.getText().toString(),phone.getText().toString());
                Intent intent=new Intent(Reference.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Reference.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setEnabled(true);
                Log.e("Reference ",""+heading.getText().toString());
                heading.setSelection(heading.getText().length()-1);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Reference.this, Home.class);
                startActivity(intent); finish();
            }
        });
    }
    public void findViewById(){
        name=findViewById(R.id.refName);
        designation=findViewById(R.id.refDesignation);
        organization=findViewById(R.id.refOrg);
        email=findViewById(R.id.refEmail);
        phone=findViewById(R.id.refPhone);
        back=findViewById(R.id.back);
        edit=findViewById(R.id.edit9);
        save=findViewById(R.id.saveRef);
        heading=findViewById(R.id.headingRef);
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
