package com.example.myapplication05.ResumePackage.Activity.Tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.Home;
import com.example.myapplication05.ResumePackage.Activity.ActivityResume.ResumeHome;

import java.io.IOException;

public class PhotoandSignature extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private ImageView photo,back,home;
    GestureDetector gestureDetector=new GestureDetector(this);
    private Button change,remove,signPad,upload,save;
    Bitmap bmp;
    String pathPhoto,pathSign;
    SharePreferance sharePreferance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoand_signature);
        findViewById();
        sharePreferance=new SharePreferance(this);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                startActivityForResult(i, 1);
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        photo.setImageResource(R.drawable.ic_boss);
                        pathPhoto="H";
                        change.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PhotoandSignature.this, Home.class);
                startActivity(intent);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                startActivityForResult(i, 8);
                upload.setTextColor(Color.parseColor("#000000"));
                signPad.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
        signPad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PhotoandSignature.this,SignPad.class);
                startActivityForResult(intent,9);

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Sign",""+pathSign);
                sharePreferance.savePhotoandSign(pathPhoto,pathSign);
                Log.e("Signature",""+pathSign);
                Intent intent=new Intent(PhotoandSignature.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PhotoandSignature.this, ResumeHome.class);
                startActivity(intent); finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            pathPhoto=picturePath;

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                photo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cursor.close();
        }
        if (requestCode == 8 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            pathSign=picturePath;
            try {
                Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cursor.close();
        }
        if (requestCode==9&&resultCode==RESULT_OK){
            String bitmap=data.getStringExtra("sign");
            pathSign=bitmap;
            signPad.setTextColor(Color.parseColor("#000000"));
        }


    }
    public void findViewById(){
        photo=findViewById(R.id.photo);
        change=findViewById(R.id.change);
        remove=findViewById(R.id.remove);
        signPad=findViewById(R.id.signPad);
        upload=findViewById(R.id.upload);
        save=findViewById(R.id.savePhoto);
        back=findViewById(R.id.back);
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
