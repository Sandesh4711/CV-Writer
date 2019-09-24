package com.example.myapplication05.ResumePackage.Activity.ActivityResume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.Adapter.DownloadedAdapter;

import java.io.File;
import java.util.ArrayList;

public class CreatedResumes extends AppCompatActivity implements GestureDetector.OnGestureListener {
    RecyclerView recyclerView;
    GestureDetector gestureDetector=new GestureDetector(this);
    DownloadedAdapter downloadedAdapter;
    ArrayList<String> list;
    TextView nothing;
    RelativeLayout relativeLayout;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_resumes);
        recyclerView = findViewById(R.id.recycleCreated);
        relativeLayout = findViewById(R.id.wer);
        back = findViewById(R.id.back);
        nothing=findViewById(R.id.nothing);
        list = new ArrayList<>();

        walkdir();
        downloadedAdapter = new DownloadedAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(downloadedAdapter);
        if (list.isEmpty()) {
            relativeLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void walkdir() {
        File dir = Environment.getExternalStorageDirectory();

        File listFile[] = dir.listFiles();

        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {

                    if (listFile[i].getName().equals("CV Writer")) {
                        File folder = new File(listFile[i].getAbsolutePath());
                        File[] listOfFiles = folder.listFiles();

                        for (int j = 0; j < listOfFiles.length; j++) {
                            if (listOfFiles[j].isFile()) {
                                list.add(listOfFiles[j].getName());
                            } else if (listOfFiles[j].isDirectory()) {

                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CreatedResumes.this, Home.class);
        startActivity(intent);
        finish();
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
