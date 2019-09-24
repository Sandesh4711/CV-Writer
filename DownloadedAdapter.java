package com.example.myapplication05.ResumePackage.Activity.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication05.R;

import java.io.File;
import java.util.ArrayList;

public class DownloadedAdapter extends RecyclerView.Adapter<DownloadedAdapter.MyFolder> {
    Context context;
    ArrayList<String> list;

    public DownloadedAdapter(Context mcontext, ArrayList<String> mList) {
        this.context = mcontext;
        this.list = mList;
    }

    @NonNull
    @Override
    public MyFolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.downloaded_sungle, parent, false);
        return new MyFolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFolder holder, final int position) {
        holder.textView.setText(list.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPdf(list.get(position));

            }
        });
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPdf(list.get(position));
            }
        });
        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputFile = new File("/storage/emulated/0/CV Writer", list.get(position));
                Uri uri = Uri.fromFile(outputFile);

                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("application/pdf");
                share.putExtra(Intent.EXTRA_STREAM, uri);
                context.startActivity(share);
             }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyFolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView img1, img2;

        public MyFolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.texxt);
            img1 = itemView.findViewById(R.id.oiu);
            img2 = itemView.findViewById(R.id.wert);
        }
    }

    void openPdf(String j) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            File file = new File("/storage/emulated/0/CV Writer", j);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            context.startActivity(intent);


        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Sorry! Unable to create", Toast.LENGTH_SHORT).show();
        }
    }
}
