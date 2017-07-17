package com.less.extract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.less.extract.HttpSend.Resback;

import okhttp3.Response;

public class AsjyyTask extends Task<String> {
	
	@Override
	void execute() {
		String url = "http://www.asjyy.com/vod-detail-id-461756.html"; // 攻壳机动队
		String regex = "id-(\\d+).html";
		Matcher matcher = Pattern.compile(regex).matcher(url);
		while(matcher.find()){
			String id = matcher.group(1);
			StepOne(id);
		}
	}
	
	@Override
	void StepOne(String param) {
		String url = "http://www.asjyy.com/vod-play-id-" + param + "-src-1-num-1.html"; // 攻壳机动队
		Map<String,String> params = new HashMap<>();
		params.put("Referer", "http://www.asjyy.com/");
		HttpSend.request(url, params, new Resback<Response>() {

			@Override
			public void done(Response t, Exception e) {
				if(e == null){
					try {
						String content = t.body().string();
						
						String regex_id = "mac_from='(\\w+)\\$\\$\\$qq'";
						String regex_video = "mac_url=unescape(.+)'"; 
						Matcher matcher_id = Pattern.compile(regex_id).matcher(content);
						Matcher matcher_video = Pattern.compile(regex_video).matcher(content);
						while(matcher_id.find() && matcher_video.find()){
							String id = matcher_id.group(1);
//							System.out.println(id);
							
							String video = matcher_video.group().replaceAll("mac_url=unescape\\('%u7f51%u76d8%u64ad%u653e%24", "").replaceAll("%24%24%24.+", "");
							StepTwo(video);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}


	@Override
	void StepTwo(String param) {
		String url = "https://player.asjyy.com/ty189.php?url=" + param; 
		System.out.println("GET " + url);
		System.exit(0);
	}


	@Override
	void StepTree(String param) {
		// nothing to do
	}
	
	public static void main(String[] args) {
		Task task = TaskFactory.create(AsjyyTask.class);
		new WorkDispatcher().dipatch(task);
	}
}
