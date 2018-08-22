package com.example.android.pheramor.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.pheramor.R;
import com.example.android.pheramor.adapter.ProfileAdapter;
import com.example.android.pheramor.model.RegistrationDetails;
import com.example.android.pheramor.util.AgeCalculator;
import com.example.android.pheramor.util.AlertDialogScreen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity{

    RegistrationDetails registrationDetails = new RegistrationDetails();
    ArrayList<String>profileKey = new ArrayList<>();
    ArrayList<String>profileValue = new ArrayList<>();
    ProfileAdapter profileAdapter = new ProfileAdapter();
    @BindView(R.id.ProfileRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.name)
    TextView textView;
    @BindView(R.id.ProfilePic)
    ImageView imageView;
    @BindView(R.id.genderImage)
    ImageView imageViewGender;
    @BindView(R.id.ageText)
    TextView textViewAge;
    @BindView(R.id.interestImage)
    ImageView imageViewInterest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        registrationDetails = intent.getParcelableExtra("RegObject");
        Log.e("Registration Det: ", registrationDetails.toString());
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(profileAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        profileKey.clear();
        profileValue.clear();
        if (!registrationDetails.getDesc().isEmpty()){
            profileValue.add(registrationDetails.getDesc());
            profileKey.add("About Me: ");
        }
        if (!registrationDetails.getDob().equals(getResources().getString(R.string.DateOfBirthEditText))){
            profileKey.add("D.O.B.: ");
            profileValue.add(registrationDetails.getDob());
        }
        profileKey.add("Height: ");
        profileValue.add(registrationDetails.getHeightFeet().toString()
                + "\'" + registrationDetails.getHeightInches().toString()+"\"");
        profileKey.add("Email: ");
        profileValue.add(registrationDetails.getEmail());
        if(registrationDetails.getZipCode() != 0){
            profileKey.add("Zip Code: ");
            profileValue.add(registrationDetails.getZipCode().toString());
        }
        if (!registrationDetails.getmRace().equals(getResources().getStringArray(R.array.Race)[0])){
            profileValue.add(registrationDetails.getmRace());
            profileKey.add("Race: ");
        }
        if (!registrationDetails.getmReligion().equals(getResources().getStringArray(R.array.Religion)[0])){
            profileValue.add(registrationDetails.getmReligion());
            profileKey.add("Religion: ");
        }
        profileKey.add("Age Range: ");
        if (registrationDetails.getInterestAgeMax() < 60){
        profileValue.add(registrationDetails.getInterestAgeMin().toString()+" yrs to "
                +registrationDetails.getInterestAgeMax().toString()+" yrs");
        }
        else{
            profileValue.add(registrationDetails.getInterestAgeMin().toString()+" yrs to "
                    +registrationDetails.getInterestAgeMax().toString()+"+ yrs");
        }

        profileAdapter.ProfileAdapterSetter(profileKey,profileValue);

        textView.setText(registrationDetails.getFirstName() + " " + registrationDetails.getLastName());
        if(registrationDetails.getProfilePic() == null){
            Picasso.with(this).load(R.drawable.profile_placeholder).into(imageView);
        }
        else{
            Picasso.with(this).load(Uri.parse(registrationDetails.getProfilePic())).into(imageView);
        }
        if(!registrationDetails.getDob().equals(getResources().getString(R.string.DateOfBirthEditText))){
            textViewAge.setText(String.valueOf(new AgeCalculator().getAge(registrationDetails.getDob())));
        }
        else{
            textViewAge.setText("00");
        }
        if (registrationDetails.getGender() == null){
            Picasso.with(this).load(R.drawable.male_female).into(imageViewGender);
        }
        else if (registrationDetails.getGender().equals(getResources().getString(R.string.FemaleText))){
            Picasso.with(this).load(R.drawable.female).into(imageViewGender);
        }
        else {
            Picasso.with(this).load(R.drawable.male).into(imageViewGender);
        }
        if (registrationDetails.getInterestMale() == 1 && registrationDetails.getInterestFemale() == 1){
            Picasso.with(this).load(R.drawable.male_female).into(imageViewInterest);
        }
        else if (registrationDetails.getInterestMale() == 1){
            Picasso.with(this).load(R.drawable.male).into(imageViewInterest);
        }
        else {
            Picasso.with(this).load(R.drawable.female).into(imageViewInterest);
        }

    }

    @Override
    public void onBackPressed() {
        new AlertDialogScreen().showAlert(this);
    }
}
