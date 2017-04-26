package org.zhu.weixin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.zhu.weixin.message.resp.Article;
import org.zhu.weixin.message.resp.NewsMessage;
import org.zhu.weixin.message.resp.TextMessage;
import org.zhu.weixin.util.MessageUtil;

/**
 * �������������ĺ���ҵ���
 * @author ���
 *
 */
public class CoreService {
	/**
	 * ����΢�ŷ�����������
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request){
		String respMessage = null;
		
		
		try {
		
			// Ĭ�Ϸ��ص��ı���Ϣ����
			Map<String, String> requestMap = MessageUtil.paseXML(request);
			StringBuffer sb = new StringBuffer();
			
			sb.append("�ף��Բ���С���ֻ��������У�����Ҫʹ�����·�����\n");
			sb.append("[1] ʵʱ����[΢Ц]\n");
			sb.append("[2] ��ʷ�ϵĽ���[����]\n");
			sb.append("[3] ���ʷ���[��Ц]\n");
			sb.append("�ظ�����ʾ�˲˵�[ǿ]\n");
			String respContent = sb.toString();

//			// xml�������
//			System.out.println(requestMap);
			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			//��Ϣ����
			String content = requestMap.get("Content");
			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			
			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				
				if("1".equals(content)){
					//�ظ�ͼ����Ϣ
					NewsMessage news = new NewsMessage();
					news.setToUserName(fromUserName);
					news.setFromUserName(toUserName);
					news.setCreateTime(new Date().getTime());
					news.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					news.setFuncFlag(0);
					
					List<Article> list = DailyNewsService.getArticles();
					news.setArticleCount(list.size());
					news.setArticles(list);
					//��ͼ����Ϣ��ת��Ϊxml�ַ���
					respMessage = MessageUtil.newsMessageToXML(news);
					
					return respMessage;
				}
				
				if("2".equals(content)){
					System.out.println("--------");
					respContent = null;
					respContent = HistoryInTodayService.getHistory();
				}
				if("3".equals(content)){
					StringBuffer ssb = new StringBuffer();
					ssb.append("�������ֻ�����[����]ֻ��ʵ��Ӣ��->���ĵķ���\n");
					ssb.append("ʾ��������hello\n");
					ssb.append("�ظ���������ʾ�˵�");
					respContent = ssb.toString();
				}
			    if (content.startsWith("����")) {  
			        String keyWord = content.replaceAll("^����", "").trim();  
			        String result = Translator.getResult(Translator.getURL(keyWord, "en", "zh"));
			        respContent = result; 
			    } 
					
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע��";
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXML(textMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return respMessage;
	}
}
