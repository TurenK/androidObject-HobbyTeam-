package com.example.Fragment.User;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.activitys.Exercise.ExerciseMapActivity;
import com.example.activitys.R;
import com.example.model.UserInfo;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static com.example.activitys.R.id.user_detail_introduction_addr;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailIntroduction extends Fragment {

    LinearLayout backto;
    LinearLayout head;
    LinearLayout name;
    LinearLayout twohorse;
    LinearLayout sex;
    LinearLayout xuanyan;
    LinearLayout address;
    LinearLayout mytags;
    ImageView userDetailIntroductionUserImg;
    TextView userDetailIntroductionNickname;
    TextView userDetailIntroductionIntro;
    ImageView userDetailIntroductionGender;
    String userImgPath;
    TextView userDetailIntroductionAddr;
    TextView userDetailIntroductionTags;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;

    public UserDetailIntroduction() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_user_detail_introduction, container, false);
        //ac_fargment_a为fragment当前布局
        View view=inflater.inflate(R.layout.fragment_user_detail_introduction,null);
        //绑定该LinearLayout的ID
        address=(LinearLayout)view.findViewById(R.id.address);
        //设置监听
        address.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), ExerciseMapActivity.class);
                startActivity(intent);
                getAddr();
            }
        });
        return view;
    }

    private void getAddr() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String addr = aMapLocation.getAddress();//地址
                        setAddrText(addr);
                    }else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError","location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        };

        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象,该对象用于设置定位相关的属性
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //只定位一次
        mLocationOption.setOnceLocation(true);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位超时时间，单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(10000);
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 设置地址信息
     *
     * @param str
     */
    public void setAddrText(String str) {
        final String s = str;
        try {
            if (userDetailIntroductionAddr != null){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDetailIntroductionAddr.post(new Runnable() {
                            @Override
                            public void run() {
                                userDetailIntroductionAddr.setText(s);
                            }
                        });

                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUserDetailIntroduction() {
        userDetailIntroductionAddr = getView().findViewById(user_detail_introduction_addr);
        userDetailIntroductionTags = getView().findViewById(R.id.user_detail_introduction_tags);
        backto = getView().findViewById(R.id.back);
        head = getView().findViewById(R.id.head);
        name = getView().findViewById(R.id.name);
        twohorse = getView().findViewById(R.id.twohorse);
        sex = getView().findViewById(R.id.sex);
        xuanyan = getView().findViewById(R.id.xuanyan);
        address = getView().findViewById(R.id.address);
        mytags = getView().findViewById(R.id.mytags);
        userDetailIntroductionGender = getView().findViewById(R.id.user_detail_introduction_gender);
        userDetailIntroductionIntro = getView().findViewById(R.id.user_detail_introduction_intro);
        userDetailIntroductionNickname = getView().findViewById(R.id.user_detail_introduction_nickname);
        userDetailIntroductionUserImg = getView().findViewById(R.id.user_detail_introduction_userImg);
        UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
        String userImgURL = userInfo.getHeadimg();
        if (userImgURL != null) {
            if(!userImgURL.equals("null")){
            BmobFile userImg = new BmobFile("" + userInfo.getUsername()+"userdetailintroductionheadimg" + ".jpg", "", userInfo.getHeadimg());
            downloadImgFiles(userImg);
            }
        }

        userDetailIntroductionNickname.setText(userInfo.getNickname());

        String userIntro = userInfo.getIntro();
        if (userIntro != null ) {
            if( !userIntro.equals("null")){
            if (userIntro.length() > 10) {
                userIntro = userIntro.substring(0, 10) + "...";
                userDetailIntroductionIntro.setText(userIntro);
            } else {
                userDetailIntroductionIntro.setText(userIntro);
            }
            }
        }

        String userGender = userInfo.getSex();
        if(userGender==null){
            userDetailIntroductionGender.setImageResource(R.mipmap.userdetailintroductionquestion);
        }else if(userGender.equals("female")){
            userDetailIntroductionGender.setImageResource(R.mipmap.userdetailintroductionfemale);
        }else if(userGender.equals("male")){
            userDetailIntroductionGender.setImageResource(R.mipmap.userdetailintroductionmale);
        }else{
            userDetailIntroductionGender.setImageResource(R.mipmap.userdetailintroductionquestion);
        }

        List<String> tags = userInfo.getTag();
        if(tags!=null){
            if(!tags.isEmpty()){
                String tag = "";
                for(int i=0;i<tags.size();i++ ){
                    tag = tag + tags.get(i)+";";
                }
            userDetailIntroductionTags.setText(tag);
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
                    userDetailIntroductionUserImg.setImageBitmap(bitmap);
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
        initUserDetailIntroduction();

        backto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserFragment());
            }
        });
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailHeadFragment());
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailNameFragment());
            }
        });
        twohorse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailTwoHorseFragment());
            }
        });
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailSexFragment());
            }
        });
        xuanyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailWordsFragment());
            }
        });
        mytags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailTagFragment());
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
