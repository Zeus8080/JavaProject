/**
 * @author 朱冲
 * 音乐类
 */
package org.zhu.weixin.message.resp;

public class Music {
	//音乐名字
	private String Title;
	//音乐描述
	private String Description;
	//音乐链接
	private String Link;
	//高品质音乐链接,wifi下优先使用这个
	private String HQLink;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	public String getHQLink() {
		return HQLink;
	}
	public void setHQLink(String hQLink) {
		HQLink = hQLink;
	}
	
	
}
