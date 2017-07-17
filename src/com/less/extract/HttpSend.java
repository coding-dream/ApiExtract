package com.less.extract;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpSend {
	private static OkHttpClient okhttp;
	private static HashMap<String, String> hashMap = new HashMap<>();
	
	static{
		hashMap.put("Cache-Control", "max-age=0");
		hashMap.put("Connection", "keep-alive");
		hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		hashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		hashMap.put("Accept-Language", "zh-CN,zh;q=0.8");
//		hashMap.put("Accept-Encoding", "gzip, deflate, sdch");
		
		init();
	}
	
	private static enum KEY {
//		Referer("Referer"),
//		Cookie("Cookie"),
//		Accept_Encoding("Accept-Encoding"),
		
		Cache_Control("Cache-Control"),
		Connection("Connection"),
		Accept("Accept"),
		Accept_Language("Accept-Language"),
		User_Agent("User-Agent");
		
		private String v ;
		KEY(String value){
			this.v = value;
		}
		public String vV(){
			return v;
		}
		
	}

	public static void request(String url,Map<String,String>params,Resback<Response> resback){
		Request.Builder build = new Request.Builder()
				.url(url)
				.addHeader(KEY.User_Agent.vV(), defaultHeader(KEY.User_Agent.vV()))
				.addHeader(KEY.Accept.vV(), defaultHeader(KEY.Accept.vV()))
				.addHeader(KEY.Accept_Language.vV(), defaultHeader(KEY.Accept_Language.vV()))
				.addHeader(KEY.Connection.vV(), defaultHeader(KEY.Connection.vV()))
				.addHeader(KEY.Cache_Control.vV(), defaultHeader(KEY.Cache_Control.vV()));
		if(params != null){
			for(String key : params.keySet()){
				build.addHeader(key, params.get(key));
			}
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
	
	public interface Resback<Response>{
		void done(Response response,Exception e);
	}

	private static String defaultHeader(String key){
		return hashMap.get(key);
	}
	
	/**
	 * okhttp3 忽略证书
	 */
	private static void init(){
		X509TrustManager xtm = new X509TrustManager() {
	        @Override
	        public void checkClientTrusted(X509Certificate[] chain, String authType) {
	        }

	        @Override
	        public void checkServerTrusted(X509Certificate[] chain, String authType) {
	        }

	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            X509Certificate[] x509Certificates = new X509Certificate[0];
	            return x509Certificates;
	        }
	    };

	    SSLContext sslContext = null;
	    
	    try {
	        sslContext = SSLContext.getInstance("SSL");

	        sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (KeyManagementException e) {
	        e.printStackTrace();
	    }
	    
	    HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
	        @Override
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    };	
	    
	    okhttp = new OkHttpClient.Builder()
	    		.sslSocketFactory(sslContext.getSocketFactory())
	    		.hostnameVerifier(DO_NOT_VERIFY)
	    		.build();
	}
	
	
}
