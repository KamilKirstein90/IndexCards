package com.kamilkirstein.indexcards.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.kamilkirstein.indexcards.Repository;
import com.kamilkirstein.indexcards.dto.IndexCard;

public class IndexCardViewModel extends AndroidViewModel {

    private Repository mRepository;

    public IndexCardViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
    }

    public void insertIndexCard(IndexCard card){
        mRepository.insertUpdateIndexCard(card);
    }
}
