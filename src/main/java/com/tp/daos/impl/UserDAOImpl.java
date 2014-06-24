package com.tp.daos.impl;

import org.springframework.stereotype.Repository;

import com.tp.daos.UserDAO;
import com.tp.entity.User;

@Repository("UserDAO")
public class UserDAOImpl extends DAOImpl<User, Long> implements UserDAO {

}
