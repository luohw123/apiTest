package com.javaapi.test.dao.baseDao.impl.mybatisImpl;

import com.javaapi.test.dao.baseDao.IBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 16/8/2.
 */
public abstract class BaseDaoImpl<T,ID extends Serializable> implements IBaseDao<T,ID>{
    @Override
    public T findById(ID id) {
        return null;
    }

    @Override
    public List<T> findAll(List<ID> id) {
        return null;
    }

    @Override
    public Page<T> findAll(T t, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public Page<T> findAll(T t, Pageable pageable) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public int deleteById(ID id) {
        return 0;
    }

    @Override
    public int deleteByIds(List<ID> ids) {
        return 0;
    }

    @Override
    public ID saveRID(T t) {
        return (ID)Integer.valueOf(0);
    }

    @Override
    public int save(List<T> ts) {
        return 0;
    }

    @Override
    public int updateDynamic(T t) {
        return 0;
    }

    @Override
    public int updateRRC(T t) {
        return 0;
    }
}
