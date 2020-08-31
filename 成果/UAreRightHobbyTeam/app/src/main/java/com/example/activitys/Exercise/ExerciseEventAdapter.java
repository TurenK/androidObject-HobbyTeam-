package com.example.activitys.Exercise;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.R;
import com.example.model.Activitys;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by TurenK on 2017/7/9.
 */

public class ExerciseEventAdapter extends BaseAdapter {

    private List<ExerciseItemListEventInfo> eventInfoList;
    private Activity mActivity;
    //private ExerciseItemListEventInfo eventInfo;
    //private String activityImgPath;

    public ExerciseEventAdapter(List<ExerciseItemListEventInfo> eventInfoList, Activity mActivity) {
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
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (null == view) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_exercise, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ExerciseItemListEventInfo eventInfo = eventInfoList.get(i);

        viewHolder.exerciseItemName.setText(eventInfo.getActivityName());
        viewHolder.exerciseItemLikeText.setText(eventInfo.getLikenum());
        viewHolder.exerciseItemCommentText.setText(eventInfo.getCommentnum());
        List<String> tag = eventInfo.getTag();

        if (tag == null || tag.isEmpty()) {
            Log.e("tag", "exerciseitemtags");
        } else if (tag.size() == 1) {
            if (tag.get(0) != null && !tag.get(0).isEmpty()) {
                viewHolder.exerciseItemTag1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.greenyellow));
                viewHolder.exerciseItemTag1.setText(tag.get(0));
            }
        } else if (tag.size() == 2) {
            if (tag.get(0) != null && !tag.get(0).isEmpty()) {
                viewHolder.exerciseItemTag1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.greenyellow));
                viewHolder.exerciseItemTag1.setText(tag.get(0));
            }
            if (tag.get(1) != null && !tag.get(1).isEmpty()) {
                viewHolder.exerciseItemTag2.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.gold));
                viewHolder.exerciseItemTag2.setText(tag.get(1));
            }
        } else if (tag.size() == 3) {
            if (tag.get(0) != null && !tag.get(0).isEmpty()) {
                viewHolder.exerciseItemTag1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.greenyellow));
                viewHolder.exerciseItemTag1.setText(tag.get(0));
            }
            if (tag.get(1) != null && !tag.get(1).isEmpty()) {
                viewHolder.exerciseItemTag2.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.gold));
                viewHolder.exerciseItemTag2.setText(tag.get(1));
            }
            if (tag.get(2) != null && !tag.get(2).isEmpty()) {
                viewHolder.exerciseItemTag3.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.skyblue));
                viewHolder.exerciseItemTag3.setText(tag.get(2));
            }
        } else if (tag.size() == 4) {
            if (tag.get(0) != null && !tag.get(0).isEmpty()) {
                viewHolder.exerciseItemTag1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.greenyellow));
                viewHolder.exerciseItemTag1.setText(tag.get(0));
            }
            if (tag.get(1) != null && !tag.get(1).isEmpty()) {
                viewHolder.exerciseItemTag2.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.gold));
                viewHolder.exerciseItemTag2.setText(tag.get(1));
            }
            if (tag.get(2) != null && !tag.get(2).isEmpty()) {
                viewHolder.exerciseItemTag3.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.skyblue));
                viewHolder.exerciseItemTag3.setText(tag.get(2));
            }
            if (tag.get(3) != null && !tag.get(3).isEmpty()) {
                viewHolder.exerciseItemTag4.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.violet));
                viewHolder.exerciseItemTag4.setText(tag.get(3));
            }
        }

        //将图片下载至本地并缓存
        downloadImgFiles(eventInfo,viewHolder.imgExerciseItem);

        //添加按钮的监听事件

        viewHolder.exerciseItemLikeImg.setOnClickListener(new ExerciseOnClickListener(viewHolder, eventInfo) {
            @Override
            public void onClick(View view) {
                super.onClick(view);
                Toast.makeText(view.getContext(), "点击的是点赞", Toast.LENGTH_SHORT).show();
                int likenum = Integer.parseInt(eventInfo.getLikenum()) + 1;
                viewHolder.exerciseItemLikeText.setText(String.valueOf(likenum));
                Activitys temp = new Activitys();
                temp.setLikenum(String.valueOf(likenum));
                temp.update(eventInfo.getObjectId(), new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "更新成功");
                        } else {
                            Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
                viewHolder.exerciseItemLikeImg.setEnabled(false);
                viewHolder.exerciseItemLikeText.setEnabled(false);
            }
        });
        viewHolder.exerciseItemLikeText.setOnClickListener(new ExerciseOnClickListener(viewHolder, eventInfo) {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "点击的是点赞", Toast.LENGTH_SHORT).show();
                int likenum = Integer.parseInt(eventInfo.getLikenum()) + 1;
                viewHolder.exerciseItemLikeText.setText(String.valueOf(likenum));
                Activitys temp = new Activitys();
                temp.setLikenum(String.valueOf(likenum));
                temp.update(eventInfo.getObjectId(), new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "更新成功");
                        } else {
                            Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
                viewHolder.exerciseItemLikeText.setEnabled(false);
                viewHolder.exerciseItemLikeImg.setEnabled(false);
            }
        });

        viewHolder.exerciseItemName.setOnClickListener(new ExerciseOnClickListener(viewHolder, eventInfo) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ExerciseDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("objectId", eventInfo.getObjectId());
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });

        viewHolder.exerciseItemCommentText.setOnClickListener(new ExerciseOnClickListener(viewHolder, eventInfo) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ExerciseDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("objectId", eventInfo.getObjectId());
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });
        viewHolder.exerciseItemCommentImg.setOnClickListener(new ExerciseOnClickListener(viewHolder, eventInfo) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ExerciseDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("objectId", eventInfo.getObjectId());
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });
        viewHolder.exerciseItemAddteamImg.setOnClickListener(new ExerciseOnClickListener(viewHolder, eventInfo) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ExerciseDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("objectId", eventInfo.getObjectId());
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });
        viewHolder.exerciseItemAddteamText.setOnClickListener(new ExerciseOnClickListener(viewHolder, eventInfo) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ExerciseDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("objectId", eventInfo.getObjectId());
                intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }
        });

        return view;
    }

    private void downloadImgFiles(ExerciseItemListEventInfo eventInfo, final ImageView imageView) {
        if (eventInfo.getImgURL() != null) {
            if (!eventInfo.getImgURL().equals("null")) {
                BmobFile activityImg = new BmobFile("" + eventInfo.getActivitiesId() + "exerciselistimg" + ".jpg", "", eventInfo.getImgURL());
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
                            imageView.setImageBitmap(bitmap);
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
        @InjectView(R.id.linearww)
        LinearLayout linearww;
        @InjectView(R.id.empty22)
        LinearLayout empty22;
        @InjectView(R.id.exercise_item_name)
        TextView exerciseItemName;
        @InjectView(R.id.exercise_item_edit)
        ImageView exerciseItemEdit;
        @InjectView(R.id.linearww1)
        LinearLayout linearww1;
        @InjectView(R.id.linearww5)
        LinearLayout linearww5;
        @InjectView(R.id.linear1)
        LinearLayout linear1;
        @InjectView(R.id.img_exercise_item)
        ImageView imgExerciseItem;
        @InjectView(R.id.linearww11)
        LinearLayout linearww11;
        @InjectView(R.id.empty23)
        LinearLayout empty23;
        @InjectView(R.id.exercise_item_tag1)
        TextView exerciseItemTag1;
        @InjectView(R.id.empty21)
        LinearLayout empty21;
        @InjectView(R.id.exercise_item_tag2)
        TextView exerciseItemTag2;
        @InjectView(R.id.empty24)
        LinearLayout empty24;
        @InjectView(R.id.exercise_item_tag3)
        TextView exerciseItemTag3;
        @InjectView(R.id.empty25)
        LinearLayout empty25;
        @InjectView(R.id.exercise_item_tag4)
        TextView exerciseItemTag4;
        @InjectView(R.id.linearww12)
        LinearLayout linearww12;
        @InjectView(R.id.linearww10)
        LinearLayout linearww10;
        @InjectView(R.id.linearww8)
        LinearLayout linearww8;
        @InjectView(R.id.linearww6)
        LinearLayout linearww6;
        @InjectView(R.id.linearww17)
        LinearLayout linearww17;
        @InjectView(R.id.exercise_item_likeImg)
        ImageView exerciseItemLikeImg;
        @InjectView(R.id.exercise_item_likeText)
        TextView exerciseItemLikeText;
        @InjectView(R.id.linearww19)
        LinearLayout linearww19;
        @InjectView(R.id.exercise_item_commentImg)
        ImageView exerciseItemCommentImg;
        @InjectView(R.id.exercise_item_commentText)
        TextView exerciseItemCommentText;
        @InjectView(R.id.linearww20)
        LinearLayout linearww20;
        @InjectView(R.id.exercise_item_addteamImg)
        ImageView exerciseItemAddteamImg;
        @InjectView(R.id.exercise_item_addteamText)
        TextView exerciseItemAddteamText;
        @InjectView(R.id.linearww21)
        LinearLayout linearww21;
        @InjectView(R.id.linearww18)
        LinearLayout linearww18;
        @InjectView(R.id.linearww22)
        LinearLayout linearww22;
        @InjectView(R.id.linearww16)
        LinearLayout linearww16;
        @InjectView(R.id.linear5616516)
        LinearLayout linear5616516;
        @InjectView(R.id.separate_line_item)
        ImageView separateLineItem;
        @InjectView(R.id.linear45151)
        LinearLayout linear45151;
        @InjectView(R.id.relativelay5161)
        RelativeLayout relativelay5161;
        @InjectView(R.id.linearww656)
        LinearLayout linearww656;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
