package com.example.Fragment.User;


import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.Exercise.ExerciseAddnewActivity;
import com.example.activitys.MainFaceActivity;
import com.example.activitys.R;
import com.example.model.Activitys;
import com.example.model.UserInfo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailHeadFragment extends Fragment {

    private TextView userDetailHeadBack;
    private TextView userDetailHeadSubmit;
    private ImageView userDetailHeadUserImgView;
    private Button userDetailHeadAlbumBtn;
    private Button userDetailHeadCameraBtn;
    private Uri imageUri;
    private String picturePath;
    private BmobFile bmobFile;
    private String userImgPath;

    private static final int PIC = 100;
    private static final int CAM = 200;

    public UserDetailHeadFragment() {
        // Required empty public constructor
    }

    private void requirePer() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                    1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_detail_head, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    private void initComData() {
        userDetailHeadBack = getView().findViewById(R.id.user_detail_head_back);
        userDetailHeadSubmit = getView().findViewById(R.id.user_detail_head_submit);
        userDetailHeadUserImgView = getView().findViewById(R.id.user_detail_head_userImgView);
        userDetailHeadAlbumBtn = getView().findViewById(R.id.user_detail_head_AlbumBtn);
        userDetailHeadCameraBtn = getView().findViewById(R.id.user_detail_head_CameraBtn);

        UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
        String userImgURL = userInfo.getHeadimg();
        if (userImgURL != null  ) {
            if(!userImgURL.equals("null")){
            BmobFile userImg = new BmobFile("" + userInfo.getUsername() + ".jpg", "", userInfo.getHeadimg());
            downloadImgFiles(userImg);
            }
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
                    //Toast.makeText(UserDetailIntroduction.this.getActivity().getApplicationContext(), "下载成功", Toast.LENGTH_SHORT).show();
                    Log.i("bmob", "userimg");
                    userImgPath = savePath;
                    Bitmap bitmap = BitmapFactory.decodeFile(userImgPath);
                    userDetailHeadUserImgView.setImageBitmap(bitmap);
                } else {
                    //Toast.makeText(UserDetailIntroduction.this.getActivity().getApplicationContext(), "下载失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("bmob", "userimg failed" + e.getErrorCode() + e.getMessage());
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
        requirePer();
        initComData();
        userDetailHeadBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailIntroduction());
            }
        });

        userDetailHeadAlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pic = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //PIC 表示请求码，便于在onActivityResult里进行判断，这里需要声明一个静态成员变量
                startActivityForResult(pic, PIC);
            }
        });

        userDetailHeadCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent_sca = new Intent("android.media.action.IMAGE_CAPTURE");
                File file = new File(getActivity().getCacheDir(), "userdetailheadtemp.jpeg");
                try {
                    if (file.exists())
                        file.delete();
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                    imageUri = Uri.fromFile(file);
                } else {
                    imageUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider", file);
                }
                intent_sca.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent_sca, CAM);
            }
        });

        userDetailHeadSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertUserImgItem();
            }
        });
    }

    private void insertUserImgItem() {
        if (picturePath == null || picturePath.isEmpty()) {
            Toast.makeText(getActivity(), "头像不能为空", Toast.LENGTH_SHORT).show();
        } else {
            bmobFile = new BmobFile(new File(picturePath));
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(getActivity(), "图片上传成功", Toast.LENGTH_SHORT).show();
                        String imgUrltemp = bmobFile.getFileUrl();
                        UserInfo newUser = new UserInfo();
                        newUser.setHeadimg(imgUrltemp);
                        UserInfo insertItem = BmobUser.getCurrentUser(UserInfo.class);
                        newUser.update(insertItem.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(getActivity(), "用户信息更新成功", Toast.LENGTH_SHORT).show();
                                    changeContent(new UserDetailIntroduction());
                                }else{
                                    Toast.makeText(getActivity(), "用户信息更新失败"+e.getErrorCode()+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "图片上传失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                }
            });
        }

    }

    private void changeContent(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Main_Frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    //重写启动新的activity以后的回调方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PIC: {
                if (resultCode == getActivity().RESULT_OK && data != null) {
                    //因为读取本地图库，会存在权限问题，需要在manifest文件里添加<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
                    //这个可以先不和学生讲，由他们根据报错信息自行查找解决方案，网络上可以搜索到解决方案
                    Uri selectedImage = data.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    // Toast.makeText(ExerciseAddnewActivity.this, picturePath, Toast.LENGTH_SHORT).show();

                    Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                    userDetailHeadUserImgView.setImageBitmap(bitmap);
                }
                break;
            }
            case CAM: {
                if (resultCode == getActivity().RESULT_OK && data != null) {
                    //获得返回数据
                    userDetailHeadUserImgView.setImageURI(imageUri);
                    picturePath = new File(getActivity().getCacheDir(), "userdetailheadtemp.jpeg").getPath();
//                    userDetailHeadUserImgView.setImageURI(imageUri);
//                    picturePath = new File(ExerciseAddnewActivity.this.getCacheDir(), "scatemp.jpeg").getPath();
                }
                break;
            }
        }
    }
}
