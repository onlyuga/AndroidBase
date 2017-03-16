package com.suri.core.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetworkUtils {

    public static Boolean isNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo networkinfo = manager.getActiveNetworkInfo();
            if (networkinfo != null && networkinfo.isConnected()  && networkinfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
