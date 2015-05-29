package com.yizi.iwuse.general.service.events;

import java.util.ArrayList;

import com.yizi.iwuse.general.model.ThemeItem;

/**		主题事件类
 * @author hehaodong
 *
 */
public class ThemeEvent {

	/***主题数据***/
	private ArrayList<ThemeItem> themeArray;
	
	public ThemeEvent(ArrayList<ThemeItem> themeArray){
		this.themeArray = themeArray;
	}

	public ArrayList<ThemeItem> getThemeArray() {
		return themeArray;
	}

	public void setThemeArray(ArrayList<ThemeItem> themeArray) {
		this.themeArray = themeArray;
	}
	
	
}
