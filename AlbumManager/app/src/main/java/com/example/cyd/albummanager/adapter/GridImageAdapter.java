package com.example.cyd.albummanager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cyd.albummanager.Bean.ImageBean;
import com.example.cyd.albummanager.Bean.ImageGroup;
import com.example.cyd.albummanager.R;
import com.example.cyd.albummanager.data.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.truizlop.sectionedrecyclerview.SimpleSectionedAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cyd on 19-4-8.
 */

public class GridImageAdapter extends SectionedRecyclerViewAdapter<GridImageAdapter.HeadHolder,
                                        GridImageAdapter.GirdImageHolder,GridImageAdapter.FooterHolder> {
    private static final String TAG = GridImageAdapter.class.getSimpleName();
    private Context mContext;
    //存放所有图片的路径的列表
    private List<ImageBean> imgList = new ArrayList<>();
    private List<ImageGroup> imageGroups = new ArrayList<>();//分组信息
    private LayoutInflater mInfalter;
    private DisplayImageOptions mOptions;//加载设置

    public GridImageAdapter(Context context,List<ImageGroup> imageGroups) {
        this.mContext = context;
        this.imageGroups = imageGroups;
        mInfalter = LayoutInflater.from(context);
        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)//还没加载出来时显示的图片
                .showImageForEmptyUri(R.drawable.ic_empty1)//无效URL加载的图片
                .showImageOnFail(R.drawable.ic_error)//加载失败时显示的图片
                .cacheInMemory(true)//打开内存缓存
                .cacheOnDisk(true)//打开磁盘缓存
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    protected int getSectionCount() {
        return imageGroups.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        return imageGroups.get(section).getImageBeanList().size();
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeadHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeadHolder(mInfalter.inflate(R.layout.header_date_layout,parent,false));
    }

    @Override
    protected FooterHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected GirdImageHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new GirdImageHolder(mInfalter.inflate(R.layout.grid_item_layout,parent,false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(HeadHolder holder, int section) {
        holder.headerText.setText(imageGroups.get(section).getDateHeader());//显示时期
    }

    @Override
    protected void onBindSectionFooterViewHolder(FooterHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(GirdImageHolder holder, int section, int position) {
        ImageLoader.getInstance()
                .displayImage(imageGroups.get(section).getImageBeanList().get(position).getUilFilePath(),
                        holder.imgView, mOptions, new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingStarted(String imageUri, View view) {

                            }

                            @Override
                            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                            }

                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                            }
                        }, new ImageLoadingProgressListener() {
                            @Override
                            public void onProgressUpdate(String imageUri, View view, int current, int total) {

                            }
                        });
    }


    class GirdImageHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;


        public GirdImageHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.img_item);

        }
    }

    class HeadHolder extends RecyclerView.ViewHolder {
        public TextView headerText;

        public HeadHolder(View itemView) {
            super(itemView);
            headerText = (TextView) itemView.findViewById(R.id.header_date);
        }
    }
    class FooterHolder extends RecyclerView.ViewHolder {

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
}
