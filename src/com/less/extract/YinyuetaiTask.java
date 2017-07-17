package com.less.extract;

import org.json.JSONArray;
import org.json.JSONObject;

import com.less.extract.HttpSend.Resback;

import okhttp3.Response;

public class YinyuetaiTask extends Task<String> {

	@Override
	void execute() {
		String keyword = "T-are";
		int pageIndex = 0;
		String url = "http://so.yinyuetai.com/search/playlist-search?callback=jsonp7887223285&_api=get.sheetList&keyword="
				+ keyword + "&pageIndex=" + pageIndex + "&pageSize=10";
		HttpSend.request(url, null, new Resback<Response>() {

			@Override
			public void done(Response response, Exception e) {
				if (e == null) {
					try {
						String content = response.body().string();
						String json = content.substring(0, content.length() - 1).replaceAll("jsonp7887223285\\(", "");

						JSONObject root = new JSONObject(json);
						JSONArray datas = root.optJSONObject("playlist").optJSONArray("data");

						for (int i = 0; i < datas.length(); i++) {
							JSONObject data = datas.optJSONObject(i);

							int playlistId = data.optInt("id"); // 个人分享上传的ID
							String title = data.optString("title");
							String playlistImg = data.optString("playlistImg");

							StepOne(playlistId + "||" + title + "||" + playlistImg);
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			}
		});
	}

	@Override
	void StepOne(String param) {
		String videoInfo[] = param.split("\\|\\|");

		String playlistId = videoInfo[0];
		String title = videoInfo[1];
		String plalistImg = videoInfo[2];

		String url = "http://m.yinyuetai.com/mv/get-simple-playlist-info?callback=jsonp4&playlistId=" + playlistId;
		HttpSend.request(url, null, new Resback<Response>() {

			@Override
			public void done(Response response, Exception e) {
				if (e == null) {
					try {
						String content = response.body().string();

						String json = content.substring(0, content.length() - 1).replaceAll("jsonp4\\(", "");

						JSONObject root = new JSONObject(json);
						JSONArray videos = root.optJSONObject("playlistInfo").optJSONArray("videos");

						for (int i = 0; i < videos.length(); i++) {
							JSONObject video = videos.optJSONObject(i).optJSONObject("videoModel");
							String title = video.optString("content"); // 个人分享上传的ID
							String videoUrl = video.optString("videoUrl");
							String videoUrl2 = video.optString("videoUrl2", "none");
							String videoUrl3 = video.optString("videoUrl3", "none");

							StepTwo(title + "||" + videoUrl + "||" + videoUrl2 + "||" + videoUrl3);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}

	@Override
	void StepTwo(String param) {
		String video [] = param.split("\\|\\|");
		String title = video[0];
		String url1 = video[1];
		String url2 = video[2];
		String url3 = video[3];
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("========= "+title+" ========= \r\n");
		stringBuilder.append(url1+"\r\n");
		stringBuilder.append(url2+"\r\n");
		stringBuilder.append(url3+"\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("\r\n");
		
		System.out.println(stringBuilder.toString());
		// 具体视频
		// http://m.yinyuetai.com/mv/get-simple-video-info?videoId=4030068&callback=__jsonp__w6bju
	}

	@Override
	void StepTree(String param) {

	}

	public static void main(String[] args) {
		Task task = TaskFactory.create(YinyuetaiTask.class);
		new WorkDispatcher().dipatch(task);
	}

}
