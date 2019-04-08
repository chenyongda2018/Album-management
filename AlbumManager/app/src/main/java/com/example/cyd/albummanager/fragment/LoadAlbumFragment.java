package com.example.cyd.albummanager.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cyd.albummanager.R;
import com.example.cyd.albummanager.adapter.GridImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoadAlbumFragment extends Fragment {
    public static final String TAG = LoadAlbumFragment.class.getSimpleName();

    private RecyclerView mImgRecyclerView;
    private GridImageAdapter mImageAdapter;

    //存放所有图片的路径的列表
    private List<StringBuffer> imgList = new ArrayList<>();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initAllImgInThePhone();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_load_album, container, false);
        //设置布局
        mImageAdapter = new GridImageAdapter(getContext(), imgList);
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

    /**
     * 查询手机里所有的图片
     */
    public void initAllImgInThePhone() {

        String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE
        };
        //全部图片
        String where = MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=? or "
                + MediaStore.Images.Media.MIME_TYPE + "=?";
        //指定格式
        String[] whereArgs = {"image/jpeg", "image/png", "image/jpg"};
        //查询
        Cursor cursor = getContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, where, whereArgs,
                MediaStore.Images.Media.DATE_MODIFIED + " desc ");
        while (cursor.moveToNext()) {
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String dataStr = new String(data, 0, data.length - 1);
            StringBuffer imgPath = new StringBuffer("file://").append(dataStr);
            imgList.add(imgPath);
            Log.d(TAG, imgPath.toString());
        }


    }


}
