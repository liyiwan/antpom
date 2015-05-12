package com.yizi.iwuse.comm.service;

import com.yizi.iwuse.utils.ILog;


/**
 * 抽象过的所有发送给服务器的命令
 */
public class MsgInterface
{
    /** The logging tag used by this class with ILog. */
    protected static final String TAG = "MsgInterface";

    /*
     * @param fisSync 是同步消息还是异步命令
     * 
     * @param fwaitMailId 如果是异步，则对应的的邮箱的id
     * 
     */
    public enum CmdInterface {
    	/** 请求邮箱消息的命令字 */
        GetMail_BoxData,
        // ------------------用户登陆相关----------begin------------------
        CUSTOMER_DetailInfo,
        
        /** 身份验证 */
        LOGIN_Authentication,
        /** 获取对接子设备类型 */
        LOGIN_GetSubDevice,
        /** 获取对接终端的lisence */
        LOGIN_GetLicenseInfo,
        
        /** 订阅邮箱 */
        LOGIN_subscribeMailData, LOGIN_subscribeMailMsg,
        // ------------------用户登陆相关-----------end------------------
        
        // ------------------公共模块----------------------------
        /** 发送日志的命令 */
        PUBLIC_SendLogCmd,
        
        //----------------------商品-------------begin---------------------/
        PRODUCT_ProductInfo,
        //----------------------商品-------------end-----------------------/
        
        //----------------------订单-------------begin---------------------/
        ORDER_OrderInfo, 
        //----------------------订单-------------end-----------------------/
        
        unknown;
        
        protected static final String TAG = "CmdInterface";
        /** 是否为同步命令 */
        private boolean isSync = true;

        /** 对接设备对应的命令 */
        private String[] cmds;

        /** 对应的邮箱消息 */
        private String waitMailId;

        /**
         * true@WEB_GetMailBoxData|WEB_GetMailBoxData@null 是否为同步@具体的命令
         * @param fisSync 是同步消息还是异步命令
         * @param fwaitMailId 如果是异步，则对应的的邮箱的id
         * @param fcmdHost 主机对应的命令
         * @param fcmdVct 终端对应的命令
         */

        /**
         * 从命令字符串转为命令枚举
         * @param cmd 命令字符串
         * @return 对应的命令枚举
         */
        public static CmdInterface getInstance(String cmd)
        {
            try
            {
                return CmdInterface.valueOf(cmd);
            }
            catch (Exception e)
            {
                return unknown;
            }
        }

		/**
		 * 根据抽象的命令得到和设备对接的具体的命令字符串
		 * 
		 * @param cmd
		 *            抽象的命令字
		 * @return 具体连接设备对应的命令
		 */
		public String[] getCmds(CmdInterface cmd) {
			switch (cmd) {
			// ~~~~~~~~~~~~~~~~~登录相关~~~~~~~~~~~~~~~~~~~~
			case CUSTOMER_DetailInfo:
				return new String[]
						{"customer_detailinfo"};
			case LOGIN_Authentication:
				return new String[] 
						{ "Web_RequestCertificate" };
			case LOGIN_GetSubDevice:
				return new String[] 
						{ "ConfGetTh1000Version" };
			case LOGIN_subscribeMailData:
				return new String[] {}; // 无
				
			//~~~~~~~~~~~~~~~~~~商品相关~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case PRODUCT_ProductInfo:
				return new String[]
						{"PRODUCT_ProductInfo"};
				
			//~~~~~~~~~~~~~~~~~~~订单~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case ORDER_OrderInfo:
				return new String[]
						{"order_orderinfo"};
				/** 发送日志的命令 */
			case PUBLIC_SendLogCmd:
				return new String[] 
						{ "ConfSendHidLog" };
			default:
				break;
			}
			ILog.e(TAG,
					" Not any command match the command:"
							+ cmd.name());
			return null;
		}
	}

    public static String[] FilterMsg = new String[]
    { "MSG_SYSLOG_RSP" };
}
