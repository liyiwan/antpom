package com.yizi.iwuse.order.service;

import com.yizi.iwuse.ICoreService;
import com.yizi.iwuse.comm.model.CmdResultInfo;
import com.yizi.iwuse.comm.service.CmdSendAdapter;
import com.yizi.iwuse.comm.service.MsgInterface.CmdInterface;

public class OrderService implements ICoreService{

	@Override
	public boolean initState() {
		return true;
	}
	
	
	/****
	 * ex. 获取订单信息
	 * 
	 * @return
	 */
	public CmdResultInfo getProductInfo(){
	    
		// EventBus.getDefault().post(null);
		
		return CmdSendAdapter.sendCmd(CmdInterface.ORDER_OrderInfo, null);
	}

}
