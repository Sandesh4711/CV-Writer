package com.example.myapplication05.ResumePackage.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication05.ResumePackage.Activity.Tabs.AchievementAndAwards;
import com.example.myapplication05.ResumePackage.Activity.Tabs.Activities;
import com.example.myapplication05.ResumePackage.Activity.Tabs.BasicDetails;
import com.example.myapplication05.ResumePackage.Activity.Tabs.DateDeclaration;
import com.example.myapplication05.ResumePackage.Activity.Tabs.Education;
import com.example.myapplication05.ResumePackage.Activity.Tabs.Experience;
import com.example.myapplication05.ResumePackage.Activity.Tabs.HobbiesandPersonalStrengths;
import com.example.myapplication05.ResumePackage.Activity.Tabs.IndustrialExposure;
import com.example.myapplication05.ResumePackage.Activity.Tabs.Interests;
import com.example.myapplication05.ResumePackage.Activity.Tabs.PersonalInfo;
import com.example.myapplication05.ResumePackage.Activity.Tabs.PhotoandSignature;
import com.example.myapplication05.ResumePackage.Activity.Tabs.ProjectDetails;
import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.Tabs.Reference;
import com.example.myapplication05.ResumePackage.Activity.ModelResume.ModelResumeHome;
import com.example.myapplication05.ResumePackage.Activity.Tabs.TechnicalSkills;

import java.util.ArrayList;

public class GridViewResumeAdapter extends RecyclerView.Adapter<GridViewResumeAdapter.MyViewHolder> {
    private ArrayList<ModelResumeHome> model;
private Context mcontext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView headind;
        public  ImageView imageView;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            headind =view.findViewById(R.id.text);
            imageView =view.findViewById(R.id.icon);
            relativeLayout =view.findViewById(R.id.backgoundSingle);
        }
    }


    public GridViewResumeAdapter(Context context,ArrayList<ModelResumeHome> model1) {
        this.model = model1;
        this.mcontext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_grid_resume, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
if (model.get(position).getHeading().length()>18){
    holder.headind.setTextSize(17f);
}
        holder.headind.setText(model.get(position).getHeading());
        holder.imageView.setImageResource(model.get(position).getIcons());
        holder.relativeLayout.setBackgroundResource(model.get(position).getBackgroundimages());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==0) {
                    Intent intent = new Intent(mcontext, BasicDetails.class);
                    mcontext.startActivity(intent);
                }else if (position==1){
                    Intent intent = new Intent(mcontext, PersonalInfo.class);
                    mcontext.startActivity(intent);
                }else if (position==2){
                    Intent intent = new Intent(mcontext, Education.class);
                    mcontext.startActivity(intent);
                }else if(position==3){
                    Intent intent = new Intent(mcontext, Experience.class);
                    mcontext.startActivity(intent);
                }else if (position==4){
                    Intent intent=new Intent(mcontext, ProjectDetails.class);
                    mcontext.startActivity(intent);
                }else if (position==5){
                    Intent intent=new Intent(mcontext, TechnicalSkills.class);
                    mcontext.startActivity(intent);
                }else if (position==6){
                    Intent intent=new Intent(mcontext, Interests.class);
                    mcontext.startActivity(intent);
                }else if (position==7){
                    Intent intent=new Intent(mcontext, IndustrialExposure.class);
                    mcontext.startActivity(intent);
                }else if (position==8){
                    Intent intent=new Intent(mcontext, AchievementAndAwards.class);
                    mcontext.startActivity(intent);
                }else if (position==9){
                    Intent intent=new Intent(mcontext, Activities.class);
                    mcontext.startActivity(intent);
                }else if (position==10){
                    Intent intent=new Intent(mcontext, HobbiesandPersonalStrengths.class);
                    mcontext.startActivity(intent);
                }else if (position==11){
                    Intent intent=new Intent(mcontext, DateDeclaration  .class);
                    mcontext.startActivity(intent);
                }else if (position==12){
                    Intent intent=new Intent(mcontext, Reference.class);
                    mcontext.startActivity(intent);
                }else if (position==13){
                    Intent intent=new Intent(mcontext, PhotoandSignature.class);
                    mcontext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

}
