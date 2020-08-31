package com.example.activitys.LoginAndRegister;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.DefinedComponents.AuthCodeView;
import com.example.activitys.R;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class Forgetpwd extends Activity {

    private EditText passwordEdit;
    private EditText CAPEdit;
    private EditText RepeatPswEdit;
    private ImageView PswEditTip;
    private ImageView RepeatPswEditTip;
    private ImageView CAPEditTip;
    private AuthCodeView authCodeView;
    private boolean canUser = false;
    private boolean canPsw = false;
    private boolean canRepeatPsw = false;
    private boolean canCAP = false;
    private CountDownTime countDownTime;
    private Button backBtn;
    private Button submitBTN;
    private Button sendCAPBtn;
    private EditText UserEdit;
    private ImageView UserEditTip;
    private EditText msgCAP;

    private void initData() {
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        CAPEdit = (EditText) findViewById(R.id.CAPEdit);
        RepeatPswEdit = (EditText) findViewById(R.id.RepeatPswEdit);
        PswEditTip = (ImageView) findViewById(R.id.PswEditTip);
        RepeatPswEditTip = (ImageView) findViewById(R.id.RepeatPswEditTip);
        CAPEditTip = (ImageView) findViewById(R.id.CAPEditTip);
        authCodeView = (AuthCodeView) findViewById(R.id.AuthCodeView);
        backBtn = (Button) findViewById(R.id.backBtn);
        submitBTN = (Button) findViewById(R.id.submitBTN);
        sendCAPBtn = (Button) findViewById(R.id.sendCAPBtn);
        UserEdit = (EditText) findViewById(R.id.UserEdit);
        UserEditTip = (ImageView) findViewById(R.id.UserEditTip);
        msgCAP = (EditText) findViewById(R.id.msgCAP);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpwd);
        initData();

        // UserEdit changing the focus
        passwordEdit.addTextChangedListener(new TextWatcher() {
                                                @Override
                                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                                    String UserEditString = passwordEdit.getText().toString();
                                                    if (UserEditString == null || UserEditString.isEmpty()
                                                            || UserEditString.length() < 8 || UserEditString.length() > 12) {
                                                        PswEditTip.setImageResource(R.mipmap.wrongtip);
                                                        PswEditTip.setVisibility(ImageView.VISIBLE);
                                                        canPsw = false;
                                                        RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                                                        RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                                                        canRepeatPsw = false;
                                                    } else {
                                                        PswEditTip.setImageResource(R.mipmap.righttip);
                                                        PswEditTip.setVisibility(ImageView.VISIBLE);
                                                        canPsw = true;
                                                    }
                                                }

                                                @Override
                                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                    String UserEditString = passwordEdit.getText().toString();
                                                    if (UserEditString == null || UserEditString.isEmpty()
                                                            || UserEditString.length() < 8 || UserEditString.length() > 12) {
                                                        PswEditTip.setImageResource(R.mipmap.wrongtip);
                                                        PswEditTip.setVisibility(ImageView.VISIBLE);
                                                        canPsw = false;
                                                        RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                                                        RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                                                        canRepeatPsw = false;
                                                    } else {
                                                        PswEditTip.setImageResource(R.mipmap.righttip);
                                                        PswEditTip.setVisibility(ImageView.VISIBLE);
                                                        canPsw = true;
                                                    }
                                                }

                                                @Override
                                                public void afterTextChanged(Editable s) {
                                                    String UserEditString = passwordEdit.getText().toString();
                                                    if (UserEditString == null || UserEditString.isEmpty()
                                                            || UserEditString.length() < 8 || UserEditString.length() > 12) {
                                                        PswEditTip.setImageResource(R.mipmap.wrongtip);
                                                        PswEditTip.setVisibility(ImageView.VISIBLE);
                                                        canPsw = false;
                                                        RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                                                        RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                                                        canRepeatPsw = false;
                                                    } else {
                                                        PswEditTip.setImageResource(R.mipmap.righttip);
                                                        PswEditTip.setVisibility(ImageView.VISIBLE);
                                                        canPsw = true;
                                                    }
                                                }
                                            }
        );

        // UserEdit changing the focus
        RepeatPswEdit.addTextChangedListener(new TextWatcher() {
                                                 @Override
                                                 public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                                     String UserEditString = RepeatPswEdit.getText().toString();
                                                     if (UserEditString == null || UserEditString.isEmpty()
                                                             || UserEditString.length() < 8 || UserEditString.length() > 12
                                                             || !UserEditString.equals(passwordEdit.getText().toString())) {
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
                                                     String UserEditString = RepeatPswEdit.getText().toString();
                                                     if (UserEditString == null || UserEditString.isEmpty()
                                                             || UserEditString.length() < 8 || UserEditString.length() > 12
                                                             || !UserEditString.equals(passwordEdit.getText().toString())) {
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
                                                     String UserEditString = RepeatPswEdit.getText().toString();
                                                     if (UserEditString == null || UserEditString.isEmpty()
                                                             || UserEditString.length() < 8 || UserEditString.length() > 12
                                                             || !UserEditString.equals(passwordEdit.getText().toString())) {
                                                         RepeatPswEditTip.setImageResource(R.mipmap.wrongtip);
                                                         RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                                                         canRepeatPsw = false;
                                                     } else {
                                                         RepeatPswEditTip.setImageResource(R.mipmap.righttip);
                                                         RepeatPswEditTip.setVisibility(ImageView.VISIBLE);
                                                         canRepeatPsw = true;
                                                     }
                                                 }
                                             }
        );

        // authcodeview changing
        authCodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authCodeView.changeImage();
                String CAPString = CAPEdit.getText().toString();
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

        // UserEdit changing the focus
        CAPEdit.addTextChangedListener(new TextWatcher() {
                                           @Override
                                           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               String UserEditString = CAPEdit.getText().toString();
                                               String CAPREAL = authCodeView.getAuthCode();
                                               if (UserEditString == null || UserEditString.isEmpty()
                                                       || UserEditString.length() != 4 || !CAPREAL.equals(CAPEdit.getText().toString())) {
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
                                               String UserEditString = CAPEdit.getText().toString();
                                               String CAPREAL = authCodeView.getAuthCode();
                                               if (UserEditString == null || UserEditString.isEmpty()
                                                       || UserEditString.length() != 4 || !CAPREAL.equals(CAPEdit.getText().toString())) {
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
                                               String UserEditString = CAPEdit.getText().toString();
                                               String CAPREAL = authCodeView.getAuthCode();
                                               if (UserEditString == null || UserEditString.isEmpty()
                                                       || UserEditString.length() != 4 || !CAPREAL.equals(CAPEdit.getText().toString())) {
                                                   CAPEditTip.setImageResource(R.mipmap.wrongtip);
                                                   CAPEditTip.setVisibility(ImageView.VISIBLE);
                                                   canCAP = false;
                                               } else {
                                                   CAPEditTip.setImageResource(R.mipmap.righttip);
                                                   CAPEditTip.setVisibility(ImageView.VISIBLE);
                                                   canCAP = true;
                                               }
                                           }
                                       }
        );
        // UserEdit changing the focus
        UserEdit.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                                String UserEditString = UserEdit.getText().toString();
                                                if (UserEditString == null || UserEditString.isEmpty() || UserEditString.length() < 11 || UserEditString.length() > 15) {
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
                                                String UserEditString = UserEdit.getText().toString();
                                                if (UserEditString == null || UserEditString.isEmpty() || UserEditString.length() < 11 || UserEditString.length() > 15) {
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
                                                String UserEditString = UserEdit.getText().toString();
                                                if (UserEditString == null || UserEditString.isEmpty() || UserEditString.length() < 11 || UserEditString.length() > 15) {
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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forgetpwd.this.finish();
            }
        });

        sendCAPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!canUser) {
                    Toast.makeText(Forgetpwd.this, "手机号输入有误", Toast.LENGTH_SHORT).show();
                } else {
                    BmobQuery<BmobUser> query = new BmobQuery<BmobUser>();
                    query.addWhereEqualTo("username", UserEdit.getText().toString());
                    query.findObjects(new FindListener<BmobUser>() {
                        @Override
                        public void done(List<BmobUser> object, BmobException e) {
                            if (e == null) {
                                BmobSMS.requestSMSCode(UserEdit.getText().toString(), "确定用户手机", new QueryListener<Integer>() {
                                    @Override
                                    public void done(Integer integer, BmobException e) {
                                        Log.e("tag", "test1");
                                        if (e == null) {
                                            Toast.makeText(Forgetpwd.this, "发送成功", Toast.LENGTH_SHORT).show();
                                            countDownTime = new CountDownTime(60000, 1000, sendCAPBtn);
                                            countDownTime.start();
                                        } else {
                                            Toast.makeText(Forgetpwd.this, "发送失败:" + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(Forgetpwd.this, "用户不存在", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!judgeRegist()) {
                } else {
                    BmobUser.resetPasswordBySMSCode(msgCAP.getText().toString(), passwordEdit.getText().toString(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(Forgetpwd.this, "修改成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(Forgetpwd.this, MainActivity.class);
                                Forgetpwd.this.finish();
                            } else {
                                Toast.makeText(Forgetpwd.this, "修改失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean judgeRegist() {
        if (!canCAP) {
            Toast.makeText(Forgetpwd.this, "验证码输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!canRepeatPsw) {
            Toast.makeText(Forgetpwd.this, "密码两次输入不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!canPsw) {
            Toast.makeText(Forgetpwd.this, "密码输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!canUser) {
            Toast.makeText(Forgetpwd.this, "手机输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
