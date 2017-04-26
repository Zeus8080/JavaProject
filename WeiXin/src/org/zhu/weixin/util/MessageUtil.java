package org.zhu.weixin.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.zhu.weixin.message.resp.Article;
import org.zhu.weixin.message.resp.MusicMessage;
import org.zhu.weixin.message.resp.NewsMessage;
import org.zhu.weixin.message.resp.TextMessage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 
	 */
	private static XStream xstream = new XStream(new XppDriver(){
		public HierarchicalStreamWriter createWriter(Writer out){
			return new PrettyPrintWriter(out){
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
		
	});
	
	
	/**
	 * 解析发过来的xml请求
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> paseXML(HttpServletRequest request) throws Exception{
		//将解析结果存储在本地map对象中
		Map<String,String> map = new HashMap<String, String>();
		//从request中获取输入流
		InputStream inputStream = request.getInputStream();
		//读输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		//获取xml文档的根节点
		Element root = document.getRootElement();
		//获取根节点的子节点
		List<Element> elementList = root.elements();
		//遍历节点，并把内容放到map中去
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		//释放资源
		inputStream.close();
		inputStream = null;
		
		return map;
		
	}
	/**
	 * 将文本信息转换成xml格式
	 * @param text
	 * @return String
	 */
	public static String textMessageToXML(TextMessage text){
		xstream.alias("xml", text.getClass());
		return xstream.toXML(text);
		
	}
	/**
	 * 将音乐格式信息转成xml
	 * @param music
	 * @return String
	 */
	public static String musicMessageToXML(MusicMessage music){
		xstream.alias("xml", music.getClass());
		return xstream.toXML(music);
	}
	
	public static String newsMessageToXML(NewsMessage news){
		xstream.alias("xml", news.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(news);
	}
}
