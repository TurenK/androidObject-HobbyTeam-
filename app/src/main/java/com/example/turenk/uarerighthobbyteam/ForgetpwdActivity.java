package com.example.turenk.uarerighthobbyteam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by TurenK on 2017/7/5.
 */
public class ForgetpwdActivity extends Activity {
    private EditText userID = null;
    private EditText msgCAP = null;
    private EditText psw = null;
    private EditText repearPsw = null;
    private EditText CAP = null;
    private Button sendMsg = null;
    private Button back = null;
    private Button register = null;
    private ImageView UserEditTip = null;
    private ImageView msgCAPEditTip = null;
    private ImageView PswEditTip = null;
    private ImageView RepeatPswEditTip = null;
    private ImageView CAPEditTip = null;
    private boolean canUser = false;
    private boolean canPsw = false;
    private boolean canMsgCAP = false;
    private boolean canRepeatPsw = false;
    private boolean canCAP = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpwd);

        InitFace();

        // userID changing the focus
        userID.addTextChangedListener(new TextWatcher() {
                                          @Override
                                          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              String userIDString = userID.getText().toString();
                                              if ( userIDString==null||userIDString.isEmpty()|| userIDString.length() < 11 || userIDString.length() > 15) {
                                                  UserEditTip.setImageResource(R.mipmap.wrongtip);
                                                  UserEditTip.setVisibility(ImageView.VISIBLE);
                                                  canUser = false;
                                              }  else {
                                                  UserEditTip.setImageResource(R.mipmap.righttip);
                                                  UserEditTip.setVisibility(ImageView.VISIBLE);
                                                  canUser = true;
                                              }
                                          }

                                          @Override
                                          public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              String userIDString = userID.getText().toString();
                                              if (userIDString == null || userIDString.isEmpty() || userIDString.length() < 11 || userIDString.length() > 15) {
                                                  UserEditTip.setImageResource(R.mipmap.wrongtip);
                                                  UserEditTip.setVisibility(ImageView.VISIBLE);
                                                  canUser = false;
                                              } else {
                                                  UserEditTip.setImageResource(R.mipmap.righttip);
                                                  UserEditTip.setVisibility(ImageView.VISIBLE);
                                                  canUser = true;
                                              }
                                          }

                                          @Override
                                          public void afterTextChanged(Editable s) {
                                              String userIDString = userID.getText().toString();
                                              if ( userIDString==null||userIDString.isEmpty()|| userIDString.length() < 11 || userIDString.length() > 15) {
                                                  UserEditTip.setImageResource(R.mipmap.wrongtip);
                                                  UserEditTip.setVisibility(ImageView.VISIBLE);
                                                  canUser = false;
                                              }  else {
                                                  UserEditTip.setImageResource(R.mipmap.righttip);
                                                  UserEditTip.setVisibility(ImageView.VISIBLE);
                                                  canUser = true;
                                              }
                                          }
                                      }
        );

        // messageCAP changing the focus
        msgCAP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String msgCAPString = msgCAP.getText().toString();
                if (msgCAPString == null || msgCAPString.isEmpty() || msgCAPString.length() != 4) {
                    msgCAPEditTip.setImageResource(R.mipmap.wrongtip);
                    msgCAPEditTip.setVisibility(ImageView.VISIBLE);
                    canMsgCAP = false;
                }
                // judge the CAPCHAR


                else {
                    msgCAPEditTip.setImageResource(R.mipmap.righttip);
                    msgCAPEditTip.setVisibility(ImageView.VISIBLE);
                    canMsgCAP = true;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String msgCAPString = msgCAP.getText().toString();
                if (msgCAPString == null || msgCAPString.isEmpty() || msgCAPString.length() != 4) {
                    msgCAPEditTip.setImageResource(R.mipmap.wrongtip);
                    msgCAPEditTip.setVisibility(ImageView.VISIBLE);
                    canMsgCAP = false;
                }
                // judge the CAPCHAR


                else {
                    msgCAPEditTip.setImageResource(R.mipmap.righttip);
                    msgCAPEditTip.setVisibility(ImageView.VISIBLE);
                    canMsgCAP = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String msgCAPString = msgCAP.getText().toString();
                if (msgCAPString == null || msgCAPString.isEmpty() || msgCAPString.length() != 4) {
                    msgCAPEditTip.setImageResource(R.mipmap.wrongtip);
                    msgCAPEditTip.setVisibility(ImageView.VISIBLE);
                    canMsgCAP = false;
                }
                // judge the CAPCHAR


                else {
                    msgCAPEditTip.setImageResource(R.mipmap.righttip);
                    msgCAPEditTip.setVisibility(ImageView.VISIBLE);
                    canMsgCAP = true;
                }
            }
        });

        // password changing the focus
        psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String oldpsw = psw.getText().toString();
                if (oldpsw == null || oldpsw.isEmpty() || oldpsw.length() < 8 || oldpsw.length() > 15) {
                    PswEditTip.setImageResource(R.mipmap.wrongtip);
                    PswEditTip.setVisibility(ImageView.VISIBLE);
                    canPsw = false;
                } else {
                    PswEditTip.setImageResource(R.mipmap.righttip);
                    PswEditTip.setVisibility(ImageView.VISIBLE);
                    canPsw = true;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String oldpsw = psw.getText().toString();
                if (oldpsw == null || oldpsw.isEmpty() || oldpsw.length() < 8 || oldpsw.length() > 15) {
                    PswEditTip.setImageResource(R.mipmap.wrongtip);
                    PswEditTip.setVisibility(ImageView.VISIBLE);
                    canPsw = false;
                } else {
                    PswEditTip.setImageResource(R.mipmap.righttip);
                    PswEditTip.setVisibility(ImageView.VISIBLE);
                    canPsw = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String oldpsw = psw.getText().toString();
                if (oldpsw == null || oldpsw.isEmpty() || oldpsw.length() < 8 || oldpsw.length() > 15) {
                    PswEditTip.setImageResource(R.mipmap.wrongtip);
                    PswEditTip.setVisibility(ImageView.VISIBLE);
                    canPsw = false;
                } else {
                    PswEditTip.setImageResource(R.mipmap.righttip);
                    PswEditTip.setVisibility(ImageView.VISIBLE);
                    canPsw = true;
                }
            }
        });

        // repeatPsw changing the focus
        repearPsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String oldpsw = psw.getText().toString();
                String newpsw = repearPsw.getText().toString();
                if (!canPsw) {
                    RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                    RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                    canRepeatPsw = false;
                } else if (newpsw == null || newpsw.isEmpty() || !oldpsw.equals(newpsw)
                        || newpsw.length() < 8 || newpsw.length() > 15) {
                    RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                    RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                    canRepeatPsw = false;
                } else {
                    RepeatPswEditTip.setImageResource(R.mipmap.righttip);
                    RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                    canRepeatPsw = true;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String oldpsw = psw.getText().toString();
                String newpsw = repearPsw.getText().toString();
                if (!canPsw) {
                    RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                    RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                    canRepeatPsw = false;
                } else if (newpsw == null || newpsw.isEmpty() || !oldpsw.equals(newpsw)
                        || newpsw.length() < 8 || newpsw.length() > 15) {
                    RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                    RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                    canRepeatPsw = false;
                } else {
                    RepeatPswEditTip.setImageResource(R.mipmap.righttip);
                    RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                    canRepeatPsw = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String oldpsw = psw.getText().toString();
                String newpsw = repearPsw.getText().toString();
                if (!canPsw) {
                    RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                    RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                    canRepeatPsw = false;
                } else if (newpsw == null || newpsw.isEmpty() || !oldpsw.equals(newpsw)
                        || newpsw.length() < 8 || newpsw.length() > 15) {
                    RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                    RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                    canRepeatPsw = false;
                } else {
                    RepeatPswEditTip.setImageResource(R.mipmap.righttip);
                    RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                    canRepeatPsw = true;
                }
            }
        });

        // CAP changing the focus
        CAP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String CAPString = CAP.getText().toString();
                if (CAPString == null || CAPString.isEmpty() || CAPString.length() != 4) {
                    CAPEditTip.setImageResource(R.mipmap.wrongtip);
                    CAPEditTip.setVisibility(ImageView.VISIBLE);
                    canCAP = false;
                } else {
                    CAPEditTip.setImageResource(R.mipmap.righttip);
                    CAPEditTip.setVisibility(ImageView.VISIBLE);
                    canCAP = true;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String CAPString = CAP.getText().toString();
                if (CAPString == null || CAPString.isEmpty() || CAPString.length() != 4) {
                    CAPEditTip.setImageResource(R.mipmap.wrongtip);
                    CAPEditTip.setVisibility(ImageView.VISIBLE);
                    canCAP = false;
                } else {
                    CAPEditTip.setImageResource(R.mipmap.righttip);
                    CAPEditTip.setVisibility(ImageView.VISIBLE);
                    canCAP = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String CAPString = CAP.getText().toString();
                if (CAPString == null || CAPString.isEmpty() || CAPString.length() != 4) {
                    CAPEditTip.setImageResource(R.mipmap.wrongtip);
                    CAPEditTip.setVisibility(ImageView.VISIBLE);
                    canCAP = false;
                } else {
                    CAPEditTip.setImageResource(R.mipmap.righttip);
                    CAPEditTip.setVisibility(ImageView.VISIBLE);
                    canCAP = true;
                }
            }
        });

        // back to the last page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetpwdActivity.this.finish();
            }
        });

        // send the CAPCHAR
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canUser) {
                    Toast.makeText(ForgetpwdActivity.this, "手机号输入有误", Toast.LENGTH_SHORT).show();
                } else {
                    if (SendMsg()) {
                        CountDownTime countDownTime = new CountDownTime(60000, 1000, sendMsg);
                        countDownTime.start();
                    } else {
                        Toast.makeText(ForgetpwdActivity.this, "发送验证码失败", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(judgeRegist()){
                    String phone = userID.getText().toString();
                    String password = psw.getText().toString();

                    submit(phone,password);
                }else{
                    Toast.makeText(ForgetpwdActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // initialize the face
    protected void InitFace() {
        userID = (EditText) findViewById(R.id.UserEdit);
        msgCAP = (EditText) findViewById(R.id.msgCAP);
        psw = (EditText) findViewById(R.id.passwordEdit);
        repearPsw = (EditText) findViewById(R.id.RepeatPswEdit);
        CAP = (EditText) findViewById(R.id.CAPEdit);
        sendMsg = (Button) findViewById(R.id.sendCAPBtn);
        back = (Button) findViewById(R.id.backBtn);
        register = (Button) findViewById(R.id.submitBTN);

        UserEditTip = (ImageView) findViewById(R.id.UserEditTip);
        UserEditTip.setVisibility(ImageView.INVISIBLE);

        msgCAPEditTip = (ImageView) findViewById(R.id.msgCAPEditTip);
        msgCAPEditTip.setVisibility(ImageView.INVISIBLE);

        PswEditTip = (ImageView) findViewById(R.id.PswEditTip);
        PswEditTip.setVisibility(ImageView.INVISIBLE);

        RepeatPswEditTip = (ImageView) findViewById(R.id.RepeatPswEditTip);
        RepeatPswEditTip.setVisibility(ImageView.INVISIBLE);

        CAPEditTip = (ImageView) findViewById(R.id.CAPEditTip);
        CAPEditTip.setVisibility(ImageView.INVISIBLE);
    }

    // send message interface
    private boolean SendMsg() {
        return true;
    }

    // judge the register condition
    private boolean judgeRegist(){
        if(!canUser){
            Toast.makeText(ForgetpwdActivity.this, "手机号输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!canCAP){
            Toast.makeText(ForgetpwdActivity.this, "验证码输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!canRepeatPsw){
            Toast.makeText(ForgetpwdActivity.this, "密码两次输入不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!canPsw){
            Toast.makeText(ForgetpwdActivity.this, "密码输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!canMsgCAP){
            Toast.makeText(ForgetpwdActivity.this, "短信验证码输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // register impliment
    private void submit(String phone,String password){
        //initialize
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("phone",phone);
        params.put("pwd",password);

        // send messages
        String url = ServiceUrl.getREGUrl(ForgetpwdActivity.this);

        asyncHttpClient.post("http://172.31.34.141:8080/hobby/user/reg", params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                //in the document(json format)
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean result = jsonObject.getBoolean("success");
                String info = jsonObject.getString("info");

                //analyze the result
                if(result)
                {
                    JSONObject object = jsonObject.getJSONObject("data");
                    String userid = object.getString("id");
                    Intent intent = new Intent(ForgetpwdActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    //error
                    Toast.makeText(ForgetpwdActivity.this,info,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                Toast.makeText(ForgetpwdActivity.this,"服务器接口调用失败,"+content,Toast.LENGTH_SHORT).show();
            }
        });
    }
}