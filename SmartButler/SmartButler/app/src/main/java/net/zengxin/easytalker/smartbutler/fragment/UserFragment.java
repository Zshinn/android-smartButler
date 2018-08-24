package net.zengxin.easytalker.smartbutler.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.zengxin.easytalker.smartbutler.R;
import net.zengxin.easytalker.smartbutler.ui.CourierActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.tv_courier)
    public void clickCourier(){
        startActivity(new Intent(getActivity(), CourierActivity.class));
    }

}
