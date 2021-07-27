package com.adriant.entrevistachallenge.Paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.adriant.entrevistachallenge.Models.Result;

public class CharacterViewModel extends ViewModel {

    public LiveData<PagedList<Result>> characterPagedList;
    LiveData<PageKeyedDataSource<Integer, Result>> liveDataSource;

    public CharacterViewModel(){

        CharacterDataSourceFactory characterDataSourceFactory = new CharacterDataSourceFactory();
        liveDataSource = characterDataSourceFactory.getCharacterLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(20)
                        .build();

        characterPagedList = (new LivePagedListBuilder(characterDataSourceFactory, config)).build();

    }

}