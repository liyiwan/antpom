package com.yizi.iwuse.activity;

import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.ICoreService;
import com.yizi.iwuse.R;
import com.yizi.iwuse.product.view.event.IProductEvent;
import com.yizi.iwuse.utils.ILog;
import com.yizi.iwuse.utils.MyUncaughtExceptionHandler;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class WelcomeActivity extends Activity {
	private static final String TAG = "WelcomeActivity";
	
	/** 用来自动重启用 */
    public static PendingIntent pdIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ILog.v(TAG, "~~~~~~ WelcomeActivity onCreate ~~~~~~");
        ILog.d(TAG, "savedInstanceState");

        Intent newIntent = new Intent(WelcomeActivity.this, WelcomeActivity.class);
        pdIntent = PendingIntent.getActivity(getApplication().getBaseContext(), 0, newIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        
        // 处理异常
        Thread.setDefaultUncaughtExceptionHandler(MyUncaughtExceptionHandler.getInstance());

        // 第一次使用AppContext，销毁上次连接创建的缓存
        AppContext.destory();

        // 在主UI线程初始化全局的handler，用来在其他线程中更新UI控件状态
        AppContext.instance().initUiRes(this.getApplicationContext());
        AppContext.instance().initDisplay(this);
        
		setContentView(R.layout.layout_welcome);
		EventBus.getDefault().register(this);
	}

	/****
	 * 根据结果更新界面信息
	 * 
	 * @param event
	 */
	public void onEventMainThread(IProductEvent event) {
		// 更新界面信息
		
	}
	
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

}
