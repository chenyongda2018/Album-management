package com.example.cyd.albummanager.Bean;

/**
 * Created by cyd on 19-4-11.
 */

public class ImageBean {

    private String abFilePath;
    private String date;
    private String uilFilePath;

    public ImageBean(String abFilePath, String date, String uilFilePath) {
        this.abFilePath = abFilePath;
        this.date = date;
        this.uilFilePath = uilFilePath;
    }

    public String getAbFilePath() {
        return abFilePath;
    }

    public void setAbFilePath(String abFilePath) {
        this.abFilePath = abFilePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUilFilePath() {
        return uilFilePath;
    }

    public void setUilFilePath(String uilFilePath) {
        this.uilFilePath = uilFilePath;
    }
}
