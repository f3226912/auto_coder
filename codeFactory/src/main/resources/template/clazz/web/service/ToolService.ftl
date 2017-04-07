package com.gudeng.commerce.gd.${webProjectName}.service;

import com.gudeng.commerce.gd.${webProjectName}.dto.${entitySimpleName}DTO;
import com.gudeng.commerce.gd.${webProjectName}.entity.${entityName};

/**
 * 谷登科技代码生成器出品,模板不代表正确，请酌情修改
 * 
 * @author lidong
 *
 */
public interface ${entitySimpleName}ToolService extends BaseToolService<${entitySimpleName}DTO> {
	public Long insert(${entityName} entity) throws Exception;
}