package com.example.android.pheramor.view;

import android.graphics.drawable.GradientDrawable;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pheramor.R;
import com.example.android.pheramor.model.RegistrationDetails;
import com.example.android.pheramor.util.PasswordValidation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PasswordFragment extends Fragment {

    @BindView(R.id.PasswordFragmentToolbar)
    Toolbar toolbar;
    @BindView(R.id.PasswordNextButton)
    Button button;
    @BindView(R.id.EditTextPassword)
    EditText editText;
    @BindView(R.id.TextViewPasswordIncorrect)
    TextView textView;
    @BindView(R.id.EditTextReenterPassword)
    EditText editText1;
    @BindView(R.id.TextViewPasswordReenterIncorrect)
    TextView textView1;
    @BindView(R.id.PasswordFragmentLayout)
    RelativeLayout layout;
    RegistrationDetails registrationDetails = new RegistrationDetails();

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.password_fragment,container,false);
        ButterKnife.bind(this,view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordValidation passwordValidation = new PasswordValidation();
                if (editText.getText() == null || !passwordValidation.isPasswordValid(editText.getText())){
                    GradientDrawable gradientDrawable = (GradientDrawable) editText.getBackground();
                    gradientDrawable.setStroke(3,getResources()
                            .getColor(R.color.regActivityNegativeColor));
                    textView.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(getContext(),R.anim.shake);
                    layout.startAnimation(shake);
                }
                else if(editText1.getText() == null ||
                        !editText1.getText().toString().equals(editText.getText().toString())){
                    GradientDrawable gradientDrawable = (GradientDrawable) editText1.getBackground();
                    gradientDrawable.setStroke(3,getResources()
                            .getColor(R.color.regActivityNegativeColor));
                    textView1.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(getContext(),R.anim.shake);
                    layout.startAnimation(shake);
                }
                else {
                    registrationDetails.setPassword(editText.getText().toString());
                    layout.setVisibility(View.GONE);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("DetailsObject",registrationDetails);
                    Fragment fragment = new DetailsFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_bottom,R.anim.exit_bottom);
                    fragmentTransaction.replace(R.id.PasswordContainer,fragment,"DetailsFragment");
                    fragmentTransaction.commit();
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (textView.getVisibility() == View.VISIBLE){
                    PasswordValidation passwordValidation = new PasswordValidation();
                    boolean result = passwordValidation.isPasswordValid(charSequence);
                    if (result){
                        textView.setVisibility(View.INVISIBLE);
                        GradientDrawable gradientDrawable = (GradientDrawable) editText.getBackground();
                        gradientDrawable.setStroke(3,getResources()
                                .getColor(R.color.regActivityPositiveColor));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (textView1.getVisibility() == View.VISIBLE){
                    if (editText.getText().toString().equals(charSequence.toString())){
                        textView1.setVisibility(View.INVISIBLE);
                        GradientDrawable gradientDrawable = (GradientDrawable) editText1.getBackground();
                        gradientDrawable.setStroke(3,getResources()
                                .getColor(R.color.regActivityPositiveColor));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
                Fragment fragment = new EmailFragment();
                fragment.setArguments(bundle);
                layout.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_bottom,R.anim.exit_bottom);
                fragmentTransaction.replace(R.id.PasswordContainer,fragment);
                fragmentTransaction.commit();
            }
        });
    }
}
