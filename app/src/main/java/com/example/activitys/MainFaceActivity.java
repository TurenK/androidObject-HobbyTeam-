package com.example.activitys;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.*;
import android.app.Fragment;

import com.example.Fragment.Exercise.ExerciseFragment;
import com.example.Fragment.Message.MessageFragment;
import com.example.Fragment.Team.TeamFragment;
import com.example.Fragment.User.UserFragment;
import com.example.activitys.R;

public class MainFaceActivity extends Activity {
    LinearLayout ll_message;
    ImageView iv_message;
    TextView tv_message;
    LinearLayout ll_activity;
    ImageView iv_activity;
    TextView tv_activity;
    LinearLayout ll_team;
    ImageView iv_team;
    TextView tv_team;
    LinearLayout ll_me;
    ImageView iv_me;
    TextView tv_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_face);
        ll_message = (LinearLayout) findViewById(R.id.ll_message);
        iv_message = (ImageView) findViewById(R.id.iv_message);
        tv_message = (TextView) findViewById(R.id.tv_message);

        ll_activity = (LinearLayout) findViewById(R.id.ll_activity);
        iv_activity = (ImageView) findViewById(R.id.iv_activity);
        tv_activity = (TextView) findViewById(R.id.tv_activity);

        ll_team = (LinearLayout) findViewById(R.id.ll_team);
        iv_team = (ImageView) findViewById(R.id.iv_team);
        tv_team = (TextView) findViewById(R.id.tv_team);

        ll_me = (LinearLayout) findViewById(R.id.ll_me);
        iv_me = (ImageView) findViewById(R.id.iv_me);
        tv_me = (TextView) findViewById(R.id.tv_me);

        ll_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ExerciseFragment());
                tv_activity.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorBlack));
                tv_message.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
                tv_team.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
                tv_me.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
            }
        });
        ll_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new MessageFragment());
                tv_activity.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
                tv_message.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorBlack));
                tv_team.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
                tv_me.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
            }
        });
        ll_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new TeamFragment());
                tv_activity.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
                tv_message.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
                tv_team.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorBlack));
                tv_me.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
            }
        });
        ll_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserFragment());
                tv_activity.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
                tv_message.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
                tv_team.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorGray));
                tv_me.setTextColor(ContextCompat.getColor(MainFaceActivity.this, R.color.colorBlack));
            }
        });

        ll_activity.performClick();
    }
    private void changeContent(Fragment fragment) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Main_Frame,fragment);
        fragmentTransaction.commit();
    }
}
