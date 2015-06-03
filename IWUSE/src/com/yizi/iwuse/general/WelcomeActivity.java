package com.yizi.iwuse.general;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.R;
import com.yizi.iwuse.common.base.BaseActivity;
import com.yizi.iwuse.common.base.IEvent;
import com.yizi.iwuse.common.utils.ILog;
import com.yizi.iwuse.common.utils.IWuseUtil;
import com.yizi.iwuse.common.utils.MyUncaughtExceptionHandler;
import com.yizi.iwuse.common.widget.VideoWidget;
import com.yizi.iwuse.constants.GeneralConst;
import com.yizi.iwuse.constants.PublicConst;
import com.yizi.iwuse.general.adapter.ViewPagerAdapter;

import de.greenrobot.event.EventBus;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

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
    @ViewInject(R.id.vflp_firstguide) private ViewPager vPager_firstguide;
    /**跳转到主页**/
    @ViewInject(R.id.btn_skip) 
    private Button btn_skip;
    @ViewInject(R.id.btn_enterwuse) 
    private Button btn_enterwuse;
    
    private ViewPagerAdapter viewPagerAdapter = null;
	
    private  List<View> viewList= new ArrayList<View>();
    /**显示类型   0-首页引导，1-广告   ***/
    private int firstshow = GeneralConst.FIRSTSHOW_GUIDE;
    private static final String SHOW = "guide";
    private ViewGroup mView  = null;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ILog.v(TAG, "~~~~~~ WelcomeActivity onCreate ~~~~~~");
        ILog.d(TAG, "savedInstanceState");
        getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.layout_welcome, null);
        setContentView(mView);
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
        
        ViewUtils.inject(this);
		EventBus.getDefault().register(this);
		//注册友盟统计分析工具
		 //因页面存在activity,fragment,view，需禁止默认的页面统计方式
		MobclickAgent.openActivityDurationTrack(false);
		MobclickAgent.updateOnlineConfig(this);
		AnalyticsConfig.enableEncrypt(false);
		
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					Message msg = mHandler.obtainMessage();
					msg.what = GeneralConst.STARTGUIDE;
					mHandler.sendMessage(msg);
				} catch (InterruptedException e) {
				}
			}
		}.start();
		
		
	}

	/****
	 * 根据结果更新界面信息
	 * 
	 * @param event
	 */
	public void onEventMainThread(IEvent event) {
		// 更新界面信息
	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
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
	 * 欢迎界面后显示内容
	 * 
	 * 根据资源文件中firstshow值显示，首页引导和广告互斥，二者只选其一，首页引导优先
	 * 
	 * firstshow: guide+版本号   guide v1.x.x  
	 * 1.资源版本号是否跟当前版本号一致，不一致则说明引导页有改变，欢迎界面后显示首页引导
	 * 2.未存在首页引导，则看是否有广告，广告分为图片和视频，只会有一种格式，同时广告有时效性即广告在某一时间段显示
	 */
	public void loadFirstGuide(){
		String firstShow = AppContext.instance().appParams.getParamStringByKey("firstshow");
		String advertshow = AppContext.instance().appParams.getParamStringByKey("advertshow");
		vPager_firstguide.setVisibility(View.VISIBLE);
		String versionStr = SHOW+PublicConst.VERSION;
		if(firstShow!=null && !"".equals(firstShow) && versionStr.equals(firstShow)){
		//if(true){	
		firstshow = GeneralConst.FIRSTSHOW_GUIDE;
			btn_skip.setVisibility(View.VISIBLE);
			//引导页面的图片一般从本地获取
			viewList.add(IWuseUtil.getImageView(this,R.drawable.firstguide_1));
			viewList.add(IWuseUtil.getImageView(this,R.drawable.firstguide_2));
			viewList.add(IWuseUtil.getImageView(this,R.drawable.firstguide_3));
			viewList.add(IWuseUtil.getImageView(this,R.drawable.firstguide_4));
			
			viewPagerAdapter = new ViewPagerAdapter(this, viewList);
			
			vPager_firstguide.setAdapter(viewPagerAdapter);
			//vPager_firstguide.setAnimation(AnimationUtils.loadAnimation(WelcomeActivity.this,R.anim.push_left_in));
			btn_skip.setOnClickListener(btnOnClickListener);
			btn_enterwuse.setOnClickListener(btnOnClickListener);
			AppContext.instance().appParams.setParam("firstshow", versionStr);
		}else{
//		else if(advertshow!=null && "".equals(advertshow)
//				&& GeneralConst.FIRSTSHOW_EXISTADVERT.equals(advertshow)){
			//1.取得广告的有效时间
			String advertStart = IWuseUtil.dateFormat(AppContext.instance().appParams.getParamStringByKey("advertStart"));
			String advertEnd = IWuseUtil.dateFormat(AppContext.instance().appParams.getParamStringByKey("advertEnd"));
			String currentDate = Calendar.getInstance().get(Calendar.YEAR)+"-"+Calendar.getInstance().get(Calendar.MONTH)+"_"+Calendar.getInstance().get(Calendar.DATE);
			//2.当前时间在广告的有效时间内，则加载广告
			//if(currentDate.compareTo(advertStart)>-1 && currentDate.compareTo(advertEnd)<1) {
				firstshow = GeneralConst.FIRSTSHOW_ADVERT;
				
				String vdoPath = "android.resource://"+getPackageName()+"/"+R.raw.demo;
				VideoWidget wideoWidget = new VideoWidget(this,mView, vdoPath);
				viewList.clear();
				viewList.add(wideoWidget);
				viewList.add(IWuseUtil.getImageView(this,R.drawable.firstguide_4));
				
				viewPagerAdapter = new ViewPagerAdapter(this, viewList);
				vPager_firstguide.setAdapter(viewPagerAdapter);
			/*}else {
				//3.当前没广告，则跳转到主页面
				Intent mIntent = new Intent(WelcomeActivity.this, MainHomeActivity.class);
				WelcomeActivity.this.startActivity(mIntent);
			}*/
		}
		vPager_firstguide.setOnPageChangeListener(pageChangeListener);
	}
	
	@OnClick({R.id.btn_skip,R.id.btn_enterwuse}) 
	public void btnOnClick(View view){
		switch(view.getId()){
			case R.id.btn_skip:
			case R.id.btn_enterwuse:
				Intent mIntent = new Intent(WelcomeActivity.this, MainHomeActivity.class);
				WelcomeActivity.this.startActivity(mIntent);
				break;
		}
	}
	
	
	/**欢迎界面跳转到主界面**/
	private OnClickListener btnOnClickListener = new OnClickListener() {
		
		/**进入到主页*/
		@Override
		public void onClick(View v) {
			Intent mIntent = new Intent(WelcomeActivity.this, MainHomeActivity.class);
			WelcomeActivity.this.startActivity(mIntent);
			finish();
		}
	};
	
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int position) {
			if(position==viewList.size()-1){
				btn_enterwuse.setVisibility(View.VISIBLE);
				btn_skip.setVisibility(View.GONE);
			}else{
				btn_enterwuse.setVisibility(View.GONE);
				if (firstshow==GeneralConst.FIRSTSHOW_GUIDE) {
					btn_skip.setVisibility(View.VISIBLE);
				}
			}
		}
		
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			// do nothing
		}
		
		@Override
		public void onPageScrollStateChanged(int state) {
			// do nothing
		}
	};
	
	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GeneralConst.STARTGUIDE:
				//首页引导
				loadFirstGuide();
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

}
