package com.gudeng.commerce.gd.${serviceProject}.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gudeng.commerce.gd.${serviceProject}.dao.BaseDao;
import com.gudeng.commerce.gd.${serviceProject}.dto.${entitySimpleName}DTO;
import com.gudeng.commerce.gd.${serviceProject}.entity.${entityName};
import com.gudeng.commerce.gd.${serviceProject}.service.${entitySimpleName}Service;
/**
 * 谷登科技代码生成器出品,模板不代表正确，请酌情修改
 * 
 * @author lidong
 *
 */
public class ${entitySimpleName}ServiceImpl implements ${entitySimpleName}Service {
	@Autowired
	private BaseDao<?> baseDao;

	@Override
	public ${entitySimpleName}DTO getById(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("${pkName}", ${pkName});
		return baseDao.queryForObject("${entityName}.getById", map, ${entitySimpleName}DTO.class);
	}

	@Override
	public List<${entitySimpleName}DTO> getList(Map<String, Object> map) throws Exception {
		return baseDao.queryForList("${entityName}.getList", map, ${entitySimpleName}DTO.class);
	}

	@Override
	public int deleteById(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("${pkName}", ${pkName});
		return baseDao.execute("${entityName}.deleteById", map);
	}
	
	@Override
	public int deleteBatch(List<String> list) throws Exception {
		int len = list.size();
		Map<String, Object>[] batchValues = new HashMap[len];
		for (int i = 0; i < len; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("${pkName}", StringUtils.trim(list.get(i)));
			batchValues[i] = map;
		}
		return baseDao.batchUpdate("${entityName}.deleteById", batchValues).length;
	}

	@Override
	public int update(${entitySimpleName}DTO t) throws Exception {
		return baseDao.execute("${entityName}.update", t);
	}

	@Override
	public int getTotal(Map<String, Object> map) throws Exception {
		return baseDao.queryForObject("${entityName}.getTotal", map, Integer.class);
	}

	@Override
	public Long insert(${entityName} entity) throws Exception {
		return baseDao.persist(entity, Long.class);
	}

	@Override
	public List<${entitySimpleName}DTO> getListPage(Map<String, Object> map) throws Exception {
		return baseDao.queryForList("${entityName}.getListPage", map, ${entitySimpleName}DTO.class);
	}
}