package com.kamilkirstein.indexcards.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kamilkirstein.indexcards.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditIndexCardFrgChild#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditIndexCardFrgChild extends Fragment {

    private static final String ARG_ANSWER_QUESTION_LABEL   = "IndexCardStateOfLabel";
    private static final String ANSWER_QUESTION_VALUE = "IndexCardTextValueQOrA";

    private String mLabel;
    private String mEditTextValue;

    private TextView tvIndexCardLabel;
    private EditText etAQInput;

    public String getmEditTextValue() {
        if (etAQInput != null && !etAQInput.getText().toString().isEmpty())
            mEditTextValue = etAQInput.getText().toString();

        return mEditTextValue;
    }

    public EditIndexCardFrgChild() {
        // Required empty public constructor
    }

    public static EditIndexCardFrgChild newInstance(@NonNull String labelValue, String editTextValue) {
        EditIndexCardFrgChild fragment = new EditIndexCardFrgChild();
        Bundle args = new Bundle();
        args.putString(ARG_ANSWER_QUESTION_LABEL, labelValue);
        args.putString(ANSWER_QUESTION_VALUE, editTextValue);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLabel = getArguments().getString(ARG_ANSWER_QUESTION_LABEL);
            mEditTextValue = getArguments().getString(ANSWER_QUESTION_VALUE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_index_card_frg_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //get the view and assign the value to it
        tvIndexCardLabel = view.findViewById(R.id.tvLabelIndexCard);
        etAQInput = view.findViewById(R.id.eT_indexCardAnwser);
        tvIndexCardLabel.setText(mLabel);
        if (mEditTextValue != null)
            etAQInput.setText(mEditTextValue);
    }
}