package com.kaywalker.sns_proto2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaywalker.sns_proto2.R;
import com.kaywalker.sns_proto2.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;

public class Frag_UserList extends Fragment {

    String[] names = {"user1", "user2", "user3", "user4", "user5", "user6", "user7", "user7"};

    private View view;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_userlist, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(names));

        mRecyclerView.setHasFixedSize(true);

        return view;

    }

}
