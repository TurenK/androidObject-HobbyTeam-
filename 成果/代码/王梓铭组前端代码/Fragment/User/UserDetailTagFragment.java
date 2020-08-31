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
        choise = new boolean[49];
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
                if (tags.size() >= tagnum && !choise[7]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[7]) {
                        String tagtemp = viewHolder.tag7.getText().toString();
                        tags.add(tagtemp);
                        choise[7] = true;
                        viewHolder.tag7.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag7.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag7.getText().toString();
                        tags.remove(tagtemp);
                        choise[7] = false;
                        viewHolder.tag7.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag7.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag8:
                if (tags.size() >= tagnum && !choise[8]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[8]) {
                        String tagtemp = viewHolder.tag8.getText().toString();
                        tags.add(tagtemp);
                        choise[8] = true;
                        viewHolder.tag8.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag8.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag8.getText().toString();
                        tags.remove(tagtemp);
                        choise[8] = false;
                        viewHolder.tag8.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag8.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag9:
                if (tags.size() >= tagnum && !choise[9]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[9]) {
                        String tagtemp = viewHolder.tag9.getText().toString();
                        tags.add(tagtemp);
                        choise[9] = true;
                        viewHolder.tag9.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag9.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag9.getText().toString();
                        tags.remove(tagtemp);
                        choise[9] = false;
                        viewHolder.tag9.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag9.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag10:
                if (tags.size() >= tagnum && !choise[10]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[10]) {
                        String tagtemp = viewHolder.tag10.getText().toString();
                        tags.add(tagtemp);
                        choise[10] = true;
                        viewHolder.tag10.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag10.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag10.getText().toString();
                        tags.remove(tagtemp);
                        choise[10] = false;
                        viewHolder.tag10.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag10.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag11:
                if (tags.size() >= tagnum && !choise[11]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[11]) {
                        String tagtemp = viewHolder.tag11.getText().toString();
                        tags.add(tagtemp);
                        choise[11] = true;
                        viewHolder.tag11.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag11.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag11.getText().toString();
                        tags.remove(tagtemp);
                        choise[11] = false;
                        viewHolder.tag11.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag11.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag12:
                if (tags.size() >= tagnum && !choise[12]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[12]) {
                        String tagtemp = viewHolder.tag12.getText().toString();
                        tags.add(tagtemp);
                        choise[12] = true;
                        viewHolder.tag12.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag12.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag12.getText().toString();
                        tags.remove(tagtemp);
                        choise[12] = false;
                        viewHolder.tag12.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag12.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag13:
                if (tags.size() >= tagnum && !choise[13]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[13]) {
                        String tagtemp = viewHolder.tag13.getText().toString();
                        tags.add(tagtemp);
                        choise[13] = true;
                        viewHolder.tag13.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag13.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag13.getText().toString();
                        tags.remove(tagtemp);
                        choise[13] = false;
                        viewHolder.tag13.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag13.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag14:
                if (tags.size() >= tagnum && !choise[14]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[14]) {
                        String tagtemp = viewHolder.tag14.getText().toString();
                        tags.add(tagtemp);
                        choise[14] = true;
                        viewHolder.tag14.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag14.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag14.getText().toString();
                        tags.remove(tagtemp);
                        choise[14] = false;
                        viewHolder.tag14.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag14.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag15:
                if (tags.size() >= tagnum && !choise[15]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[15]) {
                        String tagtemp = viewHolder.tag15.getText().toString();
                        tags.add(tagtemp);
                        choise[15] = true;
                        viewHolder.tag15.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag15.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag15.getText().toString();
                        tags.remove(tagtemp);
                        choise[15] = false;
                        viewHolder.tag15.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag15.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag16:
                if (tags.size() >= tagnum && !choise[16]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[16]) {
                        String tagtemp = viewHolder.tag16.getText().toString();
                        tags.add(tagtemp);
                        choise[16] = true;
                        viewHolder.tag16.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag16.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag16.getText().toString();
                        tags.remove(tagtemp);
                        choise[16] = false;
                        viewHolder.tag16.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag16.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag17:
                if (tags.size() >= tagnum && !choise[17]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[17]) {
                        String tagtemp = viewHolder.tag17.getText().toString();
                        tags.add(tagtemp);
                        choise[17] = true;
                        viewHolder.tag17.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag17.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag17.getText().toString();
                        tags.remove(tagtemp);
                        choise[17] = false;
                        viewHolder.tag17.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag17.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag18:
                if (tags.size() >= tagnum && !choise[18]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[18]) {
                        String tagtemp = viewHolder.tag18.getText().toString();
                        tags.add(tagtemp);
                        choise[18] = true;
                        viewHolder.tag18.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag18.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag18.getText().toString();
                        tags.remove(tagtemp);
                        choise[18] = false;
                        viewHolder.tag18.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag18.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag19:
                if (tags.size() >= tagnum && !choise[19]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[19]) {
                        String tagtemp = viewHolder.tag19.getText().toString();
                        tags.add(tagtemp);
                        choise[19] = true;
                        viewHolder.tag19.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag19.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag19.getText().toString();
                        tags.remove(tagtemp);
                        choise[19] = false;
                        viewHolder.tag19.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag19.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag20:
                if (tags.size() >= tagnum && !choise[20]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[20]) {
                        String tagtemp = viewHolder.tag20.getText().toString();
                        tags.add(tagtemp);
                        choise[20] = true;
                        viewHolder.tag20.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag20.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag20.getText().toString();
                        tags.remove(tagtemp);
                        choise[20] = false;
                        viewHolder.tag20.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag20.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag21:
                if (tags.size() >= tagnum && !choise[21]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[21]) {
                        String tagtemp = viewHolder.tag21.getText().toString();
                        tags.add(tagtemp);
                        choise[21] = true;
                        viewHolder.tag21.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag21.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag21.getText().toString();
                        tags.remove(tagtemp);
                        choise[21] = false;
                        viewHolder.tag21.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag21.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag22:
                if (tags.size() >= tagnum && !choise[22]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[22]) {
                        String tagtemp = viewHolder.tag22.getText().toString();
                        tags.add(tagtemp);
                        choise[22] = true;
                        viewHolder.tag22.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag22.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag22.getText().toString();
                        tags.remove(tagtemp);
                        choise[22] = false;
                        viewHolder.tag22.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag22.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag23:
                if (tags.size() >= tagnum && !choise[23]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[23]) {
                        String tagtemp = viewHolder.tag23.getText().toString();
                        tags.add(tagtemp);
                        choise[23] = true;
                        viewHolder.tag23.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag23.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag23.getText().toString();
                        tags.remove(tagtemp);
                        choise[23] = false;
                        viewHolder.tag23.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag23.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag24:
                if (tags.size() >= tagnum && !choise[24]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[24]) {
                        String tagtemp = viewHolder.tag24.getText().toString();
                        tags.add(tagtemp);
                        choise[24] = true;
                        viewHolder.tag24.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag24.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag24.getText().toString();
                        tags.remove(tagtemp);
                        choise[24] = false;
                        viewHolder.tag24.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag24.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag25:
                if (tags.size() >= tagnum && !choise[25]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[25]) {
                        String tagtemp = viewHolder.tag25.getText().toString();
                        tags.add(tagtemp);
                        choise[25] = true;
                        viewHolder.tag25.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag25.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag25.getText().toString();
                        tags.remove(tagtemp);
                        choise[25] = false;
                        viewHolder.tag25.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag25.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag26:
                if (tags.size() >= tagnum && !choise[26]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[26]) {
                        String tagtemp = viewHolder.tag26.getText().toString();
                        tags.add(tagtemp);
                        choise[26] = true;
                        viewHolder.tag26.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag26.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag26.getText().toString();
                        tags.remove(tagtemp);
                        choise[26] = false;
                        viewHolder.tag26.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag26.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag27:
                if (tags.size() >= tagnum && !choise[27]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[27]) {
                        String tagtemp = viewHolder.tag27.getText().toString();
                        tags.add(tagtemp);
                        choise[27] = true;
                        viewHolder.tag27.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag27.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag27.getText().toString();
                        tags.remove(tagtemp);
                        choise[27] = false;
                        viewHolder.tag27.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag27.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag28:
                if (tags.size() >= tagnum && !choise[28]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[28]) {
                        String tagtemp = viewHolder.tag28.getText().toString();
                        tags.add(tagtemp);
                        choise[28] = true;
                        viewHolder.tag28.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag28.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag28.getText().toString();
                        tags.remove(tagtemp);
                        choise[28] = false;
                        viewHolder.tag28.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag28.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag29:
                if (tags.size() >= tagnum && !choise[29]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[29]) {
                        String tagtemp = viewHolder.tag29.getText().toString();
                        tags.add(tagtemp);
                        choise[29] = true;
                        viewHolder.tag29.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag29.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag29.getText().toString();
                        tags.remove(tagtemp);
                        choise[29] = false;
                        viewHolder.tag29.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag29.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag30:
                if (tags.size() >= tagnum && !choise[30]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[30]) {
                        String tagtemp = viewHolder.tag30.getText().toString();
                        tags.add(tagtemp);
                        choise[30] = true;
                        viewHolder.tag30.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag30.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag30.getText().toString();
                        tags.remove(tagtemp);
                        choise[30] = false;
                        viewHolder.tag30.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag30.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag31:
                if (tags.size() >= tagnum && !choise[31]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[31]) {
                        String tagtemp = viewHolder.tag31.getText().toString();
                        tags.add(tagtemp);
                        choise[31] = true;
                        viewHolder.tag31.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag31.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag31.getText().toString();
                        tags.remove(tagtemp);
                        choise[31] = false;
                        viewHolder.tag31.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag31.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag32:
                if (tags.size() >= tagnum && !choise[32]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[32]) {
                        String tagtemp = viewHolder.tag32.getText().toString();
                        tags.add(tagtemp);
                        choise[32] = true;
                        viewHolder.tag32.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag32.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag32.getText().toString();
                        tags.remove(tagtemp);
                        choise[32] = false;
                        viewHolder.tag32.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag32.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag33:
                if (tags.size() >= tagnum && !choise[33]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[33]) {
                        String tagtemp = viewHolder.tag33.getText().toString();
                        tags.add(tagtemp);
                        choise[33] = true;
                        viewHolder.tag33.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag33.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag33.getText().toString();
                        tags.remove(tagtemp);
                        choise[33] = false;
                        viewHolder.tag33.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag33.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag34:
                if (tags.size() >= tagnum && !choise[34]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[34]) {
                        String tagtemp = viewHolder.tag34.getText().toString();
                        tags.add(tagtemp);
                        choise[34] = true;
                        viewHolder.tag34.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag34.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag34.getText().toString();
                        tags.remove(tagtemp);
                        choise[34] = false;
                        viewHolder.tag34.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag34.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag35:
                if (tags.size() >= tagnum && !choise[35]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[35]) {
                        String tagtemp = viewHolder.tag35.getText().toString();
                        tags.add(tagtemp);
                        choise[35] = true;
                        viewHolder.tag35.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag35.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag35.getText().toString();
                        tags.remove(tagtemp);
                        choise[35] = false;
                        viewHolder.tag35.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag35.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag36:
                if (tags.size() >= tagnum && !choise[36]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[36]) {
                        String tagtemp = viewHolder.tag36.getText().toString();
                        tags.add(tagtemp);
                        choise[36] = true;
                        viewHolder.tag36.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag36.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag36.getText().toString();
                        tags.remove(tagtemp);
                        choise[36] = false;
                        viewHolder.tag36.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag36.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag37:
                if (tags.size() >= tagnum && !choise[37]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[37]) {
                        String tagtemp = viewHolder.tag37.getText().toString();
                        tags.add(tagtemp);
                        choise[37] = true;
                        viewHolder.tag37.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag37.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag37.getText().toString();
                        tags.remove(tagtemp);
                        choise[37] = false;
                        viewHolder.tag37.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag37.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag38:
                if (tags.size() >= tagnum && !choise[38]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[38]) {
                        String tagtemp = viewHolder.tag38.getText().toString();
                        tags.add(tagtemp);
                        choise[38] = true;
                        viewHolder.tag38.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag38.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag38.getText().toString();
                        tags.remove(tagtemp);
                        choise[38] = false;
                        viewHolder.tag38.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag38.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag39:
                if (tags.size() >= tagnum && !choise[39]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[39]) {
                        String tagtemp = viewHolder.tag39.getText().toString();
                        tags.add(tagtemp);
                        choise[39] = true;
                        viewHolder.tag39.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag39.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag39.getText().toString();
                        tags.remove(tagtemp);
                        choise[39] = false;
                        viewHolder.tag39.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag39.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag40:
                if (tags.size() >= tagnum && !choise[40]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[40]) {
                        String tagtemp = viewHolder.tag40.getText().toString();
                        tags.add(tagtemp);
                        choise[40] = true;
                        viewHolder.tag40.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag40.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag40.getText().toString();
                        tags.remove(tagtemp);
                        choise[40] = false;
                        viewHolder.tag40.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag40.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag41:
                if (tags.size() >= tagnum && !choise[41]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[41]) {
                        String tagtemp = viewHolder.tag41.getText().toString();
                        tags.add(tagtemp);
                        choise[41] = true;
                        viewHolder.tag41.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag41.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag41.getText().toString();
                        tags.remove(tagtemp);
                        choise[41] = false;
                        viewHolder.tag41.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag41.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag42:
                if (tags.size() >= tagnum && !choise[42]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[42]) {
                        String tagtemp = viewHolder.tag42.getText().toString();
                        tags.add(tagtemp);
                        choise[42] = true;
                        viewHolder.tag42.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag42.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag42.getText().toString();
                        tags.remove(tagtemp);
                        choise[42] = false;
                        viewHolder.tag42.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag42.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag43:
                if (tags.size() >= tagnum && !choise[43]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[43]) {
                        String tagtemp = viewHolder.tag43.getText().toString();
                        tags.add(tagtemp);
                        choise[43] = true;
                        viewHolder.tag43.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag43.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag43.getText().toString();
                        tags.remove(tagtemp);
                        choise[43] = false;
                        viewHolder.tag43.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag43.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag44:
                if (tags.size() >= tagnum && !choise[44]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[44]) {
                        String tagtemp = viewHolder.tag44.getText().toString();
                        tags.add(tagtemp);
                        choise[44] = true;
                        viewHolder.tag44.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag44.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag44.getText().toString();
                        tags.remove(tagtemp);
                        choise[44] = false;
                        viewHolder.tag44.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag44.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag45:
                if (tags.size() >= tagnum && !choise[45]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[45]) {
                        String tagtemp = viewHolder.tag45.getText().toString();
                        tags.add(tagtemp);
                        choise[45] = true;
                        viewHolder.tag45.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag45.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag45.getText().toString();
                        tags.remove(tagtemp);
                        choise[45] = false;
                        viewHolder.tag45.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag45.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag46:
                if (tags.size() >= tagnum && !choise[46]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[46]) {
                        String tagtemp = viewHolder.tag46.getText().toString();
                        tags.add(tagtemp);
                        choise[46] = true;
                        viewHolder.tag46.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag46.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag46.getText().toString();
                        tags.remove(tagtemp);
                        choise[46] = false;
                        viewHolder.tag46.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag46.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag47:
                if (tags.size() >= tagnum && !choise[47]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[47]) {
                        String tagtemp = viewHolder.tag47.getText().toString();
                        tags.add(tagtemp);
                        choise[47] = true;
                        viewHolder.tag47.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag47.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag47.getText().toString();
                        tags.remove(tagtemp);
                        choise[47] = false;
                        viewHolder.tag47.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag47.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
                break;
            case R.id.tag48:
                if (tags.size() >= tagnum && !choise[48]) {
                    Toast.makeText(getActivity(), "最多选择" + tagnum + "个", Toast.LENGTH_SHORT).show();
                } else {
                    if (!choise[48]) {
                        String tagtemp = viewHolder.tag48.getText().toString();
                        tags.add(tagtemp);
                        choise[48] = true;
                        viewHolder.tag48.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightBlue));
                        viewHolder.tag48.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.head));
                    } else {
                        String tagtemp = viewHolder.tag48.getText().toString();
                        tags.remove(tagtemp);
                        choise[48] = false;
                        viewHolder.tag48.setBackgroundColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.colorLightGray));
                        viewHolder.tag48.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.black));
                    }
                }
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
