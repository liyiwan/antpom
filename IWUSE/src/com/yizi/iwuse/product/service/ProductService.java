package com.yizi.iwuse.product.service;

import com.yizi.iwuse.ICoreService;
import com.yizi.iwuse.comm.model.CmdResultInfo;
import com.yizi.iwuse.comm.service.CmdSendAdapter;
import com.yizi.iwuse.comm.service.MsgInterface.CmdInterface;

import de.greenrobot.event.EventBus;

public class ProductService implements ICoreService{

	@Override
	public boolean initState() {
		return true;
	}
	
	/****
	 * ex. 获取商品信息
	 * 
	 * @return
	 */
	public CmdResultInfo getProductInfo(){
	    
		// EventBus.getDefault().post(null);
		
		return CmdSendAdapter.sendCmd(CmdInterface.PRODUCT_ProductInfo, null);
	}

}
