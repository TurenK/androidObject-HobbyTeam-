package com.example.Fragment.User;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.activitys.R;
import com.example.model.UserInfo;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailTagFragment extends Fragment {

    ViewHolder viewHolder;
    Button userDetailTagSubmitBTN;
    private ArrayList<String> tags;
    private boolean choise[];
    final static int tagnum = 5;

    public UserDetailTagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_detail_tag, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userDetailTagSubmitBTN = getView().findViewById(R.id.user_detail_tag_submitBTN);
        tags = new ArrayList<>();
        choise = new boolean[48];
        viewHolder = new ViewHolder(getView());


        userDetailTagSubmitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tag", "userdetailtagfragment");
                if (tags == null || tags.isEmpty()) {
                    Toast.makeText(getActivity(), "不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    UserInfo newUser = new UserInfo();
                    newUser.setTag(tags);
                    UserInfo insertItem = BmobUser.getCurrentUser(UserInfo.class);
                    newUser.update(insertItem.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "用户信息更新成功", Toast.LENGTH_SHORT).show();
                                Log.e("tag", "userdetailsex succed");
                                changeContent(new UserDetailIntroduction());
                            } else {
                                Toast.makeText(getActivity(), "用户信息更新失败" + e.getErrorCode() + e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("tag", "userdetailsex failed" + e.getErrorCode() + e.getMessage());
                            }
                        }
                    });
                }
            }
        });
//        viewHolder.userDetailFragmentTagBackbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                changeContent(new UserDetailIntroduction());
//            }
//        });
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

    @OnClick({R.id.user_detail_fragment_tag_backbtn, R.id.titleBar, R.id.tag1, R.id.tag2, R.id.tag3, R.id.tag4, R.id.tag5, R.id.tag6, R.id.tag7, R.id.tag8, R.id.tag9, R.id.tag10, R.id.tag11, R.id.tag12, R.id.tag13, R.id.tag14, R.id.tag15, R.id.tag16, R.id.tag17, R.id.tag18, R.id.tag19, R.id.tag20, R.id.tag21, R.id.tag22, R.id.tag23, R.id.tag24, R.id.tag25, R.id.tag26, R.id.tag27, R.id.tag28, R.id.tag29, R.id.tag30, R.id.tag31, R.id.tag32, R.id.tag33, R.id.tag34, R.id.tag35, R.id.tag36, R.id.tag37, R.id.tag38, R.id.tag39, R.id.tag40, R.id.tag41, R.id.tag42, R.id.tag43, R.id.tag44, R.id.tag45, R.id.tag46, R.id.tag47, R.id.tag48})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_detail_fragment_tag_backbtn: {
                changeContent(new UserDetailIntroduction());
                break;
            }
            case R.id.titleBar:
                break;
            case R.id.tag1: {
                if (tags.size() >= tagnum && !choise[1]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[1]) {
                        String tagtemp = viewHolder.tag1.getText().toString();
                        tags.add(tagtemp);
                        choise[1] = true;
                        viewHolder.tag1.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag1.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag1.getText().toString();
                        tags.remove(tagtemp);
                        choise[1] = false;
                        viewHolder.tag1.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag1.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
            }
            break;
            case R.id.tag2: {
                if (tags.size() >= tagnum && !choise[2]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[2]) {
                        String tagtemp = viewHolder.tag2.getText().toString();
                        tags.add(tagtemp);
                        choise[2] = true;
                        viewHolder.tag2.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag2.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag2.getText().toString();
                        tags.remove(tagtemp);
                        choise[2] = false;
                        viewHolder.tag2.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag2.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
            }
            break;
            case R.id.tag3: {
                if (tags.size() >= tagnum && !choise[3]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[3]) {
                        String tagtemp = viewHolder.tag3.getText().toString();
                        tags.add(tagtemp);
                        choise[3] = true;
                        viewHolder.tag3.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag3.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag3.getText().toString();
                        tags.remove(tagtemp);
                        choise[3] = false;
                        viewHolder.tag3.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag3.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
            }
            break;
            case R.id.tag4: {
                if (tags.size() >= tagnum && !choise[4]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[4]) {
                        String tagtemp = viewHolder.tag4.getText().toString();
                        tags.add(tagtemp);
                        choise[4] = true;
                        viewHolder.tag4.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag4.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag4.getText().toString();
                        tags.remove(tagtemp);
                        choise[4] = false;
                        viewHolder.tag4.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag4.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
            }
            break;
            case R.id.tag5: {
                if (tags.size() >= tagnum && !choise[5]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[5]) {
                        String tagtemp = viewHolder.tag5.getText().toString();
                        tags.add(tagtemp);
                        choise[5] = true;
                        viewHolder.tag5.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag5.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag5.getText().toString();
                        tags.remove(tagtemp);
                        choise[5] = false;
                        viewHolder.tag5.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag5.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
            }
            break;
            case R.id.tag6: {
                if (tags.size() >= tagnum && !choise[6]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[6]) {
                        String tagtemp = viewHolder.tag6.getText().toString();
                        tags.add(tagtemp);
                        choise[6] = true;
                        viewHolder.tag6.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag6.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag6.getText().toString();
                        tags.remove(tagtemp);
                        choise[6] = false;
                        viewHolder.tag6.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag6.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
            }
            break;
            case R.id.tag7:
                break;
            case R.id.tag8:
                break;
            case R.id.tag9:
                break;
            case R.id.tag10:
                break;
            case R.id.tag11:
                break;
            case R.id.tag12:
                break;
            case R.id.tag13:
                break;
            case R.id.tag14:
                break;
            case R.id.tag15:
                break;
            case R.id.tag16:
                break;
            case R.id.tag17:
                break;
            case R.id.tag18:
                break;
            case R.id.tag19:
                break;
            case R.id.tag20:
                break;
            case R.id.tag21:
                break;
            case R.id.tag22:
                break;
            case R.id.tag23:
                break;
            case R.id.tag24:
                break;
            case R.id.tag25:
                break;
            case R.id.tag26:
                break;
            case R.id.tag27:
                break;
            case R.id.tag28:
                break;
            case R.id.tag29:
                break;
            case R.id.tag30:
                break;
            case R.id.tag31:
                break;
            case R.id.tag32:
                break;
            case R.id.tag33:
                break;
            case R.id.tag34:
                break;
            case R.id.tag35:
                break;
            case R.id.tag36:
                break;
            case R.id.tag37:
                break;
            case R.id.tag38:
                break;
            case R.id.tag39:
                break;
            case R.id.tag40:
                break;
            case R.id.tag41:
                break;
            case R.id.tag42:
                break;
            case R.id.tag43:
                break;
            case R.id.tag44:
                break;
            case R.id.tag45:
                break;
            case R.id.tag46:
                break;
            case R.id.tag47:
                break;
            case R.id.tag48:
                break;
        }
    }

    static class ViewHolder {
        @InjectView(R.id.user_detail_fragment_tag_backbtn)
        Button userDetailFragmentTagBackbtn;
        @InjectView(R.id.titleBar)
        LinearLayout titleBar;
        @InjectView(R.id.tag1)
        Button tag1;
        @InjectView(R.id.tag2)
        Button tag2;
        @InjectView(R.id.tag3)
        Button tag3;
        @InjectView(R.id.tag4)
        Button tag4;
        @InjectView(R.id.tag5)
        Button tag5;
        @InjectView(R.id.tag6)
        Button tag6;
        @InjectView(R.id.tag7)
        Button tag7;
        @InjectView(R.id.tag8)
        Button tag8;
        @InjectView(R.id.tag9)
        Button tag9;
        @InjectView(R.id.tag10)
        Button tag10;
        @InjectView(R.id.tag11)
        Button tag11;
        @InjectView(R.id.tag12)
        Button tag12;
        @InjectView(R.id.tag13)
        Button tag13;
        @InjectView(R.id.tag14)
        Button tag14;
        @InjectView(R.id.tag15)
        Button tag15;
        @InjectView(R.id.tag16)
        Button tag16;
        @InjectView(R.id.tag17)
        Button tag17;
        @InjectView(R.id.tag18)
        Button tag18;
        @InjectView(R.id.tag19)
        Button tag19;
        @InjectView(R.id.tag20)
        Button tag20;
        @InjectView(R.id.tag21)
        Button tag21;
        @InjectView(R.id.tag22)
        Button tag22;
        @InjectView(R.id.tag23)
        Button tag23;
        @InjectView(R.id.tag24)
        Button tag24;
        @InjectView(R.id.tag25)
        Button tag25;
        @InjectView(R.id.tag26)
        Button tag26;
        @InjectView(R.id.tag27)
        Button tag27;
        @InjectView(R.id.tag28)
        Button tag28;
        @InjectView(R.id.tag29)
        Button tag29;
        @InjectView(R.id.tag30)
        Button tag30;
        @InjectView(R.id.tag31)
        Button tag31;
        @InjectView(R.id.tag32)
        Button tag32;
        @InjectView(R.id.tag33)
        Button tag33;
        @InjectView(R.id.tag34)
        Button tag34;
        @InjectView(R.id.tag35)
        Button tag35;
        @InjectView(R.id.tag36)
        Button tag36;
        @InjectView(R.id.tag37)
        Button tag37;
        @InjectView(R.id.tag38)
        Button tag38;
        @InjectView(R.id.tag39)
        Button tag39;
        @InjectView(R.id.tag40)
        Button tag40;
        @InjectView(R.id.tag41)
        Button tag41;
        @InjectView(R.id.tag42)
        Button tag42;
        @InjectView(R.id.tag43)
        Button tag43;
        @InjectView(R.id.tag44)
        Button tag44;
        @InjectView(R.id.tag45)
        Button tag45;
        @InjectView(R.id.tag46)
        Button tag46;
        @InjectView(R.id.tag47)
        Button tag47;
        @InjectView(R.id.tag48)
        Button tag48;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
