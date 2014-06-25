package com.tp.daos.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.dao.support.DataAccessUtils;

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

	@Override
	public void delete(EntityType entity) {
		getHibernateTemplate().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Key save(EntityType entity) {
		return (Key) getHibernateTemplate().save(entity);
	}

	@Override
	public void update(EntityType entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void refresh(EntityType entity) {
		getHibernateTemplate().refresh(entity);
	}

	@Override
	public long count() {
		String request = "SELECT count(*) FROM "
				+ entityClass.getCanonicalName();
		return DataAccessUtils.longResult(getHibernateTemplate().find(request));
	}

}
