package org.zhu.weixin.message.req;
/**
 * ����λ����Ϣ
 * @author ���
 *
 */
public class LocationMessage extends BaseMessage {
	//γ��
	private String Location_X;
	//����
	private String Location_Y;
	//��ͼ���Ŵ�С
	private String Scale;
	//λ����Ϣ
	private String Label;
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	
	
}
