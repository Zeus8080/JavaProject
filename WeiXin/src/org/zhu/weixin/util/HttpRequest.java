package org.zhu.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequest {
	public static void getReturnJson(String apiKey,String apiSecret, String urls) throws UnsupportedEncodingException{
		//拼装符合api的url
		String urlRequest = "https://api.megvii.com/facepp/v3/detect?api_key=API_KEY&api_secret=API_SECRET&url=URL";
		urlRequest = urlRequest.replace("API_KEY", apiKey);
		urlRequest = urlRequest.replace("API_SECRET", apiSecret);
		urlRequest = urlRequest.replace("URL", java.net.URLEncoder.encode(urls, "UTF-8"));
		System.out.println(urlRequest);
		//链接url,获取返回值
		StringBuffer sb = new StringBuffer();
		URL url = null;
		HttpURLConnection openConnection = null;
		InputStream inputStream = null;
		BufferedReader bf = null;
		InputStreamReader inputStreamReader = null;
		try {
			url = new URL(urlRequest);
			openConnection = (HttpURLConnection) url.openConnection();
			openConnection.setDoInput(true);
			openConnection.setRequestMethod("POST");
			openConnection.connect();
			
			//将返回的转化为字符串
			inputStream = openConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bf = new BufferedReader(inputStreamReader);
			
			String str = null;
			while((str=bf.readLine()) != null){
				sb.append(str);
			}
			System.out.println(sb.toString());
			bf.close();
			inputStream.close();
			openConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//getReturnJson("s1W4oJE4zvHYtbyJmV1Hc0OlnF_Ix-24", "GUvUTekFrATxJLmUXPXByJOpFUwKcnQo", "http://zcc.ngrok.cc/WeiXin/img/female.jpg");
	}
}
