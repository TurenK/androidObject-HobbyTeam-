package com.example.Fragment.User;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
public class UserDetailWordsFragment extends Fragment {
    EditText userDetailWordsIntro;
    TextView userDetailWordsBack;
    Button userDetailWordsSave;
    String introContent;

    public UserDetailWordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail_words, container, false);
    }

    private void initUserDetailWordsFragment(){
        String userintro = BmobUser.getCurrentUser(UserInfo.class).getIntro();
        if(userintro!=null&&!userintro.equals("null")){
        userDetailWordsIntro.setText(userintro);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userDetailWordsIntro = getView().findViewById(R.id.user_detail_words_intro);
        userDetailWordsBack = getView().findViewById(R.id.user_detail_words_back);
        userDetailWordsSave = getView().findViewById(R.id.user_detail_words_save);
        initUserDetailWordsFragment();

        userDetailWordsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserDetailIntroduction());
            }
        });
        userDetailWordsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userintro  = userDetailWordsIntro.getText().toString();
                if(userintro.isEmpty()){
                    userintro = "null";
                }
                UserInfo newUser = new UserInfo();
                newUser.setIntro(userintro);
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
        fragmentTransaction.replace(R.id.Main_Frame,fragment);
        fragmentTransaction.commit();
    }
}
