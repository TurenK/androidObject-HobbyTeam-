package com.example.Fragment.Activity;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.activitys.Activity.ActivityListEventAdapter;
import com.example.activitys.R;
import com.example.activitys.Activity.ActivityListEventInfo;
import com.example.model.Activitys;
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
public class ActivityListCreateFragment extends Fragment {


    private ListView teamListview;
    private Button backBtn;

    public ActivityListCreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        teamListview = getView().findViewById(R.id.team_listview);
        backBtn = getView().findViewById(R.id.backBtn);

        initData();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ActivityFragment());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_list_create, container, false);
    }

    private void initData(){
        BmobQuery<Activitys> activitysBmobQuery = new BmobQuery<Activitys>();

        List<BmobQuery<Activitys>> bmobQueryList = new ArrayList<>();

        List<String> stringList = BmobUser.getCurrentUser(UserInfo.class).getCreatedTeamIds();
        if(stringList!=null&&!stringList.isEmpty()) {
            for (int i = 0; i < stringList.size(); i++) {
                BmobQuery<Activitys> qtemp = new BmobQuery<Activitys>();
                qtemp.addWhereEqualTo("objectId", stringList.get(i));
                bmobQueryList.add(qtemp);
            }
        }
        activitysBmobQuery.or(bmobQueryList);
        activitysBmobQuery.findObjects(new FindListener<Activitys>() {
            @Override
            public void done(List<Activitys> list, BmobException e) {
                if(e==null){
                    if(list!=null&&!list.isEmpty()){
                        analyzeRe(list);
                    }else{
                        Toast.makeText(getActivity(),"没有创建的队伍", LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"查询失败或没有队伍", LENGTH_SHORT).show();
                }
            }
        });
    }

    private void analyzeRe(List<Activitys> list){
        List<ActivityListEventInfo> infolist = new ArrayList<>();
        for(int i=0;i<list.size();i++) {
            ActivityListEventInfo activityListEventInfo = new ActivityListEventInfo();
            activityListEventInfo.setId(list.get(i).getActivitiesId());
            activityListEventInfo.setActivityImgURL(list.get(i).getImgURL());
            activityListEventInfo.setActivityName(list.get(i).getActivityName());
            activityListEventInfo.setActivityTime(list.get(i).getActiivtiesTime());
            activityListEventInfo.setOwnerObjectId(list.get(i).getOwenerId());
            activityListEventInfo.setMaxPeople(list.get(i).getMaxMember());
            int peoplenum = 0;
            if(list.get(i).getMemberIds()!=null&&!list.get(i).getMemberIds().isEmpty()){
                peoplenum= list.get(i).getMemberIds().size();
            }
            activityListEventInfo.setCurrentPeople(String.valueOf(peoplenum));
            activityListEventInfo.setActivityObjectId(list.get(i).getObjectId());
            infolist.add(activityListEventInfo);
        }

        ActivityListEventAdapter activityListEventAdapter = new ActivityListEventAdapter(infolist,getActivity());
        teamListview.setAdapter(activityListEventAdapter);
    }


    private void changeContent(android.app.Fragment fragment) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Main_Frame,fragment);
        fragmentTransaction.commit();
    }

}
