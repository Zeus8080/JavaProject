package org.zhu.weixin.message.req;
/**
 * 文本类型消息
 * @author 朱冲
 *
 */
public class TextMessage extends BaseMessage{
	//消息内容
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
