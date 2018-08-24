package net.zengxin.easytalker.smartbutler.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.zengxin.easytalker.smartbutler.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WechatFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_wechat, container, false);
        return view;
    }

}
