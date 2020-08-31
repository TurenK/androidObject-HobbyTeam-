package com.example.activitys.LoginAndRegister;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.*;

import android.content.*;

import com.alibaba.fastjson.JSONObject;
import com.example.activitys.MainFaceActivity;
import com.example.activitys.R;

import com.example.model.UserInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class MainActivity extends Activity {
    Button log;
    EditText ID;
    EditText password;
    TextView forget;
    TextView register;
    TextView qqLogin;
    CheckBox save;
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1106216593";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private com.tencent.connect.UserInfo mUserInfo;

    private void requirePer() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_MULTICAST_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.ACCESS_NETWORK_STATE
                            , Manifest.permission.CHANGE_NETWORK_STATE
                            , Manifest.permission.READ_PHONE_STATE
                            , Manifest.permission.CHANGE_WIFI_STATE
                            , Manifest.permission.CHANGE_WIFI_MULTICAST_STATE
                            , Manifest.permission.ACCESS_WIFI_STATE},
                    1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) { //权限没有被授予
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log = (Button) findViewById(R.id.log);
        ID = (EditText) findViewById(R.id.ID);
        password = (EditText) findViewById(R.id.password);
        forget = (TextView) findViewById(R.id.forget);
        register = (TextView) findViewById(R.id.register);
        save = (CheckBox) findViewById(R.id.save);
        qqLogin = (TextView) findViewById(R.id.qq_login);
        requirePer();
        save.setChecked(true);

        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,MainActivity.this.getApplicationContext());

        Bmob.initialize(this, ServiceUrl.getAfterCloudID(MainActivity.this));

        String get = getTxtFileInfo(getApplicationContext());
        if (get != null && get.charAt(0) != '#' && get.charAt(get.length() - 1) != '#') {
            // 使用保存信息使用的##将内容分割出来
            String[] contents = get.split("##");
            ID.setText(deal(contents[0], (byte) 88));
            password.setText(deal(contents[1], (byte) 88));

        }

        //登录点击事件
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID.setEnabled(false);
                password.setEnabled(false);
                qqLogin.setEnabled(false);
                forget.setEnabled(false);
                register.setEnabled(false);
                log.setEnabled(false);
                save.setEnabled(false);
                if (save.isChecked()) {
                    saveUserInfo(getApplicationContext(), ID.getText().toString(), password.getText().toString());
                }
                String phone = ID.getText().toString();
                String pwd = password.getText().toString();
                loginCloudServer(phone, pwd);
                //Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        });

        //登录点击事件
        qqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(MainActivity.this,"all", mIUiListener);

//                if (save.isChecked()) {
//                    saveUserInfo(getApplicationContext(), ID.getText().toString(), password.getText().toString());
//                }
//                String phone = ID.getText().toString();
//                String pwd = password.getText().toString();
//                loginCloudServer(phone, pwd);
                //Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        });

        //忘记密码事件
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Forgetpwd.class);
                MainActivity.this.startActivity(intent);
            }
        });
        //注册用户事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener{

        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new com.tencent.connect.UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                        Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");
                        Toast.makeText(MainActivity.this,"登录取消",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static boolean saveUserInfo(Context context, String username, String password) {
        try {
            // 使用Android上下问获取当前项目的路径
            File file = new File(context.getFilesDir(), "userinfo.txt");
            // 创建输出流对象
            FileOutputStream fos = new FileOutputStream(file);
            // 向文件中写入信息
            fos.write((deal(username, (byte) 88) + "##" + deal(password, (byte) 88)).getBytes());
            // 关闭输出流对象
            fos.close();
            return true;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }

    public String getTxtFileInfo(Context context) {
        try {
            // 创建FIle对象
            File file = new File(context.getFilesDir(), "userinfo.txt");
            // 创建FileInputStream对象
            FileInputStream fis = new FileInputStream(file);
            // 创建BufferedReader对象
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            // 获取文件中的内容
            String content = br.readLine();
            // 关闭流对象
            fis.close();
            br.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static String deal(String str, byte skey) {
        try {
            byte[] bytes = str.getBytes("GBK");

            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ skey);
            }

            return new String(bytes, "GBK");
        } catch (Exception e) {
            return null;
        }
    }

    private void loginCloudServer(String phone, String pwd) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(phone);
        userInfo.setPassword(pwd);
        //Log.e("tag","test2");
        userInfo.login(new SaveListener<UserInfo>() {
            @Override
            public void done(UserInfo userInfo, BmobException e) {
                if (e != null) {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    ID.setEnabled(true);
                    password.setEnabled(true);
                    qqLogin.setEnabled(true);
                    forget.setEnabled(true);
                    register.setEnabled(true);
                    log.setEnabled(true);
                    save.setEnabled(true);
                } else {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, MainFaceActivity.class);
                    //Log.e("tag","test33");
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loginOwnServer(String phone, String pwd) {
        //initialize
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("pwd", pwd);

        // send messages
        String url = ServiceUrl.getLocalLOGINUrl(MainActivity.this);

        Log.e("tag", "test0");
        asyncHttpClient.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                //in the document(json format)
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean result = jsonObject.getBoolean("success");
                String info = jsonObject.getString("info");
                Log.e("tag", "test1");

                //analyze the result
                if (result) {
                    Log.e("tag", "test2");
                    Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("tag", "test3");
                    //error
                    Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                Log.e("tag", "test4");
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
