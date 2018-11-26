package com.mezan.wallpaperengine;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    int position;
    RelativeLayout layout;
    Button bacK,downloaD,seT;
    private int drawableid[]={R.drawable.big_moon,R.drawable.cloudy,R.drawable.dark,R.drawable.digital_metal,
            R.drawable.forest_river,R.drawable.ghost,R.drawable.gitter,R.drawable.green_cloudy,R.drawable.ice_sea,R.drawable.leaf,
            R.drawable.moon,R.drawable.purple,R.drawable.sea,R.drawable.tree,R.drawable.white
    };
    private String name[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        position=getIntent().getIntExtra("pos",0);
        name=getResources().getStringArray(R.array.image_name);
        //runtime request permission
        ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);

        layout=(RelativeLayout) findViewById(R.id.viewLayout);
        bacK=(Button)findViewById(R.id.btnBack);
        downloaD=(Button)findViewById(R.id.btnDownload);
        seT=(Button)findViewById(R.id.btnSet);

        layout.setBackgroundResource(drawableid[position]);
        seT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallpaperManager wp=WallpaperManager.getInstance(getApplicationContext());
                try{
                    wp.setResource(+ drawableid[position]);
                    Toast.makeText(getApplicationContext(),"Wallpaper is Set",Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Wallpaper Setting is Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        downloaD.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                //save image to gallery
                Drawable drawable=getDrawable(drawableid[position]);
                Bitmap mybitmap=((BitmapDrawable)drawable).getBitmap();
                //phase to save the image
                String saveImageURL = MediaStore.Images.Media.insertImage(
                        getContentResolver(),mybitmap,"Title Picture","Image of Pictur"
                );
                Uri saveImageURI=Uri.parse(saveImageURL);
                Toast.makeText(getApplicationContext(),"Saving Gallery",Toast.LENGTH_SHORT).show();

                //Save Image to SD card
                Bitmap bitmap;
                OutputStream output;
                bitmap=BitmapFactory.decodeResource(getResources(),drawableid[position]);
                File filepath=Environment.getExternalStorageDirectory();
                File dir=new File(filepath+"/Own_Wallpaper/");
                dir.mkdirs();
                File file=new File(dir,name[position]+".jpg");
                try{
                    output = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,output);
                    output.flush();
                    output.close();
                    Toast.makeText(getApplicationContext(),name[position]+" has been added to your SD card in Own_Wallpaper directory",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Saving Fail in SD card",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bacK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
