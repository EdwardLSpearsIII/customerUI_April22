package com.example.admin1.firstlogin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class searchListAdapter extends RecyclerView.Adapter<searchListAdapter.ExampleViewHolder> {

    private ArrayList<search_item> mSearchList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.logo2);
            mTextView1 = itemView.findViewById(R.id.name2);
            mTextView2 = itemView.findViewById(R.id.number2);
            mTextView3 = itemView.findViewById(R.id.email_address2);

        }
    }

    public searchListAdapter(ArrayList<search_item> searchList) {
        mSearchList = searchList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,
                parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        search_item currentItem = mSearchList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mTextView3.setText(currentItem.getText3());
    }

    @Override
    public int getItemCount() {
        return mSearchList.size();
    }

    public void filterList(ArrayList<search_item> filteredList) {
        mSearchList = filteredList;
        notifyDataSetChanged();
    }
}
