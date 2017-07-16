package com.less.extract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpSend {
	private static OkHttpClient okhttp = new OkHttpClient();
	private static HashMap<String, String> hashMap = new HashMap<>();
	
	static{
		hashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//		hashMap.put("Accept-Encoding", "gzip, deflate, sdch");
		hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
	}
	private static enum KEY {
		Cookie("Cookie"),
		Referer("Referer"),
		Accept("Accept"),
		Accept_Encoding("Accept-Encoding"),
		User_Agent("User-Agent");
		
		private String v ;
		KEY(String value){
			this.v = value;
		}
		public String vV(){
			return v;
		}
		
	}

	public static void request(String url,Map<String,String>params,Resback resback){
		Request.Builder build = new Request.Builder()
				.url(url)
				.addHeader(KEY.User_Agent.vV(), defaultHeader(KEY.User_Agent.vV()))
				.addHeader(KEY.Accept.vV(), defaultHeader(KEY.Accept.vV()));
		
		for(String key : params.keySet()){
			build.addHeader(key, params.get(key));
		}
		okhttp.newCall(build.build()).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call call, Response res) throws IOException {
				resback.done(res, null);
			}
			
			@Override
			public void onFailure(Call call, IOException ex) {
				System.out.println(ex.getMessage());
				resback.done(null, ex);
			}
		});
	}
	
	public interface Resback<T>{
		void done(T t,Exception e);
	}

	private static String defaultHeader(String key){
		return hashMap.get(key);
	}
	
}
