package com.adriant.entrevistachallenge.ApiService;

import com.adriant.entrevistachallenge.Models.Character;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderAPI {

    @GET("api/character")
    Call<Character> getCharacters(
            @Query("page") int page
    );

}
