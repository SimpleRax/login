package cn.tedu.login.dao;

import cn.tedu.login.entity.User;

/**
 * ���ݷ��ʽӿ�
 * */
public interface UserDAO {
	public User findByName(String uname);
}
