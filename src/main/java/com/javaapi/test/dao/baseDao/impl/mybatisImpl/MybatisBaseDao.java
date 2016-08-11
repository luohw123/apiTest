package com.javaapi.test.dao.baseDao.impl.mybatisImpl;

import com.javaapi.test.dao.baseDao.IBaseSqlDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/8/2.
 */
public class MybatisBaseDao<T, ID extends Serializable> extends BaseDaoImpl<T, ID> implements IBaseSqlDao<T, ID> {
    @Override
    public T selectOne(String sql, Map<String, Object> params) {
        return null;
    }

    @Override
    public T selectOne(String sql, Object... params) {
        return null;
    }

    @Override
    public T selectOne(String sql, List<Object> params) {
        return null;
    }

    @Override
    public List<T> selectAll(String sql, Map<String, Object> params) {
        return null;
    }

    @Override
    public Page<T> selectAll(String sql, Pageable pageable, Map<String, Object> params) {
        return null;
    }

    @Override
    public List<T> selectAll(String sql, Object... params) {
        return null;
    }

    @Override
    public Page<T> selectAll(String sql, Pageable pageable, Object... params) {
        return null;
    }

    @Override
    public List<T> selectAll(String sql, List<Object> params) {
        return null;
    }

    @Override
    public Page<T> selectAll(String sql, Pageable pageable, List<Object> params) {
        return null;
    }

    @Override
    public ID saveRID(T t) {
        return null;
    }

    @Override
    public int saveRRC(T t) {
        return 0;
    }

    @Override
    public int deleteRRC(T t) {
        return 0;
    }
}
