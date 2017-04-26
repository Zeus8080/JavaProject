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
	 * ������Ϣ���ͣ��ı�
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * ������Ϣ���ͣ�ͼ��
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * ������Ϣ���ͣ��ı�
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * ������Ϣ���ͣ�ͼƬ
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * ������Ϣ���ͣ�����λ��
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * ������Ϣ���ͣ���Ƶ
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * �¼����ͣ�subscribe(����)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * �¼����ͣ�unsubscribe(ȡ������)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * �¼����ͣ�CLICK(�Զ���˵�����¼�)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 
	 */
	private static XStream xstream = new XStream(new XppDriver(){
		public HierarchicalStreamWriter createWriter(Writer out){
			return new PrettyPrintWriter(out){
				// ������xml�ڵ��ת��������CDATA���
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
	 * ������������xml����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> paseXML(HttpServletRequest request) throws Exception{
		//����������洢�ڱ���map������
		Map<String,String> map = new HashMap<String, String>();
		//��request�л�ȡ������
		InputStream inputStream = request.getInputStream();
		//��������
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		//��ȡxml�ĵ��ĸ��ڵ�
		Element root = document.getRootElement();
		//��ȡ���ڵ���ӽڵ�
		List<Element> elementList = root.elements();
		//�����ڵ㣬�������ݷŵ�map��ȥ
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		//�ͷ���Դ
		inputStream.close();
		inputStream = null;
		
		return map;
		
	}
	/**
	 * ���ı���Ϣת����xml��ʽ
	 * @param text
	 * @return String
	 */
	public static String textMessageToXML(TextMessage text){
		xstream.alias("xml", text.getClass());
		return xstream.toXML(text);
		
	}
	/**
	 * �����ָ�ʽ��Ϣת��xml
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
