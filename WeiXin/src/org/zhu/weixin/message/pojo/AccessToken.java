package org.zhu.weixin.message.pojo;

/**
 * ΢��ͨ��ƾ֤�ӿ�
 * @author zhuchong
 *
 */
public class AccessToken {
	//��ȡ��ƾ֤
	private String token;
	//ƾ֤��Чʱ�� (��λ��)
	private int expiresIn;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}
