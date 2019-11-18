package com.example.mdmall.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.library.FlowAdapter;
import com.example.mdmall.BaseActivity;
import com.example.mdmall.R;
import com.example.mdmall.adapter.PoiOtherMessageAdapter;
import com.example.mdmall.bean.PoiAddressBean;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PositioningActivity extends BaseActivity implements LocationSource, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener, PoiSearch.OnPoiSearchListener {

    @BindView(R.id.map)
    MapView map;
    private AMap aMap;
    private GeocodeSearch geocoderSearch;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    private PoiSearch poiSearch1;

    @BindView(R.id.recy)
    RecyclerView recy;
    private PoiOtherMessageAdapter adapter;
    private LatLonPoint latLonPoint;

    @BindView(R.id.edit_search)
    EditText edit_search;
    @BindView(R.id.text_location_city)
    TextView text_location_city;
    @BindView(R.id.text_replace)
    TextView text_replace;
    @OnClick(R.id.text_replace)
    public void text_replace_Click(){
        String s = text_replace.getText().toString();
        if(s.equals("手动获取")){
            map.setVisibility(View.GONE);
            text_replace.setText("自动获取");
        }else{
            map.setVisibility(View.VISIBLE);
            text_replace.setText("手动获取");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positioning);
        ButterKnife.bind(this);
        map.onCreate(savedInstanceState);
        //初始化地图
        initMap();

        PermissionsUtil.requestPermission(getApplication(), new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                //蓝点定位
                initBlueLocation();

                edit_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
                        if(!hasFocus){
                            InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                            if (manager != null)
                                manager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                        }else{

                        }
                    }
                });
                edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH){
                            InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                            if (manager != null)
                                manager.hideSoftInputFromWindow(edit_search.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

                            initPoiSearch(edit_search.getText().toString());
                            return true;
                        }
                        return false;
                    }
                });
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
               finish();
            }
        }, Manifest.permission.ACCESS_COARSE_LOCATION);



    }
    private int page=0;
    private void initPoiSearch(String posi) {

        query = new PoiSearch.Query( posi,"", amaploca.getCity());
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setCityLimit(true);
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(page);//设置查询页码

        poiSearch1 = new PoiSearch(this, query);
        poiSearch1.setOnPoiSearchListener(this);
        //检索周边必备
        poiSearch1.setBound(new PoiSearch.SearchBound(latLonPoint, 1000, true));//
        poiSearch1.searchPOIAsyn();
    }

    private void initLocationMessage() {
        //否则取得经纬度进行逆地理编码
        latLonPoint = new LatLonPoint(amaploca.getLatitude(), amaploca.getLongitude());

        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint,200,GeocodeSearch.AMAP);

        geocoderSearch.getFromLocationAsyn(query);

    }

    private void initBlueLocation() {
        // 设置定位监听
        aMap.setLocationSource(this);
// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);

    }

    private void initMap() {
        aMap = null;
        if (aMap == null) {
            aMap = map.getMap();
            UiSettings settings=aMap.getUiSettings();//获取定位按钮
            settings.setMyLocationButtonEnabled(true);//显示定位按钮
            aMap.setMyLocationEnabled(true);//显示定位层并可触发定位
            aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
    }
    OnLocationChangedListener mListener=null;
    AMapLocationClient mlocationClient=null;
    AMapLocationClientOption mLocationOption=null;
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置是否只定位一次,默认为false
            mLocationOption.setOnceLocation(true);
            //mLocationOption.setInterval(50000);
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
            return;
        }
        mlocationClient.startLocation();//启动定位
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
    private AMapLocation amaploca=null;
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null&&aMapLocation != null) {
            if (aMapLocation != null
                    &&aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                amaploca=aMapLocation;
                //定位地理信息
                initLocationMessage();
                Log.e("AmapErr","成功");
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }
    //蓝点定位搜索结果  回调
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        Log.d("AmapErr","i = "+i);
        if (i == 1000) {

            if (regeocodeResult != null && regeocodeResult.getRegeocodeAddress() != null
                    && regeocodeResult.getRegeocodeAddress().getFormatAddress() != null) {
//				String addressName = result.getRegeocodeAddress().getFormatAddress();
                //当前蓝点定位位置
                String formatAddress = regeocodeResult.getRegeocodeAddress().getFormatAddress();
                Log.d("AmapErr",formatAddress);
                //城市
                text_location_city.setText(""+regeocodeResult.getRegeocodeAddress().getCity());
//检索周边
                initPoiSearch("");
            }
        }

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
    //   检索周围位置信息  回调
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
         //解析result获取POI信息
        if (i == 1000) {
            if (poiResult != null && poiResult.getQuery() != null) {  // 搜索poi的结果
                if (poiResult.getQuery().equals(query)) {  // 是否是同一条

                    List<PoiAddressBean> data = new ArrayList<>();//自己创建的数据集合
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    Log.d("AmapErr","poiItems.size()="+poiItems.size());
                    for(PoiItem item : poiItems){
                        //获取经纬度对象
                        LatLonPoint llp = item.getLatLonPoint();
                        double lon = llp.getLongitude();
                        double lat = llp.getLatitude();

                        String title = item.getTitle();
                        String text = item.getSnippet();
                        String provinceName = item.getProvinceName();
                        String cityName = item.getCityName();
                        String adName = item.getAdName();
                        data.add(new PoiAddressBean(String.valueOf(lon), String.valueOf(lat), title, text,provinceName,
                                cityName,adName));
                    }
                    adapter = new PoiOtherMessageAdapter(this);
                    recy.setLayoutManager(new LinearLayoutManager(this));
                    adapter.setData(data);
                    recy.setAdapter(adapter);

                }
            } else {

            }
        } else {

        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
