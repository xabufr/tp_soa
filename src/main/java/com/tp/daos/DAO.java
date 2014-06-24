package com.tp.daos;

import java.io.Serializable;

public interface DAO<Type, Key extends Serializable> {
	Type find(Key key);
}
