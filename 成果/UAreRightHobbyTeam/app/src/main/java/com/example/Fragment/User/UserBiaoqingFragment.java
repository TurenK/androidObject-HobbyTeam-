package com.example.Fragment.User;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.DefinedComponents.GlideImageLoader;
import com.example.activitys.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserBiaoqingFragment extends Fragment {

    LinearLayout back;
    LinearLayout Look1;
    LinearLayout Look2;
    LinearLayout Look3;
    LinearLayout Look4;
    LinearLayout Look5;
    LinearLayout Look6;
    public UserBiaoqingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_biaoqing, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        back= getView().findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserFragment());
            }
        });



        final ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.a);
        images.add(R.mipmap.b);
        images.add(R.mipmap.c);
        images.add(R.mipmap.d);

        Banner banner = (Banner) getView().findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);


        //banner设置方法全部调用完毕时最后调用
        banner.start();

        Look1 = getView().findViewById(R.id.biaoqing1);
        Look2 = getView().findViewById(R.id.biaoqing2);
        Look3 = getView().findViewById(R.id.biaoqing3);
        Look4 = getView().findViewById(R.id.biaoqing4);
        Look5 = getView().findViewById(R.id.biaoqing5);
        Look6 = getView().findViewById(R.id.biaoqing6);


        Look1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserBiaoqing1Fragment());
            }
        });

        Look2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserBiaoqing2Fragment());
            }
        });
        Look3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserBiaoqing3Fragment());
            }
        });
        Look4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserBiaoqing4Fragment());
            }
        });
        Look5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserBiaoqing5Fragment());
            }
        });
        Look6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserBiaoqing6Fragment());
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
