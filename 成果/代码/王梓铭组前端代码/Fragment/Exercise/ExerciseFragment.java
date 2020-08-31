package com.example.Fragment.Exercise;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.Exercise.ExerciseEventAdapter;
import com.example.activitys.Exercise.ExerciseItemListEventInfo;
import com.example.activitys.R;
import com.example.model.Activitys;
import com.example.model.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseFragment extends Fragment {

    private SearchView ivEventSearch;
    private TextView titleInterest;
    private TextView titleAll;
    private TextView titleNearby;
    private ListView lvEventlist;
    private View view;

    private void initView() {
        ivEventSearch = getView().findViewById(R.id.exercise_mainfragment_searchview);
        titleInterest =getView().findViewById(R.id.title_exercise_Interest);
        titleAll = getView().findViewById(R.id.title_exercise_All);
        titleNearby = getView().findViewById(R.id.title_exercise_Nearby);
        lvEventlist =  getView().findViewById(R.id.lv_eventlist);
    }

    private void OnclickListennersInitialized() {
        titleInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "loading...", Toast.LENGTH_SHORT).show();
                titleInterest.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                titleAll.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                titleNearby.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));

                init_Intest_Data();
            }
        });

        titleNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "loading...", Toast.LENGTH_SHORT).show();
                titleInterest.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                titleAll.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                titleNearby.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));

                init_NearBy_Data();
            }
        });

        titleAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "loading...", Toast.LENGTH_SHORT).show();
                titleInterest.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                titleAll.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                titleNearby.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));

                init_S_ALL_Data();
            }
        });

        initSearch();

        titleAll.performClick();
    }

    private void initSearch(){
        ivEventSearch.setSubmitButtonEnabled(true);
        ivEventSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s!=null&&!s.isEmpty()){
                    lvEventlist.setEnabled(false);
                    Toast.makeText(view.getContext(), "loading...", Toast.LENGTH_SHORT).show();
                    BmobQuery<Activitys> activitysBmobQuery = new BmobQuery<Activitys>();
                    //activitysBmobQuery.addWhereMatches("activityName", s+".*");
                    activitysBmobQuery.addWhereEqualTo("activityName", s);
                    activitysBmobQuery.findObjects(new FindListener<Activitys>() {
                        @Override
                        public void done(List<Activitys> list, BmobException e) {
                            if(e==null){
                                if(list!=null&&!list.isEmpty()){
                                    analyzeQueryResult(list);
                                    lvEventlist.setEnabled(true);
                                }else{
                                    Toast.makeText(view.getContext(), "查询结果为空", Toast.LENGTH_SHORT).show();
                                    lvEventlist.setEnabled(true);
                                }
                            }else{
                                Toast.makeText(view.getContext(), "查询失败", Toast.LENGTH_SHORT).show();
                                lvEventlist.setEnabled(true);
                            }
                        }
                    });
                }else{
                    Toast.makeText(view.getContext(), "不可为空", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        OnclickListennersInitialized();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercise, container, false);
        return view;
    }

    private void init_S_ALL_Data() {
        lvEventlist.setEnabled(false);
        BmobQuery<Activitys> q1 = new BmobQuery<Activitys>();
        q1.addWhereLessThanOrEqualTo("createdAt",new BmobDate(new Date()));
        q1.setLimit(10);
        q1.order("-createdAt");
        Log.e("tag", "testquery");
        q1.findObjects(new FindListener<Activitys>() {
            @Override
            public void done(List<Activitys> ary, BmobException e) {
                if (e == null) {
                    Log.e("tag", "testquerysucced");
                    analyzeQueryResult(ary);
                } else {
                    Log.e("tag", "testqueryfailed");
                    Toast.makeText(view.getContext(), "查询失败", Toast.LENGTH_SHORT).show();
                    lvEventlist.setEnabled(true);
                }
            }
        });
    }

    private void analyzeQueryResult(List<Activitys> activitiesList){
        List<ExerciseItemListEventInfo> list = new ArrayList<>();
        if (activitiesList != null) {
            for (int i = 0; i < activitiesList.size(); i++) {
                ExerciseItemListEventInfo temp = new ExerciseItemListEventInfo();
                temp.setTag(activitiesList.get(i).getTag());
                temp.setImgURL(activitiesList.get(i).getImgURL());
                temp.setCommentnum(activitiesList.get(i).getCommentnum());
                temp.setLikenum(activitiesList.get(i).getLikenum());
                temp.setActivitiesId(activitiesList.get(i).getActivitiesId());
                temp.setActivityName(activitiesList.get(i).getActivityName());
                temp.setObjectId(activitiesList.get(i).getObjectId());

                list.add(temp);
            }
        }

        ExerciseEventAdapter exerciseEventAdapter = new ExerciseEventAdapter(list,getActivity());
        lvEventlist.setAdapter(exerciseEventAdapter);
        lvEventlist.setEnabled(true);
    }

    private void init_Intest_Data() {
        lvEventlist.setEnabled(false);
        BmobQuery<Activitys> query = new BmobQuery<>();

        List<BmobQuery<Activitys>> queries = new ArrayList<BmobQuery<Activitys>>();

        List<String> tagtemp = BmobUser.getCurrentUser(UserInfo.class).getTag();
        for(int i=0;i<tagtemp.size();i++){
            BmobQuery<Activitys> q1 = new BmobQuery<>();
            List<String> temp = new ArrayList<>();
            temp.add(tagtemp.get(i));
            q1.addWhereContainsAll("tag",temp);
            queries.add(q1);
        }
        query.or(queries);
        query.setLimit(10);
        query.order("-tag,-createdAt");
        Log.e("tag", "testquery");
        query.findObjects(new FindListener<Activitys>() {
            @Override
            public void done(List<Activitys> ary, BmobException e) {
                if (e == null) {
                    Log.e("tag", "testquerysucced");
                    analyzeQueryResult(ary);
                } else {
                    Log.e("tag", "testqueryfailed");
                    Toast.makeText(view.getContext(), "查询失败", Toast.LENGTH_SHORT).show();
                    lvEventlist.setEnabled(true);
                }
            }
        });
    }

    private void init_NearBy_Data() {
        lvEventlist.setEnabled(false);
        BmobQuery<Activitys> q1 = new BmobQuery<Activitys>();
        BmobGeoPoint bmobGeoPoint = BmobUser.getCurrentUser(UserInfo.class).getAddrGeo();
        q1.addWhereNear("addrGeo",bmobGeoPoint);
        q1.setLimit(10);
        q1.order("-createdAt");
        Log.e("tag", "testquery");
        q1.findObjects(new FindListener<Activitys>() {
            @Override
            public void done(List<Activitys> ary, BmobException e) {
                if (e == null) {
                    analyzeQueryResult(ary);
                } else {
                    Log.e("tag", "testqueryfailed");
                    Toast.makeText(view.getContext(), "查询失败", Toast.LENGTH_SHORT).show();
                    lvEventlist.setEnabled(true);
                }
            }
        });
    }
}
