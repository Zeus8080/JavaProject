package org.zhu.weixin.message.req;
/**
 * ��Ϣ���㸸��(��ͨ�û�->���ں�)
 * @author ���
 *
 */
public class BaseMessage {
	//������΢�ź�
	private String ToUserName;
	//���ͷ��˺�
	private String FromUserName;
	//��Ϣ������ʱ�䣨���ͣ�
	private long CreateTime;
	//��Ϣ���ͣ�text/img/location/link��
	private String MsgType;
	//��Ϣid
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
