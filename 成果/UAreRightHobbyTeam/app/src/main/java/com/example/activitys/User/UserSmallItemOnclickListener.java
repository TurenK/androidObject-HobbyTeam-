package com.example.activitys.User;

import android.view.View;

/**
 * Created by TurenK on 2017/7/18.
 */

public class UserSmallItemOnclickListener implements View.OnClickListener {
    UserSmallItemInfo eventInfo = null;
    UsersmallItemAdapter.ViewHolder viewHolder = null;

    public UserSmallItemOnclickListener(UsersmallItemAdapter.ViewHolder viewHolder,UserSmallItemInfo eventInfo){
        this.eventInfo = eventInfo;
        this.viewHolder = viewHolder;
    }
    @Override
    public void onClick(View view) {

    }
}
