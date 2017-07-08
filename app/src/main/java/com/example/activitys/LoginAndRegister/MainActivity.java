package com.example.activitys.LoginAndRegister;

import android.app.Activity;
import android.os.Bundle;
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

public class MainActivity extends Activity {
    Button log;
    EditText ID;
    EditText password;
    TextView forget;
    TextView register;
    CheckBox save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log=(Button)findViewById(R.id.log);
        ID=(EditText)findViewById(R.id.ID);
        password=(EditText)findViewById(R.id.password);
        forget=(TextView) findViewById(R.id.forget);
        register=(TextView) findViewById(R.id.register);
        save=(CheckBox) findViewById(R.id.save);
        save.setChecked(true);

//        if(this.getIntent()!=null) {
//            String oldID = this.getIntent().getStringExtra("username");
//            if (oldID != null || !oldID.isEmpty()) {
//                ID.setText(oldID);
//            }
//        }
        Bmob.initialize(this,ServiceUrl.getAfterCloudID(MainActivity.this));

        String get=getTxtFileInfo(getApplicationContext());
        if(get!=null&&get.charAt(0)!='#'&&get.charAt(get.length()-1)!='#')
        {
            // 使用保存信息使用的##将内容分割出来
            String[] contents = get.split("##");
                ID.setText(deal(contents[0], (byte) 88));
                password.setText(deal(contents[1], (byte) 88));

        }

        //登录点击事件
        log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(save.isChecked()) {
                    saveUserInfo(getApplicationContext(),ID.getText().toString(),password.getText().toString());
                }
                String phone = ID.getText().toString();
                String pwd = password.getText().toString();
                loginCloudServer(phone,pwd);
                //Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        });
        //忘记密码事件
        forget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ForgetpwdActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        //注册用户事件
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    public static boolean saveUserInfo(Context context, String username,String password) {
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
            throw new RuntimeException();
        }
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

    static String deal(String str, byte skey){
        try {
            byte[] bytes = str.getBytes("GBK");

            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ skey);
            }

            return new String(bytes, "GBK");
        }catch (Exception e)
        {
            return null;
        }
    }

    private void loginCloudServer(String phone, String pwd){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(phone);
        userInfo.setPassword(pwd);
        userInfo.login(new SaveListener<UserInfo>() {
            @Override
            public void done(UserInfo userInfo, BmobException e) {
                if(e!=null){
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    UserInfo tempuser = UserInfo.getCurrentUser(UserInfo.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username",tempuser.getUsername());
                    bundle.putString("nickname",tempuser.getNickname());
                    bundle.putString("address",tempuser.getAddress());
                    bundle.putString("headimg",tempuser.getHeadimg());
                    bundle.putString("intimeaddr",tempuser.getIntimeaddr());
                    bundle.putString("intro",tempuser.getIntro());
                    bundle.putString("sex",tempuser.getSex());
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(MainActivity.this, MainFaceActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void loginOwnServer(String phone,String pwd){
        //initialize
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("phone",phone);
        params.put("pwd",pwd);

        // send messages
        String url = ServiceUrl.getLocalLOGINUrl(MainActivity.this);

        Log.e("tag","test0");
        asyncHttpClient.post(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                //in the document(json format)
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean result = jsonObject.getBoolean("success");
                String info = jsonObject.getString("info");
                Log.e("tag","test1");

                //analyze the result
                if(result)
                {
                    Log.e("tag","test2");
                    Toast.makeText(MainActivity.this,info,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.e("tag","test3");
                    //error
                    Toast.makeText(MainActivity.this,info,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                Log.e("tag","test4");
                Toast.makeText(MainActivity.this,content,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
