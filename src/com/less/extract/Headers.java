package com.less.extract;

import java.util.HashMap;

public class Headers {
	private static HashMap<String, String> hashMap = new HashMap<>();
	private static final String COOKIE = "";
	private static final String REFERER = "http://www.x.com/";
	
	public static enum KEY{
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

	static{
		hashMap.put("Cookie", COOKIE );
		hashMap.put("Referer", REFERER);
		hashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		hashMap.put("Accept-Encoding", "gzip, deflate, sdch");
		hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
	}
	public static String defaultHeader(String key){
		return hashMap.get(key);
	}
	
	
}
