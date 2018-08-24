package com.example.android.pheramor.adapter;

/*
 * Created by Piyush Garg on 08/20/18.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.pheramor.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileViewHolder> {

    private ArrayList<String> profileKey = new ArrayList<>();
    private ArrayList<String> profileValue = new ArrayList<>();

    public void ProfileAdapterSetter(ArrayList<String> profileKey, ArrayList<String> profileValue) {
        this.profileKey = profileKey;
        this.profileValue = profileValue;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_holder_layout,viewGroup,false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder profileViewHolder, int i) {
        profileViewHolder.profileValue.setText(profileValue.get(i));
        profileViewHolder.profileField.setText(profileKey.get(i));
        if (i == 0){
            profileViewHolder.separator.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return profileValue.size();
    }
}
