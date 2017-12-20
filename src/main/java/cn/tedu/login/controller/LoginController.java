package cn.tedu.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.login.entity.User;
import cn.tedu.login.service.AppException;
import cn.tedu.login.service.LoginService;

@Controller
public class LoginController {
	
		@Resource(name="loginService")
		private LoginService service;
	
		@RequestMapping("/toLogin.do")
		public String toLogin(){
			System.out.println("toLogin()");
			return "login";
		}
		
		@RequestMapping("/login.do")
		public String login(HttpServletRequest req,HttpSession session){
			System.out.println("login()");
			//��ȡ�û���������
			String uname =  req.getParameter("username");
			String pwd = req.getParameter("pwd");
			System.out.println("uname:"+uname+"pwd:"+pwd);
			//����ҵ���ķ�����е�¼��֤
			User user = service.checkLogin(uname, pwd);
			//�����ݣ�����session��֤
			session.setAttribute("user", user);
			//��¼�ɹ����ض�����ҳ
			return "redirect:toIndex.do";
		}
		
		@RequestMapping("/toIndex.do")
		public String toIndex(){
			System.out.println("toIndex()");
			return "index";
		}
		
		@ExceptionHandler
		public String exception(Exception e ,HttpServletRequest req){
			System.out.println("exception()");
			if(e instanceof AppException){
				//Ӧ���쳣����Ҫ��ȷ��ʾ�û�����ȡ��ȷ�Ĳ���
				req.setAttribute("login_failed", e.getMessage());
				//ת������¼ҳ��
				return "login";
			}
			return "error";
		}
}
