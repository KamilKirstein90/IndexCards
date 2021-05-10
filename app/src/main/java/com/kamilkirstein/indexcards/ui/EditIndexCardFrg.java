package com.kamilkirstein.indexcards.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kamilkirstein.indexcards.R;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditIndexCardFrg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditIndexCardFrg extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner spinner;
    private IndexCardCategorySpinnerAdapter adapter;
    private ShowIndexCardCategoriesViewModel viewModel;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List <IndexCardCategory> categories;
    Button btnSave;

    public EditIndexCardFrg() {
        // Required empty public constructor
    }

    public  List<IndexCardCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<IndexCardCategory> categories) {
        this.categories = categories;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditIndexCard.
     */
    // TODO: Rename and change types and number of parameters
    public static EditIndexCardFrg newInstance(String param1, String param2) {
        EditIndexCardFrg fragment = new EditIndexCardFrg();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories = new ArrayList<IndexCardCategory>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          return inflater.inflate(R.layout.fragment_edit_index_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ************************************************** Test for Args *******************************************************
        String name = null;
        if (getArguments() != null) {
            name = EditIndexCardFrgArgs.fromBundle(getArguments()).getCardName();
        }
        EditText etCardName = view.findViewById(R.id.et_IndexCardName);
        etCardName.setText(name);

        // **************************************** spinner adapter ****************************************
        spinner = view.findViewById(R.id.sp_category);
        adapter = new IndexCardCategorySpinnerAdapter(view.getContext(),R.layout.category_spinner_adapter);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(position == parent.getCount()-1) {
                    Toast.makeText(view.getContext(), "Neue Kategorie", Toast.LENGTH_SHORT).show();
                    // use the static new instance method to create a new dialog
                    CreateNewCategoryDialog dlg = CreateNewCategoryDialog.newInstance("Create a new Cateogry");
                    // here i take the getParentFragmentManager the getSupportFragmentManager is not supportet inside a fragment and the get FragmentManager is debricate
                    dlg.show(getParentFragmentManager(), "Create a new category dialog");
                }


            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        // ***************************************************** view Model to get data from the db ********************************
        viewModel = new ViewModelProvider(this).get(ShowIndexCardCategoriesViewModel.class);
        viewModel.getIndexCardCategories().observe(getViewLifecycleOwner(), new Observer<List<IndexCardCategory>>() {
            @Override
            public void onChanged(List<IndexCardCategory> indexCardCategories) {
                // in herer we pass the container from the db to the adapter
                adapter.setmContainer(indexCardCategories);
                adapter.notifyDataSetChanged();

                setCategories(indexCardCategories);
            }
        });


        // ****************************************************** save button later to write into the db***********************************
        btnSave = view.findViewById(R.id.btn_saveCard);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), String.valueOf(getCategories().size()), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void initEditIndexCard(){

    }
}