package org.zhu.weixin.message.resp;
/**
 * 文本类型消息
 * @author 朱冲
 *
 */
public class TextMessage extends BaseMessage{
	//消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}
	
}
