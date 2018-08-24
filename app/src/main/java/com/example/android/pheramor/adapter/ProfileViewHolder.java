package com.example.android.pheramor.adapter;

/*
 * Created by Piyush Garg on 08/20/18.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.pheramor.R;

public class ProfileViewHolder extends RecyclerView.ViewHolder {

    TextView profileField, profileValue, separator;

    ProfileViewHolder(@NonNull View itemView) {
        super(itemView);
        profileField = (TextView)itemView.findViewById(R.id.profileField);
        profileValue = (TextView)itemView.findViewById(R.id.profileValue);
        separator = (TextView)itemView.findViewById(R.id.separator);
    }
}
