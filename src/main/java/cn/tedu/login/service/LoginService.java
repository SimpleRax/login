package cn.tedu.login.service;

import cn.tedu.login.entity.User;

/**
 * ҵ���ӿ�
 * */
public interface LoginService {
	public User checkLogin(String name,String pwd);
}
