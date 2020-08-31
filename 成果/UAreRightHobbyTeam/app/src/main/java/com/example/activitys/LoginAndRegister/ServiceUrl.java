package com.example.activitys.LoginAndRegister;

import android.content.Context;
import com.example.activitys.R;

/**
 * Created by TurenK on 2017/7/6.
 */
public class ServiceUrl {
    public static String getServerUrl(Context context) {
        return context.getString(R.string.URL_SERVER);
    }

    public static String getTestREGUrl(Context context) {
        return context.getString(R.string.URL_SERVER) + context.getString(R.string.URL_REG);
    }

    public static String getTestLOGINUrl(Context context) {
        return context.getString(R.string.URL_SERVER) + context.getString(R.string.URL_LOGIN);
    }

    public static String getLocalREGUrl(Context context){
        return context.getString(R.string.localURLREG);
    }

    public static String getLocalLOGINUrl(Context context){
        return context.getString(R.string.localURLLOGIN);
    }

    public static String getAfterCloudID(Context context){
        return context.getString(R.string.AfterCloudID);
    }
}
