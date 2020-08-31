package com.example.Fragment.Message;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.activitys.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamMessageFragment extends Fragment {
    LinearLayout ll_my;
    ImageView iv_my;
    TextView tv_my;
    LinearLayout ll_join;
    ImageView iv_join;
    TextView tv_join;
    LinearLayout ll_create;
    ImageView iv_create;
    TextView tv_create;

    public TeamMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_team, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ll_my=(LinearLayout)getView().findViewById(R.id.ll_my);
        iv_my = (ImageView) getView().findViewById(R.id.iv_my);
        tv_my = (TextView) getView().findViewById(R.id.tv_my);

        ll_join=(LinearLayout)getView().findViewById(R.id.ll_join);
        iv_join = (ImageView) getView().findViewById(R.id.iv_join);
        tv_join = (TextView) getView().findViewById(R.id.tv_join);

        ll_create=(LinearLayout)getView().findViewById(R.id.ll_create);
        iv_create = (ImageView) getView().findViewById(R.id.iv_create);
        tv_create = (TextView) getView().findViewById(R.id.tv_create);

        ll_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new MessageFragment());
            }
        });

        ll_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new TeamMessageFragment());
            }
        });

        ll_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new FriendsMessageFragment());
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
