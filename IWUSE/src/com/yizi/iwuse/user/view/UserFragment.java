package com.yizi.iwuse.user.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.analytics.MobclickAgent;
import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.AppParams;
import com.yizi.iwuse.R;
import com.yizi.iwuse.common.utils.ILog;
import com.yizi.iwuse.general.MainHomeActivity;
import com.yizi.iwuse.user.LoginActivity;

/****
 * 用户管理中心
 * 
 * @author zhangxiying
 *
 */
public class UserFragment extends Fragment {

	private static final String TAG = "UserFragment";
	/***关闭**/
	@ViewInject(R.id.tv_close)
	private ImageView tv_close;
	/***用户信息，头像，昵称，积分**/
	@ViewInject(R.id.lay_userinfo)
	private RelativeLayout lay_userinfo;
	/**用户头像***/
	@ViewInject(R.id.img_usercenter_headimg)
	private ImageView img_usercenter_headimg;
	/**用户昵称**/
	@ViewInject(R.id.txt_usercenter_username)
	private TextView txt_usercenter_username;
	/**积分**/
	@ViewInject(R.id.text_usercenter_totalintegral)
	private TextView  text_usercenter_totalintegral;
	/**收藏**/
	@ViewInject(R.id.lay_usercollection)
	private LinearLayout lay_usercollection;
	/**购物车**/
	@ViewInject(R.id.lay_shopcart)
	private  LinearLayout lay_shopcart;
	/**钱包**/
	@ViewInject(R.id.lay_iwallet)
	private LinearLayout lay_iwallet;
	/**我的订单**/
	@ViewInject(R.id.txt_usercenter_iorderlist)
	private TextView txt_usercenter_iorderlist;
	/**客服**/
	@ViewInject(R.id.txt_usercenter_callservice)
	private TextView txt_usercenter_callservice;
	/**上传稿件**/
	@ViewInject(R.id.txt_usercenter_isubmission)
	private TextView txt_usercenter_isubmission;
	/**设置**/
	@ViewInject(R.id.txt_usercenter_setting)
	private TextView txt_usercenter_setting;
	/**关于**/
	@ViewInject(R.id.txt_usercenter_about)
	private TextView txt_usercenter_about;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frg_usercenter, null);
		ILog.i(TAG, "init onCreateView ");
		ViewUtils.inject(UserFragment.this, view);
		
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		tv_close = (ImageView)view.findViewById(R.id.tv_close);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tv_close.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((MainHomeActivity) getActivity()).closeUserCenter();
			}
		});
	}
	
	/****
	 * 与用户登录强相关的操作按钮事件
	 * 
	 * @param view
	 */
	@OnClick({R.id.img_usercenter_headimg,R.id.lay_usercollection,R.id.lay_shopcart,R.id.lay_iwallet,
		R.id.txt_usercenter_iorderlist,R.id.txt_usercenter_callservice,R.id.txt_usercenter_isubmission})
	public void onUserCenterClick(View view){
		ILog.i(TAG, "check login ");
		if(!AppParams.isLogin){
			Intent mIntent = new Intent(AppContext.instance().globalContext,LoginActivity.class);
			this.startActivity(mIntent);
		}
		switch(view.getId()){
			//用户信息
			case R.id.img_usercenter_headimg:
				break;
			//我的收藏
			case R.id.lay_usercollection:
				break;
			//我的购物车
			case R.id.lay_shopcart:
				break;
			//我的钱包
			case R.id.lay_iwallet:
				break;
			//我的订单
			case R.id.txt_usercenter_iorderlist:
				break;
			//物色客服
			case R.id.txt_usercenter_callservice:
				break;
			//上传投稿
			case R.id.txt_usercenter_isubmission:
				break;
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageStart(TAG);
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageEnd(TAG); 
	}

}
