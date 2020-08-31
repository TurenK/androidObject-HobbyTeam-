package com.example.Fragment.User;


import android.app.FragmentTransaction;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.activitys.R;
import static com.example.activitys.R.styleable.CompoundButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSettingMessageFragment extends Fragment {
    LinearLayout back;
    CheckBox checkbox1;
    CheckBox checkbox2;
    CheckBox checkbox3;
    CheckBox checkbox4;

    public UserSettingMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_setting_message, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        back= getView().findViewById(R.id.back);
        checkbox1= getView().findViewById(R.id.checkbox1);
        checkbox2= getView().findViewById(R.id.checkbox2);
        checkbox3= getView().findViewById(R.id.checkbox3);
        checkbox4= getView().findViewById(R.id.checkbox4);
        checkbox1.setChecked(true);
        checkbox2.setChecked(true);
        checkbox3.setChecked(true);
        checkbox4.setChecked(true);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeContent(new UserSettingFragment());
            }
        });
        checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "请不要关闭消息接收", Toast.LENGTH_SHORT).show();
                checkbox1.setChecked(true);
            }
        });
        checkbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "请不要关闭队伍通知", Toast.LENGTH_SHORT).show();
                checkbox2.setChecked(true);
            }
        });
        checkbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currVolume = 0;
                if(checkbox3.isChecked())
                {
                    try{
                        Toast.makeText(getActivity(), "声音开启成功", Toast.LENGTH_SHORT).show();
                        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setMode(AudioManager.ROUTE_SPEAKER);
                        currVolume = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
                        if(!audioManager.isSpeakerphoneOn()) {
                            //setSpeakerphoneOn() only work when audio mode set to MODE_IN_CALL.
                            audioManager.setMode(AudioManager.MODE_IN_CALL);
                            audioManager.setSpeakerphoneOn(true);
                            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                                    audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL ),
                                    AudioManager.STREAM_VOICE_CALL);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        Toast.makeText(getActivity(), "声音关闭成功", Toast.LENGTH_SHORT).show();
                        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                        if(audioManager != null) {
                            if(audioManager.isSpeakerphoneOn()) {
                                audioManager.setSpeakerphoneOn(false);
                                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,currVolume,
                                        AudioManager.STREAM_VOICE_CALL);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        checkbox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currVolume = 0;
                if(checkbox4.isChecked())
                {
                    try{
                        Toast.makeText(getActivity(), "震动开启成功", Toast.LENGTH_SHORT).show();
                        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setMode(AudioManager.ROUTE_SPEAKER);
                        currVolume = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
                        if(!audioManager.isSpeakerphoneOn()) {
                            //setSpeakerphoneOn() only work when audio mode set to MODE_IN_CALL.
                            audioManager.setMode(AudioManager.MODE_IN_CALL);
                            audioManager.setSpeakerphoneOn(true);
                            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                                    audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL ),
                                    AudioManager.STREAM_VOICE_CALL);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        Toast.makeText(getActivity(), "震动关闭成功", Toast.LENGTH_SHORT).show();
                        AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                        if(audioManager != null) {
                            if(audioManager.isSpeakerphoneOn()) {
                                audioManager.setSpeakerphoneOn(false);
                                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,currVolume,
                                        AudioManager.STREAM_VOICE_CALL);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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
