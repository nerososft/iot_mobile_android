package mobile.cenocloud.com.ceno.fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import mobile.cenocloud.com.ceno.R;
import mobile.cenocloud.com.ceno.activity.LightActivity;
import mobile.cenocloud.com.ceno.activity.MainActivity;
import mobile.cenocloud.com.ceno.activity.WifiConnectActivity;
import mobile.cenocloud.com.ceno.util.Constant;

import static android.app.Activity.RESULT_OK;


/**
 * Created by root on 17-9-13.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView;
    private LinearLayout scanBt;

    private LinearLayout ican;

    private  int REQUEST_CODE = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView(View view) {
        imageView = (ImageView)view.findViewById(R.id.device_add);

        imageView.setOnClickListener(this);


        scanBt = (LinearLayout) view.findViewById(R.id.scan_bt);
        scanBt.setOnClickListener(this);

        ican = (LinearLayout) view.findViewById(R.id.btn_ican);
        ican.setOnClickListener(this);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.device_add:
                Intent in = new Intent();
                in.setClass(this.getContext(), WifiConnectActivity.class);
                startActivity(in);
                break;
            case R.id.btn_ican:
                Intent i = new Intent();
                i.setClass(this.getContext(), LightActivity.class);
                startActivity(i);
                break;
            case R.id.scan_bt:
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // 申请权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
                    return;
                }
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;

            default:break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    // 二维码扫码
                    Intent intent = new Intent(getActivity(), CaptureActivity.class);
                    startActivityForResult(intent, Constant.REQ_QR_CODE);
                } else {
                    // 被禁止授权
                    Toast.makeText(getActivity(), "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
