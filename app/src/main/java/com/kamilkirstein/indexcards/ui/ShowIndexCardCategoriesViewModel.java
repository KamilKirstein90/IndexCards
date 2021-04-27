package com.kamilkirstein.indexcards.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.List;
// 1) MVVM first create a ViewModel Class
public class ShowIndexCardCategoriesViewModel extends ViewModel {

    // MutableLiveData can be changed, LiveData on the other hand can be only be observe but not changed
    private MutableLiveData<List<IndexCardCategory>> mIndexCardCategories;

    public LiveData<List<IndexCardCategory>> getIndexCardCategories(){
            return mIndexCardCategories;
    }



}
