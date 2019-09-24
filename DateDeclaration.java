package com.example.myapplication05.ResumePackage.Activity.Tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.Home;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.ResumeHome;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDeclaration extends AppCompatActivity implements GestureDetector.OnGestureListener{
ImageView back,calendar,edit,home;
EditText objective,decalaration,date,heading,place;
Button save;
GestureDetector gestureDetector=new GestureDetector(this);
private DatePickerDialog datePickerDialog;
SharePreferance sharePreferance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_declaration);
        findViewById();
        sharePreferance=new SharePreferance(this);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DATE);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datePickerDialog=new DatePickerDialog(DateDeclaration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(parseDateToddMMyyyy(""+i2+"-"+(i1+1)+"-"+i));
                    }},year,month,day);
                Date date=new Date();
                datePickerDialog.show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePreferance.saveHeadingDate(heading.getText().toString());
                sharePreferance.saveDateDeclaration(objective.getText().toString(),decalaration.getText().toString(),date.getText().toString(),place.getText().toString());
                Intent intent=new Intent(DateDeclaration.this, ResumeHome.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DateDeclaration.this, ResumeHome.class);
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
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DateDeclaration.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void findViewById(){
        back=findViewById(R.id.back);
        calendar=findViewById(R.id.dte);
        edit=findViewById(R.id.edit);
        objective=findViewById(R.id.objective);
        decalaration=findViewById(R.id.declaration);
        date=findViewById(R.id.date);
        save=findViewById(R.id.saveDate);
        heading=findViewById(R.id.headingDate);
        place=findViewById(R.id.place);
        home=findViewById(R.id.homeIcon);
    }
    public String parseDateToddMMyyyy( String oldDate) {
        String inputPattern = "dd-MM-yyyy";
        String outputPattern = "MMM dd, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date ;
        String str = null;

        try {
            date = inputFormat.parse(oldDate);
            str = outputFormat.format(date);
        } catch ( ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return str;
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
