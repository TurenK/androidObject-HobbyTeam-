package com.example.Fragment.Activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitys.Exercise.ExerciseEventAdapter;
import com.example.activitys.Exercise.ExerciseItemListEventInfo;
import com.example.activitys.R;
import com.example.activitys.Activity.ActivityListEventInfo;
import com.example.model.Activitys;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitySearchFragment extends Fragment {

    SearchView searchView;
    LinearLayout ll_my;
    ImageView iv_my;
    TextView tv_my;
    LinearLayout ll_join;
    ImageView iv_join;
    TextView tv_join;
    ListView team_join_listview;
    private List<ActivityListEventInfo> infolist;



    public ActivitySearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        infolist = new ArrayList<>();
        team_join_listview = getView().findViewById(R.id.team_join_listview);
        searchView = getView().findViewById(R.id.searchView);

        ll_my=(LinearLayout)getView().findViewById(R.id.ll_my);
        iv_my = (ImageView) getView().findViewById(R.id.iv_my);
        tv_my = (TextView) getView().findViewById(R.id.tv_my);

        ll_join=(LinearLayout)getView().findViewById(R.id.ll_join);
        iv_join = (ImageView) getView().findViewById(R.id.iv_join);
        tv_join = (TextView) getView().findViewById(R.id.tv_join);


        ll_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ActivityFragment());
                tv_my.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorBlack));
                tv_join.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorGray));
            }
        });

        ll_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ActivitySearchFragment());
                tv_my.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorGray));
                tv_join.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorBlack));
            }
        });

        initSearch();
    }

    private void initSearch(){
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s!=null&&!s.isEmpty()){
                    searchView.setEnabled(false);
                    Toast.makeText(getActivity(), "loading...", Toast.LENGTH_SHORT).show();
                    BmobQuery<Activitys> activitysBmobQuery = new BmobQuery<Activitys>();
                    //activitysBmobQuery.addWhereMatches("activityName", s+".*");
                    activitysBmobQuery.addWhereEqualTo("activityName", s);
                    activitysBmobQuery.findObjects(new FindListener<Activitys>() {
                        @Override
                        public void done(List<Activitys> list, BmobException e) {
                            if(e==null){
                                if(list!=null&&!list.isEmpty()){
                                    analyzeQueryResult(list);
                                    searchView.setEnabled(true);
                                }else{
                                    Toast.makeText(getActivity(), "查询结果为空", Toast.LENGTH_SHORT).show();
                                    searchView.setEnabled(true);
                                }
                            }else{
                                Toast.makeText(getActivity(), "查询失败", Toast.LENGTH_SHORT).show();
                                searchView.setEnabled(true);
                            }
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "不可为空", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
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
        team_join_listview.setAdapter(exerciseEventAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_search, container, false);
    }

    private void changeContent(Fragment fragment) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Main_Frame,fragment);
        fragmentTransaction.commit();
    }
}
