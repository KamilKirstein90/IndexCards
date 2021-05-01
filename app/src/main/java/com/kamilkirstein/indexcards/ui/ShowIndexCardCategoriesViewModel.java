package com.kamilkirstein.indexcards.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kamilkirstein.indexcards.Repository;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.List;
// 1) MVVM first create a ViewModel Class
public class ShowIndexCardCategoriesViewModel extends AndroidViewModel {

    // MutableLiveData can be changed, LiveData on the other hand can be only be observe but not changed
    private Repository mRepository;
    private LiveData<List<IndexCardCategory>> mIndexCardCategories;

    public ShowIndexCardCategoriesViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
    }

    public LiveData<List<IndexCardCategory>> getIndexCardCategories(){
        // TODO what is the differece between MutableLive Data and LiveData and what is the better coding style?
           return mIndexCardCategories= mRepository.getAllIndexCardCategories();
    }

    public void insertIndexCardCategory(IndexCardCategory category){
        mRepository.insertUpdateIndexCardCategory(category);
    }


}
