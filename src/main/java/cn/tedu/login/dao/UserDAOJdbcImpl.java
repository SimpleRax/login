package cn.tedu.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import cn.tedu.login.entity.User;

/**
 * ���ݷ��ʲ�ʵ����
 * 	ע��
 * 		@Repository���־û������ݷ��ʲ�Ӧ��ʹ�ø�ע�⣬�������ɨ��
 * */
@Repository("userDAO")
public class UserDAOJdbcImpl implements UserDAO{

	@Resource(name="ds")
	private DataSource ds;
	
	public User findByName(String uname) {
		User user = null;
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM t_user WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(uname);
				user.setPwd(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return user;
	}

}
