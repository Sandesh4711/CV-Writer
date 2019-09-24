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
import android.widget.RelativeLayout;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.Home;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.ResumeHome;
import com.google.android.material.snackbar.Snackbar;

public class BasicDetails extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private EditText heading,name;
    private EditText adress1,adress2,adress3,email,phone;
    private Button save;
    private GestureDetector gestureDetector=new GestureDetector(this);
    private RelativeLayout relativeLayout;
    private ImageView back,home,edit;
    SharePreferance sharePreferance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);
  findViewById();
        sharePreferance = new SharePreferance(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BasicDetails.this, ResumeHome.class);
                startActivity(intent);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isStringCorrect(name.getText().toString())){
                    name.setError("Enter name");
                    Snackbar snackbar=Snackbar.make(relativeLayout,"Enter name",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (!isStringCorrect(adress1.getText().toString())){
                    adress1.setError("Enter Address");
                    Snackbar snackbar=Snackbar.make(relativeLayout,"Enter address",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else if (!isStringCorrect(email.getText().toString())){
                    email.setError("Enter email-id");
                    Snackbar snackbar=Snackbar.make(relativeLayout,"Enter email-id",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (!isPhoneCorrect(phone.getText().toString())){
                    phone.setError("Enter Phone no.");
                    Snackbar snackbar=Snackbar.make(relativeLayout,"Enter valid phone no.",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    sharePreferance.saveBasicDetails(name.getText().toString(),adress1.getText().toString(),adress2.getText().toString(),adress3.getText().toString(),email.getText().toString(),phone.getText().toString());
                    sharePreferance.saveHeadingBasic(heading.getText().toString());
                    Intent intent = new Intent(BasicDetails.this, ResumeHome.class);
                    startActivity(intent);
                    finish();
                }
            }
        });        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BasicDetails.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setEnabled(true);
                heading.setSelection(heading.getText().length()-1);
            }
        });
    }
    public boolean isStringCorrect(String name){
        if (name.length()>0){
            return true;
        }
        else {return false;}
    }

    public boolean isPhoneCorrect(String phone){
        if(phone.length()!=10){
            return false;
        }
        else
        {
            return true;
        }
    }
    public  void findViewById(){
        heading=findViewById(R.id.headingBasic);
        name=findViewById(R.id.name);
        adress1=findViewById(R.id.ad1);
        adress2=findViewById(R.id.ad2);
        adress3=findViewById(R.id.ad3);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        back=findViewById(R.id.back);
        home=findViewById(R.id.homeIcon);
        save=findViewById(R.id.submit);
        relativeLayout=findViewById(R.id.relBasic);
        edit=findViewById(R.id.edit1);
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
