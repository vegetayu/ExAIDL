// Callback.aidl
package com.capsule.download;

// Declare any non-default types here with import statements
import com.capsule.download.Task;

interface Callback {
    void onProgressUpdate(out List<Task> list);
}
