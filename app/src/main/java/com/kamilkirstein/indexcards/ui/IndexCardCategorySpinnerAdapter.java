package com.kamilkirstein.indexcards.ui;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.kamilkirstein.indexcards.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class IndexCardCategorySpinnerAdapter extends ArrayAdapter<IndexCardCategory> {

    private LayoutInflater layoutInflater;
    private List<IndexCardCategory> mContainer;

    public void setmContainer(List<IndexCardCategory> mContainer) {
        this.mContainer = mContainer;
        // the container has always an item on the last place

        String sNewCategory = "New Category";
        IndexCardCategory newCategory = new IndexCardCategory();
        newCategory.setName(sNewCategory);
        this.mContainer.add(newCategory);
    }

    public IndexCardCategorySpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        layoutInflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public IndexCardCategory getItem(int position)
    {
        return mContainer.get(position);
    }

    @Override
    public int getCount() {
        if(mContainer != null)
            return mContainer.size();
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rowView = layoutInflater.inflate(R.layout.category_spinner_adapter,null, true);
        TextView lbl = (TextView) rowView.findViewById(R.id.tv_spinnerCategoryItem);
        lbl.setText(mContainer.get(position).getName());

        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if ( convertView == null)
            convertView = layoutInflater.inflate(R.layout.category_spinner_adapter,null, false);

        TextView lbl = (TextView) convertView.findViewById(R.id.tv_spinnerCategoryItem);
        lbl.setText(mContainer.get(position).getName());
        return convertView;
    }



    //TODO end this spinner arrayadapter using the following examples:
    //https://stackoverflow.com/questions/10678521/spinner-with-custom-arrayadapter-for-objects-not-displaying-selected-item
    // https://stackoverflow.com/questions/1625249/android-how-to-bind-spinner-to-custom-object-list
}
