package com.example.android.pheramor.view_model;

/*
 * Created by Piyush Garg on 08/20/18.
 */

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.pheramor.model.RegistrationDetails;
import com.example.android.pheramor.network.UpdateAPI;

public class APIResponseViewModel extends ViewModel {

    private final UpdateAPI updateAPI = new UpdateAPI();

    public LiveData<Boolean> getAPIResponse(RegistrationDetails registrationDetails) {

        return updateAPI.putData(registrationDetails);
    }
}
