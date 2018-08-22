package com.example.android.pheramor.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.android.pheramor.model.RegistrationDetails;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateAPI {

    public LiveData<Boolean> putData(RegistrationDetails registrationDetails){

        final MutableLiveData<Boolean> result = new MutableLiveData<>();
        try{
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            Gson gson = new GsonBuilder().create();
            final String json = gson.toJson(registrationDetails);
            JSONObject parameter = new JSONObject(json);
            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON, parameter.toString());
            Request request = new Request.Builder()
                    .url("https://external.dev.pheramor.com/")
                    .post(body)
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("Failure", call.request().body().toString());
                    result.postValue(false);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("Response", response.body().string());
                    result.postValue(true);
                }
            });
            return result;
        }
        catch (JSONException e){
            Log.e("JSONException",e.getMessage());
            result.postValue(false);
        }
        return result;
    }
}
