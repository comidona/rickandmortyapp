package com.adriant.entrevistachallenge.Paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.adriant.entrevistachallenge.Models.Result;

public class CharacterDataSourceFactory extends DataSource.Factory {

    private final MutableLiveData<PageKeyedDataSource<Integer, Result>> characterLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        CharacterDataSource characterDataSource = new CharacterDataSource();
        characterLiveDataSource.postValue(characterDataSource);
        return characterDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Result>> getCharacterLiveDataSource() {
        return characterLiveDataSource;
    }
}