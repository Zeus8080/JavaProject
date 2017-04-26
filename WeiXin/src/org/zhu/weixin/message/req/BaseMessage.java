package org.zhu.weixin.message.req;
/**
 * 消息顶层父类(普通用户->公众号)
 * @author 朱冲
 *
 */
public class BaseMessage {
	//开发者微信号
	private String ToUserName;
	//发送方账号
	private String FromUserName;
	//消息创建的时间（整型）
	private long CreateTime;
	//消息类型（text/img/location/link）
	private String MsgType;
	//消息id
	private long MsgId;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public long getMsgId() {
		return MsgId;
	}
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
	
}
