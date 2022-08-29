package com.example.fifawolrdcup.Manager;

import android.content.Context;

import com.example.fifawolrdcup.Models.FixtureResponse;
import com.example.fifawolrdcup.ResponseListner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class requestManager {

    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://elenasport-io1.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public requestManager(Context context) {
        this.context = context;
    }

    public void getFixtures(ResponseListner listner, int id)
    {
             CallFixture callFixture = retrofit.create(CallFixture.class);
             Call<FixtureResponse> call = callFixture.callFixtures(id);

             call.enqueue(new Callback<FixtureResponse>() {
                 @Override
                 public void onResponse(Call<FixtureResponse> call, Response<FixtureResponse> response) {
                     if(!response.isSuccessful())
                     {
                         listner.didError(response.message());
                         return;
                     }
                     listner.didFetch(response.body(),response.message());
                 }

                 @Override
                 public void onFailure(Call<FixtureResponse> call, Throwable t) {
                   listner.didError(t.getMessage());
                 }
             });
    }
    private interface CallFixture
    {
        @GET("v2/seasons/{id}/fixtures")
        @Headers(
                {"Accept:application/json",
                        "X-RapidAPI-Key: e9d964d7d2msh6ccec09db524705p150fd9jsna1dde369374b",
                        "X-RapidAPI-Host: elenasport-io1.p.rapidapi.com"
                }
        )
         Call<FixtureResponse> callFixtures(
                 @Path("id") int id
         );
    }
}
