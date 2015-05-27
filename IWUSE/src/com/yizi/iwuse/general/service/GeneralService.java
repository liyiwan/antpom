package com.yizi.iwuse.general.service;

import java.util.List;

import com.yizi.iwuse.common.base.ICoreService;
import com.yizi.iwuse.framework.model.CmdResultInfo;
import com.yizi.iwuse.framework.service.CmdSendAdapter;
import com.yizi.iwuse.framework.service.MsgInterface;
import com.yizi.iwuse.framework.service.MsgInterface.CmdInterface;

/****
 * 公共/综合业务类
 * 
 * @author zhangxiying
 *
 */
public class GeneralService implements ICoreService{

	@Override
	public boolean initState() {
		return true;
	}
	
	public List getThemeImg(){
		CmdResultInfo retObj =  CmdSendAdapter.sendCmd(CmdInterface.General_AdvertGuide, null);
		
		return null;
	}
	
	

}
