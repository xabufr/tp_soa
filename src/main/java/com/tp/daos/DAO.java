package com.tp.daos;

import java.io.Serializable;

public interface DAO<Type, Key extends Serializable> {
	Type find(Key key);
	void delete(Type entity);
	Key save(Type entity);
	void update(Type entity);
	void refresh(Type entity);
	long count();
}
