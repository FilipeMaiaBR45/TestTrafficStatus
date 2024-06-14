package com.example.testtrafficstatus;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class DataUsageService extends Service {
    private static final String TAG = "DataUsageService";
    private static final long INTERVAL = 2000; // Intervalo de 2 segundos

    private Handler handler;
    private Runnable runnable;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                checkDataUsage();
                handler.postDelayed(this, INTERVAL);
            }
        };
        handler.post(runnable);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void checkDataUsage() {
        PackageManager pm = getPackageManager();
        String trelloPackage = "com.trello";
        try {
            ApplicationInfo trelloAppInfo = pm.getApplicationInfo(trelloPackage, 0);
            int trelloUid = trelloAppInfo.uid;
            Log.d(TAG, "UID " + trelloUid);

            long trelloMobileTxBytes = TrafficStats.getUidTxBytes(trelloUid);
            long trelloMobileRxBytes = TrafficStats.getUidRxBytes(trelloUid);
            long trelloWifiTxBytes = TrafficStats.getUidTxBytes(trelloUid) - trelloMobileTxBytes;
            long trelloWifiRxBytes = TrafficStats.getUidRxBytes(trelloUid) - trelloMobileRxBytes;

            Log.d(TAG, "Trello Mobile Data Usage (Bytes): " + (trelloMobileTxBytes + trelloMobileRxBytes));
            Log.d(TAG, "Trello Wi-Fi Data Usage (Bytes): " + (trelloWifiTxBytes + trelloWifiRxBytes));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Application not found: " + e.getMessage());
        }
    }
}