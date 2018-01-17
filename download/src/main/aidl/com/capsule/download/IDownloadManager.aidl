// IDownloadManager.aidl
package com.capsule.download;

// Declare any non-default types here with import statements
import com.capsule.download.Task;
import com.capsule.download.Callback;

interface IDownloadManager {
    List<Task> queryStatus();

    void addDownloadTask(String url,String path,String tag);

    void addCallback(Callback callback);

    void removeCallback(Callback callback);

}
