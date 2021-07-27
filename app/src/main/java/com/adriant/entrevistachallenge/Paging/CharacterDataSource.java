package com.adriant.entrevistachallenge.Paging;

import androidx.paging.PageKeyedDataSource;
import androidx.annotation.NonNull;

import com.adriant.entrevistachallenge.Models.Character;
import com.adriant.entrevistachallenge.Models.Result;
import com.adriant.entrevistachallenge.ApiService.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterDataSource extends PageKeyedDataSource<Integer, Result> {

    private static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer,
                                     Result> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getCharacters(FIRST_PAGE)
                .enqueue(new Callback<Character>() {
                    @Override
                    public void onResponse(Call<Character> call, Response<Character> response) {

                        if(response.body() != null){
                            callback.onResult(response.body().getResults(), null, FIRST_PAGE+1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Character> call, Throwable t) {
                        //handling failure
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params,
                           @NonNull final LoadCallback<Integer,
                                    Result> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getCharacters(params.key)
                .enqueue(new Callback<Character>() {
                    @Override
                    public void onResponse(Call<Character> call, Response<Character> response) {

                        Integer key = (params.key > 1) ? params.key - 1 : null;

                        if(response.body() != null){
                            callback.onResult(response.body().getResults(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<Character> call, Throwable t) {
                        //handling failure
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer,
                                   Result> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getCharacters(params.key)
                .enqueue(new Callback<Character>() {
                    @Override
                    public void onResponse(Call<Character> call, Response<Character> response) {

                        if(response.body() != null) {
                            String hasNext = response.body().getInfo().getNext();
                            Integer key = !hasNext.equals("") ? params.key + 1 : null;
                            callback.onResult(response.body().getResults(), key);
                        }

                    }

                    @Override
                    public void onFailure(Call<Character> call, Throwable t) {
                        //handling failure
                    }
                });

    }
}