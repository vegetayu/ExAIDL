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

    private CopyOnWriteArrayList<Task> mTaskList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<Callback> mCallback = new RemoteCallbackList<>();

    private IBinder mBinder = new IDownloadManager.Stub() {
        @Override
        public List<Task> queryStatus() throws RemoteException {
            return mTaskList;
        }

        @Override
        public void addDownloadTask(String url, String path, String tag) throws RemoteException {
            Logger.i("收到任务：" + url + " ~~ " + path + " ~~ " + tag);
//            mTaskList.add()
        }

        @Override
        public void addCallback(Callback callback) throws RemoteException {
            mCallback.register(callback);
        }

        @Override
        public void removeCallback(Callback callback) throws RemoteException {
            mCallback.unregister(callback);
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
                        Callback item = mCallback.getBroadcastItem(j);
                        item.onProgressUpdate(mTaskList);
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
