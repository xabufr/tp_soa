package com.tp.daos.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tp.daos.UserDAO;
import com.tp.entity.User;

@Repository("UserDAO")
public class UserDAOImpl extends DAOImpl<User, Long> implements UserDAO {

	@Override
	public User findByName(String name) {
		List<?> found = getHibernateTemplate().find(
				"FROM User u WHERE u.name = ?", name);
		if (found.size() == 1) {
			return (User) found.get(0);
		}
		return null;
	}
}
