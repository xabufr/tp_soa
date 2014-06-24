package com.tp.daos.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.tp.daos.DAO;
import com.tp.utils.HibernateSessionUtils;

public class DAOImpl<EntityType, Key extends Serializable> extends
		HibernateSessionUtils implements DAO<EntityType, Key> {

	private Class<EntityType> entityClass;

	public DAOImpl() {
		entityClass = findEntityClass();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntityType find(Key key) {
		return (EntityType) getHibernateTemplate().get(entityClass, key);
	}

	@SuppressWarnings("unchecked")
	private Class<EntityType> findEntityClass() {
		Class<?> clazz = getClass();

		while (true) {
			Type currentType = clazz.getGenericSuperclass();
			if (currentType instanceof ParameterizedType) {
				Class<?> currentClass = (Class<?>) ((ParameterizedType) currentType)
						.getRawType();
				if (currentClass == DAOImpl.class) {
					Type deducedType = ((ParameterizedType) currentType)
							.getActualTypeArguments()[0];
					if (deducedType instanceof ParameterizedType) {
						return (Class<EntityType>) ((ParameterizedType) deducedType)
								.getRawType();
					} else {
						return (Class<EntityType>) deducedType;
					}
				}
			} else {
				clazz = (Class<?>) currentType;
			}
		}
	}

}
