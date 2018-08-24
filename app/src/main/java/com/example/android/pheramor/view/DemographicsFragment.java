package com.example.android.pheramor.view;

/*
 * Created by Piyush Garg on 08/20/18.
 */

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.appyvet.materialrangebar.RangeBar;
import com.example.android.pheramor.R;
import com.example.android.pheramor.model.RegistrationDetails;
import com.example.android.pheramor.view_model.APIResponseViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemographicsFragment extends Fragment {

    final Calendar myCalendar = Calendar.getInstance();
    @BindView(R.id.DOBEditText)
    EditText editText;
    @BindView(R.id.RaceSpinner)
    Spinner raceSpinner;
    @BindView(R.id.ReligionSpinner)
    Spinner religionSpinner;
    @BindView(R.id.DemographicsFragmentToolbar)
    Toolbar toolbar;
    @BindView(R.id.DemographicsNextButton)
    Button button;
    @BindView(R.id.MaleGender)
    ImageView imageViewMale;
    @BindView(R.id.FemaleGender)
    ImageView imageViewFemale;
    @BindView(R.id.DemographicsFragmentLayout)
    RelativeLayout layout;
    @BindView(R.id.RangeMinValue)
    TextView textViewMin;
    @BindView(R.id.RangeMaxValue)
    TextView textViewMax;
    @BindView(R.id.RangeBar)
    RangeBar rangeBar;
    @BindView(R.id.switchMale)
    Switch maleSwitch;
    @BindView(R.id.switchFemale)
    Switch femaleSwitch;
    RegistrationDetails registrationDetails = new RegistrationDetails();
    private APIResponseViewModel apiResponseViewModel;
    GradientDrawable gradientDrawable = new GradientDrawable();
    GradientDrawable gradientDrawable1 = new GradientDrawable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            if (bundle.getParcelable("DetailsObject") != null)
                registrationDetails = bundle.getParcelable("DetailsObject");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demographics_fragment, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        gradientDrawable = (GradientDrawable) imageViewFemale.getBackground();
        gradientDrawable1 = (GradientDrawable) imageViewMale.getBackground();

        if (registrationDetails.getGender() == null){
            gradientDrawable.setStroke(3,getResources()
                    .getColor(R.color.regActivityNegativeColor));
            gradientDrawable1.setStroke(3,getResources()
                    .getColor(R.color.regActivityNegativeColor));
        }
        else if (registrationDetails.getGender().equals(getString(R.string.MaleText))){
            gradientDrawable1.setStroke(3,getResources()
                    .getColor(R.color.regActivityPositiveColor));
            gradientDrawable.setStroke(3,getResources()
                    .getColor(R.color.regActivityNegativeColor));
        }
        else{
            gradientDrawable.setStroke(3,getResources()
                    .getColor(R.color.regActivityPositiveColor));
            gradientDrawable1.setStroke(3,getResources()
                    .getColor(R.color.regActivityNegativeColor));
        }
        if (registrationDetails.getInterestMale() == 1){
            maleSwitch.setChecked(true);
        }
        else{
            maleSwitch.setChecked(false);
        }
        if (registrationDetails.getInterestFemale() == 1){
            femaleSwitch.setChecked(true);
        }
        else {
            femaleSwitch.setChecked(false);
        }
        if(!(registrationDetails.getDob() == null))
        {
        editText.setText(registrationDetails.getDob());
        }
            rangeBar.setRangePinsByValue(registrationDetails.getInterestAgeMin(), registrationDetails.getInterestAgeMax());
            textViewMin.setText(String.valueOf(registrationDetails.getInterestAgeMin()));
            if (registrationDetails.getInterestAgeMax() == 60){
                textViewMax.setText(String.valueOf(registrationDetails.getInterestAgeMax()) + "+");
            }
            else {
                textViewMax.setText(String.valueOf(registrationDetails.getInterestAgeMax()));
            }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText.setTextColor(getResources().getColor(R.color.regActivityColorAccentSecondary));

        imageViewFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gradientDrawable.setStroke(3,getResources()
                        .getColor(R.color.regActivityPositiveColor));
                gradientDrawable1.setStroke(3,getResources()
                        .getColor(R.color.regActivityNegativeColor));
                registrationDetails.setGender(getResources().getString(R.string.FemaleText));
            }
        });

        imageViewMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gradientDrawable1.setStroke(3,getResources()
                        .getColor(R.color.regActivityPositiveColor));
                gradientDrawable.setStroke(3,getResources()
                        .getColor(R.color.regActivityNegativeColor));
                registrationDetails.setGender(getResources().getString(R.string.MaleText));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationDetails.setDob(editText.getText().toString());
                registrationDetails.setmRace(raceSpinner.getSelectedItem().toString());
                registrationDetails.setmReligion(religionSpinner.getSelectedItem().toString());
                if (maleSwitch.isChecked()){
                    registrationDetails.setInterestMale(1);
                }
                else{
                    registrationDetails.setInterestMale(0);
                }
                if (femaleSwitch.isChecked()){
                    registrationDetails.setInterestFemale(1);
                }
                else{
                    registrationDetails.setInterestFemale(0);
                }
                registrationDetails.setInterestAgeMin(Integer.valueOf(rangeBar.getLeftPinValue()));
                registrationDetails.setInterestAgeMax(Integer.valueOf(rangeBar.getRightPinValue()));
                apiResponseViewModel.getAPIResponse(registrationDetails).
                        observe(DemographicsFragment.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean){
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("DetailsObject",registrationDetails);
                            Fragment fragment = new SuccessfulFragment();
                            fragment.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = getActivity()
                                    .getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.DemographicsContainer,fragment);
                            fragmentTransaction.commit();
                        }
                        else {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("DetailsObject",registrationDetails);
                            Fragment fragment = new UnsuccessfulFragment();
                            fragment.setArguments(bundle);
                            FragmentTransaction fragmentTransaction = getActivity()
                                    .getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.DemographicsContainer,fragment)
                                    .addToBackStack("DemoFrag");
                            fragmentTransaction.commit();
                        }
                    }
                });
            }
        });

        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                textViewMin.setText(rangeBar.getLeftPinValue());
                if (Integer.valueOf(rangeBar.getRightPinValue()) == 60){
                    String s = rangeBar.getRightPinValue() + "+";
                    textViewMax.setText(s);
                }
                else{
                textViewMax.setText(rangeBar.getRightPinValue());
                }
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -18);
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Race,R.layout.spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        raceSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.Religion,R.layout.spinner_item);
        arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        religionSpinner.setAdapter(arrayAdapter1);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        apiResponseViewModel = ViewModelProviders.of(this).get(APIResponseViewModel.class);
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("ReturnObject",registrationDetails);
                Fragment fragment = new DetailsFragment();
                fragment.setArguments(bundle);
                layout.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_right,R.anim.exit_right);
                fragmentTransaction.replace(R.id.DemographicsContainer,fragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(sdf.format(myCalendar.getTime()));
    }
}
