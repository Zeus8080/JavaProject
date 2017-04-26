package org.zhu.weixin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.zhu.weixin.message.resp.Article;
/**
 * 
 * @author ZhuChong
 * 获取每日新闻
 */
public class DailyNewsService {
	public static void main(String[] args) {
		List<Article> articles = DailyNewsService.getArticles();
		
		for (Article article : articles) {
			System.out.println("标题:"+article.getTitle());
			System.out.println("新闻Url:"+article.getUrl());
			System.out.println("图片Url:"+article.getPicUrl());
		}
	}
	
	public static List<Article> getArticles(){
		String url = "http://news.163.com/";
		Article a = null;
		List<Article> list = new ArrayList<Article>();
		
		//图片地址
		/**
		 * http://i1.piimg.com/4851/bdfc5ffe51087032.png
		 * http://i1.piimg.com/4851/bf862f2067378bdd.png
		 * http://i1.piimg.com/4851/50f97762ce28198b.jpg
		 */
		String[] imgUrl = {"http://p1.bqimg.com/567571/bf862f2067378bdd.png",
				"http://p1.bqimg.com/567571/bdfc5ffe51087032.png",
				"http://p1.bqimg.com/567571/50f97762ce28198b.jpg"};
		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("ul li.top a");
			int i=0;
			for (Element e : elements) {
				a = new Article();
				a.setPicUrl(imgUrl[i]);
				a.setTitle(e.html());
				a.setUrl(e.attr("href"));
				list.add(a);
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Article article : list) {
			System.out.println("标题:"+article.getTitle());
			System.out.println("新闻Url:"+article.getUrl());
			System.out.println("图片Url:"+article.getPicUrl());
		}
		return list;
		
	}
}
