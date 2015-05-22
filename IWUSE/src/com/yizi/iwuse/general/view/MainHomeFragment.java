package com.yizi.iwuse.general.view;

import java.util.ArrayList;

import com.yizi.iwuse.R;
import com.yizi.iwuse.general.MainHomeActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;

public class MainHomeFragment extends Fragment {

	private ImageButton img_usercenter;
	private ImageButton img_productsearch;
	private ViewPager mViewPager;
	private TabPagerAdapter mPagerAdapter;
	private int currIndex = 0;// 当前页卡编号  

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.frg_mainhome, null);
		
		img_usercenter = (ImageButton) mView.findViewById(R.id.img_usercenter);
		img_productsearch = (ImageButton) mView.findViewById(R.id.img_productsearch);
		
		mViewPager = (ViewPager) mView.findViewById(R.id.view_pager);
		mPagerAdapter = new TabPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
		mViewPager.invalidate();
		mViewPager.setCurrentItem(currIndex);
		mPagerAdapter.notifyDataSetChanged();
		
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
	
	private class TabPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

		public TabPagerAdapter(FragmentManager fm) {
			super(fm);
			mViewPager.setOnPageChangeListener(this);
		}
		
		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = (WuseThemeFragment) Fragment.instantiate(getActivity(), WuseThemeFragment.class.getName());
				break;
			case 1:
				fragment = (ProductListFragment) Fragment.instantiate(getActivity(), ProductListFragment.class.getName());
				break;
			}
			return fragment;
		}
		
		@Override
		public int getCount() {
			return 2;
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
		
		}
		
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			
		}
		
		@Override
		public void onPageSelected(int position) {
			Animation animation = null;
//            int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
//            int two = one * 2;// 页卡1 -> 页卡3 偏移量
			switch (position) {
			case 0:
				mViewPager.setCurrentItem(0);
//				tvHomeTab1.setTextColor(mActivity.getResources().getColor(R.color.text_selected_color));
//				tvHomeTab2.setTextColor(mActivity.getResources().getColor(R.color.text_color));
//				tvHomeTab3.setTextColor(mActivity.getResources().getColor(R.color.text_color));
//				if (currIndex == 1) {
//                    animation = new TranslateAnimation(one, 0, 0, 0);
//                } else if (currIndex == 2) {
//                    animation = new TranslateAnimation(two, 0, 0, 0);
//                }
				break;
			case 1:
				mViewPager.setCurrentItem(1);
//				tvHomeTab1.setTextColor(mActivity.getResources().getColor(R.color.text_color));
//				tvHomeTab2.setTextColor(mActivity.getResources().getColor(R.color.text_selected_color));
//				tvHomeTab3.setTextColor(mActivity.getResources().getColor(R.color.text_color));
//				if (currIndex == 0) {
//                    animation = new TranslateAnimation(offset, one, 0, 0);
//                } else if (currIndex == 2) {
//                    animation = new TranslateAnimation(two, one, 0, 0);
//                }
				break;
			case 2:
				mViewPager.setCurrentItem(2);
//				tvHomeTab1.setTextColor(mActivity.getResources().getColor(R.color.text_color));
//				tvHomeTab2.setTextColor(mActivity.getResources().getColor(R.color.text_color));
//				tvHomeTab3.setTextColor(mActivity.getResources().getColor(R.color.text_selected_color));
//				if (currIndex == 0) {
//                    animation = new TranslateAnimation(offset, two, 0, 0);
//                } else if (currIndex == 1) {
//                    animation = new TranslateAnimation(one, two, 0, 0);
//                }
				break;
			}
			currIndex = position;
//            animation.setFillAfter(true);// True:图片停在动画结束位置
//            animation.setDuration(300);
//            cursor.startAnimation(animation);
		}
	}

}
