package com.example.admin1.firstlogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class activity_customer_search extends Fragment {
    private ArrayList<search_item> mSearchList;

    private RecyclerView mRecyclerView;
    private searchListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search_layout);
    }
    */
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container = (ViewGroup) inflater.inflate(R.layout.activity_customer_search_layout, container, false);


        createSearchList();
        buildRecyclerView();

        EditText editText = container.findViewById(R.id.searchBar);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ArrayList<search_item> emptyList = new ArrayList<>();

                //FIGURE THIS OUT!!!!!!
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        //mRecyclerView = findViewById(R.id.searchList);
        mRecyclerView  = container.findViewById(R.id.searchList);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());

        mAdapter = new searchListAdapter(mSearchList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return container;
    }

    private void filter(String text) {
        ArrayList<search_item> filteredList = new ArrayList<>();

        for (search_item item : mSearchList) {
            if (item.getText1().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }

    private void createSearchList() {
        mSearchList = new ArrayList<>();
        mSearchList.add(new search_item(R.drawable.smiley, "John Doe's BBQ", "123-456-7890", "johndoe@pvamu.edu"));
        mSearchList.add(new search_item(R.drawable.bluesmiley, "Ronald Reagan Textbooks", "098-765-4321", "ronaldreagan@pvamu.edu"));
        mSearchList.add(new search_item(R.drawable.redsmiley, "JFK's Photography", "222-333-4444", "jfkphotos@pvamu.edu"));

        mSearchList.add(new search_item(R.drawable.smiley, "Company 4", "XXX-XXX-XXXX", "company4@pvamu.edu"));
        mSearchList.add(new search_item(R.drawable.bluesmiley, "Company 5", "XXX-XXX-XXXX", "company5@pvamu.edu"));
        mSearchList.add(new search_item(R.drawable.redsmiley, "Company 6", "XXX-XXX-XXXX", "company6@pvamu.edu"));

        mSearchList.add(new search_item(R.drawable.smiley, "Company 7", "XXX-XXX-XXXX", "company7@pvamu.edu"));
        mSearchList.add(new search_item(R.drawable.bluesmiley, "Company 8", "XXX-XXX-XXXX", "company8@pvamu.edu"));
        mSearchList.add(new search_item(R.drawable.redsmiley, "Company 9", "XXX-XXX-XXXX", "company9@pvamu.edu"));

        mSearchList.add(new search_item(R.drawable.smiley, "Company 10", "XXX-XXX-XXXX", "company10@pvamu.edu"));
        mSearchList.add(new search_item(R.drawable.bluesmiley, "Company 11", "XXX-XXX-XXXX", "company11@pvamu.edu"));
        mSearchList.add(new search_item(R.drawable.redsmiley, "Company 12", "XXX-XXX-XXXX", "company12@pvamu.edu"));

    }

    private void buildRecyclerView() {

    }


}

