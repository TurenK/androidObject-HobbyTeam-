package com.example.activitys.Exercise;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.activitys.R;

import java.math.BigDecimal;

import static android.widget.Toast.LENGTH_SHORT;

public class ExerciseMapActivity extends Activity {

    private MapView mMapView = null;
    private AMap aMap;
    private Marker marker;
    //地理搜索类
    private GeocodeSearch geocodeSearch;
    private String addr ;
    private String pozition ;
    private static final int LOCATION = 2333;


    private void requirePer() {
        if (ContextCompat.checkSelfPermission(ExerciseMapActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(ExerciseMapActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE},
                    1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_map);

        //给按钮添加监听，获取当前地址，并回传给调用者activity
        final Button bt_addr_confirm = (Button) findViewById(R.id.bt_addr_confirm);
        requirePer();
        bt_addr_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ExerciseMapActivity.this,ExerciseAddnewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("addr",addr);
                intent.putExtras(bundle);//将bundle传入intent中
                setResult(LOCATION,intent);
                finish();
            }
        });
        /////////////////初始化地图///////////////////
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        ///////////////初始化定位蓝点样式类///////////////////
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        ////定位一次，且将视角移动到地图中心点。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //设置是否定位到当前位置，并显示定位小蓝点，默认为true
        myLocationStyle.showMyLocation(true);
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //设置放大的倍数
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        //设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);
        //设置图钉选项
        MarkerOptions options = new MarkerOptions();
        //图标
        //options.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation));
        //位置
        options.position(aMap.getCameraPosition().target);
        options.title("title");
        //子标题
        //options.snippet("这是我的位置");
        marker = aMap.addMarker(options);
        //地理信息获取类：根据经纬度获取地址
        geocodeSearch = new GeocodeSearch(this);


        /////////////////地图选点功能，移动地图的监听事件/////////////////////
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            //移动过程中
            public void onCameraChange(CameraPosition cameraPosition) {
                marker.setPosition(cameraPosition.target);
            }

            @Override
            //移动结束后，获得具体的地址信息
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                LatLonPoint lp = new LatLonPoint(cameraPosition.target.latitude,cameraPosition.target.longitude);
                RegeocodeQuery query = new RegeocodeQuery(lp, 500f, GeocodeSearch.AMAP);

                double lat = cameraPosition.target.latitude;
                String latS = String .format("%.2f",lat);
                double longt = cameraPosition.target.longitude;
                String longS = String .format("%.2f",longt);
                pozition="("+latS+"N,"+longS+"E)";

                //异步查询,根据经纬度查地址
                geocodeSearch.getFromLocationAsyn(query);
                geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
                    @Override
                    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                        RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
                        String formatAddress = regeocodeAddress.getFormatAddress();
                        Toast.makeText(ExerciseMapActivity.this, formatAddress,LENGTH_SHORT).show();
                        addr=pozition+formatAddress;
                        Log.w("addr",addr);
                    }
                    @Override
                    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

                    }
                });
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

}
