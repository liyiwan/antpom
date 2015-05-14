package com.yizi.iwuse.utils;


import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.yizi.iwuse.AppContext;
import com.yizi.iwuse.R;

/****
 * 图片加载器
 * 
 * @author zhangxiying
 *
 */
public class ImageUtil {
	
	/**图片加载器*/
	private static ImageLoader imageLoader = null;
	
	/****
	 * 定义一个公用的图片加载器
	 * 
	 * @return
	 */
	public static ImageLoader getImageLoader(){
		if(imageLoader==null){
			 imageLoader = new ImageLoader(AppContext.instance().requestQueue,new BitmapCache());
		}
		return imageLoader;
	}
	
	
}
