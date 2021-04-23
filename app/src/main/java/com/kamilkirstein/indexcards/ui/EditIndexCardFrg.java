package com.kamilkirstein.indexcards.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kamilkirstein.indexcards.R;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditIndexCardFrg() {
        // Required empty public constructor
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

        String name = null;

        if (getArguments() != null) {
            name = EditIndexCardFrgArgs.fromBundle(getArguments()).getCardName();
        }

        EditText etCardName = view.findViewById(R.id.et_IndexCardName);
        etCardName.setText(name);
    }
}