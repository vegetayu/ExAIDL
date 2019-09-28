package com.capsule.exaidl;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.capsule.download.DownloadCallback;
import com.capsule.download.DownloadManager;
import com.capsule.download.DownloadService;
import com.capsule.download.DownloadTask;

import java.util.List;


public class RemoteActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        findViewById(R.id.btn_bind).setOnClickListener(this);

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind:
                Intent intent = new Intent(RemoteActivity.this, DownloadService.class);
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
        }
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("vegeta","服务已连接");
            DownloadManager manager = DownloadManager.Stub.asInterface(service);
            try {
                manager.addDownloadTask("www.yuluyao.com", "/dev/tmp", "item_tag");
                manager.addCallback(mDownloadCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("vegeta","服务异常断开");
        }
    };

    private DownloadCallback mDownloadCallback = new DownloadCallback.Stub() {

        @Override
        public void onProgressUpdate(List<DownloadTask> list) throws RemoteException {
            Log.i("vegeta", "进度更新");
        }
    };
}
