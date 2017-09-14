package mobile.cenocloud.com.ceno.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.cenocloud.com.ceno.R;

/**
 * Created by root on 17-9-13.
 */

public class AppFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_fragment, container, false);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
