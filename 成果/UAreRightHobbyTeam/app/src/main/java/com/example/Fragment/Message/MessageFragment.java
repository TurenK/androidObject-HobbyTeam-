package com.example.Fragment.Message;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import android.widget.*;

import com.example.activitys.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    LinearLayout ll_my;
    ImageView iv_my;
    TextView tv_my;
    LinearLayout ll_join;
    ImageView iv_join;
    TextView tv_join;
    LinearLayout ll_create;
    ImageView iv_create;
    TextView tv_create;
    ListView MyListView;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi = inflater.inflate(R.layout.fragment_message, container,false);
        return vi ;
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
/*
        //绑定XML中的ListView，作为Item的容器
        MyListView =  getView().findViewById(R.id.MyListView);
        //生成动态数组，并且转载数据
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        for(int i=0;i<30;i++)
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", "This is Title.....");
            map.put("ItemText", "This is text.....");
            mylist.add(map);
        }

        //生成适配器，数组===》ListItem
        SimpleAdapter mSchedule = new SimpleAdapter(this,
                mylist,//数据来源
                R.layout.item_message,//ListItem的XML实现

                //动态数组与ListItem对应的子项
                new String[] {"ItemTitle", "ItemText"},

                //ListItem的XML文件里面的两个TextView ID
                new int[] {R.id.item_message_list_username,R.id.ll});
        //添加并且显示
        MyListView.setAdapter(mSchedule);

        */
    }

    private void changeContent(Fragment fragment) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Main_Frame,fragment);
        fragmentTransaction.commit();
    }
}
