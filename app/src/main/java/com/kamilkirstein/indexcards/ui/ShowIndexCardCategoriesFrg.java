package com.kamilkirstein.indexcards.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

    private RecyclerView rvCategories;
    private FloatingActionButton fab;
    private IndexCardCategoryAdapter adapter;
    private ShowIndexCardCategoriesViewModel viewModel;

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


        // TODO this maybe in one Method initRecyclerView:
        rvCategories = view.findViewById(R.id.rV_showIndexCardCategories);
        adapter = new IndexCardCategoryAdapter(view.getContext());
        rvCategories.setAdapter(adapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(view.getContext()));

        fab = view.findViewById(R.id.fab);
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