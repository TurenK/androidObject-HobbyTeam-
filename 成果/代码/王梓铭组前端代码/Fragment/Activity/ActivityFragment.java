package com.example.Fragment.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.activitys.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class   ActivityFragment extends Fragment {

    @InjectView(R.id.activity_mine)
    LinearLayout activityMine;
    @InjectView(R.id.activity_search)
    LinearLayout activitySearch;
    @InjectView(R.id.activity_created)
    LinearLayout activityCreated;
    @InjectView(R.id.activity_joined)
    LinearLayout activityJoined;
    @InjectView(R.id.activity_collected)
    LinearLayout activityCollected;
    @InjectView(R.id.activity_reported)
    LinearLayout activityReported;
    @InjectView(R.id.activity_mine_text)
    TextView activityMineText;
    @InjectView(R.id.activity_search_text)
    TextView activitySearchText;

    public ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi = inflater.inflate(R.layout.fragment_activity, container, false);
        ButterKnife.inject(this, vi);
        return vi;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activityMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ActivityFragment());
                activityMineText.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorBlack));
                activitySearchText.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorGray));
            }
        });

        activitySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ActivitySearchFragment());
                activityMineText.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorGray));
                activitySearchText.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorBlack));
            }
        });


        activityCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ActivityListCreateFragment());
            }
        });

        activityJoined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ActivityListJoininFragment());
            }
        });

        activityCollected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ActivityListCollectFragment());
            }
        });

        activityReported.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new ActivityListReportFragment());
            }
        });
    }

    private void changeContent(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Main_Frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
