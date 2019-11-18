package com.example.mdmall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mdmall.fragment.ClassifyFragment;
import com.example.mdmall.fragment.HomeFragment;
import com.example.mdmall.fragment.MyFragment;
import com.example.mdmall.fragment.ShoppingFragment;
import com.example.mdmall.fragment.WishingWellFragment;
import com.example.mdmall.view.SpecialTab;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends BaseActivity {
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.tab_main)
    PageNavigationView tabMain;
    private NavigationController navigationController;
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private WishingWellFragment wishingWellFragment;
    private ShoppingFragment shoppingFragment;
    private MyFragment myFragment;
    public static int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        navigationController = tabMain.custom()
                .addItem(newItem(R.mipmap.home01, R.mipmap.home, "首页"))
                .addItem(newItem(R.mipmap.classify, R.mipmap.classify01, "分类"))
                .addItem(newItem(R.mipmap.wishing, R.mipmap.wishing01, "免单池"))
                .addItem(newItem(R.mipmap.shopping, R.mipmap.shopping01, "购物车"))
                .addItem(newItem(R.mipmap.my, R.mipmap.my01, "我的"))
                .build();
        //初始化默认fragment
        setDefaultFragment();
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (index) {
                    case 0:
                        type = 0;
                        if(homeFragment == null){
                            homeFragment = HomeFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, homeFragment);
                        break;
                    case 1:
                        type = 1;
                        if(classifyFragment == null){
                            classifyFragment = ClassifyFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, classifyFragment);
                        break;
                    case 2:
                        type = 2;
                        if(wishingWellFragment == null){
                            wishingWellFragment = WishingWellFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, wishingWellFragment);
                        break;
                    case 3:
                        type = 3;
                        if(shoppingFragment == null){
                            shoppingFragment = ShoppingFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, shoppingFragment);
                        break;
                    case 4:
                        type = 4;
                        if(myFragment == null){
                            myFragment = MyFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, myFragment);
                        break;
                }
                fragmentTransaction.commitAllowingStateLoss();
            }

            @Override
            public void onRepeat(int index) {
                Log.d("TAG", "onRepeat: index="+index);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (index) {
                    case 0:
                        if(homeFragment == null){
                            type = 0;
                            homeFragment = HomeFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, homeFragment);
                        break;
                    case 1:
                        if(classifyFragment == null){
                            type = 1;
                            classifyFragment = ClassifyFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, classifyFragment);
                        break;
                    case 2:
                        if(wishingWellFragment == null){
                            type = 2;
                            wishingWellFragment = WishingWellFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, wishingWellFragment);
                        break;
                    case 3:
                        if(shoppingFragment == null){
                            type = 3;
                            shoppingFragment = ShoppingFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, shoppingFragment);
                        break;
                    case 4:
                        if(myFragment == null){
                            type = 4;
                            myFragment = MyFragment.getInstance();
                        }
                        fragmentTransaction.replace(R.id.fl_main, myFragment);
                        break;
                }
                fragmentTransaction.commitAllowingStateLoss();
            }
        });
    }
    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        homeFragment = HomeFragment.getInstance();
        transaction.replace(R.id.fl_main, homeFragment).commit();
    }
    /**
     * 正常tab
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(0xFFAAAAAA);
//        mainTab.setMessageNumber(number);
        mainTab.setTextCheckedColor(0xFFFFCF31);
        return mainTab;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//如果返回键按下
            //此处写退向后台的处理
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
