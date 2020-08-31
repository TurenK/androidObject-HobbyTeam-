package com.example.activitys.LoginAndRegister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.DefinedComponents.AuthCodeView;
import com.example.activitys.R;
import com.example.model.UserInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.*;

/**
 * Created by TurenK on 2017/7/5.
 */
public class RegisterActivity extends Activity {
    private EditText userID = null;
    private EditText msgCAP = null;
    private EditText psw = null;
    private EditText repearPsw = null;
    private EditText CAP = null;
    private TextView Contract = null;
    private Button sendMsg = null;
    private Button back = null;
    private Button register = null;
    private ImageView UserEditTip = null;
    private ImageView msgCAPEditTip = null;
    private ImageView PswEditTip = null;
    private ImageView RepeatPswEditTip = null;
    private ImageView CAPEditTip = null;
    private AuthCodeView authCodeView = null;
    private boolean canUser = false;
    private boolean canPsw = false;
    private boolean canRepeatPsw = false;
    private boolean canCAP = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitFace();

        // userID changing the focus
        userID.addTextChangedListener(new TextWatcher() {
                                          @Override
                                          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              String userIDString = userID.getText().toString();
                                              if (userIDString == null || userIDString.isEmpty() || userIDString.length() != 11) {
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
                                      }
        );

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
                if (CAPString == null || CAPString.isEmpty()) {
                    CAPEditTip.setImageResource(R.mipmap.wrongtip);
                    CAPEditTip.setVisibility(ImageView.VISIBLE);
                    canCAP = false;
                } else if (!CAPString.equals(authCodeView.getAuthCode())) {
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
                } else if (!CAPString.equals(authCodeView.getAuthCode())) {
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
                } else if (!CAPString.equals(authCodeView.getAuthCode())) {
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

        // authcodeview changing
        authCodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authCodeView.changeImage();
                String CAPString = CAP.getText().toString();
                if (CAPString == null || CAPString.isEmpty() || CAPString.length() != 4) {
                    CAPEditTip.setImageResource(R.mipmap.wrongtip);
                    CAPEditTip.setVisibility(ImageView.VISIBLE);
                    canCAP = false;
                } else if (!CAPString.equals(authCodeView.getAuthCode())) {
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
                RegisterActivity.this.finish();
            }
        });

        // send the CAPCHAR
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canUser) {
                    Toast.makeText(RegisterActivity.this, "手机号输入有误", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("tag", "test1");
                    BmobSMS.requestSMSCode(userID.getText().toString(), "确定用户手机", new QueryListener<Integer>() {
                        @Override
                        public void done(Integer integer, BmobException e) {
                            if (e == null) {
                                Log.e("tag", "test2");
                                Toast.makeText(RegisterActivity.this, "短信验证码发送成功", Toast.LENGTH_SHORT).show();
                                CountDownTime countDownTime = new CountDownTime(60000, 1000, sendMsg);
                                countDownTime.start();
                            } else {
                                Log.e("tag", "test3");
                                Toast.makeText(RegisterActivity.this, "短信验证码发送失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        // turn to the contract page
        Contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, ContractActivity.class);
                RegisterActivity.this.startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judgeRegist()) {
                    String phone = userID.getText().toString();
                    String password = psw.getText().toString();
                    regCloud(phone, password);
                } else {
                    Toast.makeText(RegisterActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
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
        Contract = (TextView) findViewById(R.id.contract);
        sendMsg = (Button) findViewById(R.id.sendCAPBtn);
        back = (Button) findViewById(R.id.backBtn);
        register = (Button) findViewById(R.id.registerBtn);

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

        authCodeView = (AuthCodeView) findViewById(R.id.AuthCodeView);
    }

    // judge the register condition
    private boolean judgeRegist() {
        if (!canUser) {
            Toast.makeText(RegisterActivity.this, "手机号输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!canCAP) {
            Toast.makeText(RegisterActivity.this, "图片验证码输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!canRepeatPsw) {
            Toast.makeText(RegisterActivity.this, "密码两次输入不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!canPsw) {
            Toast.makeText(RegisterActivity.this, "密码输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void regCloud(String phone, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(phone);
        userInfo.setPassword(password);
        userInfo.setMobilePhoneNumber(phone);
        userInfo.signOrLogin(msgCAP.getText().toString(), new SaveListener<UserInfo>() {
            @Override
            public void done(UserInfo userInfo, BmobException e) {
                if (e == null) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(RegisterActivity.this,MainActivity.class);
                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "注册失败"+e.getErrorCode()+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // register impliment
    private void reg(String phone, String password) {
        //initialize
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("phone", userID.getText().toString());
        params.put("pwd", psw.getText().toString());

        //String userid = userID.getText().toString();
        //Bundle bundle = new Bundle();
        //bundle.putString("username",userid);
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        //intent.putExtras(bundle);
        startActivity(intent);

//        // send messages
//        String url = ServiceUrl.getREGUrl(RegisterActivity.this);
//
//        asyncHttpClient.post(url,params,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(String content) {
////                //in the document(json format)
////                JSONObject jsonObject = JSONObject.parseObject(content);
////                boolean result = jsonObject.getBoolean("success");
////                String info = jsonObject.getString("info");
////
////                //analyze the result
////                if(result)
////                {
////                    JSONObject object = jsonObject.getJSONObject("data");
////                    String userid = object.getString("id");
//                String userid = userID.getText().toString();
//                Bundle bundle = new Bundle();
//                    bundle.putString("username",userid);
//                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
////                }
////                else
////                {
////                    Log.e("tag","test1");
////                    //error
////                    Toast.makeText(RegisterActivity.this,info,Toast.LENGTH_SHORT).show();
////                }
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                Toast.makeText(RegisterActivity.this,content,Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
