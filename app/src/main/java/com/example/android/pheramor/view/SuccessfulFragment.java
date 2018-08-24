package com.example.android.pheramor.view;

/*
 * Created by Piyush Garg on 08/20/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.pheramor.R;
import com.example.android.pheramor.model.RegistrationDetails;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuccessfulFragment extends Fragment {

    @BindView(R.id.SuccessfulTextView)
    TextView textView;
    @BindView(R.id.SuccessfulButton)
    Button button;
    @BindView(R.id.SuccessfulImageView)
    ImageView imageView;
    RegistrationDetails registrationDetails = new RegistrationDetails();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.successful_fragment,container,false);
        ButterKnife.bind(this,view);
        Bundle bundle = this.getArguments();
        registrationDetails = bundle.getParcelable("DetailsObject");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Animation animationEnterLeft = new AnimationUtils()
                .loadAnimation(getContext(),R.anim.enter_left_slow);
        Animation animationEnterRight = new AnimationUtils()
                .loadAnimation(getContext(),R.anim.enter_right_slow);
        Animation animationAppear = new AnimationUtils()
                .loadAnimation(getContext(),R.anim.appear);

        textView.startAnimation(animationEnterLeft);
        Picasso.with(getContext()).load(R.drawable.greentickmark).into(imageView);
        imageView.startAnimation(animationAppear);
        button.setAnimation(animationEnterRight);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ProfileActivity.class);
                intent.putExtra("RegObject",registrationDetails);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
