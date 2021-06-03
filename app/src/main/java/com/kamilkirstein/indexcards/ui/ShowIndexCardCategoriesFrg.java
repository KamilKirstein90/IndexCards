package com.kamilkirstein.indexcards.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.kamilkirstein.indexcards.R;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.List;

public class ShowIndexCardCategoriesFrg extends Fragment {

    private RecyclerView rvCategories;
    private IndexCardCategoryAdapter adapter;
    private ShowIndexCardCategoriesViewModel viewModel;
    private ViewPager2 viewPager2;
    IndexCardCollectionAdapter indexCardCollectionAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_card_categories, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        // ****************************set view pager 2*******************
        viewPager2 = view.findViewById(R.id.vp2_lastInsertedCards);
        indexCardCollectionAdapter = new IndexCardCollectionAdapter(this);
        viewPager2.setAdapter(indexCardCollectionAdapter);

        // TODO this maybe in one Method initRecyclerView:
        rvCategories = view.findViewById(R.id.rV_showIndexCardsWithCategories);
        adapter = new IndexCardCategoryAdapter(view.getContext());
        rvCategories.setAdapter(adapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

    public class IndexCardCollectionAdapter extends FragmentStateAdapter {
        public IndexCardCollectionAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = new EditIndexCardFrg();
            Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(EditIndexCardFrg.ARG_CARD_ID, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
}