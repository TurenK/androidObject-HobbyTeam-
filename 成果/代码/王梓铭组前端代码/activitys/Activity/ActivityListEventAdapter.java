package com.example.activitys.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.Exercise.ExerciseDetailActivity;
import com.example.activitys.R;
import com.example.model.UserInfo;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by TurenK on 2017/7/9.
 */

public class ActivityListEventAdapter extends BaseAdapter {
    Activity mActivity;
    ViewHolder viewHolder;
    private List<ActivityListEventInfo> eventInfoList;

    public ActivityListEventAdapter(List<ActivityListEventInfo> eventInfoList, Activity mActivity) {
        this.eventInfoList = eventInfoList;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return eventInfoList == null ? 0 : eventInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return eventInfoList.get(position).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_activity_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ActivityListEventInfo eventInfo = eventInfoList.get(i);
        //将图片下载至本地并缓存
        downloadImgFiles(eventInfo);
        getOwner(eventInfo);
        viewHolder.activityListActivitytimetext.setText(eventInfo.getActivityTime());
        viewHolder.activityListActivitymembernumtext.setText(eventInfo.getCurrentPeople() + "/" + eventInfo.getMaxPeople());
        viewHolder.activityListName.setText(eventInfo.getActivityName());

        //添加按钮的监听事件

        viewHolder.itemTeamListInnermainlayout.setOnClickListener(new ActivityListOnClickListener(viewHolder, eventInfo) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ExerciseDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("objectId", eventInfo.getActivityObjectId());
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });

        return view;
    }

    private void getOwner(ActivityListEventInfo eventInfo) {
        String ownerobjectid = eventInfo.getOwnerObjectId();
        BmobQuery<UserInfo> userInfoBmobQuery = new BmobQuery<>();
        userInfoBmobQuery.addWhereEqualTo("username", ownerobjectid);
        userInfoBmobQuery.findObjects(new FindListener<UserInfo>() {
            @Override
            public void done(List<UserInfo> list, BmobException e) {
                if (e == null) {
                    if (list != null && !list.isEmpty()) {
                        viewHolder.activityListOwnertext.setText(list.get(0).getNickname() + "(" + list.get(0).getUsername() + ")");
                    } else {
                        Toast.makeText(mActivity, "查询失败", LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mActivity, "查询失败", LENGTH_SHORT).show();
                }
            }
        });
    }

    private void downloadImgFiles(ActivityListEventInfo eventInfo) {
        if (eventInfo.getActivityImgURL() != null) {
            if (!eventInfo.getActivityImgURL().equals("null")) {
                BmobFile activityImg = new BmobFile("" + eventInfo.getId() + "activitylistimg" + ".jpg", "", eventInfo.getActivityImgURL());
                File saveFile = new File(Environment.getExternalStorageDirectory(), activityImg.getFilename());
                activityImg.download(saveFile, new DownloadFileListener() {
                    @Override
                    public void onStart() {
                        Log.i("bmob", "开始下载");
                    }

                    @Override
                    public void done(String savePath, BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "exerciseeventadapter userimg succeed");
                            Bitmap bitmap = BitmapFactory.decodeFile(savePath);
                            viewHolder.activityListImg.setImageBitmap(bitmap);
                        } else {
                            Log.i("bmob", "exerciseeventadapter userimg failed" + e.getErrorCode() + e.getMessage());
                        }
                    }

                    @Override
                    public void onProgress(Integer value, long newworkSpeed) {
                        Log.i("bmob", "下载进度：" + value + "," + newworkSpeed);
                    }
                });
            }
        }
    }

    static class ViewHolder {
        @InjectView(R.id.empty1)
        TextView empty1;
        @InjectView(R.id.activity_list_img)
        ImageView activityListImg;
        @InjectView(R.id.empty15)
        TextView empty15;
        @InjectView(R.id.activity_list_name)
        TextView activityListName;
        @InjectView(R.id.empty13)
        TextView empty13;
        @InjectView(R.id.activity_list_ownertext)
        TextView activityListOwnertext;
        @InjectView(R.id.activity_list_activitytimetext)
        TextView activityListActivitytimetext;
        @InjectView(R.id.empty14)
        LinearLayout empty14;
        @InjectView(R.id.activity_list_deleteAc)
        TextView activityListDeleteAc;
        @InjectView(R.id.activity_list_activitymembernumtext)
        TextView activityListActivitymembernumtext;
        @InjectView(R.id.empty12)
        LinearLayout empty12;
        @InjectView(R.id.empty16)
        LinearLayout empty16;
        @InjectView(R.id.empty18)
        LinearLayout empty18;
        @InjectView(R.id.item_team_list_innermainlayout)
        LinearLayout itemTeamListInnermainlayout;
        @InjectView(R.id.item_team_list_mainlayout)
        LinearLayout itemTeamListMainlayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
