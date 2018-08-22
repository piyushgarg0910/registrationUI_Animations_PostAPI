package com.example.android.pheramor.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.pheramor.R;
import com.example.android.pheramor.model.RegistrationDetails;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnsuccessfulFragment extends Fragment {

    @BindView(R.id.UnsuccessfulTextView)
    TextView textView;
    @BindView(R.id.UnsuccessfulButton)
    Button button;
    @BindView(R.id.UnsuccessfulImageView)
    ImageView imageView;
    @BindView(R.id.Unsuccessful_Container)
    RelativeLayout relativeLayout;
    RegistrationDetails registrationDetails = new RegistrationDetails();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unsuccessful_fragment,container,false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        if (bundle != null){
            if (bundle.getParcelable("DetailsObject") != null)
                registrationDetails = bundle.getParcelable("DetailsObject");
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("DetailsObject",registrationDetails);
                relativeLayout.setVisibility(View.GONE);
                Fragment fragment = new DemographicsFragment();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Unsuccessful_Container,fragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Animation animationEnterLeft = new AnimationUtils().loadAnimation(getContext(),R.anim.enter_left_slow);
        Animation animationEnterRight = new AnimationUtils().loadAnimation(getContext(),R.anim.enter_right_slow);
        Animation animationAppear = new AnimationUtils().loadAnimation(getContext(),R.anim.appear);

        textView.startAnimation(animationEnterLeft);
        Picasso.with(getContext()).load(R.drawable.redcrossmark).into(imageView);
        imageView.startAnimation(animationAppear);
        button.setAnimation(animationEnterRight);
    }
}
