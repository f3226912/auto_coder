package com.gudeng.commerce.gd.${serviceProject}.service;

import com.gudeng.commerce.gd.${serviceProject}.dto.${entitySimpleName}DTO;
import com.gudeng.commerce.gd.${serviceProject}.entity.${entityName};

/**
 * 谷登科技代码生成器出品,模板不代表正确，请酌情修改
 * 
 * @author lidong
 *
 */
public interface ${entitySimpleName}Service extends BaseService<${entitySimpleName}DTO> {
	public Long persist(${entityName} entity) throws Exception;
}