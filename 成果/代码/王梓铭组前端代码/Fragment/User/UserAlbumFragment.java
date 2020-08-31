package com.example.Fragment.User;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.DefinedComponents.GlideImageLoader;
import com.example.activitys.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserAlbumFragment extends Fragment {

    LinearLayout back;
    LinearLayout Album1,Album2,Album3,Album4,Album5,Album6;
    public UserAlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_album, container, false);
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

        Album1 = getView().findViewById(R.id.album1);
        Album2 = getView().findViewById(R.id.album2);
        Album3 = getView().findViewById(R.id.album3);
        Album4 = getView().findViewById(R.id.album4);
        Album5 = getView().findViewById(R.id.album5);
        Album6 = getView().findViewById(R.id.album6);


        Album1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserAlbum1Fragment());
            }
        });

        Album2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserAlbum2Fragment());
            }
        });

        Album3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserAlbum3Fragment());
            }
        });
        Album4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserAlbum4Fragment());
            }
        });
        Album5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserAlbum5Fragment());
            }
        });
        Album6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserAlbum6Fragment());
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
