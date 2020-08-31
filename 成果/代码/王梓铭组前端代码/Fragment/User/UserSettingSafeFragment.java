package com.example.Fragment.User;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.activitys.LoginAndRegister.Forgetpwd;
import com.example.activitys.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserSettingSafeFragment extends Fragment {
    LinearLayout back;
    LinearLayout forget;

    public UserSettingSafeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_setting_safe, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        back= getView().findViewById(R.id.back);
        forget=getView().findViewById(R.id.forget);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserSettingFragment());
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Forgetpwd.class));
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
