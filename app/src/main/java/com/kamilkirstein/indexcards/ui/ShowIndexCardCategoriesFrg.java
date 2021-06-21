package com.kamilkirstein.indexcards.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
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
        return inflater.inflate(R.layout.fragment_show_card_categories, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.i("INDEXCARDAcheck", "Hier komm ich noch hin");
        // ****************************set view pager 2**************************
        viewPager2 = view.findViewById(R.id.vp2_lastInsertedCards);
        indexCardCollectionAdapter = new IndexCardCollectionAdapter(this);
        viewPager2.setAdapter(indexCardCollectionAdapter);

        // TODO this is not the solution that I wanted but
        // https://stackoverflow.com/questions/10098040/android-viewpager-show-preview-of-page-on-left-and-right here is the solution to consider
        viewPager2.setClipToPadding(false);
        viewPager2.setPadding(10,0,10,0);

        // to see the next and previ card ahead


        // ****************************set recycler view*************************
        rvCategories = view.findViewById(R.id.rV_showIndexCardsWithCategories);
        adapter = new IndexCardCategoryAdapter(view.getContext());
        rvCategories.setAdapter(adapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(view.getContext()));

        setUpViewModels();
    }

    public class IndexCardCollectionAdapter extends FragmentStateAdapter {

        List<EditIndexCardFrg> frgContainer = new ArrayList<EditIndexCardFrg>();
        public IndexCardCollectionAdapter(Fragment fragment) {
            super(fragment);
        }

        public void setFrgContainer(List<EditIndexCardFrg> frgContainer) {
            this.frgContainer = frgContainer;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
                Toast.makeText(getContext(),"Welche ID hat das Fragment beim erstellen: "+ String.valueOf(frgContainer.get(position).getArguments().getInt(EditIndexCardFrg.ARG_CARD_ID)),Toast.LENGTH_LONG).show();
                return frgContainer.get(position);
            // else return a new fragment with the right id;
        }

        @Override
        public int getItemCount() {
                return frgContainer.size();
        }
        @Override
        public boolean containsItem(long itemId) {

            for (EditIndexCardFrg frg : frgContainer)
            {
                if ((long) frg.getArguments().getInt(EditIndexCardFrg.ARG_CARD_ID) == itemId )
                {
                    return true;
                }
            }
            return false;
        }

        @Override
        public long getItemId(int position) {
            if(position >= getItemCount() || position < 0)
                return RecyclerView.NO_ID;

            return (long) frgContainer.get(position).getArguments().getInt(EditIndexCardFrg.ARG_CARD_ID);
        }

    }

    // this view model gets the latest 10
    public void  setUpViewModels(){

        // viewModel for categories:
        viewModel = new ViewModelProvider(this).get(ShowIndexCardCategoriesViewModel.class);
        viewModel.getIndexCardCategories().observe(getViewLifecycleOwner(), new Observer<List<IndexCardCategory>>() {
            @Override
            public void onChanged(List<IndexCardCategory> indexCardCategories) {
                adapter.setmContainer(indexCardCategories);
                adapter.notifyDataSetChanged();
            }
        });

        // viewModel for index cards:
        indexCardViewModel = new ViewModelProvider(this).get(IndexCardViewModel.class);
        indexCardViewModel.queryLastTenIndexCards().observe(getViewLifecycleOwner(), new Observer<List<IndexCard>>() {
            @Override
            public void onChanged(List<IndexCard> indexCards) {

                mIndexCards = indexCards;
                List<EditIndexCardFrg> listOfFragments = new ArrayList<EditIndexCardFrg>();

                if(indexCards.size() >0)
                    for(IndexCard card :indexCards)
                    {
                        EditIndexCardFrg newfragment = EditIndexCardFrg.newInstance(card.getId());
                        Toast.makeText(getContext(),"setnewindex"+String.valueOf(newfragment.getArguments().getInt(EditIndexCardFrg.ARG_CARD_ID)),Toast.LENGTH_LONG ).show();
                        listOfFragments.add(newfragment);
                    }

                indexCardCollectionAdapter.setFrgContainer(listOfFragments);
                indexCardCollectionAdapter.notifyDataSetChanged();
            }
        });

    }


}