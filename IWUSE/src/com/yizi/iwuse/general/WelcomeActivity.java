package com.yizi.iwuse.general;

import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.R;
import com.yizi.iwuse.common.base.BaseActivity;
import com.yizi.iwuse.common.base.ICoreService;
import com.yizi.iwuse.common.base.IEvent;
import com.yizi.iwuse.common.utils.ILog;
import com.yizi.iwuse.common.utils.MyUncaughtExceptionHandler;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/***
 * 启动欢迎页面，整个APP的入口
 * 
 * 启动页面做的事情：1.清除上次应用程序使用的内存缓存(View缓存)
 *             2.加入全局异常handler
 *             3.初始化应用的全局变量
 * 
 * @author zhangxiying
 *
 */
public class WelcomeActivity extends BaseActivity {
	private static final String TAG = "WelcomeActivity";
	
	/** 用来自动重启用 */
    public static PendingIntent pdIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ILog.v(TAG, "~~~~~~ WelcomeActivity onCreate ~~~~~~");
        ILog.d(TAG, "savedInstanceState");

        Intent newIntent = new Intent(WelcomeActivity.this, WelcomeActivity.class);
        pdIntent = PendingIntent.getActivity(getApplication().getBaseContext(), 0, newIntent
                							 	,PendingIntent.FLAG_CANCEL_CURRENT);
        
        // 处理异常
        Thread.setDefaultUncaughtExceptionHandler(MyUncaughtExceptionHandler.getInstance());

        // 第一次使用AppContext，销毁上次连接创建的缓存
        AppContext.destory();

        // 在主UI线程初始化全局的handler，用来在其他线程中更新UI控件状态
        AppContext.instance().initUiRes(this.getApplicationContext());
        AppContext.instance().initDisplay(this);
        
		setContentView(R.layout.layout_welcome);
		EventBus.getDefault().register(this);
		
		Intent mIntent = new Intent(WelcomeActivity.this, MainHomeActivity.class);
		this.startActivity(mIntent);
	}

	/****
	 * 根据结果更新界面信息
	 * 
	 * @param event
	 */
	public void onEventMainThread(IEvent event) {
		// 更新界面信息
		
	}
	
	/****
	 * 反注册EventBus
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public void removeAllView() {
		// TODO Auto-generated method stub
	}

}
