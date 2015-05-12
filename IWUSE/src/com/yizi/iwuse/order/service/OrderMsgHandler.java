package com.yizi.iwuse.order.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.yizi.iwuse.comm.handler.AbsCmdMsgHandler;
import com.yizi.iwuse.comm.model.CmdResultInfo;
import com.yizi.iwuse.comm.service.MsgInterface.CmdInterface;
import com.yizi.iwuse.constants.PublicConst;

public class OrderMsgHandler extends AbsCmdMsgHandler {

	@Override
	public String getSendMsdDetail(CmdInterface absCmd, String cmdStr,Object bean) throws JSONException {
		JSONObject json = new JSONObject();

        switch (absCmd)
        {
	        case ORDER_OrderInfo:
	        	//json.put(name, value);
	        	break;
        }
        
        return json.toString();
	}

	@Override
	public CmdResultInfo handleCmdRsp(CmdInterface cmd,List<CmdResultInfo> results) {
		CmdResultInfo info = results.get(0);
        if (info.faultNo != PublicConst.RSP_OK_CODE)
        {
            return info;
        }
        JSONObject resInfo = info.resInfo;
        switch (cmd)
        {
            case ORDER_OrderInfo: // 无参数
            {
            	
            }
        }
        
        return info;
	}

}
