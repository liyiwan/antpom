package com.yizi.iwuse.general;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.yizi.iwuse.R;
import com.yizi.iwuse.common.base.IActivity;
import com.yizi.iwuse.general.view.MainHomeFragment;
import com.yizi.iwuse.product.view.ProductSearchFragment;
import com.yizi.iwuse.user.view.UserFragment;

public class MainHomeActivity extends FragmentActivity implements IActivity {
	
//    private DrawerLayout mainDraLayout;
	private ActionBarDrawerToggle drawerbar;
	private MainHomeFragment mainHomeFragment;
	private ScrollView layout_mainhome_usercenter;
	private LinearLayout layout_mainhome_productsearch;
	private FragmentManager mFragmentManager;
	private UserFragment userFragment;
	private ProductSearchFragment productFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.layout_mainhome);
		initView();
		initListener();
	}
	
	/***
	 * 初始化控件
	 */
	private void initView(){
//		mainDraLayout = (DrawerLayout)findViewById(R.id.lay_mainhome);
		//设置透明
//		mainDraLayout.setScrimColor(0x00000000);
		mainHomeFragment = new MainHomeFragment();
		mFragmentManager = getSupportFragmentManager();
		FragmentTransaction f_transaction = mFragmentManager.beginTransaction();
		f_transaction.replace(R.id.center_layout, mainHomeFragment);
		f_transaction.commitAllowingStateLoss();
//		initLeftLayout();
//		initRightLayout();
	}
	
	/*public void initLeftLayout(){
		//左边菜单
		layout_mainhome_usercenter = (ScrollView) findViewById(R.id.layout_mainhome_usercenter);
		View view2 = getLayoutInflater().inflate(R.layout.frg_usercenter, null);
		
		layout_mainhome_usercenter.addView(view2);
	}
	public void initRightLayout(){
		//右边菜单
		layout_mainhome_productsearch = (LinearLayout) findViewById(R.id.layout_mainhome_productsearch);
		View view = getLayoutInflater().inflate(R.layout.frg_productsearch, null);
		layout_mainhome_productsearch.addView(view);
	}*/
	
	/***
	 * 控件绑定事件
	 * 
	 */
	private void initListener(){
		/*drawerbar = new ActionBarDrawerToggle(this, mainDraLayout, R.drawable.ic_launcher, R.drawable.head_default) {
			@Override
			public void onDrawerOpened(View drawerView) {
				
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {

				super.onDrawerClosed(drawerView);
			}
		};
		mainDraLayout.setDrawerListener(drawerbar);*/
	}
	
	/***
	 * 控制用户中心菜单
	 */
	public void openUserCenterLayout() {
		
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
		hideFragments(transaction);
		if (null == userFragment) {
			userFragment = new UserFragment();
			transaction.add(R.id.center_layout, userFragment);
		} else {
			transaction.show(userFragment);
		}
		transaction.commitAllowingStateLoss();
		/*if (mainDraLayout.isDrawerOpen(layout_mainhome_usercenter)) {
			mainDraLayout.closeDrawer(layout_mainhome_usercenter);
		} else {
			mainDraLayout.openDrawer(layout_mainhome_usercenter);
			//设置透明
			mainDraLayout.setScrimColor(R.color.bg_color_deep);
		}*/
	}
	
	/**
	 * 		关闭用户中心菜单
	 */
	public void closeUserCenter(){
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
		if (null != userFragment) {
			transaction.hide(userFragment);
			if (null == mainHomeFragment) {
				mainHomeFragment = new MainHomeFragment();
				transaction.add(R.id.center_layout, mainHomeFragment);
			} else {
				transaction.show(mainHomeFragment);
			}
			transaction.commitAllowingStateLoss();
		}
	}
	
	/**		关闭所有fragment
	 * @param transaction
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (null != mainHomeFragment) {
			transaction.hide(mainHomeFragment);
		}
		if (null != userFragment) {
			transaction.hide(userFragment);
		}
	}

	/***
	 * 控制产品筛选
	 */
	public void openProductSearchLayout() {
		
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
		hideFragments(transaction);
		if (null == productFragment) {
			productFragment = new ProductSearchFragment();
			transaction.add(R.id.center_layout, productFragment);
		} else {
			transaction.show(productFragment);
		}
		transaction.commitAllowingStateLoss();
		
		/*if (mainDraLayout.isDrawerOpen(layout_mainhome_productsearch)) {
			mainDraLayout.closeDrawer(layout_mainhome_productsearch);
		} else {
			mainDraLayout.openDrawer(layout_mainhome_productsearch);
			//设置透明
			mainDraLayout.setScrimColor(R.color.bg_color_deep);
		}*/
	}
	
	/**
	 * 		关闭产品筛选
	 */
	public void closeProductSearch(){
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
		if (null != productFragment) {
			transaction.hide(productFragment);
			if (null == mainHomeFragment) {
				mainHomeFragment = new MainHomeFragment();
				transaction.add(R.id.center_layout, mainHomeFragment);
			} else {
				transaction.show(mainHomeFragment);
			}
			transaction.commitAllowingStateLoss();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void removeAllView() {

	}

}
