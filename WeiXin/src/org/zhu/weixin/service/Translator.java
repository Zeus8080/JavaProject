package org.zhu.weixin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.zhu.weixin.util.translate.Translate;

import com.google.gson.Gson;
import com.ndktools.javamd5.Mademd5;

//ʵ��С���ַ��빦��
public class Translator {
	public static void main(String[] args) {
		
		System.out.println(getResult(getURL("human", "en", "zh")));
	}

	//��ȡ�ٶȷ���api��URL
	public static String getURL(String toBeTranslate,String from, String to){
		
		try {
			toBeTranslate = URLEncoder.encode(toBeTranslate, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Mademd5 mad=new Mademd5();
		String sign;//��ȡǩ��
		String appId = "20161204000033361";//appid
		String key = "peBLgOcgf5xyNW1k91xh";//��Կ
		String salt = String.valueOf(System.currentTimeMillis());//�����
		String string1 = appId + toBeTranslate + salt + key;
		sign = (mad.toMd5(string1)).toLowerCase();
		
		String url = "http://api.fanyi.baidu.com/api/trans/vip/translate?q="+
				toBeTranslate + "&from=" + from + "&to=" + to +
				"&appid=" + appId + "&salt=" + salt+"&sign="+sign;
		String uri = null;
		try {
			uri = new String(url.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;
	}

	//��ȡ������
	public static String getResult(String url){
		InputStream inputStream = null;
		BufferedReader reader = null;
		String res = null;
		String dst = null;//����
		try {
			//����api
			URL uri = new URL(url);
			inputStream = uri.openStream();
			//��ȡapi����ֵ
			reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while((line = reader.readLine())!=null){
				sb.append(line);
			}
			res = sb.toString();//json�ַ���
			System.out.println(res);
			Gson g = new Gson();
			Translate translate = g.fromJson(res, Translate.class);
			dst = translate.getTrans_result().get(0).getDst();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return dst;
	}
}
