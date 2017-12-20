package cn.tedu.login.dao;

import cn.tedu.login.entity.User;

/**
 * 数据访问接口
 * */
public interface UserDAO {
	public User findByName(String uname);
}
