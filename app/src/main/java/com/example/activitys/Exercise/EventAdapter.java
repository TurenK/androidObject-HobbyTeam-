package com.example.activitys.Exercise;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by TurenK on 2017/7/9.
 */

public class EventAdapter extends BaseAdapter {

    private List<ItemListEventInfo> eventInfoList;

    public EventAdapter(List<ItemListEventInfo> eventInfoList) {
        this.eventInfoList = eventInfoList;
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
        ViewHolder viewHolder;
        if (null == view) {
            view = View.inflate(viewGroup.getContext(), R.layout.exercise_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ItemListEventInfo eventInfo = eventInfoList.get(i);
        //将图片下载至本地并缓存
        if (eventInfo.getHead_img() != null) {
//            Picasso.with(view.getContext()).load(ServiceUrl.SERVER_URL getServerUrl(viewGroup.getContext())
//                    + eventInfo.getHead_img()).into(viewHolder.ivEventHead);
        }
        viewHolder.Username.setText(eventInfo.getName());
        viewHolder.SendingTime.setText(eventInfo.getStart_time());
        viewHolder.contentExerciseItem.setText(eventInfo.getIntro());
        viewHolder.addrExerciseItem.setText(eventInfo.getAddr());
        //添加按钮的监听事件

        viewHolder.likeImgExerciseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "点击的是点赞", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.likeTextExerciseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "点击的是点赞", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.commentImgExerciseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "点击的是评论", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.commentTextExerciseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "点击的是评论", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.addteamImgExerciseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "点击的是加入", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.addteamTextExerciseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "点击的是加入", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.linearww)
        LinearLayout linearww;
        @InjectView(R.id.UserImg)
        ImageView UserImg;
        @InjectView(R.id.Username)
        TextView Username;
        @InjectView(R.id.SendingTime)
        TextView SendingTime;
        @InjectView(R.id.linearww3)
        LinearLayout linearww3;
        @InjectView(R.id.linearww4)
        LinearLayout linearww4;
        @InjectView(R.id.linearww2)
        LinearLayout linearww2;
        @InjectView(R.id.theme_exercise)
        Spinner themeExercise;
        @InjectView(R.id.disincline_exercise)
        ImageView disinclineExercise;
        @InjectView(R.id.linearww1)
        LinearLayout linearww1;
        @InjectView(R.id.linearww5)
        LinearLayout linearww5;
        @InjectView(R.id.linear1)
        LinearLayout linear1;
        @InjectView(R.id.linearww7)
        LinearLayout linearww7;
        @InjectView(R.id.content_exercise_item)
        TextView contentExerciseItem;
        @InjectView(R.id.linearww9)
        LinearLayout linearww9;
        @InjectView(R.id.img_exercise_item)
        ImageView imgExerciseItem;
        @InjectView(R.id.linearww11)
        LinearLayout linearww11;
        @InjectView(R.id.locationImg_exercise_item)
        ImageView locationImgExerciseItem;
        @InjectView(R.id.addr_exercise_item)
        TextView addrExerciseItem;
        @InjectView(R.id.linearww12)
        LinearLayout linearww12;
        @InjectView(R.id.linearww10)
        LinearLayout linearww10;
        @InjectView(R.id.linearww8)
        LinearLayout linearww8;
        @InjectView(R.id.linearww15)
        LinearLayout linearww15;
        @InjectView(R.id.linearww6)
        LinearLayout linearww6;
        @InjectView(R.id.linearww17)
        LinearLayout linearww17;
        @InjectView(R.id.likeImg_exercise_item)
        ImageView likeImgExerciseItem;
        @InjectView(R.id.likeText_exercise_item)
        TextView likeTextExerciseItem;
        @InjectView(R.id.linearww19)
        LinearLayout linearww19;
        @InjectView(R.id.commentImg_exercise_item)
        ImageView commentImgExerciseItem;
        @InjectView(R.id.commentText_exercise_item)
        TextView commentTextExerciseItem;
        @InjectView(R.id.linearww20)
        LinearLayout linearww20;
        @InjectView(R.id.addteamImg_exercise_item)
        ImageView addteamImgExerciseItem;
        @InjectView(R.id.addteamText_exercise_item)
        TextView addteamTextExerciseItem;
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
