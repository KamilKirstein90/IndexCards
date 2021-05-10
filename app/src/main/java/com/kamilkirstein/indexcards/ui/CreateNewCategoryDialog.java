package com.kamilkirstein.indexcards.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.kamilkirstein.indexcards.R;
import com.kamilkirstein.indexcards.Repository;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

// custom dialog for our fragment extends DialogFragment not AppCompatDialogFragment
public class CreateNewCategoryDialog extends DialogFragment implements
        android.view.View.OnClickListener  {

    private EditText etCategoryName;
    private Button btnOk;
    private Button btnCancel;
    private Repository mRepository;
    private IndexCardCategory mIndexCardCategory;
    private ShowIndexCardCategoriesViewModel viewModel;


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_dlgCreateCategoryCancel:
                this.dismiss();
                break;
            case R.id.btn_dlgCreateCategorySave:
                // TODO wirte a new category to db

                if (etCategoryName.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Please set a name", Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    String categoryName = etCategoryName.getText().toString();
                    mIndexCardCategory.setName(categoryName);
                    viewModel = new ViewModelProvider(this).get(ShowIndexCardCategoriesViewModel.class);
                    viewModel.insertIndexCardCategory(mIndexCardCategory);
                    Toast.makeText(getContext(), categoryName, Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public CreateNewCategoryDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown bel
    }

    public static CreateNewCategoryDialog newInstance(String title) {
        Bundle args = new Bundle();
        CreateNewCategoryDialog fragment = new CreateNewCategoryDialog();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // use the inflater to inflate our custom layout for the dialog
        return inflater.inflate(R.layout.dialog_create_a_index_card_category,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mIndexCardCategory = new IndexCardCategory();
        etCategoryName = view.findViewById(R.id.et_dlg_CategoryName);
        btnOk = view.findViewById(R.id.btn_dlgCreateCategorySave);
        btnCancel = view.findViewById(R.id.btn_dlgCreateCategoryCancel);
        btnOk.setOnClickListener(this::onClick);
        btnCancel.setOnClickListener(this::onClick);
        String title = getArguments().getString("title");
        getDialog().setTitle(title);
        getDialog().getWindow().setBackgroundDrawable(getDrawable(getContext(),R.drawable.dlg_background));


    }


    /* with the onCreateDialog and the AlertDialog.Builder builder i can use the existing parts to build an alert dialog but not a custom one
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //1.1 create a builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //1.2 create a layoutInflater
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        //1.3 create a view by inflating the created custom layout for our dialog with the created inflater
        View view = layoutInflater.inflate(R.layout.dialog_create_a_index_card_category, null);
        // 1.4 build the dialog witht the builder

        builder.setView(view)
                .setTitle("Create a new Category") // 1.4.1 set title
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // 1.4.2 set the negative button give it a tittle and create /TODO a OnClickListener i can replace this with a lambda funnction
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing but dismiss the dialog
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO write a category in to the db
                    }
                });
       // getDialog().getWindow().setBackgroundDrawable(getDrawable(getContext(),R.drawable.dlg_background));
        etCategoryName = view.findViewById(R.id.et_IndexCardName);
        mIndexCardCategory = new IndexCardCategory();
        mIndexCardCategory.setName(etCategoryName.getText().toString());

        // return a created dialog
        return builder.create();
    }
    */

}
