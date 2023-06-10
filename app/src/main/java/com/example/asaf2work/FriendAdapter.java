package com.example.asaf2work;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class FriendAdapter extends ArrayAdapter<Friend> {
    Context context;
    List<Friend> object;
    public FriendAdapter(@NonNull Context context, int resource, List<Friend> object) {
        super(context, resource,object);
        this.context = context;
        this.object = object;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.listview_layout,parent,false);

        TextView tvphonenumber = (TextView)view.findViewById(R.id.tvTitle);

        TextView tvfirstname = (TextView)view.findViewById(R.id.tvSubTitle);
        ImageView ivProduct=(ImageView)view.findViewById(R.id.ivProduct);
        Friend temp = object.get(position);


        ivProduct.setImageBitmap(temp.getPicture());
        tvfirstname.setText(String.valueOf(temp.getName()));
        tvphonenumber.setText(temp.getPhonenumber());




        return view;
    }
}
