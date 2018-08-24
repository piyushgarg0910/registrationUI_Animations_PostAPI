package com.example.android.pheramor.view;

/*
 * Created by Piyush Garg on 08/20/18.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.pheramor.R;
import com.example.android.pheramor.model.RegistrationDetails;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {

    @BindView(R.id.DetailsFragmentLayout)
    RelativeLayout layout;
    @BindView(R.id.DetailsFragmentToolbar)
    Toolbar toolbar;
    @BindView(R.id.DetailsNameReqText)
    TextView textView;
    @BindView(R.id.DetailsNameEditText)
    EditText editTextName;
    @BindView(R.id.DetailsLastNameEditText)
    EditText editTextLastName;
    @BindView(R.id.DetailsFeetNumPick)
    NumberPicker numberPickerFeet;
    @BindView(R.id.DetailsInchNumPick)
    NumberPicker numberPickerInches;
    @BindView(R.id.DetailsZipEditText)
    EditText editTextZip;
    @BindView(R.id.DetailsDescriptionEditText)
    EditText editTextDesc;
    @BindView(R.id.DetailsNextButton)
    Button button;
    @BindView(R.id.ProfilePicButton)
    Button profilePicButton;
    @BindView(R.id.ProfilePicImage)
    ImageView imageView;
    RegistrationDetails registrationDetails = new RegistrationDetails();
    Uri selectedImage = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            if (bundle.getParcelable("DetailsObject") != null)
                registrationDetails = bundle.getParcelable("DetailsObject");
            else
                registrationDetails = bundle.getParcelable("ReturnObject");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment,container,false);
        ButterKnife.bind(this,view);
        setHasOptionsMenu(true);
        numberPickerFeet.setMinValue(2);
        numberPickerFeet.setMaxValue(8);
        numberPickerFeet.setValue(5);
        numberPickerInches.setMinValue(0);
        numberPickerInches.setMaxValue(11);
        numberPickerInches.setValue(0);
        if (registrationDetails.getFirstName() != null){
            editTextName.setText(registrationDetails.getFirstName());
        }
        if (registrationDetails.getLastName() != null){
            editTextLastName.setText(registrationDetails.getLastName());
        }
            numberPickerFeet.setValue(registrationDetails.getHeightFeet());
            numberPickerInches.setValue(registrationDetails.getHeightInches());
            if (registrationDetails.getZipCode() != 0){
            editTextZip.setText(String.format("%05d",registrationDetails.getZipCode()));
        }
        if (registrationDetails.getDesc() != null){
            editTextDesc.setText(registrationDetails.getDesc());
        }
        if (registrationDetails.getProfilePic() != null){
            selectedImage = Uri.parse(registrationDetails.getProfilePic());
            Picasso.with(getContext()).load(selectedImage).into(imageView);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextName.getText().toString().isEmpty()){
                    GradientDrawable gradientDrawable = (GradientDrawable) editTextName
                            .getBackground();
                    gradientDrawable.setStroke(3,getResources()
                            .getColor(R.color.regActivityNegativeColor));
                    textView.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(getContext(),R.anim.shake);
                    layout.startAnimation(shake);
                }
                else {
                    registrationDetails.setFirstName(editTextName.getText().toString());
                    registrationDetails.setLastName(editTextLastName.getText().toString());
                    registrationDetails.setHeightFeet(numberPickerFeet.getValue());
                    registrationDetails.setHeightInches(numberPickerInches.getValue());
                    if (editTextZip.getText().toString().isEmpty())
                        editTextZip.setText(getResources().getString(R.string.ZipPlaceHolder));
                    registrationDetails.setZipCode(Integer.valueOf(editTextZip.getText().toString()));
                    registrationDetails.setDesc(editTextDesc.getText().toString());
                    if (selectedImage != null)
                    registrationDetails.setProfilePic(selectedImage.toString());
                    layout.setVisibility(View.GONE);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("DetailsObject",registrationDetails);
                    Fragment fragment = new DemographicsFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_right,R.anim.exit_right);
                    fragmentTransaction.replace(R.id.DetailsContainer,fragment,"DemographicsFragment");
                    fragmentTransaction.commit();
                }
            }
        });

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (textView.getVisibility() == View.VISIBLE){
                    if (charSequence.length() > 0){
                        textView.setVisibility(View.INVISIBLE);
                        GradientDrawable gradientDrawable = (GradientDrawable) editTextName
                                .getBackground();
                        gradientDrawable.setStroke(3,getResources()
                                .getColor(R.color.regActivityPositiveColor));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        profilePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),100);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {

                case 100:
                    if (resultCode == Activity.RESULT_OK) {
                        selectedImage = data.getData();
                        Picasso.with(getContext()).load(selectedImage).into(imageView);
                    }
                    break;
            }
        } catch (Exception e) {
            Log.e("Profile Pic Exception",
                    "Exception in onActivityResult : " + e.getMessage());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("ReturnObject",registrationDetails);
                Fragment fragment = new PasswordFragment();
                fragment.setArguments(bundle);
                layout.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_top,R.anim.exit_top);
                fragmentTransaction.replace(R.id.DetailsContainer,fragment);
                fragmentTransaction.commit();
            }
        });
    }
}
