package com.example.mdmall.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.mdmall.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.mdmall.R.mipmap.ic_launcher_round;

public class FilterFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.reset)
    TextView reset;
    @BindView(R.id.determine)
    TextView determine;
    //    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    //    @BindView(R.id.drawer_content)
    FrameLayout drawer_content;

    TextView tvFilter;
    ImageView ivFilter;
    @BindView(R.id.et_low_price)
    EditText etLowPrice;
    @BindView(R.id.et_high_price)
    EditText etHighPrice;
    @BindView(R.id.tv_exemption)
    TextView tvExemption;
    private View rootView;
    private String isExemption = null;
    private String low_price;
    private String high_price;
    boolean in_check = false;
    boolean refreshFlag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.product_filter_drawerlayout, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        drawer_content = getActivity().findViewById(R.id.drawer_content);
        tvFilter = getActivity().findViewById(R.id.tv_filter);
        ivFilter = getActivity().findViewById(R.id.iv_filter);
        return rootView;
    }


    @OnClick({R.id.reset, R.id.determine, R.id.et_low_price, R.id.et_high_price})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.reset:
                drawerLayout.closeDrawer(drawer_content);
                isExemption = null;
                in_check = false;
                Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_background);
                tvExemption.setBackground(drawable);
                low_price = "";
                high_price = "";
                etLowPrice.setText("");
                etHighPrice.setText("");
                refreshFlag = true;
               // EventBus.getDefault().post(new Screening(refreshFlag, low_price, high_price, isExemption));
                break;
            case R.id.determine:
                drawerLayout.closeDrawer(drawer_content);
                low_price = etLowPrice.getText().toString();
                high_price = etHighPrice.getText().toString();
                if (!low_price.equals("") && !high_price.equals("")) {
                    if (Integer.parseInt(low_price) > Integer.parseInt(high_price)) {
                        etLowPrice.setText(high_price);
                        etHighPrice.setText(low_price);
                        low_price = etLowPrice.getText().toString();
                        high_price = etHighPrice.getText().toString();
                    }
                }
                refreshFlag = true;
               // EventBus.getDefault().post(new Screening(refreshFlag, low_price, high_price, isExemption));
                break;
            case R.id.et_low_price:
                break;
            case R.id.et_high_price:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.tv_exemption)
    public void onViewClicked() {
        if (in_check) {
            isExemption = null;
            in_check = false;
            /*Drawable drawable = getResources().getDrawable(R.drawable.grey_corner);
            tvExemption.setBackground(drawable);*/
        } else {
            isExemption = "1";
            in_check = true;
            /*Drawable drawable = getResources().getDrawable(R.drawable.red_corner);
            tvExemption.setBackground(drawable);*/
        }

    }
}
