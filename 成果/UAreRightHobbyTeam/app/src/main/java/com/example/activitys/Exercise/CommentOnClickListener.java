package com.example.activitys.Exercise;

import android.view.View;

/**
 * Created by TurenK on 2017/7/18.
 */

public class CommentOnClickListener implements View.OnClickListener {
    CommentListInfo eventInfo = null;
    CommentListAdapter.ViewHolder viewHolder = null;

    public CommentOnClickListener(CommentListAdapter.ViewHolder viewHolder,CommentListInfo eventInfo){
        this.eventInfo = eventInfo;
        this.viewHolder = viewHolder;
    }

    @Override
    public void onClick(View view){

    }
}
