package com.capsule.download;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import capsule.bamboo.Logger;

/**
 * Created by yuluyao on 2018/1/16.
 */

public class DownloadService extends Service {

    private CopyOnWriteArrayList<DownloadTask> mDownloadTaskList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<DownloadCallback> mCallback = new RemoteCallbackList<>();

    private IBinder mBinder = new DownloadManager.Stub() {
        @Override
        public List<DownloadTask> queryStatus() throws RemoteException {
            return mDownloadTaskList;
        }

        @Override
        public void addDownloadTask(String url, String path, String tag) throws RemoteException {
            Logger.i("收到任务：" + url + " ~~ " + path + " ~~ " + tag);
//            mDownloadTaskList.add()
        }

        @Override
        public void addCallback(DownloadCallback downloadCallback) throws RemoteException {
            mCallback.register(downloadCallback);
        }

        @Override
        public void removeCallback(DownloadCallback downloadCallback) throws RemoteException {
            mCallback.unregister(downloadCallback);
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new DownloadWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    private  class DownloadWorker implements  Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(5000);
                    int count = mCallback.beginBroadcast();
                    for (int j = 0; j < count; j++) {
                        DownloadCallback item = mCallback.getBroadcastItem(j);
                        item.onProgressUpdate(mDownloadTaskList);
                    }
                    mCallback.finishBroadcast();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }


        }
    }


}
