package com.kamilkirstein.indexcards.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kamilkirstein.indexcards.R;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShowIndexCardCategoriesFrg extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<IndexCardCategory> containerCategories = new ArrayList<IndexCardCategory>();
        IndexCardCategory iC1 = new IndexCardCategory();
        iC1.setName("Card1");

        IndexCardCategory iC2 = new IndexCardCategory();
        iC2.setName("Card2");

        containerCategories.add(iC1);
        containerCategories.add(iC2);



        // populate the recycler view:
        RecyclerView rvCategories = view.findViewById(R.id.rV_showIndexCardCategories);
        final IndexCardCategoryAdapter adapter = new IndexCardCategoryAdapter(view.getContext());
        rvCategories.setAdapter(adapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter.setmContainer(containerCategories);



        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowIndexCardCategoriesFrgDirections.ActionFirstFragmentToEditIndexCard action =
                       ShowIndexCardCategoriesFrgDirections.actionFirstFragmentToEditIndexCard("Test1");

               NavHostFragment.findNavController(ShowIndexCardCategoriesFrg.this)
                        .navigate(action);
            }
        });

    }
}