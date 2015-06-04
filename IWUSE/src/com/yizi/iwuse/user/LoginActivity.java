package com.yizi.iwuse.user;

import java.util.Iterator;
import java.util.Map;


import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.Tencent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.AppParams;
import com.yizi.iwuse.R;
import com.yizi.iwuse.common.base.BaseActivity;
import com.yizi.iwuse.constants.UserConst;
import com.yizi.iwuse.user.service.events.UserEvents;

import de.greenrobot.event.EventBus;

/****
 * 用户登录Activity
 * 
 * @author zhangxiying
 *
 */
public class LoginActivity extends BaseActivity {

	private static final String TAG = "LoginActivity";

	/** 用户ID/手机号 **/
	@ViewInject(R.id.edt_userid)
	private EditText edt_userid;
	/** 密码 **/
	@ViewInject(R.id.edt_password)
	private EditText edt_password;
	/** 登录 **/
	@ViewInject(R.id.btn_login)
	private Button btn_login;
	/** 忘记密码 **/
	@ViewInject(R.id.txt_forgetuserinfo)
	private TextView txt_forgetuserinfo;
	/** 微信授权登录 **/
	@ViewInject(R.id.img_oauth_weichat)
	private ImageView img_oauth_weichat;
	/** QQ授权登录 **/
	@ViewInject(R.id.img_oauth_qq)
	private ImageView img_oauth_qq;
	/** 支付宝授权登录 **/
	@ViewInject(R.id.img_oauth_paybao)
	private ImageView img_oauth_paybao;
	/** 微博授权登录 **/
	@ViewInject(R.id.img_oauth_weibo)
	private ImageView img_oauth_weibo;
	/**返回**/
	@ViewInject(R.id.img_userlogin_back)
	private ImageView img_back;
	/**登录切换按钮**/
	@ViewInject(R.id.btn_switch_login)
	private Button btn_switch_login;
	/**注册切换按钮**/
	@ViewInject(R.id.btn_switch_regist)
	private Button btn_switch_regist;
	/**登录层**/
	@ViewInject(R.id.lay_switch_login)
	private LinearLayout lay_switch_login;
	/**注册层**/
	@ViewInject(R.id.lay_switch_regist)
    private LinearLayout lay_switch_regist;
	/**注册用户名**/
	@ViewInject(R.id.edt_regist_userid)
	private EditText edt_regist_userid;
	/**注册密码**/
	@ViewInject(R.id.edt_regist_password)
	private EditText edt_regist_password;
	@ViewInject(R.id.btn_regist_connfirm)
	private Button btn_regist_connfirm;
	
	/** 友盟授权登录集成 **/
	private UMSocialService mController = null;
	
	/**登录方式**/
	private int login_type = LOGINTYPE_WEICHAT;
	private static final int LOGINTYPE_WEICHAT = 0x9001;
	private static final int LOGINTYPE_QQ = 0x9002;
	private static final int LOGINTYPE_PAYBAO = 0x9003;
	private static final int LOGINTYPE_WEIBO = 0x9004;

	public static QQAuth mQQAuth;
	private Tencent mTencent;
	private UserInfo mInfo;
	
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.layout_usercenter_login);
		mContext = this;
		/** 注册xUtils UI框架 **/
		ViewUtils.inject(this);
		/** 注册EventBus **/
		EventBus.getDefault().register(this);
		
		mController = UMServiceFactory.getUMSocialService(UserConst.LOGIN_YOUMENG);
		registLoginHandler();
	}

	/****
	 * 登录事件 通过xUtils绑定View事件
	 * 
	 * @param view
	 */
	@OnClick({ R.id.btn_switch_login,R.id.btn_switch_regist, R.id.btn_login, R.id.txt_forgetuserinfo,
			R.id.img_oauth_weichat, R.id.img_oauth_qq, R.id.img_oauth_paybao,
			R.id.img_oauth_weibo,R.id.img_userlogin_back,R.id.btn_regist_connfirm })
	public void onLoginClickListener(View view) {
		switch (view.getId()) {
			case R.id.btn_switch_login:
				lay_switch_login.setVisibility(View.VISIBLE);
				lay_switch_regist.setVisibility(View.GONE);
				break;
			case R.id.btn_switch_regist:
				lay_switch_login.setVisibility(View.GONE);
				lay_switch_regist.setVisibility(View.VISIBLE);
				break;
			case R.id.btn_login:
				break;
			case R.id.txt_forgetuserinfo:
				break;
			case R.id.img_oauth_weichat:
				login_type = LOGINTYPE_WEICHAT;
				onThridLogin(SHARE_MEDIA.WEIXIN);
				break;
			case R.id.img_oauth_qq:
				login_type = LOGINTYPE_QQ;
				onThridLogin(SHARE_MEDIA.QQ);
				break;
			case R.id.img_oauth_paybao:
				login_type = LOGINTYPE_PAYBAO;
				break;
			case R.id.img_oauth_weibo:
				login_type = LOGINTYPE_WEIBO;
				onThridLogin(SHARE_MEDIA.SINA);
				break;
			case R.id.btn_regist_connfirm:
				break;
			case R.id.img_userlogin_back:
				finish();
				break;
			default:
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (login_type) {
			case LOGINTYPE_WEIBO: {
				/** 使用SSO授权必须添加如下代码 */
				UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
						requestCode);
				if (ssoHandler != null) {
					ssoHandler.authorizeCallBack(requestCode, resultCode, data);
				}
				break;
			}
			case LOGINTYPE_QQ: {
				break;
			}
		}
	}

	private void onLoginWithWeichat(){
		
		
	}
	
	/****
	 * 注册授权登录的监听
	 * 
	 */
	private void registLoginHandler() {
		// 微信
		UMWXHandler wxHandler = new UMWXHandler(LoginActivity.this,
				UserConst.OAUTH_WEICHAT_APPID, UserConst.OAUTH_WEICHAT_SECRET);
		wxHandler.mShareContent  = UserConst.OAUTH_WEICHAT_SCOPE;
		wxHandler.addToSocialSDK();
		// QQ
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(LoginActivity.this,
				UserConst.OAUTH_QQ_APPID, UserConst.OAUTH_QQ_APPKEY);
		qqSsoHandler.addToSocialSDK();
		// 新浪微博
		SinaSsoHandler sinaSsoHandler = new SinaSsoHandler();

		mController.getConfig().setSsoHandler(sinaSsoHandler);
	}

	/**
	 * 授权第三方登录。如果授权成功，则获取用户信息
	 * 微信、QQ、微博
	 * 
	 * @param platform
	 */
	private void onThridLogin(final SHARE_MEDIA platform) {
		mController.doOauthVerify(LoginActivity.this, platform,
			new UMAuthListener() {

				@Override
				public void onStart(SHARE_MEDIA platform) {
					Toast.makeText(LoginActivity.this, "授权开始",Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onError(SocializeException e, SHARE_MEDIA platform) {
					Toast.makeText(LoginActivity.this, AppContext.instance().globalContext.getString(R.string.oauth_promt_failed),Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onComplete(Bundle value, SHARE_MEDIA platform) {
					Toast.makeText(LoginActivity.this, "授权完成",Toast.LENGTH_SHORT).show();
					//标记为已登录状态
					AppParams.isLogin = true;
					// 获取uid
					String uid = value.getString("uid");
					if (!TextUtils.isEmpty(uid)) {
						// uid不为空，获取用户信息
						handleUserInfo(platform);
					} else {
						
						Toast.makeText(LoginActivity.this, AppContext.instance().globalContext.getString(R.string.oauth_promt_failed),Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onCancel(SHARE_MEDIA platform) {
					Toast.makeText(LoginActivity.this, AppContext.instance().globalContext.getString(R.string.oauth_promt_cancel),
							Toast.LENGTH_SHORT).show();
				}
			});
	}

	/**
	 * 获取用户信息
	 * 
	 * @param platform
	 */
	private void handleUserInfo(final SHARE_MEDIA platform) {
		mController.getPlatformInfo(LoginActivity.this, platform,
			new UMDataListener() {

				@Override
				public void onStart() {

				}

				@Override
				public void onComplete(int status, Map<String, Object> info) {
					if (info != null) {
						// 根据平台类型，解析用户信息
						AppContext.instance().userService.onLoginUpdateUserInfo(platform, info); 
					}
				}
			});
	}

	/****
	 * 用户监后台事件
	 * 
	 * @param userEvent
	 */
	public void onEventMainThread(UserEvents userEvent) {
		switch (userEvent.eventtype) {
		case UserConst.ENVENTTYPE_LOGIN:
			Toast.makeText(mContext, mContext.getString(R.string.login), Toast.LENGTH_LONG).show();
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public void removeAllView() {

	}

}
