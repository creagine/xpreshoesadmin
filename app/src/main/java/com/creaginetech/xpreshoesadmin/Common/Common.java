package com.creaginetech.xpreshoesadmin.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.creaginetech.xpreshoesadmin.Model.Shop;

public class Common {

//    public static User currentUser;
    public static Shop currentShop;

    public static String shopSelected = "";

    // partt 15 check internet connection
    public static boolean isConnectedToInternet (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager !=null)
        {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
            {
                for (int i=0;i<info.length;i++)
                {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;

    }

}
