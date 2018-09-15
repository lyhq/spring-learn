package com.atguigu.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional //标注这里添加了事务
	public void insertUser() {
		userDao.insert();
		//otherDao.other();
		System.out.println("成功插入数据。。。");
		int i = 10/0;
	}
}
