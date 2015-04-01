package com.vision.game.dao;

import java.util.List;

import com.vision.game.bean.AdminUser;

/**
 * AdminUserDao接口
 * @author tangkunyin
 * @see 2013-05-30
 */
public interface AdminUserDao {
	//查询总记录数，即现在多少位管理员
	public int countAll();
	
	//查询所有人员情况
	public List<AdminUser> selectAll();
	
	//根据userName和password查某一位。在对比role，都正确，给予登录
	public AdminUser findByUserLogin(String userName,String passWord);
	
	//添加管理员
	public int addAdUser(AdminUser adminUser);
	
	//删除管理员
	public int deleteAdUser(int id);
	
	//修改管理员
	public int updateAdUser(int id);
}
