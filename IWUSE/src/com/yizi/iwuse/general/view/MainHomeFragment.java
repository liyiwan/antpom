package com.yizi.iwuse.general.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yizi.iwuse.R;
import com.yizi.iwuse.general.MainHomeActivity;
import com.yizi.iwuse.product.view.ProductFilterFragment;

/**		主页
 * @author hehaodong
 *
 */
public class MainHomeFragment extends Fragment {

	@ViewInject(R.id.view_pager) private ViewPager mViewPager;
	/***个人中心选择按钮***/
	@ViewInject(R.id.tv_usercenter) private TextView tv_usercenter;
	/***筛选选择按钮***/
	@ViewInject(R.id.tv_productsearch) private TextView tv_productsearch;
	private TabPagerAdapter mPagerAdapter;
	/***当前页卡编号***/
	private int currIndex = 0;
	/***主题选择按钮***/
	@ViewInject(R.id.tv_mainhome_iwusetheme) private TextView tv_mainhome_iwusetheme;
	/***单品选择按钮***/
	@ViewInject(R.id.tv_mainhome_productlist) private TextView tv_mainhome_productlist;
//	private LinearLayout ll_top_left_menu;
	@ViewInject(R.id.ll_title) private LinearLayout ll_title;
	public static int titleHeight = 0;
	private FragmentManager mFragmentManager;
	/***筛选页面***/
	private ProductFilterFragment productFragment;
	private boolean isFilterOpen = false;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.frg_mainhome, null);
		ViewUtils.inject(this,mView);
//		ll_top_left_menu = (LinearLayout) mView.findViewById(R.id.ll_top_menu);
//		ib_mainhome_iwusetheme.setTextColor(Color.parseColor("#000000"));
//		txt_mainhome_iwusetheme.setSelected(true);
		
		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mFragmentManager = getActivity().getSupportFragmentManager();
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
		
		// 点击打开个人中心
		tv_usercenter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainHomeActivity) getActivity()).openUserCenterLayout();
			}
		});
		
		// 点击打开过滤菜单
		tv_productsearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentTransaction transaction = mFragmentManager.beginTransaction();
				transaction.setCustomAnimations(R.anim.push_top_in, R.anim.push_top_out);
				if(!isFilterOpen){
					isFilterOpen = true;
					if (null == productFragment) {
						productFragment = new ProductFilterFragment();
						transaction.add(R.id.fl_filter_fragment, productFragment);
					} else {
						transaction.show(productFragment);
					}
				}else{
					isFilterOpen = false;
					if (null != productFragment) {
						transaction.hide(productFragment);
					}
				}
				transaction.commitAllowingStateLoss();
//				((MainHomeActivity) getActivity()).openProductSearchLayout();
			}
		});
		
		
		/***切换到主题页卡***/
		tv_mainhome_iwusetheme.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				if(txt_mainhome_iwusetheme.isSelected()){
//					if(ll_top_left_menu.getVisibility() == View.VISIBLE){
//						txt_mainhome_iwusetheme.setBackgroundColor(Color.parseColor("#00000000"));
//						ll_top_left_menu.setVisibility(View.GONE);
//					}else{
//						txt_mainhome_iwusetheme.setBackgroundColor(Color.parseColor("#C0C0C0"));;
//						ll_top_left_menu.setVisibility(View.VISIBLE);
//					}
//				}else{
//					ll_top_left_menu.setVisibility(View.GONE);
					mViewPager.setCurrentItem(0);
//				}
			}
		
		});
		
		/***切换到单品页卡***/
		tv_mainhome_productlist.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				if(txt_mainhome_productlist.isSelected()){
//					if(ll_top_left_menu.getVisibility() == View.VISIBLE){
//						txt_mainhome_productlist.setBackgroundColor(Color.parseColor("#00000000"));
//						ll_top_left_menu.setVisibility(View.GONE);
//					}else{
//						txt_mainhome_productlist.setBackgroundColor(Color.parseColor("#C0C0C0"));
//						ll_top_left_menu.setVisibility(View.VISIBLE);
//					}
//				}else{
//					ll_top_left_menu.setVisibility(View.GONE);
					mViewPager.setCurrentItem(1);
//				}
			}
		
		});
	}
	
	/**		主页切换适配器
	 * @author hehaodong
	 *
	 */
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
//				ib_mainhome_iwusetheme.setSelected(true);
//				txt_mainhome_productlist.setSelected(false);
//				ib_mainhome_iwusetheme.setTextColor(Color.parseColor("#000000"));
//				txt_mainhome_productlist.setTextColor(Color.parseColor("#A8A8A8"));
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
//				txt_mainhome_iwusetheme.setSelected(false);
//				txt_mainhome_productlist.setSelected(true);
//				txt_mainhome_iwusetheme.setTextColor(Color.parseColor("#A8A8A8"));
//				txt_mainhome_productlist.setTextColor(Color.parseColor("#000000"));
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
