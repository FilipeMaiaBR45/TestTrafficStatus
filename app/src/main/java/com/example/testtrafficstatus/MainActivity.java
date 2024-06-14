package com.example.testtrafficstatus;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TrafficStatsExample";
    private static final int JOB_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, DataUsageService.class);
        startService(serviceIntent);


        //scheduleJob();

//        // Obtendo o total de bytes transmitidos e recebidos pela rede móvel
//        long mobileTxBytes = TrafficStats.getMobileTxBytes();
//        long mobileRxBytes = TrafficStats.getMobileRxBytes();
//
//        // Obtendo o total de bytes transmitidos e recebidos pela rede Wi-Fi
//        long wifiTxBytes = TrafficStats.getTotalTxBytes() - mobileTxBytes;
//        long wifiRxBytes = TrafficStats.getTotalRxBytes() - mobileRxBytes;
//
//        Log.d(TAG, "Total Mobile Data Usage (Bytes): " + (mobileTxBytes + mobileRxBytes));
//        Log.d(TAG, "Total Wi-Fi Data Usage (Bytes): " + (wifiTxBytes + wifiRxBytes));
//
//        // Obtendo o uso de dados por aplicativo
//        int uid = android.os.Process.myUid();
//        //int otherUid = android.os.Process.getUidForName("teste");
//        long appMobileTxBytes = TrafficStats.getUidTxBytes(uid);
//        long appMobileRxBytes = TrafficStats.getUidRxBytes(uid);
//        long appWifiTxBytes = TrafficStats.getUidTxBytes(uid) - appMobileTxBytes;
//        long appWifiRxBytes = TrafficStats.getUidRxBytes(uid) - appMobileRxBytes;
//
//        Log.d(TAG, "App Mobile Data Usage (Bytes): " + (appMobileTxBytes + appMobileRxBytes));
//        Log.d(TAG, "App Wi-Fi Data Usage (Bytes): " + (appWifiTxBytes + appWifiRxBytes));
//
//        String packageName = "com.trello"; // Substitua pelo pacote do app desejado
//        try {
//            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(packageName, 0);
//            int otherUid = appInfo.uid;
//
//            long otherAppMobileTxBytes = TrafficStats.getUidTxBytes(otherUid);
//            long otherAppMobileRxBytes = TrafficStats.getUidRxBytes(otherUid);
//            long otherAppWifiTxBytes = TrafficStats.getUidTxBytes(otherUid) - otherAppMobileTxBytes;
//            long otherAppWifiRxBytes = TrafficStats.getUidRxBytes(otherUid) - otherAppMobileRxBytes;
//
//            Log.d(TAG, "Other App Mobile Data Usage (Bytes): " + (otherAppMobileTxBytes + otherAppMobileRxBytes));
//            Log.d(TAG, "Other App Wi-Fi Data Usage (Bytes): " + (otherAppWifiTxBytes + otherAppWifiRxBytes));
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(TAG, "Application not found: " + e.getMessage());
//        }

//        // Listar todos os aplicativos instalados
//        PackageManager pm = getPackageManager();
//        List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
//
//        // Logar informações sobre os aplicativos instalados
//        for (ApplicationInfo app : apps) {
//            Log.d(TAG, "App: " + app.loadLabel(pm).toString() + ", Package: " + app.packageName);
//        }
//
//        // Tentar encontrar o UID do aplicativo Trello
//        String trelloPackage = "com.trello";
//        try {
//            ApplicationInfo trelloAppInfo = pm.getApplicationInfo(trelloPackage, 0);
//            int trelloUid = trelloAppInfo.uid;
//
//            long trelloMobileTxBytes = TrafficStats.getUidTxBytes(trelloUid);
//            long trelloMobileRxBytes = TrafficStats.getUidRxBytes(trelloUid);
//            long trelloWifiTxBytes = TrafficStats.getUidTxBytes(trelloUid) - trelloMobileTxBytes;
//            long trelloWifiRxBytes = TrafficStats.getUidRxBytes(trelloUid) - trelloMobileRxBytes;
//
//            Log.d(TAG, "Trello Mobile Data Usage (Bytes): " + (trelloMobileTxBytes + trelloMobileRxBytes));
//            Log.d(TAG, "Trello Wi-Fi Data Usage (Bytes): " + (trelloWifiTxBytes + trelloWifiRxBytes));
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(TAG, "Application not found: " + e.getMessage());
//        }
//
//    }

//    private static final String TAG = "NetworkStatsExample";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        NetworkStatsManager networkStatsManager = (NetworkStatsManager) getSystemService(Context.NETWORK_STATS_SERVICE);
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//
//        String subscriberId = telephonyManager.getSubscriberId();
//
//        getNetworkStats(networkStatsManager, subscriberId);
//    }
//
//    private void getNetworkStats(NetworkStatsManager networkStatsManager, String subscriberId) {
//        try {
//            // Obter estatísticas de dados móveis
//            NetworkStats mobileStats = networkStatsManager.querySummary(NetworkStats.Bucket.DEFAULT_NETWORK_ALL, subscriberId, 0, System.currentTimeMillis());
//            logNetworkStats(mobileStats, "Mobile");
//
//            // Obter estatísticas de dados Wi-Fi
//            NetworkStats wifiStats = networkStatsManager.querySummary(NetworkStats.Bucket.DEFAULT_NETWORK_ALL, "", 0, System.currentTimeMillis());
//            logNetworkStats(wifiStats, "Wi-Fi");
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void logNetworkStats(NetworkStats networkStats, String networkType) throws RemoteException {
//        PackageManager packageManager = getPackageManager();
//        Map<Integer, String> uidToPackageName = new HashMap<>();
//
//        NetworkStats.Bucket bucket = new NetworkStats.Bucket();
//        while (networkStats.hasNextBucket()) {
//            networkStats.getNextBucket(bucket);
//
//            int uid = bucket.getUid();
//            long rxBytes = bucket.getRxBytes();
//            long txBytes = bucket.getTxBytes();
//
//            String packageName = uidToPackageName.get(uid);
//            if (packageName == null) {
//                packageName = getPackageNameForUid(uid, packageManager);
//                uidToPackageName.put(uid, packageName);
//            }
//
//            Log.d(TAG, networkType + " - UID: " + uid + " - Package: " + packageName + " - RX Bytes: " + rxBytes + ", TX Bytes: " + txBytes);
//        }
//        networkStats.close();
//    }
//
//    private String getPackageNameForUid(int uid, PackageManager packageManager) {
//        String[] packages = packageManager.getPackagesForUid(uid);
//        if (packages != null && packages.length > 0) {
//            try {
//                ApplicationInfo appInfo = packageManager.getApplicationInfo(packages[0], 0);
//                return packageManager.getApplicationLabel(appInfo).toString();
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return "Unknown";
//    }


    }

//    private void scheduleJob() {
//        ComponentName componentName = new ComponentName(this, DataUsageJobService.class);
//        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                .setPeriodic(15 * 60 * 1000) // 15 minutes
//                .setPersisted(true)
//                .build();
//
//        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        int resultCode = jobScheduler.schedule(jobInfo);
//        if (resultCode == JobScheduler.RESULT_SUCCESS) {
//            Log.d(TAG, "Job scheduled successfully!");
//        } else {
//            Log.d(TAG, "Job scheduling failed!");
//        }
//    }
}
