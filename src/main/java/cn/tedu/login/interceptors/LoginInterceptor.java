package cn.tedu.login.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//����session��֤��������
public class LoginInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		System.out.println("��ʼsession��֤����");
		//���session����
		HttpSession session = req.getSession();
		//ͨ��session�����ȡ����
		Object obj = session.getAttribute("user");
		if(obj == null){
			//û�е�¼���ض��򵽵�¼ҳ��
			System.out.println("û�е�¼,�ض��򵽵�¼ҳ��");
			res.sendRedirect("toLogin.do");
			return false;
		}
		//��¼�������������
		System.out.println("��¼���ˡ��������");
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
