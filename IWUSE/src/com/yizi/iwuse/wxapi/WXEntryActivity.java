package com.yizi.iwuse.wxapi;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.R;
import com.yizi.iwuse.common.utils.ILog;
import com.yizi.iwuse.constants.UserConst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/****
 * 微信授权登录
 * 
 * @author zhangxiying
 *
 */
public class WXEntryActivity extends Activity {
	private static final String TAG = "WXEntryActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    /****
     * 处理微信官方返回的结果信息
     * 
     * @param intent
     */
    private void handleIntent(Intent intent) {
        SendAuth.Resp resp = new SendAuth.Resp(intent.getExtras());
        switch(resp.errCode){
        	case BaseResp.ErrCode.ERR_OK:
        		//用户授权成功，从微信端获取用户唯一标识信息
        		HttpUtils http = new HttpUtils();
        		String oauth_weichat_url_step1 = UserConst.OAUTH_WEICHAT_URL
        				+"appid="+UserConst.OAUTH_WEICHAT_APPID+"&secret="+UserConst.OAUTH_WEICHAT_APPID+"&code="+resp.code
        				+"&grant_type=authorization_code";
        		http.send(HttpMethod.GET, oauth_weichat_url_step1, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException exception, String info) {
						 Toast.makeText(AppContext.instance().globalContext, 
								 AppContext.instance().globalContext.getString(R.string.prompt_oauth_weichat_exception), Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						try {
							JSONObject resJson = new JSONObject(responseInfo.result);
							
						} catch (JSONException e) {
							ILog.e(TAG, e);
						}
					}
        			
				});
        		
        		
        	break;
        }
    }
	
	
}
