package com.yizi.iwuse.user.service;

import com.yizi.iwuse.common.base.ICoreService;
import com.yizi.iwuse.framework.model.CmdResultInfo;
import com.yizi.iwuse.framework.service.CmdSendAdapter;
import com.yizi.iwuse.framework.service.MsgInterface.CmdInterface;

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
	
}
