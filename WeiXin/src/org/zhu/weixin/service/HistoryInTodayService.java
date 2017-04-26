package org.zhu.weixin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 
 * @author 朱冲
 * 获取历史上的今天的信息
 *
 */
public class HistoryInTodayService {
	public static void main(String[] args) {
		System.out.println(HistoryInTodayService.getHistory());
	}
	
	//获取"http://www.lssdjt.com/"的网页
	public static String getHtml(){
		StringBuffer sb = null;
		HttpURLConnection conn = null;
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URL url = new URL("http://www.lssdjt.com/");
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			in = conn.getInputStream();
			isr = new InputStreamReader(in, "utf-8");
			br = new BufferedReader(isr);
			
			//处理结果
			sb = new StringBuffer();
			String s = null;
			while((s = br.readLine()) != null){
				sb.append(s);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(isr != null){
				try {
					isr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				conn.disconnect();
			}
		}
		return sb.toString();
	}
	
	//过滤网页上无用的内容，获取历史上的今天
	public static String getHistory(){
		StringBuffer sb = new StringBuffer();
		String str1 = HistoryInTodayService.getHtml();
		Document doc = Jsoup.parse(str1);
		sb.append("**").append(doc.select("h4 b").get(0).html()).append("**").append("\n");
		Elements e = doc.getElementsByAttributeValue("class", "list clearfix");
		for (Element el : e) {
			Elements times = el.select("li a em");
			Elements history = el.select("li a i");
			for(int i=0;i<times.size()-20;i++){
				sb.append("  "+(i+1)+"."+times.get(i).html()+history.get(i).html()+"\n");
			}
		}
		return sb.toString();
	}
}
