package com.yizi.iwuse.user.service;

import java.util.Map;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.AppParams;
import com.yizi.iwuse.common.base.ICoreService;
import com.yizi.iwuse.constants.UserConst;
import com.yizi.iwuse.framework.model.CmdResultInfo;
import com.yizi.iwuse.framework.service.CmdSendAdapter;
import com.yizi.iwuse.framework.service.MsgInterface.CmdInterface;
import com.yizi.iwuse.user.model.ThridUserInfo;
import com.yizi.iwuse.user.service.events.UserEvents;

import de.greenrobot.event.EventBus;

public class UserService implements ICoreService {

	@Override
	public boolean initState() {
		return true;
	}

	/****
	 * ex. 获取用户信息
	 * 
	 * @return
	 */
	public CmdResultInfo getCustomerInfo(){
		// EventBus.getDefault().post(null);
		return CmdSendAdapter.sendCmd(CmdInterface.CUSTOMER_DetailInfo, null);
	}
	
	/****
	 * 登录成功后，更新用户信息
	 * 
	 */
	public void onLoginUpdateUserInfo(SHARE_MEDIA platform,Map<String, Object> info){
		ThridUserInfo userInfo = new ThridUserInfo();
		switch(platform){
			case WEIXIN:
				break;
			case QQ:
				break;
			case SINA:
				break;
			default:break;
		}
		// 修改用户全局登录状态为已登录
		AppParams.isLogin = true;
		
		UserEvents userEvent = new UserEvents();
		userEvent.eventtype = UserConst.ENVENTTYPE_LOGIN;
		userEvent.customObj = userInfo;
		
		EventBus.getDefault().post(userEvent);
	}
	
	/***
	 * 注销用户，更新用户信息
	 * 
	 */
	public void onLogoutUpdateUserInfo(){
		
		
	}
	
	
}
