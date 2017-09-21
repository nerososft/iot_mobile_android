package mobile.cenocloud.com.ceno.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mobile.cenocloud.com.ceno.R;
import mobile.cenocloud.com.ceno.activity.WifiConnectActivity;

/**
 * Created by root on 17-9-13.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView(View view) {
        imageView = (ImageView)view.findViewById(R.id.device_add);

        imageView.setOnClickListener(this);
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

            default:break;
        }
    }
}
