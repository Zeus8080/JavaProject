/**
 * @author ���
 * ͼ����Ϣ
 */
package org.zhu.weixin.message.resp;

import java.util.*;

public class NewsMessage extends BaseMessage{
	//ͼ����Ϣ�ĸ�����������10������
	private int ArticleCount;
	//����ͼ����Ϣ��Ĭ�ϵ�һ����ͼ
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
