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
			//读取用户名和密码
			String uname =  req.getParameter("username");
			String pwd = req.getParameter("pwd");
			System.out.println("uname:"+uname+"pwd:"+pwd);
			//调用业务层的服务进行登录验证
			User user = service.checkLogin(uname, pwd);
			//绑定数据，用于session验证
			session.setAttribute("user", user);
			//登录成功，重定向到首页
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
				//应用异常，需要明确提示用户，采取正确的操作
				req.setAttribute("login_failed", e.getMessage());
				//转发到登录页面
				return "login";
			}
			return "error";
		}
}
