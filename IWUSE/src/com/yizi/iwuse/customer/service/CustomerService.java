package com.yizi.iwuse.customer.service;

import com.yizi.iwuse.ICoreService;
import com.yizi.iwuse.comm.model.CmdResultInfo;
import com.yizi.iwuse.comm.service.CmdSendAdapter;
import com.yizi.iwuse.comm.service.MsgInterface.CmdInterface;

public class CustomerService implements ICoreService {

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
