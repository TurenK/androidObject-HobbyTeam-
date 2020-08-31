package com.example.Fragment.User;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.activitys.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserSettingPersonnalFragment extends Fragment {


    LinearLayout back;
    CheckBox checkbox1;
    CheckBox checkbox2;
    CheckBox checkbox3;
    public UserSettingPersonnalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_setting_personnal, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        back= getView().findViewById(R.id.back);
        checkbox1= getView().findViewById(R.id.checkbox1);
        checkbox2= getView().findViewById(R.id.checkbox2);
        checkbox3= getView().findViewById(R.id.checkbox3);
        checkbox1.setChecked(false);
        checkbox2.setChecked(true);
        checkbox3.setChecked(true);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserSettingFragment());
            }
        });
        checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox1.isChecked())
                {
                    Toast.makeText(getActivity(), "开启成功", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getActivity(), "取消成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox2.isChecked())
                {
                    Toast.makeText(getActivity(), "开启成功", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getActivity(), "取消成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox3.isChecked())
                {
                    Toast.makeText(getActivity(), "开启成功", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getActivity(), "取消成功", Toast.LENGTH_SHORT).show();
                }
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
