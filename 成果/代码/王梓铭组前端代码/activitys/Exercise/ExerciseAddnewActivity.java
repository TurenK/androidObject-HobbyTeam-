package com.example.activitys.Exercise;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.activitys.R;
import com.example.model.Activitys;
import com.example.model.UserInfo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class ExerciseAddnewActivity extends Activity {

    private static final int PIC = 100;
    private static final int CAM = 200;
    private static final int CALCULATE = 1000;
    private static final int LOCATION = 2333;
    private final static int TAGS = 105;
    @InjectView(R.id.backBtn)
    Button backBtn;
    @InjectView(R.id.titleBar)
    LinearLayout titleBar;
    @InjectView(R.id.NameEdit)
    EditText NameEdit;
    @InjectView(R.id.ActNameFrame)
    LinearLayout ActNameFrame;
    @InjectView(R.id.TimeFrame)
    Button TimeFrame;
    @InjectView(R.id.ActTimeFrame)
    FrameLayout ActTimeFrame;
    @InjectView(R.id.PostEdit)
    Button PostEdit;
    @InjectView(R.id.ActPostFrame)
    FrameLayout ActPostFrame;
    @InjectView(R.id.exercise_add_tagtext)
    Button exerciseAddTagtext;
    @InjectView(R.id.exercise_add_tagframe)
    FrameLayout exerciseAddTagframe;
    @InjectView(R.id.ContentEditTip)
    EditText ContentEditTip;
    @InjectView(R.id.ActContentFrame)
    FrameLayout ActContentFrame;
    @InjectView(R.id.empty_team_add)
    LinearLayout emptyTeamAdd;
    @InjectView(R.id.picture)
    ImageView picture;
    @InjectView(R.id.submitBTN)
    Button submitBTN;
    private String picturePath = "";
    private BmobFile bmobFile;
    private Uri imageUri;
    private List<String> tags;
    private String positionAddr;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_addnew);
        ButterKnife.inject(this);
        requirePer();
        tags = new ArrayList<>();
    }

    private void requirePer() {
        if (ContextCompat.checkSelfPermission(ExerciseAddnewActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                ) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(ExerciseAddnewActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE},
                    1);
        }
    }


    private void insertExerciseItem() {
        if (picturePath == null || picturePath.isEmpty()) {
            Activitys insertItem = new Activitys();
            insertItem.setImgURL("null");
            insertItem.setOwenerId(BmobUser.getCurrentUser(UserInfo.class).getUsername());
            insertItem.setActivityName(NameEdit.getText().toString());
            insertItem.setActiivtiesTime(TimeFrame.getText().toString());
            positionAddr = PostEdit.getText().toString();
            Toast.makeText(ExerciseAddnewActivity.this,positionAddr , Toast.LENGTH_SHORT).show();
            String temp=positionAddr;
            double lat=Double.parseDouble(temp.substring(1,6));
            double longt=Double.parseDouble(temp.substring(8,13));
            String last=temp.substring(15,temp.length());
            Toast.makeText(ExerciseAddnewActivity.this,lat+longt+last, Toast.LENGTH_SHORT).show();
            BmobGeoPoint point=new BmobGeoPoint(longt,lat);
            insertItem.setAddrGeo(point);
            insertItem.setAddress(last);
            insertItem.setTag(tags);
            insertItem.setLikenum("0");
            insertItem.setCommentnum("0");
            insertItem.setContent(ContentEditTip.getText().toString());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());
            insertItem.setSendingTime(date);
            insertItem.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
                            List<String> userCreated = userInfo.getCreatedTeamIds();
                        if(userCreated==null||userCreated.isEmpty()){
                            userCreated = new ArrayList<String>();
                        }
                        userCreated.add(s);
                        userInfo.setCreatedTeamIds(userCreated);
                        userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                                @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(ExerciseAddnewActivity.this, "添加活动成功", Toast.LENGTH_SHORT).show();
                                    ExerciseAddnewActivity.this.finish();
                                }else{
                                    Toast.makeText(ExerciseAddnewActivity.this, "添加活动失败", Toast.LENGTH_SHORT).show();
                                    NameEdit.setEnabled(true);
                                    TimeFrame.setEnabled(true);
                                    PostEdit.setEnabled(true);
                                    exerciseAddTagtext.setEnabled(true);
                                    ContentEditTip.setEnabled(true);
                                    picture.setEnabled(true);
                                    submitBTN.setEnabled(true);
                                }
                            }
                        });
                    } else {
                        Toast.makeText(ExerciseAddnewActivity.this, "添加活动失败", Toast.LENGTH_SHORT).show();
                        NameEdit.setEnabled(true);
                        TimeFrame.setEnabled(true);
                        PostEdit.setEnabled(true);
                        exerciseAddTagtext.setEnabled(true);
                        ContentEditTip.setEnabled(true);
                        picture.setEnabled(true);
                        submitBTN.setEnabled(true);
                    }
                }
            });
        } else {
            bmobFile = new BmobFile(new File(picturePath));
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(ExerciseAddnewActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                        String imgUrltemp = bmobFile.getFileUrl();
                        Activitys insertItem = new Activitys();
                        insertItem.setActivityName(NameEdit.getText().toString());
                        insertItem.setActiivtiesTime(TimeFrame.getText().toString());
                        insertItem.setOwenerId(BmobUser.getCurrentUser(UserInfo.class).getUsername());
                        insertItem.setTag(tags);
                        insertItem.setLikenum("0");
                        positionAddr = PostEdit.getText().toString();
                        Toast.makeText(ExerciseAddnewActivity.this,positionAddr , Toast.LENGTH_SHORT).show();
                        String temp=positionAddr;
                        double lat=Double.parseDouble(temp.substring(1,6));
                        double longt=Double.parseDouble(temp.substring(8,13));
                        String last=temp.substring(15,temp.length());
                        Toast.makeText(ExerciseAddnewActivity.this,lat+longt+last, Toast.LENGTH_SHORT).show();
                        BmobGeoPoint point=new BmobGeoPoint(longt,lat);
                        insertItem.setAddrGeo(point);
                        insertItem.setAddress(last);
                        insertItem.setCommentnum("0");
                        insertItem.setContent(ContentEditTip.getText().toString());
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        String date = df.format(new Date());
                        insertItem.setSendingTime(date);
                        insertItem.setImgURL(imgUrltemp);
                        insertItem.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
                                    List<String> userCreated = userInfo.getCreatedTeamIds();
                                    if(userCreated==null||userCreated.isEmpty()){
                                        userCreated = new ArrayList<String>();
                                    }
                                    userCreated.add(s);
                                    userInfo.setCreatedTeamIds(userCreated);
                                    userInfo.update(userInfo.getObjectId(), new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if(e==null){
                                                Toast.makeText(ExerciseAddnewActivity.this, "添加活动成功", Toast.LENGTH_SHORT).show();
                                                ExerciseAddnewActivity.this.finish();
                                            }else{
                                                Toast.makeText(ExerciseAddnewActivity.this, "添加活动失败", Toast.LENGTH_SHORT).show();
                                                NameEdit.setEnabled(true);
                                                TimeFrame.setEnabled(true);
                                                PostEdit.setEnabled(true);
                                                exerciseAddTagtext.setEnabled(true);
                                                ContentEditTip.setEnabled(true);
                                                picture.setEnabled(true);
                                                submitBTN.setEnabled(true);
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(ExerciseAddnewActivity.this, "添加活动失败", Toast.LENGTH_SHORT).show();
                                    NameEdit.setEnabled(true);
                                    TimeFrame.setEnabled(true);
                                    PostEdit.setEnabled(true);
                                    exerciseAddTagtext.setEnabled(true);
                                    ContentEditTip.setEnabled(true);
                                    picture.setEnabled(true);
                                    submitBTN.setEnabled(true);
                                }
                            }
                        });
                    } else {
                        Toast.makeText(ExerciseAddnewActivity.this, "图片上传失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                        NameEdit.setEnabled(true);
                        TimeFrame.setEnabled(true);
                        PostEdit.setEnabled(true);
                        exerciseAddTagtext.setEnabled(true);
                        ContentEditTip.setEnabled(true);
                        picture.setEnabled(true);
                        submitBTN.setEnabled(true);
                    }

                }

                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                }
            });
        }
    }

    private void chooseImage() {
        String[] items = new String[]{"图库", "相机"};
        new AlertDialog.Builder(ExerciseAddnewActivity.this)
                .setTitle("选择来源")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0: {//图库
                                //启动图库的activity，需要用隐式意图,括号里的参数是固定写法
                                //Log.e("tag", "test1");
                                Intent pic = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                //PIC 表示请求码，便于在onActivityResult里进行判断，这里需要声明一个静态成员变量
                                startActivityForResult(pic, PIC);
                                break;
                            }
                            case 1: {//相机
                                Log.e("tag", "testcamera");
//                                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                //告诉相机拍摄完毕输出图片到指定的Uri
//                                startActivityForResult(camera, CAM);

                                Intent intent_sca = new Intent("android.media.action.IMAGE_CAPTURE");

                                File file ;
                                if (Environment.getExternalStorageState().equals(
                                        Environment.MEDIA_MOUNTED)) {//判断是否已经挂载
                                   file = new File(ExerciseAddnewActivity.this.getExternalCacheDir(), "scatemp.jpeg");
                                }else{
                                    file= new File(ExerciseAddnewActivity.this.getCacheDir(), "scatemp.jpeg");
                                }

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
                                    imageUri = FileProvider.getUriForFile(ExerciseAddnewActivity.this, ExerciseAddnewActivity.this.getPackageName() + ".fileprovider", file);
                                }
                                intent_sca.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                startActivityForResult(intent_sca, CAM);

                                break;
                            }
                        }
                    }
                }).setCancelable(true)
                .show();
    }


    //重写启动新的activity以后的回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LOCATION:{
                if(resultCode == LOCATION&&data!=null){
                    String address = data.getExtras().getString("addr");
                    PostEdit.setText(address);
                }
                break;
            }
            case PIC: {
                if (resultCode == RESULT_OK && data != null) {
                    //因为读取本地图库，会存在权限问题，需要在manifest文件里添加<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
                    //这个可以先不和学生讲，由他们根据报错信息自行查找解决方案，网络上可以搜索到解决方案
                    Uri selectedImage = data.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    // Toast.makeText(ExerciseAddnewActivity.this, picturePath, Toast.LENGTH_SHORT).show();

                    Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                    picture.setImageBitmap(bitmap);
                }
                break;
            }
            case CAM: {
                if (resultCode == RESULT_OK ) {
                    picture.setImageURI(null);
                    picture.setImageURI(imageUri);
                    if (Environment.getExternalStorageState().equals(
                            Environment.MEDIA_MOUNTED)) {//判断是否已经挂载
                        picturePath = new File(ExerciseAddnewActivity.this.getExternalCacheDir(), "scatemp.jpeg").getPath();
                    }else{
                        picturePath = new File(ExerciseAddnewActivity.this.getCacheDir(), "scatemp.jpeg").getPath();
                    }

                    Log.e("tag","testt");
                }
                break;
            }
            case CALCULATE: {
                if (resultCode == 1000 && data != null) {
                    //获得返回数据
                    Bundle extras = data.getExtras();
                    int year = extras.getInt("year");
                    int month = extras.getInt("month")+1;
                    int day = extras.getInt("day");
                    String hour = String.valueOf(extras.getInt("hour"));
                    String minute = String.valueOf(extras.getInt("minute"));
                    if (hour.length() < 2) {
                        hour = "0" + hour;
                    }
                    if (minute.length() < 2) {
                        minute = "0" + minute;
                    }

                    TimeFrame.setText("" + year + "年" + month + "月" + day + "日" + "  " + hour + ":" + minute);
                }
                break;
            }
//            case LOCATION: {
//                if (resultCode == RESULT_OK && data != null) {
//                    String address = getIntent().getExtras().getString("addr");
//                    if (address != null && !address.isEmpty()) {
//                        positionAddr = address;
//                        if (address.length() > 11) {
//                            address = address.substring(11) + "...";
//                        }
//                        PostEdit.setText(address);
//                    }
//                }
//                break;
//            }
            case TAGS: {
                if (resultCode == TAGS && data != null) {
                    String tagtemp = data.getExtras().getString("tag");
                    if (tagtemp != null) {
                        tags.clear();
                        String tagstemp[] = tagtemp.split(";");
                        for (int i = 0; i < tagstemp.length; i++) {
                            if (tagstemp[i] != null)
                                tags.add(tagstemp[i]);
                        }
                        exerciseAddTagtext.setText(tagtemp);
                    }
                }
                break;
            }
        }
    }

    @OnClick({R.id.backBtn, R.id.TimeFrame, R.id.PostEdit, R.id.exercise_add_tagtext, R.id.submitBTN, R.id.picture})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn: {
                ExerciseAddnewActivity.this.finish();
                break;
            }
            case R.id.picture: {
                chooseImage();
                break;
            }
            case R.id.PostEdit: {
                Intent intent = new Intent(ExerciseAddnewActivity.this, ExerciseMapActivity.class);
                startActivityForResult(intent,LOCATION);
                break;
            }
            case R.id.submitBTN: {
                NameEdit.setEnabled(false);
                TimeFrame.setEnabled(false);
                PostEdit.setEnabled(false);
                exerciseAddTagtext.setEnabled(false);
                ContentEditTip.setEnabled(false);
                picture.setEnabled(false);
                submitBTN.setEnabled(false);
                String activityname = NameEdit.getText().toString();
                String activitytime = TimeFrame.getText().toString();
                String activityaddr = PostEdit.getText().toString();
                String activitytags = exerciseAddTagtext.getText().toString();
                String activitycontent = ContentEditTip.getText().toString();

                if (activityname != null&&!activityname.isEmpty() && activitytime != null&&!activitytime.isEmpty()
                        && activityaddr != null &&!activityaddr.isEmpty() && activitycontent != null &&!activitycontent.isEmpty()
                        && activitytags!=null&&!activitytags.isEmpty() &&activitytags.substring(activitytags.length() - 1, activitytags.length()).equals(";")
                        ) {
                    insertExerciseItem();
                } else {
                    Toast.makeText(ExerciseAddnewActivity.this, "请填写正确", Toast.LENGTH_SHORT).show();
                    NameEdit.setEnabled(true);
                    TimeFrame.setEnabled(true);
                    PostEdit.setEnabled(true);
                    exerciseAddTagtext.setEnabled(true);
                    ContentEditTip.setEnabled(true);
                    picture.setEnabled(true);
                    submitBTN.setEnabled(true);
                }
                break;
            }
            case R.id.TimeFrame: {
                Intent intent = new Intent(ExerciseAddnewActivity.this, ExerciseAddnewDateTime.class);
                startActivityForResult(intent, CALCULATE);
                break;
            }
            case R.id.exercise_add_tagtext: {
                Intent intent = new Intent(ExerciseAddnewActivity.this, TagAddActivity.class);
                startActivityForResult(intent, TAGS);
                break;
            }
        }
    }
}