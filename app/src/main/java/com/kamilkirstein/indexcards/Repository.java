package com.kamilkirstein.indexcards;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.kamilkirstein.indexcards.asynctaskreplacement.BaseTask;
import com.kamilkirstein.indexcards.asynctaskreplacement.TaskRunner;
import com.kamilkirstein.indexcards.dao.IndexCardCategoryDao;
import com.kamilkirstein.indexcards.dao.IndexCardDao;
import com.kamilkirstein.indexcards.dao.IndexCardsDataBase;
import com.kamilkirstein.indexcards.dto.IndexCard;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.List;

public class Repository {

    private IndexCardDao indexCardDao;
    private LiveData<IndexCard> oneIndexCard;
    private LiveData<List<IndexCard>> allIndexCards;

    private IndexCardCategoryDao indexCardCategoryDao;
    private LiveData<IndexCardCategory> oneIndexCardCategory;
    private LiveData<List<IndexCardCategory>> allIndexCardCategories;

    public Repository(Application application)
    {
        IndexCardsDataBase db = IndexCardsDataBase.getIndexCardsDB(application);
        indexCardDao = db.indexCardDao();
        indexCardCategoryDao = db.indexCardCategoryDao();
    }

    // *********************************************** index card *******************************************************************************
    public LiveData<IndexCard> getOneIndexCard(@NonNull int id) {
        oneIndexCard = indexCardDao.readIndexCard(id);
        return oneIndexCard;
    }

    public LiveData<List<IndexCard>> getAllIndexCards() {
        allIndexCards = indexCardDao.queryIndexCards();
        return allIndexCards;
    }

    public void insertUpdateIndexCard(IndexCard indexCard){
        // if the id of the passed IndexCard obj is > 0 this method calls a update database operation
        TaskRunner runner = new TaskRunner();
        runner.executeAsync(new insertUpdateIndexCardTask(indexCard, this.indexCardDao));
    }

    // *********************************************** index card category **********************************************************************
    public LiveData<IndexCardCategory> getOneIndexCardCategory(@NonNull int id) {
        oneIndexCardCategory = indexCardCategoryDao.readIndexCardCategory(id);
        return oneIndexCardCategory;
    }

    public LiveData<List<IndexCardCategory>> getAllIndexCardCategories() {
        allIndexCardCategories = indexCardCategoryDao.queryIndexCardCategories();
        return allIndexCardCategories;
    }

    public void insertUpdateIndexCardCategory(IndexCardCategory indexCardCategory){
        // if the id of the passed IndexCard obj is > 0 this method calls a update database operation
        TaskRunner runner = new TaskRunner();
        runner.executeAsync(new insertUpdateIndexCardCategoryTask(indexCardCategory, this.indexCardCategoryDao));
    }
    // ********************************************* async insert/update for index card and index card category *********************************

    public static class insertUpdateIndexCardTask extends BaseTask<Void> {

        private IndexCard mIndexCard;
        private IndexCardDao mIndexCardDao;

        public insertUpdateIndexCardTask(IndexCard indexCard, IndexCardDao indexCardDao) {
            this.mIndexCard = indexCard;
            this.mIndexCardDao = indexCardDao;
        }

        @Override
        public Void call() throws Exception {

            if (mIndexCard.getId() > 0)
                mIndexCardDao.updateIndexCard(mIndexCard);
            else
                mIndexCardDao.insertIndexCard(mIndexCard); // here is the insert operation similar to the operation in the doInBackground class

            return super.call();
        }
    }


    public static class insertUpdateIndexCardCategoryTask extends BaseTask<Void> {

        private IndexCardCategory mIndexCardCategory;
        private IndexCardCategoryDao mIndexCardCategoryDao;

        public insertUpdateIndexCardCategoryTask(IndexCardCategory indexCardCategory, IndexCardCategoryDao indexCardCategoryDao) {

            this.mIndexCardCategory = indexCardCategory;
            this.mIndexCardCategoryDao = indexCardCategoryDao;
        }

        @Override
        public Void call() throws Exception {

            if (mIndexCardCategory.getId() > 0)
                mIndexCardCategoryDao.updateIndexCardCategory(mIndexCardCategory);
            else
                mIndexCardCategoryDao.insertIndexCardCategory(mIndexCardCategory); // here is the insert operation similar to the operation in the doInBackground class

            return super.call();
        }
    }

}
