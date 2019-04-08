package com.example.cyd.albummanager.fragment;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cyd.albummanager.R;
import com.example.cyd.albummanager.adapter.GridImageAdapter;

public class LoadAlbumFragment extends Fragment {
    public static final String TAG = LoadAlbumFragment.class.getSimpleName();

    private RecyclerView mImgRecyclerView;
    private GridImageAdapter mImageAdapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_load_album, container, false);
        mImageAdapter = new GridImageAdapter(getContext());
        //设置布局
        mImgRecyclerView = (RecyclerView) rootView.findViewById(R.id.grid_recy_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mImgRecyclerView.setLayoutManager(gridLayoutManager);
        mImgRecyclerView.setAdapter(mImageAdapter);

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public LoadAlbumFragment() {

    }


}
