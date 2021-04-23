package com.kamilkirstein.indexcards.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kamilkirstein.indexcards.R;
import com.kamilkirstein.indexcards.dto.IndexCardCategory;

import org.w3c.dom.Text;
import java.util.List;


public class IndexCardCategoryAdapter extends RecyclerView.Adapter<IndexCardCategoryAdapter.IndexCardCategoryViewHolder> {

    private List<IndexCardCategory> mContainer;
    private LayoutInflater mInflater;

    public IndexCardCategoryAdapter(Context ct) {
        this.mInflater = LayoutInflater.from(ct);
    }

    @NonNull
    @Override
    public IndexCardCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_indexcardcategory, parent, false);
        return  new IndexCardCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexCardCategoryViewHolder holder, int position) {

        if (mContainer != null)
        {
            holder.mTvName.setText(mContainer.get(position).getName());
            holder.mTVAmountOfCards.setText(String.valueOf(mContainer.get(position).getIdxCards().size()));
        }

    }

    void setmContainer(List<IndexCardCategory> container)
    {
        mContainer = container;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mContainer != null)
            return mContainer.size();
        else return 0;
    }

    public class IndexCardCategoryViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTvName;
        private final TextView mTVAmountOfCards;
        private final Button btnShowCards;


        private IndexCardCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tV_CategoryNameValue);
            mTVAmountOfCards = itemView.findViewById(R.id.tV_AmountOfCardsValue);
            btnShowCards = itemView.findViewById(R.id.btn_showCards);
        }
    }
}