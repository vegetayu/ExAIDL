package com.capsule.download;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yuluyao on 2018/1/17.
 */

public class Task implements Parcelable {

    private int id;
    private  String tag;
    private  int progressPercent;
    private String storePath;
    private String sourceUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.tag);
        dest.writeInt(this.progressPercent);
        dest.writeString(this.storePath);
        dest.writeString(this.sourceUrl);
    }

    public Task() {
    }

    protected Task(Parcel in) {
        this.id = in.readInt();
        this.tag = in.readString();
        this.progressPercent = in.readInt();
        this.storePath = in.readString();
        this.sourceUrl = in.readString();
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
