package org.zhu.weixin.util.translate;

import java.util.List;

public class Translate {
    // ʵ�ʲ��õ�Դ����  
    private String from;  
    // ʵ�ʲ��õ�Ŀ������  
    private String to;  
    // �����  
    private List<ResultPair> trans_result;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public List<ResultPair> getTrans_result() {
		return trans_result;
	}
	public void setTrans_result(List<ResultPair> trans_result) {
		this.trans_result = trans_result;
	}
    
}
