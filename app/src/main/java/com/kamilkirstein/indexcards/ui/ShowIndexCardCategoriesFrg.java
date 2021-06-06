package com.kamilkirstein.indexcards.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.kamilkirstein.indexcards.R;
import com.kamilkirstein.indexcards.dto.IndexCard;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.ArrayList;
import java.util.List;

public class ShowIndexCardCategoriesFrg extends Fragment {

    private RecyclerView rvCategories;
    private IndexCardCategoryAdapter adapter;
    private ShowIndexCardCategoriesViewModel viewModel;
    private ViewPager2 viewPager2;
    IndexCardCollectionAdapter indexCardCollectionAdapter;


    private IndexCardViewModel indexCardViewModel;
    private List<IndexCard> mIndexCards;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        setUpViewModels();
        return inflater.inflate(R.layout.fragment_show_card_categories, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.i("INDEXCARDAcheck", "Hier komm ich noch hin");
        // ****************************set view pager 2**************************
        viewPager2 = view.findViewById(R.id.vp2_lastInsertedCards);
        indexCardCollectionAdapter = new IndexCardCollectionAdapter(this);
        viewPager2.setAdapter(indexCardCollectionAdapter);

        // ****************************set recycler view*************************
        rvCategories = view.findViewById(R.id.rV_showIndexCardsWithCategories);
        adapter = new IndexCardCategoryAdapter(view.getContext());
        rvCategories.setAdapter(adapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    public class IndexCardCollectionAdapter extends FragmentStateAdapter {

        // TODO List of index Cards as member
        List<IndexCard> container;

        public void setContainer(List<IndexCard> container) {
            this.container = container;
        }

        public IndexCardCollectionAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Toast.makeText(getContext(), "ID aus Container: " + String.valueOf(container.get(position).getId()), Toast.LENGTH_SHORT).show();
            return EditIndexCardFrg.newInstance(container.get(position).getId());
           /* Fragment fragment = new EditIndexCardFrg();
            Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(EditIndexCardFrg.ARG_CARD_ID, container.get(position).getId());
            fragment.setArguments(args);
            return fragment;
            */

        }

        @Override
        public int getItemCount() {
            if(container !=null)
                return container.size();

            return 10;
        }
    }
    // this view model gets the latest 10
    public void setUpViewModels(){
        // viewmodel for the categories
        // Notice! the ViewModelProviders.of is debricated insted use :
        viewModel = new ViewModelProvider(this).get(ShowIndexCardCategoriesViewModel.class);
        //TODO check why here getViewLifeyclerOwner insted of this as owner
        viewModel.getIndexCardCategories().observe(getViewLifecycleOwner(), new Observer<List<IndexCardCategory>>() {
            @Override
            public void onChanged(List<IndexCardCategory> indexCardCategories) {
                adapter.setmContainer(indexCardCategories);
                adapter.notifyDataSetChanged();
            }
        });

        // indxCardViewModel for the ten latest cards
        indexCardViewModel = new ViewModelProvider(this).get(IndexCardViewModel.class);
        indexCardViewModel.queryAllIndexCards().observe(getViewLifecycleOwner(), new Observer<List<IndexCard>>() {
            @Override
            public void onChanged(List<IndexCard> indexCards) {
                if(indexCards == null)
                {
                    Log.i("INDEXCARDASLIST", "abfrage ergibt eine null liste");
                    indexCards = new ArrayList<IndexCard>();
                }

                mIndexCards = indexCards;
                indexCardCollectionAdapter.setContainer(mIndexCards);
                //indexCardCollectionAdapter.notifyDataSetChanged();
            }
        });
       /*
        indexCardViewModel.queryLastTenIndexCards().observe(getViewLifecycleOwner(), new Observer<List<IndexCard>>() {
            @Override
            public void onChanged(List<IndexCard> indexCards) {
                if(indexCards == null)
                    indexCards = new ArrayList<IndexCard>();

                mIndexCards = indexCards;
                indexCardCollectionAdapter.setContainer(mIndexCards);
                indexCardCollectionAdapter.notifyDataSetChanged();
            }
        });
        */
    }
}