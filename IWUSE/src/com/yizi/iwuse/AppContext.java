package com.yizi.iwuse;

import android.app.Application;






import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.yizi.iwuse.comm.service.impl.HttpCommMgr;
import com.yizi.iwuse.framework.AppParams;
import com.yizi.iwuse.utils.ILog;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;

/**
 * 整个应用的全局上下文
 */
public class AppContext
{
    /** The logging tag used by this class with  */
    protected static final String TAG = "AppContext";

    private static volatile AppContext instance = null;


    /** 全局的连接管理器 */
    public HttpCommMgr connMgr;
    
    /** iwuse 版本号通过packetInfo得到赋值 */
    public String iwuseVer;

    /** 带入http头信息中向主机发送的版本号 */

    /** hid的版本日期 */
    public final static String iwuseReleaseDate = "  Release 1.0.0.0 2015.5.8";

    /** ===================================== Warning：全局控制区 end ===================================== **/

    /** 所有Event的集合 */
    public List<ICoreEvent> eventList = new ArrayList<ICoreEvent>();


    /** 帮助模式 */
    public boolean helpMode = false;

    /** 是否需要升级iwuse app */
    public boolean isUpdate = false;

    /** 开始升级iwuse的标准，用于保证只进行一次文件取操作。 */
    public boolean isBeginIwuseUpadate = false;

    /** 升级地址的 URL */
    public String updateUrl = "/IWuse.apk";

    /** 当前升级包的版本 */
    public String updateVersion = "";

    /** 应用程序的全局上下文 */
    public Context globalContext;

    /** 应用程序的全局参数 */
    public AppParams appParams;

    /** 是否完全初始化 */
    public boolean isInited = false;

    /** 是否该类已经实例化完成 */
    public static boolean isInstanceed = false;

    /** 屏的像素宽和高 */
    public int[] disPlay =
    { 1280, 800 };

    /** 是否当前为开发者模式 */
    public static final boolean isDevelopMode = false;

    /** 是否当前为开发者模式 */
    public static final boolean isTestMode = false;

    /** 当前活动的activity */
    public Activity curActivity = null;

    /**
     * 标记会控中是否在等待命令结果返回 handleFlag = true 可以点击按钮，发送操作命令
     * 
     * handleFlag = false 则会控页面任何一个按钮点击都不会发送命令
     */
    public boolean handleFlag = true;

    /**
     * 获取AppContext的单例
     * @return AppContext的实例
     */
    public static AppContext instance()
    {
        // 如果为空或是没有实例化完成，则等待
        if (instance == null || !isInstanceed)
        {
            synchronized (AppContext.class)
            {
                if (instance == null)
                {
                    instance = new AppContext();
                    instance.init();
                    isInstanceed = true;
                }
            }
        }

        return instance;
    }

    private void init()
    {
        // 注册event层的类
        registEvent();

        // 初始化连接管理器
        connMgr = new HttpCommMgr();

        // for 自动化测试
        if (isTestMode)
        {
        }
    }

    /**
     * 注册service层的各个类
     */
    private void registEvent()
    {

        // 设置事件类
//        settingEvent = new SettingEvent();
//        eventList.add(settingEvent);

    }

    /**
     * 注册全局UI handler,需要在UI线程中进行
     */
    public synchronized void initUiRes(Context context)
    {
        if (isInited)
        {
            return;
        }

        ILog.v(TAG, "initUiRes ...");
        // 记录一个全局上下文
        globalContext = context;

        // 初始化参数文件
        appParams = new AppParams(globalContext);
        appParams.initFileData();



        try
        {
            iwuseVer = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch (NameNotFoundException e)
        {
        	iwuseVer = "unkonw";
            ILog.e(TAG, e);
        }

        isInited = true;
    }

    /**
     * 初始化屏幕的像素参数
     * @param activity
     */
    public void initDisplay(Activity activity)
    {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        disPlay[0] = metric.widthPixels; // 屏幕宽度（像素）
        disPlay[1] = metric.heightPixels; // 屏幕高度（像素）
    }

    /**
     * 初始化每个event需要进行的一些状态初始化
     * 
     * 暂时都返回true,不能因为event数据初始化异常造成用户不能登录
     * @return 全部初始化成功，返回true，否则返回false
     */
    public boolean initServiceState()
    {

        for (ICoreEvent event : eventList)
        {
            if (!event.initState())
            {
                ILog.e(TAG, "init event [" + event.toString() + "]'s state failed.");
                // return false;
            }
        }
        ILog.d(TAG, "init event's state.");
        return true;
    }


    /**
     * 销毁该全局处理器
     */
    public static void destory()
    {
        ILog.i(TAG, "destory appcontent......");
        if (null != instance)
        {
            instance = null;
        }
        isInstanceed = false;

        // for 自动化测试
        if (isTestMode)
        {
        	
        }
        // 销毁view 工厂
        //ViewFactory.destory();
    }


    /**
     * 获取当前的配置区域
     * @return
     */
    public Locale getCurrentLocale()
    {
        if (globalContext != null)
        {
            return globalContext.getResources().getConfiguration().locale;
        }
        return Locale.getDefault();
    }
}

