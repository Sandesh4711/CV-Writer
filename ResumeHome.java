package com.example.myapplication05.ResumePackage.Activity.ActivityResume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.Adapter.GridViewResumeAdapter;
import com.example.myapplication05.ResumePackage.Activity.ModelResume.ModelResumeHome;
import com.example.myapplication05.ResumePackage.Activity.Tabs.SharePreferance;

import java.util.ArrayList;

public class ResumeHome extends AppCompatActivity {
    private Button generate;
    ImageView home;
    SharePreferance sharePreferance;
    private RecyclerView recyclerView;
    private ModelResumeHome model;
    private ArrayList<ModelResumeHome> resumeItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home=findViewById(R.id.homeIcon);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResumeHome.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        sharePreferance=new SharePreferance(this);
        generate=findViewById(R.id.generateR);
        recyclerView=findViewById(R.id.gridviewResume);
        resumeItems=new ArrayList<>();
        model=new ModelResumeHome();
        model.setHeading("Basic Details");
        model.setIcons(R.drawable.ic_man_user);
        model.setBackgroundimages(R.drawable.basicd);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Personal Info");
        model.setIcons(R.drawable.ic_business_person_silhouette_wearing_tie);
        model.setBackgroundimages(R.drawable.experience);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Education");
        model.setIcons(R.drawable.ic_graduate_cap);
        model.setBackgroundimages(R.drawable.education);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Experience");
        model.setIcons(R.drawable.ic_portfolio);
        model.setBackgroundimages(R.drawable.personali);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Projects");
        model.setIcons(R.drawable.ic_project_management);
        model.setBackgroundimages(R.drawable.projects);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Technical Skills");
        model.setIcons(R.drawable.ic_risks);
        model.setBackgroundimages(R.drawable.tech);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Interests");
        model.setIcons(R.drawable.ic_like);
        model.setBackgroundimages(R.drawable.interests);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Industrial Exposure");
        model.setIcons(R.drawable.ic_industry_worker_with_cap_protection_and_a_laptop);
        model.setBackgroundimages(R.drawable.industry);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Achievements and Awards");
        model.setIcons(R.drawable.ic_trophy);
        model.setBackgroundimages(R.drawable.awards);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Activities");
        model.setIcons(R.drawable.ic_stretching);
        model.setBackgroundimages(R.drawable.activity);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Strengths");
        model.setIcons(R.drawable.ic_man_lifting_weights);
        model.setBackgroundimages(R.drawable.strengths);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Objective, Date and Declaration");
        model.setIcons(R.drawable.ic_calendar);
        model.setBackgroundimages(R.drawable.date);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Reference");
        model.setIcons(R.drawable.ic_refer);
        model.setBackgroundimages(R.drawable.reference);
        resumeItems.add(model);
        model=new ModelResumeHome();
        model.setHeading("Photos and Signature");
        model.setIcons(R.drawable.ic_picture);
        model.setBackgroundimages(R.drawable.sign);
        resumeItems.add(model);
        GridViewResumeAdapter gridViewResumeAdapter=new GridViewResumeAdapter(this,resumeItems);
        GridLayoutManager mGridManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mGridManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gridViewResumeAdapter);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sharePreferance.getBasicDetails(SharePreferance.NAME).matches("H")) {
                    Intent intent = new Intent(ResumeHome.this, PDFGenerator.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(ResumeHome.this, "Fill the basic details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ResumeHome.this,Home.class);
        startActivity(intent);
        finish();
    }
}
