package com.example.cyd.albummanager.Bean;

import java.util.List;

/**
 * Created by cyd on 19-4-11.
 */

public class ImageGroup {
    private String dateHeader;
    private List<ImageBean> imageBeanList;

    public ImageGroup(String dateHeader, List<ImageBean> imageBeanList) {
        this.dateHeader = dateHeader;
        this.imageBeanList = imageBeanList;
    }

    public String getDateHeader() {
        return dateHeader;
    }

    public void setDateHeader(String dateHeader) {
        this.dateHeader = dateHeader;
    }

    public List<ImageBean> getImageBeanList() {
        return imageBeanList;
    }

    public void setImageBeanList(List<ImageBean> imageBeanList) {
        this.imageBeanList = imageBeanList;
    }
}
