package com.gdeng.code.out.customer.intf.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gdeng.code.common.Constant;
import com.gdeng.code.factory.CodeFactory;
import com.gdeng.code.util.EntityUtil;
import com.gdeng.code.util.FileUtil;

import freemarker.template.Template;

public class IntfServiceImplWriter implements Constant {
	private static final Logger log = Logger.getLogger(IntfServiceImplWriter.class);

	public static void genServiceImplCode(CodeFactory factory, Object instance, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成ServiceImpl--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/intf/service/impl/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);

			root.put("serviceProject", serviceProject);
			root.put("entityName", entityName);
			root.put("entitySimpleName", entitySimpleName);

			String pkName2 = EntityUtil.getPKName(instance.getClass());
			String pkName = null;
			String pkClumn = null;
			Map<String, String> map = EntityUtil.getFieldNameAndColumnName(instance.getClass());
			for (Map.Entry<String, String> entry : map.entrySet()) {
				pkName = entry.getKey();
				pkClumn = entry.getValue();
				if (pkName.equals(pkName2)) {
					break;
				}
			}
			root.put("pkName", pkName);
			root.put("pkClumn", pkClumn);
			String templateName = "ServiceImpl.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/intf/service/impl/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + entitySimpleName + "ServiceImpl.java";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
			InftSpringXmlWriter.genSpringXmlCode(factory, instance, serviceProject);
			InftSpringXmlWriter.genSpringHessianCode(factory, instance, serviceProject);
			IntfSqlMapWriter.genSqlMapCode(factory, instance, serviceProject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成ServiceImpl--------结束--------");
	}

}