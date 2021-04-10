package com.example.patientrecordsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    EditText name,fathername,age;
    Bitmap bitmap;
    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;
    ChipGroup chipGroup;
    Chip chip1,chip2;
    Button image,submit,save;
    Bitmap captureImage;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static int REQUEST_CODE = 100;
    OutputStream outputStream;
    ImageView imageView;
    String getradio;
    String getChip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        name=findViewById(R.id.name);
        fathername=findViewById(R.id.father_name);
        age=findViewById(R.id.age);
        radioGroup=findViewById(R.id.radioGroup);
        chipGroup=findViewById(R.id.chip_group);
        chip1=findViewById(R.id.chip1);
        chip2=findViewById(R.id.chip2);
        image=findViewById(R.id.picture_btn);
        save=findViewById(R.id.savebtn);
        submit=findViewById(R.id.submit_btn);
        radioButton1=findViewById(R.id.radio1);
        radioButton2=findViewById(R.id.radio2);
        chip1.setText("Active");
        chip2.setText("In Active");



        spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Consultancy, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        String getSpinner=spinner.getSelectedItem().toString();
//        int chipid=chipGroup.getCheckedChipId();
//       String chip=chip1.getText().toString();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},100);
                }
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }


                savetoGallery();
                Toast.makeText(MainActivity.this, "image is Saved", Toast.LENGTH_SHORT).show();


            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getname=name.getText().toString();
                String getFathername=fathername.getText().toString();
                String Age=age.getText().toString();
                String getSpinner=spinner.getSelectedItem().toString();
                if (radioButton1.isChecked()){
                    getradio=radioButton1.getText().toString();

                }else if (radioButton2.isChecked()){
                    getradio=radioButton2.getText().toString();
                }
                if (chip1.isChecked()){
                    getChip=chip1.getText().toString();
                }else if (chip2.isChecked()){
                    getChip=chip2.getText().toString();
                }

                Intent intent=new Intent(MainActivity.this,nextActivity.class);
                intent.putExtra("name",getname);
                intent.putExtra("father Name",getFathername);
                intent.putExtra("Age",Age);
                intent.putExtra("spinner",getSpinner);
                intent.putExtra("radio",getradio);
                intent.putExtra("chip",getChip);
//                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
//                intent.putExtra("byteArray", _bs.toByteArray());

                startActivity(intent);
            }
        });




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CODE){

            if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                savetoGallery();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void savetoGallery() {
        File dir=new File(Environment.getDataDirectory(),"Save image");
        if(!dir.exists()){
            dir.mkdir();
        }
        BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();
        File file=new File(dir,System.currentTimeMillis()+".jpg");
        try {
            outputStream=new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        Toast.makeText(this, "image savved", Toast.LENGTH_SHORT).show();
//        try {
//            outputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        captureImage= ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        FileOutputStream fileOutputStream=null;
//        File file= Environment.getExternalStorageDirectory();
//        File dir= new File(file.getAbsolutePath()+"/pictures");
//        dir.mkdirs();
//        String filename=String.format("%d.jpeg",System.currentTimeMillis());
//        File outfile=new File(dir,filename);
//        try {
//            fileOutputStream= new FileOutputStream(outfile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        captureImage.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
//        try {
//            fileOutputStream.flush();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            fileOutputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            captureImage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(captureImage);
        }
    }



}