package com.less.extract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.less.extract.HttpSend.Resback;

import okhttp3.Request;
import okhttp3.Response;

public class ZhihuTask extends Task {
	
	@Override
	void execute() {
		String url = "http://www.java1234.com";
		Map<String,String> params = new HashMap<>();
		params.put("Referer", "http://www.baidu.com");
		HttpSend.request(url, params, new Resback<Response>() {

			@Override
			public void done(Response t, Exception e) {
				try {
					System.out.println(t.body().string());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	void StepOne(Request request) {
		
	}

	@Override
	void StepTwo(Request request) {
		
	}

	@Override
	void StepTree(Request request) {
		
	}

	@Override
	void StepFour(Request request) {
		
	}

	@Override
	void StepFive(Request request) {
		
	}
	

}
