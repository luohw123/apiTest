package com.javaapi.test.dao.baseDao.impl.hibernateImpl;

import com.javaapi.test.dao.baseDao.IBaseDao;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T, ID extends Serializable> implements IBaseDao<T, ID> {

    private Class<T> clazz;
    @Autowired
    private SessionFactory sessionFactory;
    private String identifierPropertyName;

    public BaseDaoImpl() {
        //使用反射技术得到T的真实类型
        //获取当前new的对象的泛型的父类
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取第一个类型参数的真实类型
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public String getIdentifierPropertyName() {
        if(identifierPropertyName == null){
            identifierPropertyName = sessionFactory.getClassMetadata(clazz).getIdentifierPropertyName();
        }
        System.out.println("idddd="+identifierPropertyName);
        return identifierPropertyName;
    }

    /**
     * 获取当前可用的Session对象
     *
     * @return
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    protected T get(ID id, boolean lock) {
        T entity;
        if (lock) {
            entity = (T) getSession().get(getClazz(), id,
                    LockMode.UPGRADE);
        } else {
            entity = (T) getSession().get(getClazz(), id);
        }
        return entity;
    }

    @Override
    public T findById(ID id) {
        return get(id, false);
    }

    @Override
    public List<T> findAll(List<ID> id) {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM ");
        sb.append(clazz.getSimpleName());
        sb.append(" WHERE ");
        sb.append(getIdentifierPropertyName());
        sb.append(" IN :ids");
        Query ids = getSession().createQuery(sb.toString()).setParameterList("ids", id);
        List list = ids.list();
        return list;
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
        StringBuilder sb = new StringBuilder();
        sb.append("FROM ");
        sb.append(clazz.getSimpleName());
        Query ids = getSession().createQuery(sb.toString());
        List list = ids.list();
        return list;
    }

    @Override
    public int deleteById(ID id) {
        T one = findById(id);
        if (one == null) {
            return 0;
        }
        try {
            getSession().delete(one);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteByIds(List<ID> ids) {
        if(ids == null || ids.size() == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("FROM ");
        sb.append(clazz.getSimpleName());
        sb.append(" WHERE ");
        sb.append(getIdentifierPropertyName());
        sb.append(" IN :ids");
        Query update = getSession().createQuery(sb.toString()).setParameterList("ids", ids);
        int i = update.executeUpdate();
        return i;
    }



    @Override
    public ID saveRID(T t) {
        ID save = (ID) getSession().save(t);
        return save;
    }

    @Override
    public int save(List<T> ts) {
        int i = 0;
        Session session = getSession();
        for (T t : ts) {
            session.save(t);
            i++;
            if (i%100==0){
                session.flush();
                session.clear();
            }
        }
        session.flush();
        session.clear();
        return i;
    }

    @Override
    public int updateDynamic(T t) {
            throw new RuntimeException("暂未实现");
    }


    @Override
    public int saveRRC(T t) {
        try {
            getSession().save(t);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteRRC(T t) {
        try {
            getSession().delete(t);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /**
     * 返回更新行数, 不会出异常
     * Return Row Count
     * @param t
     * @return
     */
    @Override
    public int updateRRC(T t) {
        try {
            getSession().update(t);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }



    /**
     * 获得Finder的记录总数
     *
     * @param finder
     * @return
     */
    protected int countQueryResult(Finder finder) {
        Query query = getSession().createQuery(finder.getRowCountHql());
        finder.setParamsToQuery(query);
        if (finder.isCacheable()) {
            query.setCacheable(true);
        }
        return ((Number) query.iterate().next()).intValue();
    }
}
