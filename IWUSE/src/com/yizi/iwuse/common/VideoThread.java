package com.yizi.iwuse.common;

import com.yizi.iwuse.general.model.ThemeItem;
import com.yizi.iwuse.general.service.events.ThemeEvent;
import com.yizi.iwuse.general.service.events.VideoEvent;
import com.yizi.iwuse.general.view.WuseThemeFragment.ViewHolder;

import de.greenrobot.event.EventBus;
import android.view.View;

public class VideoThread extends Thread {

	private ViewHolder viewHolder;
	
	public VideoThread(ViewHolder viewHolder){
		this.viewHolder = viewHolder;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
			showVideo();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized  void showVideo(){
//    	VideoEvent videoEvent = new VideoEvent();
//    	videoEvent.setViewHolder(viewHolder);
    	EventBus.getDefault().post(viewHolder);
	}

}
