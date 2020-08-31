package com.example.activitys.Exercise;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.R;
import com.example.model.Comments;

import org.w3c.dom.Comment;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by TurenK on 2017/7/18.
 */

public class CommentListAdapter extends BaseAdapter {

    private List<CommentListInfo> eventInfoList;
    private Activity mActivity;

    public CommentListAdapter(List<CommentListInfo> eventInfoList, Activity mActivity) {
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
            view = View.inflate(viewGroup.getContext(), R.layout.item_comment, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        CommentListInfo eventInfo = eventInfoList.get(i);
        //将图片下载至本地并缓存
        downloadImgFiles(eventInfo, viewHolder.itemCommentHeadimg);

        viewHolder.itemCommentSendingTime.setText(eventInfo.getSendingTime());
        viewHolder.itemCommentLiketext.setText(eventInfo.getLikenum());
        viewHolder.itemCommentName.setText(eventInfo.getNicknameAndUsername());
        viewHolder.itemCommentContent.setText(eventInfo.getContent());

        viewHolder.itemCommentLikelayout.setOnClickListener(new CommentOnClickListener(viewHolder,eventInfo){
            @Override
            public void onClick(View view) {
                super.onClick(view);
                Toast.makeText(view.getContext(), "点击的是点赞", Toast.LENGTH_SHORT).show();
                final int likenum = Integer.parseInt(eventInfo.getLikenum()) + 1;

                Comments temp = new Comments();
                temp.setLikenum(String.valueOf(likenum));
                temp.update(eventInfo.getObjectId(), new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "更新成功");
                            viewHolder.itemCommentLiketext.setText(String.valueOf(likenum));
                        } else {
                            Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
                viewHolder.itemCommentLikelayout.setEnabled(false);
            }
        });

        return view;
    }

    private void downloadImgFiles(CommentListInfo eventInfo, final ImageView imageView) {
        if (eventInfo.getUserHeadImg() != null) {
            if (!eventInfo.getUserHeadImg().equals("null")) {
                BmobFile activityImg = new BmobFile("" + eventInfo.getUserObjectId() + "commentlistimg" + ".jpg", "", eventInfo.getUserHeadImg());
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
        @InjectView(R.id.item_comment_headimg)
        ImageView itemCommentHeadimg;
        @InjectView(R.id.item_comment_name)
        TextView itemCommentName;
        @InjectView(R.id.item_comment_namelayout)
        LinearLayout itemCommentNamelayout;
        @InjectView(R.id.empty33)
        LinearLayout empty33;
        @InjectView(R.id.item_comment_content)
        TextView itemCommentContent;
        @InjectView(R.id.item_comment_taglayout)
        LinearLayout itemCommentTaglayout;
        @InjectView(R.id.item_comment_infomainlayout)
        LinearLayout itemCommentInfomainlayout;
        @InjectView(R.id.empty36)
        ImageView empty36;
        @InjectView(R.id.item_comment_liketext)
        TextView itemCommentLiketext;
        @InjectView(R.id.item_comment_likelayout)
        LinearLayout itemCommentLikelayout;
        @InjectView(R.id.item_comment_sendingTime)
        TextView itemCommentSendingTime;
        @InjectView(R.id.empty111)
        LinearLayout empty111;
        @InjectView(R.id.item_comment_mainlayout)
        LinearLayout itemCommentMainlayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
