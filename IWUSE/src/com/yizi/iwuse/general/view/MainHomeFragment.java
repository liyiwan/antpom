package com.yizi.iwuse.general.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yizi.iwuse.R;
import com.yizi.iwuse.general.MainHomeActivity;

public class MainHomeFragment extends Fragment {

	private ImageButton img_usercenter;
	private ImageButton img_productsearch;
	private ViewPager mViewPager;
	private TabPagerAdapter mPagerAdapter;
	private int currIndex = 0;// 当前页卡编号 
	private TextView txt_mainhome_iwusetheme;
	private TextView txt_mainhome_productlist;
	private LinearLayout ll_top_left_menu;
	private LinearLayout ll_title;
	public static int titleHeight = 0;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.frg_mainhome, null);
		
		img_usercenter = (ImageButton) mView.findViewById(R.id.img_usercenter);
		img_productsearch = (ImageButton) mView.findViewById(R.id.img_productsearch);
		ll_top_left_menu = (LinearLayout) mView.findViewById(R.id.ll_top_menu);
		txt_mainhome_iwusetheme = (TextView) mView.findViewById(R.id.txt_mainhome_iwusetheme);
		txt_mainhome_productlist = (TextView) mView.findViewById(R.id.txt_mainhome_productlist);
		txt_mainhome_iwusetheme.setTextColor(Color.parseColor("#000000"));
		txt_mainhome_iwusetheme.setSelected(true);
		ll_title = (LinearLayout) mView.findViewById(R.id.ll_title);
		mViewPager = (ViewPager) mView.findViewById(R.id.view_pager);
		
		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        ll_title.measure(w, h);
        titleHeight = ll_title.getMeasuredHeight();
		
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
		
		txt_mainhome_iwusetheme.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(txt_mainhome_iwusetheme.isSelected()){
					if(ll_top_left_menu.getVisibility() == View.VISIBLE){
						txt_mainhome_iwusetheme.setBackgroundColor(Color.parseColor("#00000000"));
						ll_top_left_menu.setVisibility(View.GONE);
					}else{
						txt_mainhome_iwusetheme.setBackgroundColor(Color.parseColor("#C0C0C0"));;
						ll_top_left_menu.setVisibility(View.VISIBLE);
					}
				}else{
					ll_top_left_menu.setVisibility(View.GONE);
					mViewPager.setCurrentItem(0);
				}
			}
		
		});
		txt_mainhome_productlist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(txt_mainhome_productlist.isSelected()){
					if(ll_top_left_menu.getVisibility() == View.VISIBLE){
						txt_mainhome_productlist.setBackgroundColor(Color.parseColor("#00000000"));
						ll_top_left_menu.setVisibility(View.GONE);
					}else{
						txt_mainhome_productlist.setBackgroundColor(Color.parseColor("#C0C0C0"));
						ll_top_left_menu.setVisibility(View.VISIBLE);
					}
				}else{
					ll_top_left_menu.setVisibility(View.GONE);
					mViewPager.setCurrentItem(1);
				}
			}
		
		});
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
				txt_mainhome_iwusetheme.setSelected(true);
				txt_mainhome_productlist.setSelected(false);
				txt_mainhome_iwusetheme.setTextColor(Color.parseColor("#000000"));
				txt_mainhome_productlist.setTextColor(Color.parseColor("#A8A8A8"));
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
				txt_mainhome_iwusetheme.setSelected(false);
				txt_mainhome_productlist.setSelected(true);
				txt_mainhome_iwusetheme.setTextColor(Color.parseColor("#A8A8A8"));
				txt_mainhome_productlist.setTextColor(Color.parseColor("#000000"));
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
			}
			currIndex = position;
//            animation.setFillAfter(true);// True:图片停在动画结束位置
//            animation.setDuration(300);
//            cursor.startAnimation(animation);
		}
	}

}
