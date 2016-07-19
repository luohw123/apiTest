package com.javaapi.test.dao.baseDao;

import java.io.Serializable;

/**
 * Created by user on 16/7/15.
 */
public interface IBaseDao<T extends Serializable> {
    T findOne(T id);
}
