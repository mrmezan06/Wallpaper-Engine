package com.mezan.wallpaperengine;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

public class Design_App extends AppCompatActivity {

    ListView gridView;
    private int drawableid[]={R.drawable.big_moon,R.drawable.cloudy,R.drawable.dark,R.drawable.digital_metal,
            R.drawable.forest_river,R.drawable.ghost,R.drawable.gitter,R.drawable.green_cloudy,R.drawable.ice_sea,R.drawable.leaf,
            R.drawable.moon,R.drawable.purple,R.drawable.sea,R.drawable.tree,R.drawable.white
    };
    private String name[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design__app);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        gridView=(ListView) findViewById(R.id.gv);
        name=getResources().getStringArray(R.array.image_name);
        MY_Adapter adapter=new MY_Adapter(getApplicationContext(),name,drawableid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Design_App.this,MainActivity.class);
                intent.putExtra("pos",position);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
