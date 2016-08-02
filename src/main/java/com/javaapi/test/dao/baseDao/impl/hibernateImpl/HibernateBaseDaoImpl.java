package com.javaapi.test.dao.baseDao.impl.hibernateImpl;

import com.javaapi.test.dao.baseDao.IBaseHqlDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/2.
 */
public class HibernateBaseDaoImpl<T,ID extends Serializable> extends BaseDaoImpl<T,ID> implements IBaseHqlDao<T,ID> {

    @Override
    public Page<T> findAll(String hql, Pageable pageable, List params) {
        return null;
    }

    @Override
    public T findOne(String hql, Map params) {
        return null;
    }

    @Override
    public T findOne(String hql, Object... params) {
        return null;
    }

    @Override
    public List<T> findAll(String hql, Object... params) {
        return null;
    }

    @Override
    public Page<T> findAll(String hql, Pageable pageable, Object... params) {
        return null;
    }

    @Override
    public List<T> findAll(String hql, List params) {
        return null;
    }

    @Override
    public Page<T> findAll(String hql, Pageable pageable, Map params) {
        return null;
    }

    @Override
    public List<T> findAll(String hql, Map params) {
        return null;
    }

    @Override
    public T findOne(String hql, List params) {
        return null;
    }
}
