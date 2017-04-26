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
 * 核心请求处理类
 * @autor 朱冲
 * 
 */
public class CoreServlet extends HttpServlet {

	/**
	 * 确认来自服务器的消息
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//微信加密签名
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		//通过signature对请求进行校验，若校验成功则返回echostr，否则失败
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			System.out.println("校验成功");
			out.print(echostr);
			
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//将请求、相应类型都设置为utf-8防止出现乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//调用核心业务类接收、处理消息
		String respMessage = CoreService.processRequest(request);
		System.out.println(respMessage);
		//响应消息
		PrintWriter out = response.getWriter();
		System.out.println("========");
		out.print(respMessage);
		out.close();
		out = null;
	}


}
