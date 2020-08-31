package com.example.activitys.Activity;

import android.view.View;

/**
 * Created by TurenK on 2017/7/16.
 */

public class ActivityListOnClickListener implements View.OnClickListener {


    ActivityListEventInfo eventInfo = null;
    ActivityListEventAdapter.ViewHolder viewHolder = null;

    public ActivityListOnClickListener(ActivityListEventAdapter.ViewHolder viewHolder, ActivityListEventInfo eventInfo){
        this.eventInfo = eventInfo;
        this.viewHolder = viewHolder;
    }

    @Override
    public void onClick(View view) {

    }
}
