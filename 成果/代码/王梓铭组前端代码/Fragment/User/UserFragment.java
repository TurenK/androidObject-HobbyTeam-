package com.example.Fragment.User;


import android.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.activitys.LoginAndRegister.MainActivity;
import com.example.activitys.R;

import com.example.model.UserInfo;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    LinearLayout Detail;
    LinearLayout album;
    LinearLayout face;
    LinearLayout setting;
    LinearLayout data;
    LinearLayout out;
    ImageView userMainfaceUserImg;
    TextView userMainfaceNickname;
    TextView userMainfaceUsername;
    String userImgPath;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    private void initUserData() {
        UserInfo userInfo = new UserInfo();
        userInfo = BmobUser.getCurrentUser(UserInfo.class);
        userMainfaceNickname.setText(userInfo.getNickname());
        userMainfaceUsername.setText("手机号：" + userInfo.getUsername());
        userImgPath = null;
        if (userInfo.getHeadimg() != null&&!userInfo.getHeadimg().equals("null")) {
            BmobFile userImg = new BmobFile("" + userInfo.getUsername()+"usermainfaceheadimg" + ".jpg", "", userInfo.getHeadimg());
            downloadImgFiles(userImg);
        }
    }

    public void downloadImgFiles(BmobFile file) {
        File saveFile = new File(Environment.getExternalStorageDirectory(), file.getFilename());
        file.download(saveFile, new DownloadFileListener() {

            @Override
            public void onStart() {
                Log.i("bmob", "开始下载");
            }

            @Override
            public void done(String savePath, BmobException e) {
                if (e == null) {
                   // Toast.makeText(UserFragment.this.getActivity().getApplicationContext(), "下载成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob", "userfragment userimg succeed");
                    userImgPath = savePath;
                    Bitmap bitmap = BitmapFactory.decodeFile(userImgPath);
                    userMainfaceUserImg.setImageBitmap(bitmap);
                } else {
                    //Toast.makeText(UserFragment.this.getActivity().getApplicationContext(), "下载失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("bmob", "userfragment userimg failed"+e.getErrorCode()+e.getMessage());
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob", "下载进度：" + value + "," + newworkSpeed);
            }

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userMainfaceUserImg = getView().findViewById(R.id.user_mainface_userImg);
        userMainfaceNickname = getView().findViewById(R.id.user_mainface_nickname);
        userMainfaceUsername = getView().findViewById(R.id.user_mainface_username);
        Detail = getView().findViewById(R.id.Detail);
        album = getView().findViewById(R.id.album);
        face = getView().findViewById(R.id.face);
        setting = getView().findViewById(R.id.setting);
        data = getView().findViewById(R.id.data);
        out = getView().findViewById(R.id.out);
        initUserData();

        Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailIntroduction());
            }
        });

        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserAlbumFragment());
            }
        });

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserBiaoqingFragment());
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserSettingFragment());
            }
        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserRecordFragment());
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UserFragment.this.getActivity(), MainActivity.class);
                BmobUser.logOut();
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void changeContent(Fragment fragment) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Main_Frame, fragment);
        fragmentTransaction.commit();
    }

}
