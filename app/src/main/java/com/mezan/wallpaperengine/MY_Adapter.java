package com.mezan.wallpaperengine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MY_Adapter extends BaseAdapter {

    String name[];
    int drawableid[];
    Context context;
    private LayoutInflater inflater;
    MY_Adapter(Context context,String name[],int drwableid[]){
        this.context=context;
        this.name=name;
        this.drawableid=drwableid;
    }
    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.samplelayoutlistview,parent,false);
        }
        ImageView imageView=(ImageView) view.findViewById(R.id.imgLay);
        TextView textView=(TextView) view.findViewById(R.id.txtLay);
        imageView.setImageResource(drawableid[i]);
        textView.setText(name[i]);
        return view;
    }
}
