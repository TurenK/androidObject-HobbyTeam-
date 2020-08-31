package com.example.Fragment.User;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
public class UserDetailNameFragment extends Fragment {

    TextView userDetailNameBack;
    Button userDetailNameSave;
    EditText userDetailNameText;

    public UserDetailNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail_name, container, false);
    }

    private void initUserDetailNameText() {
        String usernickname = BmobUser.getCurrentUser(UserInfo.class).getNickname();
        if (usernickname != null) {
            if (!usernickname.equals("null")) {
                userDetailNameText.setText(usernickname);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userDetailNameBack = getView().findViewById(R.id.user_detail_name_back);
        userDetailNameSave = getView().findViewById(R.id.user_detail_name_save);
        userDetailNameText = getView().findViewById(R.id.user_detail_name_text);
        initUserDetailNameText();

        userDetailNameBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailIntroduction());
            }
        });

        userDetailNameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = userDetailNameText.getText().toString();
                if (nickname == null) {
                    Toast.makeText(getActivity(), "请输入昵称", Toast.LENGTH_SHORT).show();
                } else {
                    UserInfo newUser = new UserInfo();
                    newUser.setNickname(nickname);
                    UserInfo insertItem = BmobUser.getCurrentUser(UserInfo.class);
                    newUser.update(insertItem.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "用户信息更新成功", Toast.LENGTH_SHORT).show();
                                changeContent(new UserDetailIntroduction());
                            } else {
                                Toast.makeText(getActivity(), "用户信息更新失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
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
