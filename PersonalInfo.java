package com.example.myapplication05.ResumePackage.Activity.Tabs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.Home;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.ResumeHome;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PersonalInfo extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private ImageView back,home,calendar,edit;
    private RelativeLayout relativeLayout;
    GestureDetector gestureDetector=new GestureDetector(this);
    private Spinner marital;
    SharePreferance sharePreferance;
    private DatePickerDialog datePickerDialog;
    EditText lang,nationality,DoB,heading,fname;
    Button savePer;
    ArrayAdapter<String> arrayAdapter;
    String[] status={"Unmarried","Married"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        findViewById();
        sharePreferance = new SharePreferance(this);
        arrayAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,status);
        marital.setAdapter(arrayAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PersonalInfo.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
    savePer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (!isStringCorrect(DoB.getText().toString())){
            Snackbar snackbar=Snackbar.make(relativeLayout,"Select Date of Birth",Snackbar.LENGTH_LONG);
            snackbar.show();
        }else if (!isStringCorrect(lang.getText().toString())){
            Snackbar snackbar=Snackbar.make(relativeLayout,"Enter atleast one language",Snackbar.LENGTH_LONG);
            snackbar.show();
        }else if (!isStringCorrect(fname.getText().toString())){
            Snackbar snackbar=Snackbar.make(relativeLayout,"Enter Father's Name",Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        else {
            sharePreferance.saveHeadingPersonal(heading.getText().toString());
            sharePreferance.savePersonalInfo(DoB.getText().toString(),marital.getSelectedItem().toString(),lang.getText().toString(),nationality.getText().toString(),fname.getText().toString());
            Intent intent=new Intent(PersonalInfo.this,ResumeHome.class);
            startActivity(intent); finish();
        }
    }
});
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PersonalInfo.this, Home.class);
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
        calendar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DATE);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
datePickerDialog=new DatePickerDialog(PersonalInfo.this, new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Log.e("DATE:",""+i2+"-"+(i1+1)+"-"+i);
DoB.setText(parseDateToddMMyyyy(""+i2+"-"+(i1+1)+"-"+i));
    }},year,month,day);
datePickerDialog.getDatePicker().setMaxDate(cldr.getTimeInMillis());
datePickerDialog.show();
                }
        });

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


    public boolean isStringCorrect(String name){
        if (name.length()>0){
            return true;
        }
        else {return false;}
    }
    public void findViewById(){
        back=findViewById(R.id.back);
        relativeLayout=findViewById(R.id.relPersonal);
        DoB=findViewById(R.id.dob);
        heading=findViewById(R.id.headingPer);
        edit=findViewById(R.id.edit2);
        calendar=findViewById(R.id.dobcalendar);
        marital=findViewById(R.id.marital);
        lang=findViewById(R.id.lang);
        nationality=findViewById(R.id.nationality);
        savePer=findViewById(R.id.submitPer);
        fname=findViewById(R.id.fname);
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
