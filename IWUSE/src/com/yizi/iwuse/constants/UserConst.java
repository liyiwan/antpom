package com.yizi.iwuse.constants;

/****
 * 用户常量
 * 
 * @author zhangxiying
 *
 */
public class UserConst {
	
	/**用户事件类型-登录**/
	public static final int ENVENTTYPE_LOGIN = 0x500001;
	
	 public static final String LOGIN_YOUMENG = "com.umeng.login";
	
	/**~~~~~~~~~~~~~~授权登录~~~~~~~~~~~~~~~**/
	//微信
	 //应用标识
	public static final String OAUTH_WEICHAT_APPID = "wxd240c43dd010f108";
	//应用密钥
	public static final String OAUTH_WEICHAT_SECRET = "8b0423e8272fecafbb61ece531c03d05";
	 //授权作用域
	public static final String OAUTH_WEICHAT_SCOPE = "all";
	//保持请求和回调的状态
	public static final String OAUTH_WEICHAT_STATE = "weichat_iwuse_0x9891";
	/**微信授权登录成功，获取数据**/
	//第一步URL
	public static final String OAUTH_WEICHAT_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";
													//appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"
	//QQ
	 //应用标识
	public static final String OAUTH_QQ_APPID = "1104654550";
	
	public static final String OAUTH_QQ_APPKEY = "bBt8igECdhIH2aGU";
	
	public static final String OAUTH_QQ_SCOPE = "get_user_info,add_t";
	
	//新浪微博
	public static final String OAUTH_WEIBO_APPID = "1516988532";
	public static final String OAUTH_WEIBO_SECRET = "57e7ad9d11911632a0709d5bc1d166dc";
	
	
	
	
}
