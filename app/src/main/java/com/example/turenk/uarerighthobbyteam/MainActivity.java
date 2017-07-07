package com.example.turenk.uarerighthobbyteam;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.io.*;
import android.content.*;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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

        String get=getTxtFileInfo(getApplicationContext());
        if(get!=null&&!get.equals("##"))
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
                login(phone,pwd);
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

    private void login(String phone,String pwd){
        //initialize
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("phone",phone);
        params.put("pwd",pwd);

        // send messages
        String url = ServiceUrl.getLOGINUrl(MainActivity.this);

        Log.e("tag","test5464564");
        asyncHttpClient.post("http://172.31.34.141:8080/hobby/user/login",params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                //in the document(json format)
                Log.e("tag","test334");
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean result = jsonObject.getBoolean("success");
                String info = jsonObject.getString("info");
                Log.e("tag","testsadad2");

                //analyze the result
                if(result)
                {
                    Toast.makeText(MainActivity.this,info,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //error
                    Toast.makeText(MainActivity.this,info,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                Log.e("tag","test1");
                Toast.makeText(MainActivity.this,content,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
