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
 * 处理服务器请求的核心业务层
 * @author 朱冲
 *
 */
public class CoreService {
	/**
	 * 处理微信发过来的请求
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request){
		String respMessage = null;
		
		
		try {
		
			// 默认返回的文本消息内容
			Map<String, String> requestMap = MessageUtil.paseXML(request);
			StringBuffer sb = new StringBuffer();
			
			sb.append("亲，对不起！小助手还在完善中，您是要使用以下服务吗？\n");
			sb.append("[1] 实时新闻[微笑]\n");
			sb.append("[2] 历史上的今天[呲牙]\n");
			sb.append("[3] 单词翻译[奸笑]\n");
			sb.append("回复？显示此菜单[强]\n");
			String respContent = sb.toString();

//			// xml请求解析
//			System.out.println(requestMap);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			//消息内容
			String content = requestMap.get("Content");
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				
				if("1".equals(content)){
					//回复图文消息
					NewsMessage news = new NewsMessage();
					news.setToUserName(fromUserName);
					news.setFromUserName(toUserName);
					news.setCreateTime(new Date().getTime());
					news.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					news.setFuncFlag(0);
					
					List<Article> list = DailyNewsService.getArticles();
					news.setArticleCount(list.size());
					news.setArticles(list);
					//将图文消息类转换为xml字符串
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
					ssb.append("翻译助手还很弱[可怜]只能实现英文->中文的翻译\n");
					ssb.append("示例：翻译hello\n");
					ssb.append("回复“？”显示菜单");
					respContent = ssb.toString();
				}
			    if (content.startsWith("翻译")) {  
			        String keyWord = content.replaceAll("^翻译", "").trim();  
			        String result = Translator.getResult(Translator.getURL(keyWord, "en", "zh"));
			        respContent = result; 
			    } 
					
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
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
