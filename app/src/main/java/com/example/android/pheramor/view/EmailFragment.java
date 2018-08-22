package com.example.android.pheramor.view;

import android.content.Intent;
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
import com.example.android.pheramor.util.EmailValidation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailFragment extends Fragment {

    @BindView(R.id.emailFragmentToolbar)
    Toolbar toolbar;
    @BindView(R.id.EmailNextButton)
    Button button;
    @BindView(R.id.EditTextEmail)
    EditText editText;
    @BindView(R.id.TextViewEmailIncorrect)
    TextView textView;
    @BindView(R.id.EmailFragmentLayout)
    RelativeLayout layout;
    RegistrationDetails registrationDetails;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.email_fragment,container,false);
        ButterKnife.bind(this,view);
        setHasOptionsMenu(true);
        if (registrationDetails.getEmail() != null){
            editText.setText(registrationDetails.getEmail());
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmailValidation emailValidation = new EmailValidation();
                if (editText.getText() == null || !emailValidation.isEmailValid(editText.getText())){
                    GradientDrawable gradientDrawable = (GradientDrawable) editText.getBackground();
                    gradientDrawable.setStroke(3,getResources()
                            .getColor(R.color.regActivityNegativeColor));
                    textView.setVisibility(View.VISIBLE);
                    Animation shake = AnimationUtils.loadAnimation(getContext(),R.anim.shake);
                    layout.startAnimation(shake);
                }
                else {
                    registrationDetails.setEmail(editText.getText().toString());
                    layout.setVisibility(View.GONE);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("DetailsObject",registrationDetails);
                    Fragment fragment = new PasswordFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_top,R.anim.exit_top);
                    fragmentTransaction.replace(R.id.EmailContainer,fragment,"PasswordFragment");
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
                    EmailValidation emailValidation = new EmailValidation();
                    boolean result = emailValidation.isEmailValid(charSequence);
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RegistrationActivity.class);
                intent.putExtra("ReturnObject",registrationDetails);
                getActivity().startActivity(intent);
            }
        });
    }
}
