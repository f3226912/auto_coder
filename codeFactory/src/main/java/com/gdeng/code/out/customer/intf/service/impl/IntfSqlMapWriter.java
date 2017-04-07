package com.gdeng.code.out.customer.intf.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.gdeng.code.common.Constant;
import com.gdeng.code.factory.CodeFactory;
import com.gdeng.code.util.EntityUtil;
import com.gdeng.code.util.FileUtil;

import freemarker.template.Template;

class IntfSqlMapWriter implements Constant {
	private static final Logger log = Logger.getLogger(IntfSqlMapWriter.class);

	/**
	 * 生成对象的sql
	 * 
	 * @param factory
	 * @param instance
	 * @param serviceProject
	 * @throws Exception
	 */
	public static void genSqlMapCode(CodeFactory factory, Object instance, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成sql--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/intf/service/sqlmap/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);
			String entityTableName = EntityUtil.getEntityTableName(instance.getClass());
			root.put("entityName", entityName);
			root.put("entityTableName", entityTableName);
			Map<String, String> map = EntityUtil.getFieldNameAndColumnName(instance.getClass());
			String pkName = EntityUtil.getPKName(instance.getClass());
			String pkClumn = map.get(pkName);
			List<String> clumns = new ArrayList<String>();
			List<String> fileds = new ArrayList<String>();
			int i = 0;
			for (Map.Entry<String, String> entry : map.entrySet()) {
				clumns.add(entry.getValue());
				fileds.add(entry.getKey());
				if (StringUtils.isEmpty(pkName)) {
					pkName = entry.getKey();
					pkClumn = entry.getValue();
				}
			}
			root.put("pkName", pkName);
			root.put("pkClumn", pkClumn);
			root.put("insertSql", IntfSqlMapUtil.genInsertSql(clumns, fileds, entityTableName));
			root.put("updateSql", IntfSqlMapUtil.genUpdateSql(clumns, fileds, entityTableName, pkName, pkClumn));
			root.put("getByIdSql", IntfSqlMapUtil.genGetByIdSql(clumns, fileds, entityTableName, pkName, pkClumn));
			root.put("getListSql", IntfSqlMapUtil.genGetListSql(clumns, fileds, entityTableName, pkName, pkClumn));
			root.put("getTotalSql", IntfSqlMapUtil.genGetTotalSql(clumns, fileds, entityTableName, pkName, pkClumn));
			String templateName = "sqlMap.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/intf/service/sqlMap/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + "sqlMap_" + entityName + ".xml";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
			log.info("--谷登科技JAVA代码生成工具----生成sql--------结束--------");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("--谷登科技JAVA代码生成工具----生成sql--------出错--------");
		}
	}
}