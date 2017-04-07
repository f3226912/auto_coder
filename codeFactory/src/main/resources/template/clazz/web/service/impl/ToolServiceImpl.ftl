package com.gudeng.commerce.gd.${webProjectName}.service.impl;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.caucho.hessian.client.HessianProxyFactory;
import com.gudeng.commerce.gd.${webProjectName}.service.${entitySimpleName}ToolService;
import com.gudeng.commerce.gd.${webProjectName}.util.GdProperties;
import com.gudeng.commerce.gd.${serviceProject}.dto.${entitySimpleName}DTO;
import com.gudeng.commerce.gd.${serviceProject}.entity.${entityName};
import com.gudeng.commerce.gd.${serviceProject}.service.${entitySimpleName}Service;

public class ${entitySimpleName}ToolServiceImpl implements ${entitySimpleName}ToolService {
	/** hessian 接口配置工具 **/
	@Autowired
	public GdProperties gdProperties;
	private static ${entitySimpleName}Service ${entityLowerCaseSimpleName}Service;

	protected ${entitySimpleName}Service getHessian${entitySimpleName}Service() throws MalformedURLException {
		String url = gdProperties.get${entitySimpleName}ServiceUrl();
		if (${entityLowerCaseSimpleName}Service == null) {
			HessianProxyFactory factory = new HessianProxyFactory();
			factory.setOverloadEnabled(true);
			${entityLowerCaseSimpleName}Service = (${entitySimpleName}Service) factory.create(${entitySimpleName}Service.class, url);
		}
		return ${entityLowerCaseSimpleName}Service;
	}

	@Override
	public ${entitySimpleName}DTO getById(String id) throws Exception {
		return getHessian${entitySimpleName}Service().getById(id);
	}

	@Override
	public List<${entitySimpleName}DTO> getList(Map<String, Object> map) throws Exception {
		return getHessian${entitySimpleName}Service().getList(map);
	}

	@Override
	public int deleteById(String id) throws Exception {
		return getHessian${entitySimpleName}Service().deleteById(id);
	}
	
	@Override
	public int deleteBatch(List<String> list) throws Exception {
		return getHessian${entitySimpleName}Service().deleteBatch(list);
	}

	@Override
	public int update(${entitySimpleName}DTO t) throws Exception {
		return getHessian${entitySimpleName}Service().update(t);
	}

	@Override
	public int getTotal(Map<String, Object> map) throws Exception {
		return getHessian${entitySimpleName}Service().getTotal(map);
	}

	@Override
	public Long insert(${entityName} entity) throws Exception {
		return getHessian${entitySimpleName}Service().insert(entity);
	}

	@Override
	public List<${entitySimpleName}DTO> getListPage(Map<String, Object> map) throws Exception {
		return getHessian${entitySimpleName}Service().getListPage(map);
	}
}