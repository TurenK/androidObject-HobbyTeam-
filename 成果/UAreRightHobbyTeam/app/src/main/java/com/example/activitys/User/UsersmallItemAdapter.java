package com.example.activitys.User;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.R;
import com.example.model.Friends;
import com.example.model.UserInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by TurenK on 2017/7/16.
 */

public class UsersmallItemAdapter extends BaseAdapter {

    private List<UserSmallItemInfo> eventInfoList;
    private Activity mActivity;

    public UsersmallItemAdapter(List<UserSmallItemInfo> eventInfoList, Activity mActivity) {
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
        return eventInfoList.get(position).getUseritemsmallId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (null == view) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_user_small, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        UserSmallItemInfo eventInfo = eventInfoList.get(i);

        initButton(viewHolder.itemSmallUserAddFriend,eventInfo);

        viewHolder.itemUserSmallName.setText(eventInfo.getNickname() + "(" + eventInfo.getUsername() + ")");
        List<String> tag = eventInfo.getUsertags();

        if (tag == null || tag.isEmpty()) {
            Log.e("tag", "itemUserSmallTags");
        } else if (tag.size() == 1) {
            if (tag.get(0) != null && !tag.get(0).isEmpty()) {
                viewHolder.itemUserSmallTag1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.greenyellow));
                viewHolder.itemUserSmallTag1.setText(tag.get(0));
            }
        } else if (tag.size() == 2) {
            if (tag.get(0) != null && !tag.get(0).isEmpty()) {
                viewHolder.itemUserSmallTag1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.greenyellow));
                viewHolder.itemUserSmallTag1.setText(tag.get(0));
            }
            if (tag.get(1) != null && !tag.get(1).isEmpty()) {
                viewHolder.itemUserSmallTag2.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.gold));
                viewHolder.itemUserSmallTag2.setText(tag.get(1));
            }
        } else if (tag.size() == 3) {
            if (tag.get(0) != null && !tag.get(0).isEmpty()) {
                viewHolder.itemUserSmallTag1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.greenyellow));
                viewHolder.itemUserSmallTag1.setText(tag.get(0));
            }
            if (tag.get(1) != null && !tag.get(1).isEmpty()) {
                viewHolder.itemUserSmallTag2.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.gold));
                viewHolder.itemUserSmallTag2.setText(tag.get(1));
            }
            if (tag.get(2) != null && !tag.get(2).isEmpty()) {
                viewHolder.itemUserSmallTag3.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.skyblue));
                viewHolder.itemUserSmallTag3.setText(tag.get(2));
            }
        } else if (tag.size() == 4) {
            if (tag.get(0) != null && !tag.get(0).isEmpty()) {
                viewHolder.itemUserSmallTag1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.greenyellow));
                viewHolder.itemUserSmallTag1.setText(tag.get(0));
            }
            if (tag.get(1) != null && !tag.get(1).isEmpty()) {
                viewHolder.itemUserSmallTag2.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.gold));
                viewHolder.itemUserSmallTag2.setText(tag.get(1));
            }
            if (tag.get(2) != null && !tag.get(2).isEmpty()) {
                viewHolder.itemUserSmallTag3.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.skyblue));
                viewHolder.itemUserSmallTag3.setText(tag.get(2));
            }
            if (tag.get(3) != null && !tag.get(3).isEmpty()) {
                viewHolder.itemUserSmallTag4.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.violet));
                viewHolder.itemUserSmallTag4.setText(tag.get(3));
            }
        } else if (tag.size() == 5) {
            if (tag.get(0) != null && !tag.get(0).isEmpty()) {
                viewHolder.itemUserSmallTag1.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.greenyellow));
                viewHolder.itemUserSmallTag1.setText(tag.get(0));
            }
            if (tag.get(1) != null && !tag.get(1).isEmpty()) {
                viewHolder.itemUserSmallTag2.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.gold));
                viewHolder.itemUserSmallTag2.setText(tag.get(1));
            }
            if (tag.get(2) != null && !tag.get(2).isEmpty()) {
                viewHolder.itemUserSmallTag3.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.skyblue));
                viewHolder.itemUserSmallTag3.setText(tag.get(2));
            }
            if (tag.get(3) != null && !tag.get(3).isEmpty()) {
                viewHolder.itemUserSmallTag4.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.violet));
                viewHolder.itemUserSmallTag4.setText(tag.get(3));
            }
            if (tag.get(4) != null && !tag.get(4).isEmpty()) {
                viewHolder.itemUserSmallTag5.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.lightpink));
                viewHolder.itemUserSmallTag5.setText(tag.get(4));
            }
        }

        //将图片下载至本地并缓存
        if (eventInfo.getUserheadimgUrl() != null && !eventInfo.getUserheadimgUrl().equals("null")) {
            final ImageView imageView = viewHolder.itemUserSmallHeadimg;
            BmobFile activityImg = new BmobFile("" + eventInfo.getUseritemsmallId() + "usersmallitemimg" + ".jpg", "", eventInfo.getUserheadimgUrl());
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

        viewHolder.itemSmallUserAddFriend.setOnClickListener(new UserSmallItemOnclickListener(viewHolder,eventInfo) {
            @Override
            public void onClick(View view) {
                final String friendname = eventInfo.getUsername();
                final String currentusername = BmobUser.getCurrentUser(UserInfo.class).getUsername();

                BmobQuery<Friends> friendsBmobQuery = new BmobQuery<>();

                List<BmobQuery<Friends>> queries = new ArrayList<>();
                BmobQuery<Friends> temp1 = new BmobQuery<>();
                temp1.addWhereEqualTo("username",currentusername);
                BmobQuery<Friends> temp2 = new BmobQuery<>();
                temp2.addWhereEqualTo("friendname",friendname);
                queries.add(temp1);
                queries.add(temp2);

                friendsBmobQuery.and(queries);
                friendsBmobQuery.findObjects(new FindListener<Friends>() {
                    @Override
                    public void done(List<Friends> list, BmobException e) {
                        if(e==null){
                            if(list!=null&&!list.isEmpty()){
                              list.get(0).delete(list.get(0).getObjectId(), new UpdateListener() {
                                  @Override
                                  public void done(BmobException e) {
                                      if(e==null){
                                          Toast.makeText(mActivity,"删除好友成功", LENGTH_SHORT).show();
                                          viewHolder.itemSmallUserAddFriend.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.head));
                                          viewHolder.itemSmallUserAddFriend.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
                                          viewHolder.itemSmallUserAddFriend.setText("添加好友");
                                      }else{
                                          Toast.makeText(mActivity,"删除好友失败", LENGTH_SHORT).show();
                                      }
                                  }
                              });
                            }else{
                                Friends friends = new Friends();
                                friends.setUsername(currentusername);
                                friends.setFriendname(friendname);
                                friends.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if(e==null){
                                            Toast.makeText(mActivity,"添加好友成功", LENGTH_SHORT).show();
                                            viewHolder.itemSmallUserAddFriend.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.colorGray));
                                            viewHolder.itemSmallUserAddFriend.setTextColor(ContextCompat.getColor(mActivity, R.color.head));
                                            viewHolder.itemSmallUserAddFriend.setText("删除好友");
                                        }else {
                                            Toast.makeText(mActivity,"添加好友失败", LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }else {
                            Toast.makeText(mActivity,"数据错误", LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return view;
    }

    private void initButton(final Button addFriendBtn, UserSmallItemInfo eventInfo){
        String friendname = eventInfo.getUsername();
        String currentusername = BmobUser.getCurrentUser(UserInfo.class).getUsername();

        BmobQuery<Friends> friendsBmobQuery = new BmobQuery<>();

        List<BmobQuery<Friends>> queries = new ArrayList<>();
        BmobQuery<Friends> temp1 = new BmobQuery<>();
        temp1.addWhereEqualTo("username",currentusername);
        BmobQuery<Friends> temp2 = new BmobQuery<>();
        temp2.addWhereEqualTo("friendname",friendname);
        queries.add(temp1);
        queries.add(temp2);

        friendsBmobQuery.and(queries);
        friendsBmobQuery.findObjects(new FindListener<Friends>() {
            @Override
            public void done(List<Friends> list, BmobException e) {
                if(e==null){
                    if(list!=null&&!list.isEmpty()){
                        addFriendBtn.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.colorGray));
                        addFriendBtn.setText("已添加");
                        addFriendBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.head));
                    }else{
                        addFriendBtn.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.head));
                        addFriendBtn.setText("添加好友");
                        addFriendBtn.setTextColor(ContextCompat.getColor(mActivity, R.color.black));
                    }
                }else {
                    Toast.makeText(mActivity,"初始化加好友按钮失败", LENGTH_SHORT).show();
                }
            }
        });
    }

    static class ViewHolder {
        @InjectView(R.id.item_user_small_headimg)
        ImageView itemUserSmallHeadimg;
        @InjectView(R.id.item_user_small_name)
        TextView itemUserSmallName;
        @InjectView(R.id.item_user_small_namelayout)
        LinearLayout itemUserSmallNamelayout;
        @InjectView(R.id.item_user_small_tag1)
        TextView itemUserSmallTag1;
        @InjectView(R.id.item_user_small_tag2)
        TextView itemUserSmallTag2;
        @InjectView(R.id.item_user_small_tag3)
        TextView itemUserSmallTag3;
        @InjectView(R.id.item_user_small_tag4)
        TextView itemUserSmallTag4;
        @InjectView(R.id.item_user_small_tag5)
        TextView itemUserSmallTag5;
        @InjectView(R.id.item_user_small_taglayout)
        LinearLayout itemUserSmallTaglayout;
        @InjectView(R.id.item_user_small_infomainlayout)
        LinearLayout itemUserSmallInfomainlayout;
        @InjectView(R.id.item_user_small_mainlayout)
        LinearLayout itemUserSmallMainlayout;
        @InjectView(R.id.item_small_user_addFriend)
        Button itemSmallUserAddFriend;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
