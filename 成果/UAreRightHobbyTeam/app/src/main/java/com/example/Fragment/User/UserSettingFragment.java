package com.example.Fragment.User;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.activitys.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserSettingFragment extends Fragment {

    LinearLayout back;
    LinearLayout safe;
    LinearLayout message;
    LinearLayout personal;
    LinearLayout about;
    LinearLayout general;
    public UserSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_setting, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        back= getView().findViewById(R.id.back);
        safe= getView().findViewById(R.id.safe);
        message= getView().findViewById(R.id.message);
        personal= getView().findViewById(R.id.personal);
        general= getView().findViewById(R.id.general);
        about= getView().findViewById(R.id.about);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserFragment());
            }
        });
        safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserSettingSafeFragment());
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserSettingMessageFragment());
            }
        });
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserSettingPersonnalFragment());
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserSettingGeneralFragment());
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
