package com.yizi.iwuse.general.view;

import java.util.ArrayList;

import com.yizi.iwuse.R;
import com.yizi.iwuse.general.MainHomeActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MainHomeFragment extends Fragment {

	private ImageButton img_usercenter;
	private ImageButton img_productsearch;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.frg_mainhome, null);
		
		img_usercenter = (ImageButton) mView.findViewById(R.id.img_usercenter);
		img_productsearch = (ImageButton) mView.findViewById(R.id.img_productsearch);
		// 点击打开左边菜单
		img_usercenter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainHomeActivity) getActivity()).openUserCenterLayout();
			}
		});
		// 点击打开右边菜单
		img_productsearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainHomeActivity) getActivity()).openProductSearchLayout();
			}
		});
		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

}
