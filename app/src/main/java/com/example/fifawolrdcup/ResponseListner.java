package com.example.fifawolrdcup;

import com.example.fifawolrdcup.Models.FixtureResponse;

public interface ResponseListner {

    void didFetch(FixtureResponse response, String message);
    void didError(String message);
}
