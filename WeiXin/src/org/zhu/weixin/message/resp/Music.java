/**
 * @author ���
 * ������
 */
package org.zhu.weixin.message.resp;

public class Music {
	//��������
	private String Title;
	//��������
	private String Description;
	//��������
	private String Link;
	//��Ʒ����������,wifi������ʹ�����
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
