package com.javaapi.test.dao.baseDao;

import java.io.Serializable;
import java.util.List;

/**
 * 一个baseDao应该有的功能
 * 根据id 查询实体,返回单个实体
 * 根据id list进行查询,并返回实体list
 * 根据实体查询实体list,返回分页实体list,分页需要order by   //TODO ??
 * 可以自定义sql/hql,可连表                              TODO
 * <p>
 * <p>
 * <p>
 * <p>
 * 根据id进行删除,并返回删除个数
 * 根据id list进行删除,并返回删除个数
 * 根据实体条件进行删除,并返回删除个数  (可选)
 * <p>
 * 动态更新单个实体,传入单个实体,返回更新个数: 0/1
 * 对全部字段进行更新,传入单个实体,返回更新个数: 0/1
 * <p>
 * 保存单个实体,返回插入条数 0/1
 * 保存实体list,返回插入条数
 * <p>
 * <p>
 * 判断实体是否存在,传入实体参数,返回 布尔值  (boolean)
 * 判断id对应的数据是否存在,传入id参数,返回 布尔值  (boolean)
 */
public interface IBaseDao<T, ID extends Serializable> {

    /**
     *   * 根据id 查询实体,返回单个实体

     * @param id
     * @return
     */
    public T findOne(ID id);


    /**
     * 按id list进行查询实体,返回单个实体
     * @param id
     * @return
     */
    public List<T> findAll(List<ID> id);


    /**按实体进行分页查询
     * @param
     */
    public List<T> findAll(T t, Integer pageNo, Integer pageSize);


    /**
     * 按单个id进行删除
     *
     * @param id
     * @return
     */
    public int delete(ID id);

    /**
     * 按批量id进行删除
     *
     * @param ids
     * @return
     */
    public int delete(List<ID> ids);

    /**
     * 保存单个实体,返回插入条数 0/1
     *
     * @param t
     * @return
     */
    public int save(T t);

    /**
     * 保存实体list,返回插入条数
     *
     * @param ts
     * @return
     */
    public int save(List<T> ts);

    /**
     * 按id,对非null值进行更新 (动态更新)
     *
     * @param t
     * @return
     */
    public int updateDynamic(T t);

    /**
     * 按id,对全部字段进行更新
     *
     * @param t
     * @return
     */
    public int update(T t);


}
