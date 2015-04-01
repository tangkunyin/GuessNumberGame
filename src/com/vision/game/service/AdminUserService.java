package com.vision.game.service;

import java.util.List;
import com.vision.game.bean.AdminUser;
import com.vision.game.dao.AdminUserDao;

/**
 * AdminUserService
 * 处理业务逻辑。共Controller调用
 * @author tangkunyin
 */
public class AdminUserService {
	private AdminUserDao adminUserDao;
	public AdminUserDao getAdminUserDao() {
		return adminUserDao;
	}

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	
	public int countAll() {
		return adminUserDao.countAll();
	}

	public List<AdminUser> selectAll() {
		return adminUserDao.selectAll();
	}

	public AdminUser findByUserLogin(String userName, String passWord) {
		return adminUserDao.findByUserLogin(userName, passWord);
	}

	public int addAdUser(AdminUser adminUser) {
		return adminUserDao.addAdUser(adminUser);
	}

	public int deleteAdUser(int id) {
		return adminUserDao.deleteAdUser(id);
	}

	public int updateAdUser(int id) {
		return adminUserDao.updateAdUser(id);
	}
}
