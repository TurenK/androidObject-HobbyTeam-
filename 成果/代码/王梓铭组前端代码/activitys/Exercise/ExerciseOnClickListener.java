package com.example.activitys.Exercise;

import android.view.View;

/**
 * Created by TurenK on 2017/7/15.
 */

public class ExerciseOnClickListener implements View.OnClickListener {

    ExerciseItemListEventInfo eventInfo = null;
    ExerciseEventAdapter.ViewHolder viewHolder = null;

    public ExerciseOnClickListener(ExerciseEventAdapter.ViewHolder viewHolder,ExerciseItemListEventInfo eventInfo){
        this.eventInfo = eventInfo;
        this.viewHolder = viewHolder;
    }

    @Override
    public void onClick(View view){

    }
}
