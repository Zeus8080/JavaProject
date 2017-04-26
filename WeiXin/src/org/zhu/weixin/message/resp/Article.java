/**
 * @author 朱冲
 * 图文消息中的单个消息
 */
package org.zhu.weixin.message.resp;

public class Article {
	//消息名称
	private String Title;
	//消息描述
	private String Description;
	//图片链接
	private String PicUrl;
	//点击跳转的链接
	private String Url;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return null==Description ? "空" : Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return null==PicUrl ? "空" : PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return null==Url ? "空" : Url;
	}
	public void setUrl(String url) {
		Url = url;
	}

}
