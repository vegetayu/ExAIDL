// IDownloadManager.aidl
package com.capsule.download;

// Declare any non-default types here with import statements
import com.capsule.download.DownloadTask;
import com.capsule.download.DownloadCallback;

interface DownloadManager {
    List<DownloadTask> queryStatus();

    void addDownloadTask(String url,String path,String tag);

    void addCallback(DownloadCallback downloadCallback);

    void removeCallback(DownloadCallback downloadCallback);

}
