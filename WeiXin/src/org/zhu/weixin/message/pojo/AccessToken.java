package org.zhu.weixin.message.pojo;

/**
 * 微信通用凭证接口
 * @author zhuchong
 *
 */
public class AccessToken {
	//获取的凭证
	private String token;
	//凭证有效时间 (单位秒)
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
