package com.example.activitys.Exercise;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.R;
import com.example.model.Activitys;
import com.example.model.Comments;
import com.example.model.UserInfo;

import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ExerciseDetailActivity extends Activity {
    @InjectView(R.id.exercise_detail_backBtn)
    Button exerciseDetailBackBtn;
    @InjectView(R.id.titleBar)
    LinearLayout titleBar;
    @InjectView(R.id.exercise_detail_memberBtn)
    Button exerciseDetailMemberBtn;
    @InjectView(R.id.exercise_detail_touxiang)
    ImageView exerciseDetailTouxiang;
    @InjectView(R.id.exercise_detail_mingzi)
    TextView exerciseDetailMingzi;
    @InjectView(R.id.exercise_detail_date)
    TextView exerciseDetailDate;
    @InjectView(R.id.exercise_detail_yihang)
    LinearLayout exerciseDetailYihang;
    @InjectView(R.id.exercise_detail_intro)
    TextView exerciseDetailIntro;
    @InjectView(R.id.exercise_detail_introzong)
    LinearLayout exerciseDetailIntrozong;
    @InjectView(R.id.textView23)
    TextView textView23;
    @InjectView(R.id.exercise_detail_pic)
    ImageView exerciseDetailPic;
    @InjectView(R.id.exercise_detail_address)
    TextView exerciseDetailAddress;
    @InjectView(R.id.exercise_detail_time)
    TextView exerciseDetailTime;
    @InjectView(R.id.exercise_detail_zannum)
    TextView exerciseDetailZannum;
    @InjectView(R.id.exercise_detail_likelayout)
    LinearLayout exerciseDetailLikelayout;
    @InjectView(R.id.exercise_detail_comnum)
    TextView exerciseDetailComnum;
    @InjectView(R.id.exercise_detail_commentlayout)
    LinearLayout exerciseDetailCommentlayout;
    @InjectView(R.id.zhuannum)
    TextView zhuannum;
    @InjectView(R.id.exercise_detail_sendtolayout)
    LinearLayout exerciseDetailSendtolayout;
    @InjectView(R.id.exercise_detail_teamname)
    TextView exerciseDetailTeamname;
    @InjectView(R.id.exercise_detail_listview)
    ListView exerciseDetailListview;
    @InjectView(R.id.exercise_detail_addteam)
    Button exerciseDetailAddteam;
    @InjectView(R.id.exercise_detail_collect)
    Button exerciseDetailCollect;
    @InjectView(R.id.exercise_detail_report)
    Button exerciseDetailReport;

    String exerciseObjectId;
    String ownerId;
    String ownerHeadImgURL;
    String activityImgURL;
    boolean whetherAddTeam;
    boolean whetherCollectTeam;
    boolean whetherReportTeam;
    boolean whetherOwner;
    List<String> teammembers;
    @InjectView(R.id.textView24)
    TextView textView24;
    @InjectView(R.id.exercise_detail_addcomment)
    ImageView exerciseDetailAddcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        ButterKnife.inject(this);

        initExerciseDetailActivity();

        initListener();
    }

    private void initListener() {
        joininActivity();

        addComment();

        collectExercise();

        reportExercise();

        exerciseDetailMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (teammembers == null || teammembers.isEmpty()) {
                    Toast.makeText(ExerciseDetailActivity.this, "小队成员为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ExerciseDetailActivity.this, ExerciseMemberActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("memberUsernames", (ArrayList<String>) teammembers);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void reportExercise() {
        exerciseDetailReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseDetailReport.setEnabled(false);
                exerciseDetailCollect.setEnabled(false);
                exerciseDetailAddcomment.setEnabled(false);
                exerciseDetailAddteam.setEnabled(false);
                exerciseDetailListview.setEnabled(false);
                exerciseDetailBackBtn.setEnabled(false);
                if (whetherReportTeam) {
                    UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
                    List<String> reportIds = userInfo.getReportTeamIds();
                    if (reportIds != null && !reportIds.isEmpty()) {
                        reportIds.remove(exerciseObjectId);
                        userInfo.setReportTeamIds(reportIds);
                        userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(ExerciseDetailActivity.this, "取消举报成功", Toast.LENGTH_SHORT).show();
                                    whetherReportTeam = false;
                                    exerciseDetailReport.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
                                    exerciseDetailReport.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.white));
                                    exerciseDetailReport.setText("举 报");
                                    exerciseDetailReport.setEnabled(true);
                                    exerciseDetailCollect.setEnabled(true);
                                    exerciseDetailAddcomment.setEnabled(true);
                                    exerciseDetailAddteam.setEnabled(true);
                                    exerciseDetailListview.setEnabled(true);
                                    exerciseDetailBackBtn.setEnabled(true);
                                } else {
                                    Toast.makeText(ExerciseDetailActivity.this, "取消举报失败", Toast.LENGTH_SHORT).show();
                                    exerciseDetailReport.setEnabled(true);
                                    exerciseDetailCollect.setEnabled(true);
                                    exerciseDetailAddcomment.setEnabled(true);
                                    exerciseDetailAddteam.setEnabled(true);
                                    exerciseDetailListview.setEnabled(true);
                                    exerciseDetailBackBtn.setEnabled(true);
                                }
                            }
                        });
                    } else {
                        Toast.makeText(ExerciseDetailActivity.this, "数据有误", Toast.LENGTH_SHORT).show();
                        exerciseDetailReport.setEnabled(true);
                        exerciseDetailCollect.setEnabled(true);
                        exerciseDetailAddcomment.setEnabled(true);
                        exerciseDetailAddteam.setEnabled(true);
                        exerciseDetailListview.setEnabled(true);
                        exerciseDetailBackBtn.setEnabled(true);
                    }
                } else {
                    UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
                    List<String> reportIds = userInfo.getReportTeamIds();
                    if (reportIds == null || reportIds.isEmpty()) {
                        reportIds = new ArrayList<String>();
                    }
                    reportIds.add(exerciseObjectId);
                    userInfo.setReportTeamIds(reportIds);
                    userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(ExerciseDetailActivity.this, "举报成功", Toast.LENGTH_SHORT).show();
                                whetherReportTeam = true;
                                exerciseDetailReport.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.colorGray));
                                exerciseDetailReport.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
                                exerciseDetailReport.setText("已举报");
                                exerciseDetailReport.setEnabled(true);
                                exerciseDetailCollect.setEnabled(true);
                                exerciseDetailAddcomment.setEnabled(true);
                                exerciseDetailAddteam.setEnabled(true);
                                exerciseDetailListview.setEnabled(true);
                                exerciseDetailBackBtn.setEnabled(true);
                            } else {
                                Toast.makeText(ExerciseDetailActivity.this, "举报失败", Toast.LENGTH_SHORT).show();
                                exerciseDetailReport.setEnabled(true);
                                exerciseDetailCollect.setEnabled(true);
                                exerciseDetailAddcomment.setEnabled(true);
                                exerciseDetailAddteam.setEnabled(true);
                                exerciseDetailListview.setEnabled(true);
                                exerciseDetailBackBtn.setEnabled(true);
                            }
                        }
                    });

                }
            }
        });
    }

    private void collectExercise() {
        exerciseDetailCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseDetailReport.setEnabled(false);
                exerciseDetailCollect.setEnabled(false);
                exerciseDetailAddcomment.setEnabled(false);
                exerciseDetailAddteam.setEnabled(false);
                exerciseDetailListview.setEnabled(false);
                exerciseDetailBackBtn.setEnabled(false);
                if (whetherCollectTeam) {
                    UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
                    List<String> collectIds = userInfo.getCollectTeamIds();
                    if (collectIds != null && !collectIds.isEmpty()) {
                        collectIds.remove(exerciseObjectId);
                        userInfo.setCollectTeamIds(collectIds);
                        userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(ExerciseDetailActivity.this, "取消关注成功", Toast.LENGTH_SHORT).show();
                                    whetherCollectTeam = false;
                                    exerciseDetailCollect.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
                                    exerciseDetailCollect.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.white));
                                    exerciseDetailCollect.setText("关 注");
                                    exerciseDetailReport.setEnabled(true);
                                    exerciseDetailCollect.setEnabled(true);
                                    exerciseDetailAddcomment.setEnabled(true);
                                    exerciseDetailAddteam.setEnabled(true);
                                    exerciseDetailListview.setEnabled(true);
                                    exerciseDetailBackBtn.setEnabled(true);
                                } else {
                                    Toast.makeText(ExerciseDetailActivity.this, "取消关注失败", Toast.LENGTH_SHORT).show();
                                    exerciseDetailReport.setEnabled(true);
                                    exerciseDetailCollect.setEnabled(true);
                                    exerciseDetailAddcomment.setEnabled(true);
                                    exerciseDetailAddteam.setEnabled(true);
                                    exerciseDetailListview.setEnabled(true);
                                    exerciseDetailBackBtn.setEnabled(true);
                                }
                            }
                        });
                    } else {
                        Toast.makeText(ExerciseDetailActivity.this, "数据有误", Toast.LENGTH_SHORT).show();
                        exerciseDetailReport.setEnabled(true);
                        exerciseDetailCollect.setEnabled(true);
                        exerciseDetailAddcomment.setEnabled(true);
                        exerciseDetailAddteam.setEnabled(true);
                        exerciseDetailListview.setEnabled(true);
                        exerciseDetailBackBtn.setEnabled(true);
                    }
                } else {
                    UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
                    List<String> collectIds = userInfo.getCollectTeamIds();
                    if (collectIds == null || collectIds.isEmpty()) {
                        collectIds = new ArrayList<String>();
                    }
                    collectIds.add(exerciseObjectId);
                    userInfo.setCollectTeamIds(collectIds);
                    userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(ExerciseDetailActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                                whetherCollectTeam = true;
                                exerciseDetailCollect.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.colorGray));
                                exerciseDetailCollect.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
                                exerciseDetailCollect.setText("已关注");
                                exerciseDetailReport.setEnabled(true);
                                exerciseDetailCollect.setEnabled(true);
                                exerciseDetailAddcomment.setEnabled(true);
                                exerciseDetailAddteam.setEnabled(true);
                                exerciseDetailListview.setEnabled(true);
                                exerciseDetailBackBtn.setEnabled(true);
                            } else {
                                Toast.makeText(ExerciseDetailActivity.this, "关注失败", Toast.LENGTH_SHORT).show();
                                exerciseDetailReport.setEnabled(true);
                                exerciseDetailCollect.setEnabled(true);
                                exerciseDetailAddcomment.setEnabled(true);
                                exerciseDetailAddteam.setEnabled(true);
                                exerciseDetailListview.setEnabled(true);
                                exerciseDetailBackBtn.setEnabled(true);
                            }
                        }
                    });

                }
            }
        });
    }

    private void addComment() {
        exerciseDetailAddcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseDetailReport.setEnabled(false);
                exerciseDetailCollect.setEnabled(false);
                exerciseDetailAddcomment.setEnabled(false);
                exerciseDetailAddteam.setEnabled(false);
                exerciseDetailListview.setEnabled(false);
                exerciseDetailBackBtn.setEnabled(false);
                final EditText inputServer = new EditText(ExerciseDetailActivity.this);
                inputServer.setFocusable(true);

                AlertDialog.Builder builder = new AlertDialog.Builder(ExerciseDetailActivity.this);
                builder.setTitle("请输入评论").setView(inputServer).setNegativeButton(
                        "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                exerciseDetailReport.setEnabled(true);
                                exerciseDetailCollect.setEnabled(true);
                                exerciseDetailAddcomment.setEnabled(true);
                                exerciseDetailAddteam.setEnabled(true);
                                exerciseDetailListview.setEnabled(true);
                                exerciseDetailBackBtn.setEnabled(true);
                            }
                        });
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String inputName = inputServer.getText().toString();
                                if (inputName != null && !inputName.isEmpty()) {
                                    Toast.makeText(ExerciseDetailActivity.this, "正在上传", Toast.LENGTH_SHORT).show();
                                    Comments comments = new Comments();
                                    UserInfo currentuser = UserInfo.getCurrentUser(UserInfo.class);
                                    comments.setUserHeadImg(currentuser.getHeadimg());
                                    comments.setUserObjectId(currentuser.getObjectId());
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String datenow = sdf.format(new Date());
                                    comments.setSendingTime(datenow);
                                    comments.setActivityObjectId(exerciseObjectId);
                                    comments.setContent(inputName);
                                    comments.setLikenum("0");
                                    comments.setNicknameAndUsername(currentuser.getNickname() + "(" + currentuser.getUsername() + ")");
                                    comments.save(new SaveListener<String>() {
                                        @Override
                                        public void done(String s, BmobException e) {
                                            if (e == null) {
                                                Toast.makeText(ExerciseDetailActivity.this, "评论上传成功", Toast.LENGTH_SHORT).show();
                                                initCommentList();
                                            } else {
                                                Toast.makeText(ExerciseDetailActivity.this, "评论上传失败", Toast.LENGTH_SHORT).show();
                                                exerciseDetailReport.setEnabled(true);
                                                exerciseDetailCollect.setEnabled(true);
                                                exerciseDetailAddcomment.setEnabled(true);
                                                exerciseDetailAddteam.setEnabled(true);
                                                exerciseDetailListview.setEnabled(true);
                                                exerciseDetailBackBtn.setEnabled(true);
                                            }
                                        }
                                    });
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(ExerciseDetailActivity.this, "评论不能为空", Toast.LENGTH_SHORT).show();
                                    exerciseDetailReport.setEnabled(true);
                                    exerciseDetailCollect.setEnabled(true);
                                    exerciseDetailAddcomment.setEnabled(true);
                                    exerciseDetailAddteam.setEnabled(true);
                                    exerciseDetailListview.setEnabled(true);
                                    exerciseDetailBackBtn.setEnabled(true);
                                }
                            }
                        });
                builder.show();
            }
        });
    }

    private void joininActivity() {
        exerciseDetailAddteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseDetailReport.setEnabled(false);
                exerciseDetailCollect.setEnabled(false);
                exerciseDetailAddcomment.setEnabled(false);
                exerciseDetailAddteam.setEnabled(false);
                exerciseDetailListview.setEnabled(false);
                exerciseDetailBackBtn.setEnabled(false);
                if (whetherOwner) {
                    new AlertDialog.Builder(ExerciseDetailActivity.this).setTitle("删除活动")//设置对话框标题
                            .setMessage("确定删除此活动吗？")//设置显示的内容
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                    Activitys activitys = new Activitys();
                                    activitys.delete(exerciseObjectId, new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if(e==null){
                                                Toast.makeText(ExerciseDetailActivity.this, "活动数据删除成功", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(ExerciseDetailActivity.this, "活动数据删除失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                    BmobQuery<Comments> commentsBmobQuery = new BmobQuery<Comments>();
                                    commentsBmobQuery.addWhereEqualTo("ActivityObjectId",exerciseObjectId);
                                    commentsBmobQuery.findObjects(new FindListener<Comments>() {
                                        @Override
                                        public void done(List<Comments> list, BmobException e) {
                                            if(e==null){
                                                if(list!=null&&!list.isEmpty()){
                                                    List<BmobObject> bmobObjects = new ArrayList<BmobObject>();
                                                   bmobObjects.addAll(list);
                                                    new BmobBatch().deleteBatch(bmobObjects).doBatch(new QueryListListener<BatchResult>() {
                                                        @Override
                                                        public void done(List<BatchResult> list, BmobException e) {
                                                            if(e==null){
                                                                for(int i=0;i<list.size();i++){
                                                                    BatchResult result = list.get(i);
                                                                    BmobException ex =result.getError();
                                                                    if(ex==null){
                                                                        Toast.makeText(ExerciseDetailActivity.this, "第"+i+"个评论数据删除成功", Toast.LENGTH_SHORT).show();
                                                                    }else{
                                                                        Toast.makeText(ExerciseDetailActivity.this, "第"+i+"个评论数据删除失败", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            }else{
                                                                Toast.makeText(ExerciseDetailActivity.this, "评论数据删除失败", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                                }else{
                                                    Toast.makeText(ExerciseDetailActivity.this, "评论数据为空", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(ExerciseDetailActivity.this, "评论数据查询失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                    List<String> exerciselistid = new ArrayList<String>();
                                    exerciselistid.add(exerciseObjectId);
                                    BmobQuery<UserInfo> temp1 = new BmobQuery<UserInfo>();
                                    temp1.addWhereContainsAll("collectTeamIds",exerciselistid);
                                    temp1.findObjects(new FindListener<UserInfo>() {
                                        @Override
                                        public void done(List<UserInfo> list, BmobException e) {
                                            if(e==null){
                                                if(list!=null&&!list.isEmpty()){
                                                    for(UserInfo userInfo:list){
                                                        List<String> usercollectlist  = userInfo.getCollectTeamIds();
                                                        usercollectlist.remove(exerciseObjectId);
                                                        userInfo.setCollectTeamIds(usercollectlist);
                                                        userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                                                            @Override
                                                            public void done(BmobException e) {
                                                                if(e==null){
                                                                    Toast.makeText(ExerciseDetailActivity.this, "用户删除collect列成功", Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    Toast.makeText(ExerciseDetailActivity.this, "用户删除collect列失败", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                }else{
                                                    Toast.makeText(ExerciseDetailActivity.this, "用户收藏不含此活动", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(ExerciseDetailActivity.this, "用户收藏列查询失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                    BmobQuery<UserInfo> temp2 = new BmobQuery<UserInfo>();
                                    temp2.addWhereContainsAll("createdTeamIds",exerciselistid);
                                    temp2.findObjects(new FindListener<UserInfo>() {
                                        @Override
                                        public void done(List<UserInfo> list, BmobException e) {
                                            if(e==null){
                                                if(list!=null&&!list.isEmpty()){
                                                    for(UserInfo userInfo:list){
                                                        List<String> usercollectlist  = userInfo.getCreatedTeamIds();
                                                        usercollectlist.remove(exerciseObjectId);
                                                        userInfo.setCreatedTeamIds(usercollectlist);
                                                        userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                                                            @Override
                                                            public void done(BmobException e) {
                                                                if(e==null){
                                                                    Toast.makeText(ExerciseDetailActivity.this, "用户删除create列成功", Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    Toast.makeText(ExerciseDetailActivity.this, "用户删除create列失败", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                }else{
                                                    Toast.makeText(ExerciseDetailActivity.this, "用户创建不含此活动", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(ExerciseDetailActivity.this, "用户创建列查询失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                    BmobQuery<UserInfo> temp3 = new BmobQuery<UserInfo>();
                                    temp3.addWhereContainsAll("joinedTeamIds",exerciselistid);
                                    temp3.findObjects(new FindListener<UserInfo>() {
                                        @Override
                                        public void done(List<UserInfo> list, BmobException e) {
                                            if(e==null){
                                                if(list!=null&&!list.isEmpty()){
                                                    for(UserInfo userInfo:list){
                                                        List<String> usercollectlist  = userInfo.getJoinedTeamIds();
                                                        usercollectlist.remove(exerciseObjectId);
                                                        userInfo.setJoinedTeamIds(usercollectlist);
                                                        userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                                                            @Override
                                                            public void done(BmobException e) {
                                                                if(e==null){
                                                                    Toast.makeText(ExerciseDetailActivity.this, "用户删除joined列成功", Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    Toast.makeText(ExerciseDetailActivity.this, "用户删除joined列失败", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                }else{
                                                    Toast.makeText(ExerciseDetailActivity.this, "用户加入不含此活动", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(ExerciseDetailActivity.this, "用户加入列查询失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });


                                    BmobQuery<UserInfo> temp4 = new BmobQuery<UserInfo>();
                                    temp4.addWhereContainsAll("reportTeamIds",exerciselistid);
                                    temp4.findObjects(new FindListener<UserInfo>() {
                                        @Override
                                        public void done(List<UserInfo> list, BmobException e) {
                                            if(e==null){
                                                if(list!=null&&!list.isEmpty()){
                                                    for(UserInfo userInfo:list){
                                                        List<String> usercollectlist  = userInfo.getReportTeamIds();
                                                        usercollectlist.remove(exerciseObjectId);
                                                        userInfo.setReportTeamIds(usercollectlist);
                                                        userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                                                            @Override
                                                            public void done(BmobException e) {
                                                                if(e==null){
                                                                    Toast.makeText(ExerciseDetailActivity.this, "用户删除report列成功", Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    Toast.makeText(ExerciseDetailActivity.this, "用户删除report列失败", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                }else{
                                                    Toast.makeText(ExerciseDetailActivity.this, "用户举报不含此活动", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(ExerciseDetailActivity.this, "用户举报列查询失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                    ExerciseDetailActivity.this.finish();
                                }
                            }).setNegativeButton("取消",new DialogInterface.OnClickListener() {//添加返回按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//响应事件
                            dialog.cancel();
                        }
                    }).show();//在按键响应事件中显示此对话框

                } else {
                    if (whetherAddTeam) {
                        BmobQuery<Activitys> query = new BmobQuery<Activitys>();
                        query.getObject(exerciseObjectId, new QueryListener<Activitys>() {
                            @Override
                            public void done(Activitys object, BmobException e) {
                                if (e == null) {
                                    teammembers = object.getMemberIds();
                                    String usertempname = BmobUser.getCurrentUser(UserInfo.class).getUsername();
                                    if (teammembers == null || teammembers.isEmpty()) {
                                        Toast.makeText(ExerciseDetailActivity.this, "数据有误", Toast.LENGTH_SHORT).show();
                                        exerciseDetailReport.setEnabled(true);
                                        exerciseDetailCollect.setEnabled(true);
                                        exerciseDetailAddcomment.setEnabled(true);
                                        exerciseDetailAddteam.setEnabled(true);
                                        exerciseDetailListview.setEnabled(true);
                                        exerciseDetailBackBtn.setEnabled(true);
                                    } else if (teammembers.contains(usertempname)) {
                                        teammembers.remove(usertempname);
                                        updateTeamByTeamId();
                                    } else {
                                        Toast.makeText(ExerciseDetailActivity.this, "数据有误", Toast.LENGTH_SHORT).show();
                                        exerciseDetailReport.setEnabled(true);
                                        exerciseDetailCollect.setEnabled(true);
                                        exerciseDetailAddcomment.setEnabled(true);
                                        exerciseDetailAddteam.setEnabled(true);
                                        exerciseDetailListview.setEnabled(true);
                                        exerciseDetailBackBtn.setEnabled(true);
                                    }
                                } else {
                                    Toast.makeText(ExerciseDetailActivity.this, "查询失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    exerciseDetailReport.setEnabled(true);
                                    exerciseDetailCollect.setEnabled(true);
                                    exerciseDetailAddcomment.setEnabled(true);
                                    exerciseDetailAddteam.setEnabled(true);
                                    exerciseDetailListview.setEnabled(true);
                                    exerciseDetailBackBtn.setEnabled(true);
                                }
                            }

                        });
                    } else {
                        BmobQuery<Activitys> query = new BmobQuery<Activitys>();
                        query.getObject(exerciseObjectId, new QueryListener<Activitys>() {
                            @Override
                            public void done(Activitys object, BmobException e) {
                                if (e == null) {
                                    teammembers = object.getMemberIds();
                                    String usertempname = BmobUser.getCurrentUser(UserInfo.class).getUsername();
                                    if (teammembers == null || teammembers.isEmpty()) {
                                        teammembers = new ArrayList<String>();
                                        teammembers.add(usertempname);
                                        updateTeamByTeamId();
                                    } else if (!teammembers.contains(usertempname)) {
                                        teammembers.add(usertempname);
                                        updateTeamByTeamId();
                                    } else {
                                        Toast.makeText(ExerciseDetailActivity.this, "数据有误", Toast.LENGTH_SHORT).show();
                                        exerciseDetailReport.setEnabled(true);
                                        exerciseDetailCollect.setEnabled(true);
                                        exerciseDetailAddcomment.setEnabled(true);
                                        exerciseDetailAddteam.setEnabled(true);
                                        exerciseDetailListview.setEnabled(true);
                                        exerciseDetailBackBtn.setEnabled(true);
                                    }
                                } else {
                                    Toast.makeText(ExerciseDetailActivity.this, "查询失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    exerciseDetailReport.setEnabled(true);
                                    exerciseDetailCollect.setEnabled(true);
                                    exerciseDetailAddcomment.setEnabled(true);
                                    exerciseDetailAddteam.setEnabled(true);
                                    exerciseDetailListview.setEnabled(true);
                                    exerciseDetailBackBtn.setEnabled(true);
                                }
                            }

                        });
                    }
                }
            }
        });
    }

    private void updateAddTeamIds(List<String> addTeamIds) {
        UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
        userInfo.setJoinedTeamIds(addTeamIds);
        userInfo.update(userInfo.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    if (whetherAddTeam) {
                        Toast.makeText(ExerciseDetailActivity.this, "已退出活动", Toast.LENGTH_SHORT).show();
                        whetherAddTeam = false;
                        exerciseDetailAddteam.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
                        exerciseDetailAddteam.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.black));
                        exerciseDetailAddteam.setText("加入");
                        exerciseDetailReport.setEnabled(true);
                        exerciseDetailCollect.setEnabled(true);
                        exerciseDetailAddcomment.setEnabled(true);
                        exerciseDetailAddteam.setEnabled(true);
                        exerciseDetailListview.setEnabled(true);
                        exerciseDetailBackBtn.setEnabled(true);
                        teammembers.clear();
                    } else {
                        Toast.makeText(ExerciseDetailActivity.this, "已加入活动", Toast.LENGTH_SHORT).show();
                        whetherAddTeam = true;
                        exerciseDetailAddteam.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.colorGray));
                        exerciseDetailAddteam.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
                        exerciseDetailAddteam.setText("已加入");
                        exerciseDetailReport.setEnabled(true);
                        exerciseDetailCollect.setEnabled(true);
                        exerciseDetailAddcomment.setEnabled(true);
                        exerciseDetailAddteam.setEnabled(true);
                        exerciseDetailListview.setEnabled(true);
                        exerciseDetailBackBtn.setEnabled(true);
                        teammembers.clear();
                    }
                    Log.i("bmob", "更新成功");
                } else {
                    Toast.makeText(ExerciseDetailActivity.this, "查询失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                    exerciseDetailReport.setEnabled(true);
                    exerciseDetailCollect.setEnabled(true);
                    exerciseDetailAddcomment.setEnabled(true);
                    exerciseDetailAddteam.setEnabled(true);
                    exerciseDetailListview.setEnabled(true);
                    exerciseDetailBackBtn.setEnabled(true);
                    teammembers.clear();
                }
            }
        });
    }

    private void updateTeamByTeamId() {
        Activitys teams = new Activitys();
        teams.setMemberIds(teammembers);
        teams.update(exerciseObjectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    if (whetherAddTeam) {
                        List<String> addTeamIds = BmobUser.getCurrentUser(UserInfo.class).getJoinedTeamIds();
                        addTeamIds.remove(exerciseObjectId);
                        updateAddTeamIds(addTeamIds);
                    } else {
                        List<String> addTeamIds = BmobUser.getCurrentUser(UserInfo.class).getJoinedTeamIds();
                        addTeamIds.add(exerciseObjectId);
                        updateAddTeamIds(addTeamIds);
                    }
                    Log.i("bmob", "更新成功");
                } else {
                    Toast.makeText(ExerciseDetailActivity.this, "查询失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                    exerciseDetailReport.setEnabled(true);
                    exerciseDetailCollect.setEnabled(true);
                    exerciseDetailAddcomment.setEnabled(true);
                    exerciseDetailAddteam.setEnabled(true);
                    exerciseDetailListview.setEnabled(true);
                    exerciseDetailBackBtn.setEnabled(true);
                    teammembers.clear();
                }
            }
        });
    }

    private void initAddbtn() {
        whetherOwner = false;
        UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
        List<String> createAc = userInfo.getCreatedTeamIds();
        List<String> joinedAc = userInfo.getJoinedTeamIds();
        if (createAc != null && !createAc.isEmpty() && createAc.contains(exerciseObjectId)) {
            exerciseDetailAddteam.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.colorGray));
            exerciseDetailAddteam.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
            exerciseDetailAddteam.setText("已创建");
            whetherOwner = true;
        } else if (joinedAc != null && !joinedAc.isEmpty()) {
            if (joinedAc.contains(exerciseObjectId)) {
                exerciseDetailAddteam.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.colorGray));
                exerciseDetailAddteam.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
                exerciseDetailAddteam.setText("已加入");
                whetherAddTeam = true;
            } else {
                exerciseDetailAddteam.setText("加 入");
                whetherAddTeam = false;
            }
        } else {
            exerciseDetailAddteam.setText("加 入");
            whetherAddTeam = false;
        }

    }

    private void initCollectBtn() {
        UserInfo currentuser = BmobUser.getCurrentUser(UserInfo.class);
        if (currentuser.getCollectTeamIds() != null && !currentuser.getCollectTeamIds().isEmpty()) {
            if (currentuser.getCollectTeamIds().contains(exerciseObjectId)) {
                exerciseDetailCollect.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.colorGray));
                exerciseDetailCollect.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
                exerciseDetailCollect.setText("已关注");
                whetherCollectTeam = true;
            } else {
                exerciseDetailCollect.setText("关 注");
                whetherCollectTeam = false;
            }
        } else {
            exerciseDetailCollect.setText("关 注");
            whetherCollectTeam = false;
        }
    }

    private void initReportBtn() {
        UserInfo currentuser = BmobUser.getCurrentUser(UserInfo.class);
        if (currentuser.getReportTeamIds() != null && !currentuser.getReportTeamIds().isEmpty()) {
            if (currentuser.getReportTeamIds().contains(exerciseObjectId)) {
                exerciseDetailReport.setBackgroundColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.colorGray));
                exerciseDetailReport.setTextColor(ContextCompat.getColor(ExerciseDetailActivity.this, R.color.head));
                exerciseDetailReport.setText("已举报");
                whetherReportTeam = true;
            } else {
                exerciseDetailReport.setText("举 报");
                whetherReportTeam = false;
            }
        } else {
            exerciseDetailReport.setText("举 报");
            whetherReportTeam = false;
        }
    }

    private void initExerciseDetailActivity() {
        Toast.makeText(ExerciseDetailActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
        exerciseDetailReport.setEnabled(false);
        exerciseDetailCollect.setEnabled(false);
        exerciseDetailAddcomment.setEnabled(false);
        exerciseDetailAddteam.setEnabled(false);
        exerciseDetailListview.setEnabled(false);
        exerciseDetailBackBtn.setEnabled(false);
        exerciseObjectId = getIntent().getExtras().getString("objectId");
        initAddbtn();
        initCollectBtn();
        initReportBtn();
        BmobQuery<Activitys> query = new BmobQuery<Activitys>();
        query.getObject(exerciseObjectId, new QueryListener<Activitys>() {

            @Override
            public void done(Activitys object, BmobException e) {
                if (e == null) {
                    ownerId = object.getOwenerId();
                    if (ownerId == null) {
                        exerciseDetailReport.setEnabled(true);
                        exerciseDetailCollect.setEnabled(true);
                        exerciseDetailAddcomment.setEnabled(true);
                        exerciseDetailAddteam.setEnabled(true);
                        exerciseDetailListview.setEnabled(true);
                        exerciseDetailBackBtn.setEnabled(true);
                        Toast.makeText(ExerciseDetailActivity.this, "错误：无owner", Toast.LENGTH_SHORT).show();
                    } else {
                        exerciseDetailDate.setText(object.getSendingTime());
                        exerciseDetailIntro.setText(object.getContent());
                        exerciseDetailAddress.setText("地点： " + object.getAddress());
                        exerciseDetailTime.setText("时间： " + object.getActiivtiesTime());
                        exerciseDetailZannum.setText(object.getLikenum());
                        exerciseDetailComnum.setText(object.getCommentnum());
                        exerciseDetailTeamname.setText("队名： "+object.getActivityName());
                        activityImgURL = object.getImgURL();
                        if (activityImgURL != null) {
                            if (!activityImgURL.equals("null")) {
                                BmobFile activityImg = new BmobFile("" + object.getActivitiesId() + "exercisedetailactivityimg" + ".jpg", "", activityImgURL);
                                downloadActivityImgFiles(activityImg);
                            }
                        }
                        teammembers = object.getMemberIds();

                        BmobQuery<UserInfo> queryUser = new BmobQuery<UserInfo>();
                        queryUser.addWhereEqualTo("username", ownerId);
                        queryUser.findObjects(new FindListener<UserInfo>() {
                            @Override
                            public void done(List<UserInfo> object, BmobException e) {
                                if (e == null) {
                                    if (object.isEmpty()) {
                                        Toast.makeText(ExerciseDetailActivity.this, "错误：无owner", Toast.LENGTH_SHORT).show();
                                        exerciseDetailReport.setEnabled(true);
                                        exerciseDetailCollect.setEnabled(true);
                                        exerciseDetailAddcomment.setEnabled(true);
                                        exerciseDetailAddteam.setEnabled(true);
                                        exerciseDetailListview.setEnabled(true);
                                        exerciseDetailBackBtn.setEnabled(true);
                                    } else {
                                        UserInfo owner = object.get(0);
                                        ownerHeadImgURL = owner.getHeadimg();
                                        exerciseDetailMingzi.setText(owner.getNickname());
                                        if (ownerHeadImgURL != null) {
                                            if (!ownerHeadImgURL.equals("null")) {
                                                BmobFile ownerheadImg = new BmobFile("" + ownerId + "exercisedetailheadimg" + ".jpg", "", ownerHeadImgURL);
                                                downloadOwnerHeadImgFiles(ownerheadImg);
                                            }
                                        }
                                    }
                                } else {
                                    Toast.makeText(ExerciseDetailActivity.this, "错误：无owner", Toast.LENGTH_SHORT).show();
                                    exerciseDetailReport.setEnabled(true);
                                    exerciseDetailCollect.setEnabled(true);
                                    exerciseDetailAddcomment.setEnabled(true);
                                    exerciseDetailAddteam.setEnabled(true);
                                    exerciseDetailListview.setEnabled(true);
                                    exerciseDetailBackBtn.setEnabled(true);
                                }
                            }
                        });
                    }
                } else {
                    Toast.makeText(ExerciseDetailActivity.this, "错误：无activity", Toast.LENGTH_SHORT).show();
                    exerciseDetailReport.setEnabled(true);
                    exerciseDetailCollect.setEnabled(true);
                    exerciseDetailAddcomment.setEnabled(true);
                    exerciseDetailAddteam.setEnabled(true);
                    exerciseDetailListview.setEnabled(true);
                    exerciseDetailBackBtn.setEnabled(true);
                }
            }
        });

        initCommentList();
    }

    private void initCommentList() {
        BmobQuery<Comments> commentsBmobQuery = new BmobQuery<>();
        commentsBmobQuery.addWhereEqualTo("ActivityObjectId", exerciseObjectId);
        commentsBmobQuery.findObjects(new FindListener<Comments>() {
            @Override
            public void done(List<Comments> list, BmobException e) {
                if (e == null) {
                    if (list != null && !list.isEmpty()) {
                        List<CommentListInfo> listInfoList = new ArrayList<CommentListInfo>();
                        for (int i = 0; i < list.size(); i++) {
                            CommentListInfo commentListInfo = new CommentListInfo();
                            commentListInfo.setContent(list.get(i).getContent());
                            commentListInfo.setLikenum(list.get(i).getLikenum());
                            commentListInfo.setNicknameAndUsername(list.get(i).getNicknameAndUsername());
                            commentListInfo.setObjectId(list.get(i).getObjectId());
                            commentListInfo.setSendingTime(list.get(i).getSendingTime());
                            commentListInfo.setUserObjectId(list.get(i).getUserObjectId());
                            commentListInfo.setUserHeadImg(list.get(i).getUserHeadImg());

                            listInfoList.add(commentListInfo);
                        }

                        CommentListAdapter commentListAdapter = new CommentListAdapter(listInfoList, getParent());
                        exerciseDetailListview.setAdapter(commentListAdapter);
                        exerciseDetailReport.setEnabled(true);
                        exerciseDetailCollect.setEnabled(true);
                        exerciseDetailAddcomment.setEnabled(true);
                        exerciseDetailAddteam.setEnabled(true);
                        exerciseDetailListview.setEnabled(true);
                        exerciseDetailBackBtn.setEnabled(true);
                    } else {
                        Toast.makeText(ExerciseDetailActivity.this, "没有评论", Toast.LENGTH_SHORT).show();
                        exerciseDetailReport.setEnabled(true);
                        exerciseDetailCollect.setEnabled(true);
                        exerciseDetailAddcomment.setEnabled(true);
                        exerciseDetailAddteam.setEnabled(true);
                        exerciseDetailListview.setEnabled(true);
                        exerciseDetailBackBtn.setEnabled(true);
                    }
                } else {
                    Toast.makeText(ExerciseDetailActivity.this, "评论查询错误", Toast.LENGTH_SHORT).show();
                    exerciseDetailReport.setEnabled(true);
                    exerciseDetailCollect.setEnabled(true);
                    exerciseDetailAddcomment.setEnabled(true);
                    exerciseDetailAddteam.setEnabled(true);
                    exerciseDetailListview.setEnabled(true);
                    exerciseDetailBackBtn.setEnabled(true);
                }
            }
        });
    }

    // update
    public void downloadOwnerHeadImgFiles(BmobFile file) {
        File saveFile = new File(Environment.getExternalStorageDirectory(), file.getFilename());
        file.download(saveFile, new DownloadFileListener() {
            @Override
            public void onStart() {
                Log.i("bmob", "开始下载");
            }

            @Override
            public void done(String savePath, BmobException e) {
                if (e == null) {
                    Log.i("bmob", "exerciseeventadapter userimg succeed");
                    Bitmap bitmap = BitmapFactory.decodeFile(savePath);
                    exerciseDetailTouxiang.setImageBitmap(bitmap);
                    exerciseDetailReport.setEnabled(true);
                    exerciseDetailCollect.setEnabled(true);
                    exerciseDetailAddcomment.setEnabled(true);
                    exerciseDetailAddteam.setEnabled(true);
                    exerciseDetailListview.setEnabled(true);
                    exerciseDetailBackBtn.setEnabled(true);
                } else {
                    Log.i("bmob", "exerciseeventadapter userimg failed" + e.getErrorCode() + e.getMessage());
                    exerciseDetailReport.setEnabled(true);
                    exerciseDetailCollect.setEnabled(true);
                    exerciseDetailAddcomment.setEnabled(true);
                    exerciseDetailAddteam.setEnabled(true);
                    exerciseDetailListview.setEnabled(true);
                    exerciseDetailBackBtn.setEnabled(true);
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob", "下载进度：" + value + "," + newworkSpeed);
            }
        });
    }

    // update
    public void downloadActivityImgFiles(BmobFile file) {
        File saveFile = new File(Environment.getExternalStorageDirectory(), file.getFilename());
        file.download(saveFile, new DownloadFileListener() {
            @Override
            public void onStart() {
                Log.i("bmob", "开始下载");
            }

            @Override
            public void done(String savePath, BmobException e) {
                if (e == null) {
                    Log.i("bmob", "exerciseeventadapter userimg succeed");
                    Bitmap bitmap = BitmapFactory.decodeFile(savePath);
                    exerciseDetailPic.setImageBitmap(bitmap);
                    exerciseDetailReport.setEnabled(true);
                    exerciseDetailCollect.setEnabled(true);
                    exerciseDetailAddcomment.setEnabled(true);
                    exerciseDetailAddteam.setEnabled(true);
                    exerciseDetailListview.setEnabled(true);
                    exerciseDetailBackBtn.setEnabled(true);
                } else {
                    Log.i("bmob", "exerciseeventadapter userimg failed" + e.getErrorCode() + e.getMessage());
                    exerciseDetailReport.setEnabled(true);
                    exerciseDetailCollect.setEnabled(true);
                    exerciseDetailAddcomment.setEnabled(true);
                    exerciseDetailAddteam.setEnabled(true);
                    exerciseDetailListview.setEnabled(true);
                    exerciseDetailBackBtn.setEnabled(true);
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob", "下载进度：" + value + "," + newworkSpeed);
            }
        });
    }

    @OnClick(R.id.exercise_detail_backBtn)
    public void onClick() {
        this.finish();
    }
}
