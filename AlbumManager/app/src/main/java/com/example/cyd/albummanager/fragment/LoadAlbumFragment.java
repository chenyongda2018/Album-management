package com.example.cyd.albummanager.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cyd.albummanager.Bean.ImageBean;
import com.example.cyd.albummanager.Bean.ImageGroup;
import com.example.cyd.albummanager.R;
import com.example.cyd.albummanager.adapter.GridImageAdapter;
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LoadAlbumFragment extends Fragment {
    public static final String TAG = LoadAlbumFragment.class.getSimpleName();

    private RecyclerView mImgRecyclerView;
    private GridImageAdapter mImageAdapter;

    //存放所有图片的路径的列表
    private List<ImageBean> imgList = new ArrayList<>();
    private List<ImageGroup> imageGroups  = new ArrayList<>();


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
        //设置布局

        mImageAdapter = new GridImageAdapter(getContext(), imageGroups);
        initAllImgInThePhone();
        mImgRecyclerView = (RecyclerView) rootView.findViewById(R.id.grid_recy_view);
        mImgRecyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        SectionedSpanSizeLookup lookup = new SectionedSpanSizeLookup(mImageAdapter,gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(lookup);
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
        List<String> dateList = new ArrayList<>();//存日期,因为照片按分组
        while (cursor.moveToNext()) {
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String dataStr = new String(data, 0, data.length - 1);//图片在手机里的路径
            File file = new File(dataStr);
            long time = file.lastModified(); //记录此图片的上次修改时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(time); // 2019-04-10
            StringBuffer imgPath = new StringBuffer("file://").append(dataStr);
            ImageBean bean = new ImageBean(dataStr, dateStr, imgPath.toString());
            imgList.add(bean);
            if (!dateList.contains(dateStr)) {
                dateList.add(dateStr);
            }
        }

        for (String str : dateList) {
            ImageGroup group = new ImageGroup(str);
            List<ImageBean> beans = new ArrayList<>();

            for (ImageBean bean : imgList) {
                if(bean.getDate().equals(str)) {
                    beans.add(bean);
                }
            }
            group.setImageBeanList(beans);
            imageGroups.add(group);
        }
        mImageAdapter.notifyDataSetChanged();


        for (ImageGroup group : imageGroups) {
            if (group.getImageBeanList().isEmpty()) {
                Log.d(TAG,group.getDateHeader());
            }
        }



    }


}
