package com.example.activitys.Exercise;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.R;
import com.example.activitys.User.UserSmallItemInfo;
import com.example.activitys.User.UsersmallItemAdapter;
import com.example.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ExerciseMemberActivity extends Activity {

    @InjectView(R.id.backBtn)
    Button backBtn;
    @InjectView(R.id.activity_member_titletext)
    TextView activityMemberTitletext;
    @InjectView(R.id.activity_member_title)
    LinearLayout activityMemberTitle;
    @InjectView(R.id.activity_member_list)
    ListView activityMemberList;
    List<String> memberIds;
    UserSmallItemInfo userSmallItemInfo;
    List<UserSmallItemInfo> userSmallItemInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_member);
        ButterKnife.inject(this);
        initData();
    }

    private void initData(){
        memberIds = new ArrayList<>();
        userSmallItemInfoList = new ArrayList<>();

        memberIds = getIntent().getExtras().getStringArrayList("memberUsernames");

            BmobQuery<UserInfo> userInfoBmobQuery = new BmobQuery<>();

        List<BmobQuery<UserInfo>> queries = new ArrayList<>();
        for(int i =0;i<memberIds.size();i++){
            BmobQuery<UserInfo> temp = new BmobQuery<>();
            temp.addWhereEqualTo("username",memberIds.get(i));
            queries.add(temp);
        }
        userInfoBmobQuery.or(queries);
            userInfoBmobQuery.findObjects(new FindListener<UserInfo>() {
                @Override
                public void done(List<UserInfo> list, BmobException e) {
                    if(e==null){
                        if(list!=null&&!list.isEmpty()){
                            for(int i=0;i<list.size();i++){
                            userSmallItemInfo = new UserSmallItemInfo();
                            userSmallItemInfo.setNickname(list.get(i).getNickname());
                            userSmallItemInfo.setUseritemsmallId(list.get(i).getUserId());
                            userSmallItemInfo.setUserheadimgUrl(list.get(i).getHeadimg());
                            userSmallItemInfo.setUsertags(list.get(i).getTag());
                            userSmallItemInfo.setUsername(list.get(i).getUsername());

                            userSmallItemInfoList.add(userSmallItemInfo);
                            }

                            UsersmallItemAdapter usersmallItemAdapter = new UsersmallItemAdapter(userSmallItemInfoList,ExerciseMemberActivity.this);
                            activityMemberList.setAdapter(usersmallItemAdapter);
                        }else{
                            Toast.makeText(ExerciseMemberActivity.this, "查询结果不存在", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ExerciseMemberActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @OnClick(R.id.backBtn)
    public void onClick() {
        finish();
    }
}
