package test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.login.dao.UserDAO;
import cn.tedu.login.entity.User;
import cn.tedu.login.service.LoginService;

public class TestCase {
	@Test//���� ���ӳ�
	public void test1() throws SQLException{
		String config = "spring-mvc.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
	//	BasicDataSource ds =  ac.getBean("ds",BasicDataSource.class);
		/*
		 * DataSource��һ���ӿڣ�BasicDataSource�Ǹýӿڵ�ʵ����
		 * */
		DataSource ds  = ac.getBean("ds",DataSource.class);
		System.out.println(ds.getConnection());
	}
	
	@Test//���� ���ݷ��ʲ�
	public void test2(){
		String config = "spring-mvc.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		UserDAO dao = ac.getBean("userDAO",UserDAO.class);
		User user = dao.findByName("king");
		System.out.println("user:"+user);
	}
	
	@Test//���� ҵ���
	public void test3(){
		String config = "spring-mvc.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		LoginService service = ac.getBean("loginService",LoginService.class);
		User user = service.checkLogin("king","1234");
		System.out.println("user:"+user);
	}
}
