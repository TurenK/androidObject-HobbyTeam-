package com.example.Fragment.User;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.R;
import com.example.model.UserInfo;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailSexFragment extends Fragment {

    TextView userDetailSexBack;
    ImageView userDetailSexSave;
    RadioGroup userDetailSexChoosebox;
    RadioButton userDetailSexChooseboxMale;
    RadioButton userDetailSexChooseboxFemale;
    RadioButton userDetailSexChooseboxOther;
    String checkedGender;
    String checkedgenderEn = "null";

    public UserDetailSexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail_sex, container, false);
    }

    private void initUserDetailSexFragment() {
        userDetailSexBack = getView().findViewById(R.id.user_detail_sex_back);
        userDetailSexSave = getView().findViewById(R.id.user_detail_sex_save);
        userDetailSexChoosebox = getView().findViewById(R.id.user_detail_sex_choosebox);
        userDetailSexChooseboxFemale = getView().findViewById(R.id.user_detail_sex_choosebox_female);
        userDetailSexChooseboxMale = getView().findViewById(R.id.user_detail_sex_choosebox_male);
        userDetailSexChooseboxOther = getView().findViewById(R.id.user_detail_sex_choosebox_other);

        String usergender = BmobUser.getCurrentUser(UserInfo.class).getSex();
        if (usergender == null) {
            userDetailSexChoosebox.check(userDetailSexChooseboxOther.getId());
        }else if(usergender.equals("male")){
            userDetailSexChoosebox.check(userDetailSexChooseboxMale.getId());
        }else if(usergender.equals("female")){
            userDetailSexChoosebox.check(userDetailSexChooseboxFemale.getId());
        }else{
            userDetailSexChoosebox.check(userDetailSexChooseboxOther.getId());
        }

        RadioButton rb = getActivity().findViewById(userDetailSexChoosebox.getCheckedRadioButtonId());
        checkedGender = rb.getText().toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUserDetailSexFragment();

        userDetailSexBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailIntroduction());
            }
        });

        userDetailSexChoosebox.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int checkedID = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = getView().findViewById(checkedID);
                String checkedRB = rb.getText().toString();
                checkedGender = checkedRB;
            }
        });

        userDetailSexSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkedGender==null||checkedGender.equals("null")||checkedGender.equals("其他")){
                    checkedgenderEn = "null";
                }else if(checkedGender.equals("男")){
                    checkedgenderEn = "male";
                }else if(checkedGender.equals("女")){
                    checkedgenderEn = "female";
                }else {
                    checkedgenderEn = "null";
                }

                UserInfo newUser = new UserInfo();
                newUser.setSex(checkedgenderEn);
                UserInfo insertItem = BmobUser.getCurrentUser(UserInfo.class);
                newUser.update(insertItem.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(getActivity(), "用户信息更新成功", Toast.LENGTH_SHORT).show();
                            Log.e("tag","userdetailsex succed");
                            changeContent(new UserDetailIntroduction());
                        }else{
                            Toast.makeText(getActivity(), "用户信息更新失败"+e.getErrorCode()+e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("tag","userdetailsex failed"+e.getErrorCode()+e.getMessage());
                        }
                    }
                });
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
