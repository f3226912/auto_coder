package com.gudeng.commerce.gd.${serviceProject}.service;

import java.util.List;
import java.util.Map;

/**
 * 谷登科技代码生成器出品,模板不代表正确，请酌情修改
 * 
 * @author lidong
 *
 */
public interface BaseService<T> {
	/**
	 * 根据ID查询对象
	 * 
	 * @param id
	 * @return DTO
	 * 
	 */
	public T getById(String id) throws Exception;
	
	/**
	 * 保存
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Integer insert(T t) throws Exception;

	/**
	 * 根据条件查询list(不分页查询)
	 * 
	 * @param map
	 * @return List<DTO>
	 */
	public List<T> getList(Map<String, Object> map) throws Exception;

	/**
	 * 根据条件查询list(分页查询)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<T> getListPage(Map<String, Object> map) throws Exception;

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 * @return 影响条数
	 * 
	 */
	public int deleteById(String id) throws Exception;

	/**
	 * 根据ID集合批量删除对象
	 * 
	 * @param list
	 * @return 影响条数
	 * 
	 */
	public int deleteBatch(List<String> list) throws Exception;
	
	/**
	 * 修改
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public int update(T t) throws Exception;

	/**
	 * 查询记录数
	 * 
	 * @param map
	 * @return 记录数
	 * 
	 */
	public int getTotal(Map<String, Object> map) throws Exception;
}
