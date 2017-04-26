/**
 * @author 朱冲
 * 图文消息
 */
package org.zhu.weixin.message.resp;

import java.util.*;

public class NewsMessage extends BaseMessage{
	//图文消息的个数，限制在10条以内
	private int ArticleCount;
	//多条图文消息，默认第一条大图
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	
}
