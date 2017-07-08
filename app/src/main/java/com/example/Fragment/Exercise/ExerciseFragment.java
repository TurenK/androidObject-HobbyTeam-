package com.example.Fragment.Exercise;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.activitys.Exercise.EventAdapter;
import com.example.activitys.Exercise.ItemListEventInfo;
import com.example.activitys.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseFragment extends Fragment {


    private ListView lvEventList;
    private List<ItemListEventInfo> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise,container,false);
        lvEventList = (ListView) view.findViewById(R.id.lv_eventlist);
        list = new ArrayList<>();
        initData();
        return view;
    }

    private void initData() {
//        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
//        String url = ServiceUrl.getUrl(getActivity(),R.string.URL_EVENT_LIST);
//
//        asyncHttpClient.post(url,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(String content) {
//                Log.d("content",content);
//                JSONObject jsonObject = JSONObject.parseObject(content);
//                boolean success = jsonObject.getBoolean("success");
//                if(success)
//                {
//                    String strobj = jsonObject.getString("data");
//                    Log.d("strobj",strobj);
////注意：json字符串的属性字段名称和EventInfo.class中的属性字段名称需要保存一致
//                    list = JSON.parseArray(strobj, EventInfo.class);
//                    Log.d("list",list.size()+"");
//                    EventAdapter eventAdapter = new EventAdapter(list);
//                    lvEventList.setAdapter(eventAdapter);
//                }
//            }
//            @Override
//            public void onFailure(Throwable error, String content) {
//                Toast.makeText(getActivity(),"服务器连接错误，"+content,Toast.LENGTH_SHORT).show();
//            }
//        });
        for(int i=0;i<9;i++){
        ItemListEventInfo temp = new ItemListEventInfo();
        temp.setName("gay典");
        temp.setAddr("北交男厕所");
        temp.setIntro("来约啊");
        temp.setStart_time("2011/00/00");
        list.add(temp);
        }
        EventAdapter eventAdapter = new EventAdapter(list);
        lvEventList.setAdapter(eventAdapter);
    }
}
