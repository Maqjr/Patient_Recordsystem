package com.example.patientrecordsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class nextActivity extends AppCompatActivity {
    TextView title,getname,getfather,getage,spinnertext,chipText,radioText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        title=findViewById(R.id.title);
        getname=findViewById(R.id.name);
        getfather=findViewById(R.id.father);
        getage=findViewById(R.id.age);
        spinnertext=findViewById(R.id.spinner_text);
        chipText=findViewById(R.id.gender);
        imageView=findViewById(R.id.image_view);
        radioText=findViewById(R.id.status);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String father=intent.getStringExtra("father Name");
        String Age=intent.getStringExtra("Age");
        String spinner=intent.getStringExtra("spinner");
        String radio=intent.getStringExtra("radio");
        String chip=intent.getStringExtra("chip");
        title.setText("Welcome to "+name+" Record System");
        getname.setText("Name:  "+name);
        getfather.setText("Father name:  "+father);
        getage.setText("Age:  "+Age);
        spinnertext.setText("Consultancy:  "+spinner);
        chipText.setText("Status:  "+chip);
        radioText.setText("Gender:  "+radio);
//        if(getIntent().hasExtra("byteArray")) {
//             imageView = new ImageView(this);
//            Bitmap _bitmap = BitmapFactory.decodeByteArray(
//                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
//            imageView.setImageBitmap(_bitmap);
//        }
    }
}