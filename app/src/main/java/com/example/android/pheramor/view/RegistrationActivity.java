package com.example.android.pheramor.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.android.pheramor.R;
import com.example.android.pheramor.model.RegistrationDetails;
import com.example.android.pheramor.util.AlertDialogScreen;
import com.felipecsl.gifimageview.library.GifImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    private boolean storagePermissionResult = false;
    RegistrationDetails registrationDetails = null;
    @BindView(R.id.AnimatedGIFView)
    GifImageView gifImageView;
    @BindView(R.id.RegActButton)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        ButterKnife.bind(this);
        gifImageView = findViewById(R.id.AnimatedGIFView);
        Glide.with(this)
                .load(R.drawable.adn_moleculas)
                .asGif()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(gifImageView);
        gifImageView.startAnimation();

       // Intent intent = getIntent();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissionResult();
                LaunchFragment();
            }
        });
    }

    private void getPermissionResult() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE)
                    == PackageManager.PERMISSION_GRANTED){
                storagePermissionResult = true;
            }
            else {
                RequestPermission();
            }
        }
        else{
            storagePermissionResult = true;
        }
    }

    private void RequestPermission(){
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                {
                    storagePermissionResult = true;
                    LaunchFragment();
                }
                else if (grantResults.length != 0 &&
                        grantResults[0] == PackageManager.PERMISSION_DENIED
                    && grantResults[1] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void LaunchFragment(){
        if (storagePermissionResult) {
            button.setVisibility(View.GONE);
            registrationDetails = new RegistrationDetails();
            Bundle bundle = new Bundle();
            bundle.putParcelable("DetailsObject",registrationDetails);
            Fragment fragment = new EmailFragment();
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_left,R.anim.exit_left);
            fragmentTransaction.replace(R.id.RegActivityLayout, fragment, "EmailFragment");
            fragmentTransaction.commit();
        }
        else
            getPermissionResult();
    }

    @Override
    public void onBackPressed() {
        new AlertDialogScreen().showAlert(this);
    }
}