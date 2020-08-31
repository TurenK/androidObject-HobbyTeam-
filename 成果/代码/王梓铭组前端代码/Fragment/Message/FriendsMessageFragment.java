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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.R;
import com.example.activitys.User.UserSmallItemInfo;
import com.example.activitys.User.UsersmallItemAdapter;
import com.example.model.Friends;
import com.example.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsMessageFragment extends Fragment {
    LinearLayout ll_my;
    ImageView iv_my;
    TextView tv_my;
    LinearLayout ll_join;
    ImageView iv_join;
    TextView tv_join;
    LinearLayout ll_create;
    ImageView iv_create;
    TextView tv_create;
    ListView textListView;
    SearchView fragment_message_friends_search;

    public FriendsMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_friends, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textListView = getView().findViewById(R.id.textView);
        fragment_message_friends_search = getView().findViewById(R.id.fragment_message_friends_search);

        ll_my = (LinearLayout) getView().findViewById(R.id.ll_my);
        iv_my = (ImageView) getView().findViewById(R.id.iv_my);
        tv_my = (TextView) getView().findViewById(R.id.tv_my);

        ll_join = (LinearLayout) getView().findViewById(R.id.ll_join);
        iv_join = (ImageView) getView().findViewById(R.id.iv_join);
        tv_join = (TextView) getView().findViewById(R.id.tv_join);

        ll_create = (LinearLayout) getView().findViewById(R.id.ll_create);
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

        initListView();

        initSearch();
    }

    private void analyzeQueryResult(List<UserInfo> activitiesList){
        List<UserSmallItemInfo> list = new ArrayList<>();
        if (activitiesList != null) {
            for (int i = 0; i < activitiesList.size(); i++) {
                UserSmallItemInfo temp = new UserSmallItemInfo();
                temp.setUsertags(activitiesList.get(i).getTag());
                temp.setUserheadimgUrl(activitiesList.get(i).getHeadimg());
                temp.setUseritemsmallId(activitiesList.get(i).getUserId());
                temp.setNickname(activitiesList.get(i).getNickname());
                temp.setUsername(activitiesList.get(i).getUsername());

                list.add(temp);
            }
        }

        UsersmallItemAdapter exerciseEventAdapter = new UsersmallItemAdapter(list,getActivity());
        textListView.setAdapter(exerciseEventAdapter);
        textListView.setEnabled(true);
        fragment_message_friends_search.setEnabled(true);
    }

    private void initListView() {
        textListView.setEnabled(false);
        fragment_message_friends_search.setEnabled(false);
        String currentname = BmobUser.getCurrentUser(UserInfo.class).getUsername();
        BmobQuery<Friends> friendsBmobQuery = new BmobQuery<>();
        friendsBmobQuery.addWhereEqualTo("username", currentname);
        friendsBmobQuery.findObjects(new FindListener<Friends>() {
            @Override
            public void done(List<Friends> list, BmobException e) {
                if (e == null) {
                    if (list != null && !list.isEmpty()) {
                        BmobQuery<UserInfo> userInfoBmobQuery = new BmobQuery<UserInfo>();

                        List<BmobQuery<UserInfo>> queries = new ArrayList<BmobQuery<UserInfo>>();
                        for (int i = 0; i < list.size(); i++) {
                            BmobQuery<UserInfo> temp = new BmobQuery<UserInfo>();
                                String friendname  = list.get(i).getFriendname();
                            temp.addWhereEqualTo("username", friendname);
                            queries.add(temp);
                        }
                        userInfoBmobQuery.or(queries);
                        userInfoBmobQuery.findObjects(new FindListener<UserInfo>() {
                            @Override
                            public void done(List<UserInfo> list, BmobException e) {
                                if (e == null) {
                                    if (list != null && !list.isEmpty()) {
                                        analyzeQueryResult(list);
                                    } else {
                                        Toast.makeText(getActivity(), "没有好友哦~~", LENGTH_SHORT).show();
                                        textListView.setEnabled(true);
                                        fragment_message_friends_search.setEnabled(true);
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "好友查询错误", LENGTH_SHORT).show();
                                    textListView.setEnabled(true);
                                    fragment_message_friends_search.setEnabled(true);
                                }
                            }
                        });

                    } else {
                        Toast.makeText(getActivity(), "好友查询错误", LENGTH_SHORT).show();
                        textListView.setEnabled(true);
                        fragment_message_friends_search.setEnabled(true);
                    }
                } else {
                    Toast.makeText(getActivity(), "查询失败或没有队伍", LENGTH_SHORT).show();
                    textListView.setEnabled(true);
                    fragment_message_friends_search.setEnabled(true);
                }
            }
        });
    }

    private void initSearch(){
        fragment_message_friends_search.setSubmitButtonEnabled(true);
        fragment_message_friends_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s!=null&&!s.isEmpty()){
                    textListView.setEnabled(false);
                    fragment_message_friends_search.setEnabled(false);
                    Toast.makeText(getActivity(), "loading...", Toast.LENGTH_SHORT).show();
                    BmobQuery<UserInfo> userInfoBmobQuery = new BmobQuery<UserInfo>();

                    BmobQuery<UserInfo> temp1 = new BmobQuery<UserInfo>();
                    BmobQuery<UserInfo> temp2 = new BmobQuery<UserInfo>();
                    temp1.addWhereEqualTo("nickname", s);
                    temp2.addWhereEqualTo("username",s);
                    List<BmobQuery<UserInfo>> queries = new ArrayList<BmobQuery<UserInfo>>();
                    queries.add(temp1);
                    queries.add(temp2);

                    userInfoBmobQuery.or(queries);
                    userInfoBmobQuery.findObjects(new FindListener<UserInfo>() {
                        @Override
                        public void done(List<UserInfo> list, BmobException e) {
                            if(e==null){
                                if(list!=null&&!list.isEmpty()){
                                    analyzeQueryResult(list);
                                }else{
                                    Toast.makeText(getActivity(), "查询结果为空", Toast.LENGTH_SHORT).show();
                                    textListView.setEnabled(true);
                                    fragment_message_friends_search.setEnabled(true);
                                }
                            }else{
                                Toast.makeText(getActivity(), "查询失败", Toast.LENGTH_SHORT).show();
                                textListView.setEnabled(true);
                                fragment_message_friends_search.setEnabled(true);
                            }
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "不可为空", Toast.LENGTH_SHORT).show();
                    textListView.setEnabled(true);
                    fragment_message_friends_search.setEnabled(true);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
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
