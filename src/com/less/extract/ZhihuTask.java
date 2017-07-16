package com.less.extract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.less.extract.HttpSend.Resback;

import okhttp3.Response;

public class ZhihuTask extends Task<String> {
	
	@Override
	void execute() {
		String url = "http://www.java1234.com";
		Map<String,String> params = new HashMap<>();
		params.put("Referer", "http://www.baidu.com");
		HttpSend.request(url, params, new Resback<Response>() {

			@Override
			public void done(Response t, Exception e) {
				try {
					StepOne(t.body().string());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	@Override
	void StepOne(String param) {
		
	}


	@Override
	void StepTwo(String param) {
		
	}


	@Override
	void StepTree(String param) {
		
	}


	@Override
	void StepFour(String param) {
		
	}
	
	public static void main(String[] args) {
		Task task = TaskFactory.create(ZhihuTask.class);
		new WorkDispatcher().dipatch(task);
	}
}
