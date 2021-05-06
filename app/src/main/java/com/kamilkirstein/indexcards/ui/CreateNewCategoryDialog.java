package com.kamilkirstein.indexcards.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.kamilkirstein.indexcards.R;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

// custom dialog for our fragment:
public class CreateNewCategoryDialog extends AppCompatDialogFragment {

    private EditText etCategoryName;
    private IndexCardCategory mIndexCardCategory;

    //1) override the onCreateDialog method:
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

        etCategoryName = view.findViewById(R.id.et_IndexCardName);
        mIndexCardCategory = new IndexCardCategory();
        mIndexCardCategory.setName(etCategoryName.getText().toString());

        // return a created dialog
        return builder.create();
    }
}
