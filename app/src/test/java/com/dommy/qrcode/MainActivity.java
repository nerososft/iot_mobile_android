//package com.dommy.qrcode;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.dommy.qrcode.util.Constant;
//import com.google.zxing.activity.CaptureActivity;
//
//import mobile.cenocloud.com.ceno.R;
//
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    Button btnQrCode; // 扫码
//    TextView tvResult; // 结果
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        initView();
//    }
//
//    private void initView() {
//        btnQrCode = (Button) findViewById(R.id.btn_qrcode);
//        btnQrCode.setOnClickListener(this);
//
//        tvResult = (TextView) findViewById(R.id.txt_result);
//    }
//
//    // 开始扫码
//    private void startQrCode() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            // 申请权限
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
//            return;
//        }
//        // 二维码扫码
//        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
//        startActivityForResult(intent, Constant.REQ_QR_CODE);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_qrcode:
//                startQrCode();
//                break;
//        }
//    }
//

//
//
//}
