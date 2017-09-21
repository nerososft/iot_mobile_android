package mobile.cenocloud.com.ceno.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mobile.cenocloud.com.ceno.R;
import mobile.cenocloud.com.ceno.fragment.AppFragment;
import mobile.cenocloud.com.ceno.fragment.HomeFragment;
import mobile.cenocloud.com.ceno.fragment.SelfFragment;
import mobile.cenocloud.com.ceno.fragment.ShopFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int bottom_text_color = 0xffa0a0a0;
    int bottom_text_color_active = 0xff1296db;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    HomeFragment homeFragment;
    ShopFragment shopFragment;
    SelfFragment selfFragment;
    AppFragment appFragment;

    LinearLayout bottomButtonHome;
    LinearLayout bottomButtonShop;
    LinearLayout bottomButtonSelf;
    LinearLayout bottomButtonApp;

    ImageView bottomButtonHomeImg;
    ImageView bottomButtonAppImg;
    ImageView bottomButtonShopImg;
    ImageView bottomButtonSelfImg;

    TextView bottomButtonHomeText;
    TextView bottomButtonAppText;
    TextView bottomButtonShopText;
    TextView bottomButtonSelfText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //实例化要管理的fragment

        initView();
        initFragment();

        fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
        fragmentTransaction.commit();
        
    }

    private void initFragment() {
        homeFragment  = new HomeFragment();
        shopFragment = new ShopFragment();
        selfFragment = new SelfFragment();
        appFragment = new AppFragment();
    }

    private void initView() {
        bottomButtonHome = (LinearLayout)this.findViewById(R.id.bottom_bt_home);
        bottomButtonShop = (LinearLayout) this.findViewById(R.id.bottom_bt_shop);
        bottomButtonSelf = (LinearLayout) this.findViewById(R.id.bottom_bt_self);
        bottomButtonApp = (LinearLayout)this.findViewById(R.id.bottom_bt_app);

        bottomButtonHomeImg = (ImageView) this.findViewById(R.id.bottom_bt_home_img);
        bottomButtonAppImg = (ImageView) this.findViewById(R.id.bottom_bt_app_img);
        bottomButtonShopImg = (ImageView) this.findViewById(R.id.bottom_bt_shop_img);
        bottomButtonSelfImg = (ImageView) this.findViewById(R.id.bottom_bt_self_img);

        bottomButtonHomeText = (TextView) this.findViewById(R.id.bottom_bt_home_text);
        bottomButtonAppText = (TextView) this.findViewById(R.id.bottom_bt_app_text);
        bottomButtonShopText = (TextView) this.findViewById(R.id.bottom_bt_shop_text);
        bottomButtonSelfText = (TextView) this.findViewById(R.id.bottom_bt_self_text);

        bottomButtonHome.setOnClickListener(this);
        bottomButtonShop.setOnClickListener(this);
        bottomButtonSelf.setOnClickListener(this);
        bottomButtonApp.setOnClickListener(this);

        bottomButtonHomeImg.setImageDrawable(getResources().getDrawable(R.drawable.home_blue));
        bottomButtonHomeText.setTextColor(bottom_text_color_active);
    }
    void bottomButtonClear(){
        bottomButtonHomeImg.setImageDrawable(getResources().getDrawable(R.drawable.home));
        bottomButtonAppImg.setImageDrawable(getResources().getDrawable(R.drawable.application));
        bottomButtonShopImg.setImageDrawable(getResources().getDrawable(R.drawable.shop));
        bottomButtonSelfImg.setImageDrawable(getResources().getDrawable(R.drawable.my));

        bottomButtonHomeText.setTextColor(bottom_text_color);
        bottomButtonAppText.setTextColor(bottom_text_color);
        bottomButtonShopText.setTextColor(bottom_text_color);
        bottomButtonSelfText.setTextColor(bottom_text_color);

    }
    @Override
    public void onClick(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.bottom_bt_home:
                fragmentTransaction.replace(R.id.fragment_container,homeFragment);
                bottomButtonClear();
                bottomButtonHomeImg.setImageDrawable(getResources().getDrawable(R.drawable.home_blue));
                bottomButtonHomeText.setTextColor(bottom_text_color_active);
                break;
            case R.id.bottom_bt_shop:
                fragmentTransaction.replace(R.id.fragment_container,shopFragment);
                bottomButtonClear();
                bottomButtonShopImg.setImageDrawable(getResources().getDrawable(R.drawable.shop_blue));
                bottomButtonShopText.setTextColor(bottom_text_color_active);

                break;
            case R.id.bottom_bt_self:
                fragmentTransaction.replace(R.id.fragment_container,selfFragment);
                bottomButtonClear();
                bottomButtonSelfImg.setImageDrawable(getResources().getDrawable(R.drawable.my_blue));
                bottomButtonSelfText.setTextColor(bottom_text_color_active);
                break;
            case R.id.bottom_bt_app:
                fragmentTransaction.replace(R.id.fragment_container,appFragment);
                bottomButtonClear();
                bottomButtonAppImg.setImageDrawable(getResources().getDrawable(R.drawable.application_blue));
                bottomButtonAppText.setTextColor(bottom_text_color_active);
                break;
            default:break;
        }
        fragmentTransaction.commit();

    }
}
