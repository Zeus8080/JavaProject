package org.zhu.weixin.message.resp;
/**
 * �ı�������Ϣ
 * @author ���
 *
 */
public class TextMessage extends BaseMessage{
	//��Ϣ����
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}
	
}
