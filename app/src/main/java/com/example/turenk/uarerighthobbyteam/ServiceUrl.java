package com.example.turenk.uarerighthobbyteam;

import android.content.Context;
import com.example.turenk.uarerighthobbyteam.R;

/**
 * Created by TurenK on 2017/7/6.
 */
public class ServiceUrl {
    public static String getServerUrl(Context context) {
        return context.getString(R.string.URL_SERVER);
    }

    public static String getREGUrl(Context context) {
        return context.getString(R.string.URL_SERVER) + context.getString(R.string.URL_REG);
    }

    public static String getLOGINUrl(Context context) {
        return context.getString(R.string.URL_SERVER) + context.getString(R.string.URL_LOGIN);
    }

    public static String getLocalREGUrl(Context context){
        return context.getString(R.string.localURLREG);
    }
}
