package com.yizi.iwuse.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
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
	
	/**用户ID/手机号**/
	@ViewInject(R.id.edt_userid)
	private EditText edt_userid;
	/**用户ID/手机号**/
	@ViewInject(R.id.edt_password)
	private EditText edt_password;
	/**用户ID/手机号**/
	@ViewInject(R.id.btn_login)
	private Button btn_login;
	/**用户ID/手机号**/
	@ViewInject(R.id.txt_regist)
	private TextView txt_regist;
	/**用户ID/手机号**/
	@ViewInject(R.id.txt_forgetuserinfo)
	private TextView txt_forgetuserinfo;
	/**用户ID/手机号**/
	@ViewInject(R.id.btn_oauth_weichat)
	private Button btn_oauth_weichat;
	/**用户ID/手机号**/
	@ViewInject(R.id.btn_oauth_qq)
	private Button btn_oauth_qq;
	/**用户ID/手机号**/
	@ViewInject(R.id.btn_oauth_paybao)
	private Button btn_oauth_paybao;
	/**用户ID/手机号**/
	@ViewInject(R.id.btn_oauth_weibo)
	private Button btn_oauth_weibo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.layout_usercenter_login);
		/**注册xUtils UI框架**/
		ViewUtils.inject(this);
		/**注册EventBus**/
		EventBus.getDefault().register(this);
		
	}
	
	
	/****
	 * 登录事件
	 * 通过xUtils绑定View事件
	 * @param view
	 */
	@OnClick({R.id.btn_login,R.id.txt_regist,R.id.txt_forgetuserinfo
		,R.id.btn_oauth_weichat,R.id.btn_oauth_qq,R.id.btn_oauth_paybao,R.id.btn_oauth_weibo})
	public void onLoginClickListener(View view){
		switch(view.getId()){
			case R.id.btn_login:
				 
				 break;
			case R.id.txt_regist:
				break;
			case R.id.txt_forgetuserinfo:
				break;
			case R.id.btn_oauth_weichat:
				break;
			case R.id.btn_oauth_qq:
				break;
			case R.id.btn_oauth_paybao:
				break;
			case R.id.btn_oauth_weibo:
				break;
			default:
					break;
		}
		
	}
	
	/****
	 * 用户监后台事件
	 * 
	 * @param userEvent
	 */
	public void onEventMainThread(UserEvents userEvent){
		 switch(userEvent.eventtype){
		 	case UserConst.enventtype_login:
		 		
		 		
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
