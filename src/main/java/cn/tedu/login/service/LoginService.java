package cn.tedu.login.service;

import cn.tedu.login.entity.User;

/**
 * 业务层接口
 * */
public interface LoginService {
	public User checkLogin(String name,String pwd);
}
