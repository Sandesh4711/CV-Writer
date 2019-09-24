package com.example.myapplication05.ResumePackage.Activity.Tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication05.R;
import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;

public class SignPad extends AppCompatActivity {
    private SignatureView sign;
    private Button saveSign,clear;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_pad);
        findViewById();
        saveSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Bitmap sBitmap= sign.getSignatureBitmap();
               String asdf=BitMapToString(sBitmap);

                Intent intent=new Intent();
                intent.putExtra("sign",asdf);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign.clearCanvas();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignPad.this,PhotoandSignature.class);
                startActivity(intent); finish();
            }
        });
    }
    public void findViewById(){
        sign=findViewById(R.id.signature_view);
        saveSign=findViewById(R.id.saveSign);
        clear=findViewById(R.id.clear);
        back=findViewById(R.id.back);
    }
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}
