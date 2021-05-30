package com.kamilkirstein.indexcards.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
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
import com.kamilkirstein.indexcards.dto.IndexCard;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditIndexCardFrg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditIndexCardFrg extends Fragment {

    //    TODO Kamil Check this out for dynamicaly programfragments inside a fragment
    //    https://stackoverflow.com/questions/16728426/android-nested-fragment-approach

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_CARD_ID = "IndexCardId";

    Spinner spinner;
    private IndexCardCategorySpinnerAdapter adapter;
    private ShowIndexCardCategoriesViewModel viewModel;
    private List<IndexCardCategory> mCategories;


    private int mArgCardId = 0;
    private EditText  etCardName;
    private IndexCardViewModel indexCardViewModel;
    private IndexCard mCard;

    //TODO: create a IndexCardViewModel
    private Button btnSave;
    private Button btnSwitch;
    private StateOfCard mCardState = StateOfCard.QUESTION;

    public enum StateOfCard{
        QUESTION ,
        ANSWER
    }

    public EditIndexCardFrg() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EditIndexCardFrg newInstance(int indexCardId) {
        EditIndexCardFrg fragment = new EditIndexCardFrg();
        Bundle args = new Bundle();
        args.putInt(ARG_CARD_ID, indexCardId);
        fragment.setArguments(args);
        return fragment;
    }

    public  List<IndexCardCategory> getmCategories() {
        return mCategories;
    }

    public void setmCategories(List<IndexCardCategory> mCategories) {
        this.mCategories = mCategories;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategories = new ArrayList<IndexCardCategory>();
        mCard = new IndexCard();
        //TO DO chek the id of the Fragment
        Log.i("IDEDITFRG", String.valueOf(this.getId()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_index_card, container, false);
        
        setUpChildFragment();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ************************************************** Test for Args *******************************************************
        if (getArguments() != null && savedInstanceState != null) {
            mArgCardId = savedInstanceState.getInt(ARG_CARD_ID);

            if (mArgCardId > 0)
            {
                // if this is right I will get index card for the id from the db and the fragment is for editing a consisting index card
            }
        }

        setUpEditTexts(view);
        setUpSpinnerAndAdapter(view);


        // ***************************************************** view Model to get data from the db ********************************
        viewModel = new ViewModelProvider(this).get(ShowIndexCardCategoriesViewModel.class);
        viewModel.getIndexCardCategories().observe(getViewLifecycleOwner(), new Observer<List<IndexCardCategory>>() {
            @Override
            public void onChanged(List<IndexCardCategory> indexCardCategories) {

                // in here we pass the container from the db to the adapter
                adapter.setmContainer(indexCardCategories);
                adapter.notifyDataSetChanged();
                setmCategories(indexCardCategories);
            }
        });

        MainActivity mainAct = (MainActivity) getActivity();
        mainAct.setUpTextViewBottomAppbar(this);
    }

    private void setUpChildFragment(){

        // the initial state is always the question side
        mCardState = StateOfCard.QUESTION;

        // check if there is already a child fragment
        EditIndexCardFrgChild editIndexCardChildFrg  = (EditIndexCardFrgChild) getChildFragmentManager().findFragmentById(R.id.fl_editICFrgHolder);

        // add the child fragment to the frame layout
        if (null == editIndexCardChildFrg) {

            editIndexCardChildFrg = EditIndexCardFrgChild.newInstance(getLabelBasedOnStateOfCard(), getValueBasedOnStateOfCard());
            FragmentTransaction transaction = getChildFragmentManager()
                    .beginTransaction();

            transaction.add(R.id.fl_editICFrgHolder, editIndexCardChildFrg)
                    .addToBackStack(null).commit();
        }

    }

    private void setUpEditTexts(@NonNull View view){
        EditText etCardName = view.findViewById(R.id.et_IndexCardName);
        etCardName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    mCard.setName(etCardName.getText().toString());

            }
        });
    }

    private void setUpSpinnerAndAdapter(@NonNull View view){
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
    }

    // check if the input for the index card is valid, not empty
    private boolean checkInput(){

        if(mCard.getName().isEmpty())
        {
            Toast.makeText(getContext(), "Please set a card name.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(mCard.getCategoryId() == 0)
        {
            Toast.makeText(getContext(), "Please choose a card category.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(mCard.getQuestion().isEmpty())
        {
            Toast.makeText(getContext(), "Please set a Question.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(mCard.getAnswer().isEmpty())
        {
            Toast.makeText(getContext(), "Please set an answer.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    // this method will be called from "outside" the fragment in the main Act over the current Fragment and then the child fragment
    public void changeQA(){

        // get a the current child fragment and safe the EditTextvalue of it to the IndexCard member of the parent fragment
        EditIndexCardFrgChild editIndexCardChildFrg  = (EditIndexCardFrgChild) getChildFragmentManager().findFragmentById(R.id.fl_editICFrgHolder);

        if (editIndexCardChildFrg != null)
        {
            if (mCardState == StateOfCard.ANSWER)
                mCard.setAnswer(editIndexCardChildFrg.getmEditTextValue());
            if(mCardState == StateOfCard.QUESTION)
                mCard.setQuestion(editIndexCardChildFrg.getmEditTextValue());
        }
        // change the state

        if(mCardState == StateOfCard.ANSWER)
            mCardState = StateOfCard.QUESTION;
        else
            mCardState = StateOfCard.ANSWER;


        EditIndexCardFrgChild newChildFrg = EditIndexCardFrgChild.newInstance(getLabelBasedOnStateOfCard(), getValueBasedOnStateOfCard());
        FragmentTransaction transaction = getChildFragmentManager()
                .beginTransaction();
        transaction.setCustomAnimations(R.anim.card_flip_left_in, R.anim.card_flip_left_out, R.anim.card_flip_right_in, R.anim.card_flip_right_out);
        transaction.replace(R.id.fl_editICFrgHolder, newChildFrg)
                .addToBackStack(null).commit();
    }

    public String getLabelBasedOnStateOfCard()
    {
        switch (mCardState)
        {
            case ANSWER:
                return "Answer";
            case QUESTION:
                return "Question";
        }
        return "";
    }

    private String getValueBasedOnStateOfCard(){

        if (mCard != null) {
            switch (mCardState) {
                case ANSWER:
                    return mCard.getAnswer();
                case QUESTION:
                    return mCard.getQuestion();
            }
        }
        return "";
    }

    public void safeIndexCard(){

        if(!checkInput())
            return;

        indexCardViewModel = new ViewModelProvider(this).get(IndexCardViewModel.class);
        indexCardViewModel.insertIndexCard(mCard);
    }
}