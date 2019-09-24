package com.example.myapplication05.ResumePackage.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.Tabs.SharePreferance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyClass> {
Context context;
String list;
JSONArray array;
    public ExperienceAdapter(Context mcontext,String list) throws JSONException {
this.context=mcontext;
this.list=list;
array=(new JSONArray(list));
}


    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_experience_adapter, parent, false);
        return new ExperienceAdapter.MyClass(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass holder, int position) {
        try {
            JSONObject object=array.getJSONObject(position);
            holder.org.setText("-> Company name :- "+object.getString("Organization"));
            holder.desig.setText("     Designation       :- "+object.getString("Designation"));
            holder.dura.setText("     Duration              :- "+object.getString("Duration"));
            holder.tech.setText("     Technology        :- "+object.getString("Technology"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return array.length();
    }

    public class MyClass extends RecyclerView.ViewHolder{
private TextView org,desig,dura,tech;
MyClass(View view){
    super(view);
    org=(TextView)view.findViewById(R.id.experienceO);
    desig=(TextView)view.findViewById(R.id.experienceD);
    dura=(TextView)view.findViewById(R.id.experiencedu);
    tech=(TextView)view.findViewById(R.id.experienceT);
}
    }
}
