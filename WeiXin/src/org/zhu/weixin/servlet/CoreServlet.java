package org.zhu.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zhu.weixin.service.CoreService;
import org.zhu.weixin.util.SignUtil;

/**
 * ������������
 * @autor ���
 * 
 */
public class CoreServlet extends HttpServlet {

	/**
	 * ȷ�����Է���������Ϣ
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//΢�ż���ǩ��
		String signature = request.getParameter("signature");
		//ʱ���
		String timestamp = request.getParameter("timestamp");
		//�����
		String nonce = request.getParameter("nonce");
		//����ַ���
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		//ͨ��signature���������У�飬��У��ɹ��򷵻�echostr������ʧ��
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			System.out.println("У��ɹ�");
			out.print(echostr);
			
		}
		out.close();
		out = null;
	}

	/**
	 * ����΢�ŷ���������Ϣ
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��������Ӧ���Ͷ�����Ϊutf-8��ֹ��������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//���ú���ҵ������ա�������Ϣ
		String respMessage = CoreService.processRequest(request);
		System.out.println(respMessage);
		//��Ӧ��Ϣ
		PrintWriter out = response.getWriter();
		System.out.println("========");
		out.print(respMessage);
		out.close();
		out = null;
	}


}
