// Callback.aidl
package com.capsule.download;

// Declare any non-default types here with import statements
import com.capsule.download.DownloadTask;

interface DownloadCallback {
    void onProgressUpdate(out List<DownloadTask> list);
}
