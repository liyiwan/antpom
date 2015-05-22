package com.yizi.iwuse.general;

import java.util.ArrayList;
import java.util.List;

import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.R;
import com.yizi.iwuse.common.base.BaseActivity;
import com.yizi.iwuse.common.base.IEvent;
import com.yizi.iwuse.common.utils.ILog;
import com.yizi.iwuse.common.utils.IWuseUtil;
import com.yizi.iwuse.common.utils.MyUncaughtExceptionHandler;
import com.yizi.iwuse.general.adapter.ViewPagerAdapter;

import de.greenrobot.event.EventBus;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewFlipper;

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
    /***引导页图片切换工具**/
    private ViewPager vPager_firstguide;
    /**跳转到主页**/
    private Button btn_skip,btn_enterwuse;
    
    ViewPagerAdapter viewPagerAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ILog.v(TAG, "~~~~~~ WelcomeActivity onCreate ~~~~~~");
        ILog.d(TAG, "savedInstanceState");

        setContentView(R.layout.layout_welcome);
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
        
		EventBus.getDefault().register(this);
		//首页引导
		this.loadFirstGuide();
		
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
		EventBus.getDefault().unregister(this);
		removeAllView();
		super.onDestroy();
	}

	@Override
	public void removeAllView() {
		
	}
	
	/***
	 * 载入首页引导
	 *  
	 * 
	 */
	public void loadFirstGuide(){
		String showGuide = AppContext.instance().appParams.getParamStringByKey("showguide");
		if(showGuide==null || "".equals(showGuide)||"0".equals(showGuide)){
			vPager_firstguide = (ViewPager)findViewById(R.id.vflp_firstguide);
			btn_skip = (Button) findViewById(R.id.btn_skip);
			btn_enterwuse = (Button) findViewById(R.id.btn_enterwuse);
			btn_skip.setVisibility(View.VISIBLE);
			final List<View> viewList= new ArrayList<View>();
			//应该从服务器获取
			viewList.add(IWuseUtil.getImageView(this,R.drawable.firstguide_1));
			viewList.add(IWuseUtil.getImageView(this,R.drawable.firstguide_2));
			viewList.add(IWuseUtil.getImageView(this,R.drawable.firstguide_3));
			viewList.add(IWuseUtil.getImageView(this,R.drawable.firstguide_4));
			
			viewPagerAdapter = new ViewPagerAdapter(this, viewList);
			
			vPager_firstguide.setAdapter(viewPagerAdapter);
			//vPager_firstguide.setAnimation(AnimationUtils.loadAnimation(WelcomeActivity.this,R.anim.push_left_in));
			
			vPager_firstguide.setOnPageChangeListener(new OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int position) {
					if(position==viewList.size()-1){
						btn_enterwuse.setVisibility(View.VISIBLE);
						btn_skip.setVisibility(View.GONE);
					}else{
						btn_enterwuse.setVisibility(View.GONE);
						btn_skip.setVisibility(View.VISIBLE);
					}
				}
				
				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				}
				
				/****
				 * @param state
				 *    SCROLL_STATE_IDLE
				 *	  SCROLL_STATE_DRAGGING
				 *	  SCROLL_STATE_SETTLING
				 */
				@Override
				public void onPageScrollStateChanged(int state) {
				}
			});
			
			btn_skip.setOnClickListener(btnOnClickListener);
			btn_enterwuse.setOnClickListener(btnOnClickListener);
			
		}
	}
	
	/**欢迎界面跳转到主界面**/
	private OnClickListener btnOnClickListener = new OnClickListener() {
		
		/**进入到主页*/
		@Override
		public void onClick(View v) {
			Intent mIntent = new Intent(WelcomeActivity.this, MainHomeActivity.class);
			WelcomeActivity.this.startActivity(mIntent);
		}
	};
	

}
