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

class InftSpringXmlWriter implements Constant {
	private static final Logger log = Logger.getLogger(InftSpringXmlWriter.class);

	public static void genSpringXmlCode(CodeFactory factory, Object instance, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成spring-impl.xml--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/intf/service/spring/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);
			String entityLowerCaseSimpleName = EntityUtil.getEntityLowerCaseSimpleName(instance);
			root.put("serviceProject", serviceProject);
			root.put("entityName", entityName);
			root.put("entitySimpleName", entitySimpleName);
			root.put("entityLowerCaseSimpleName", entityLowerCaseSimpleName);
			String templateName = "spring-impl.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/intf/service/spring/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + "spring-impl.xml";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成spring-impl.xml--------结束--------");
	}
	
	public static void genSpringHessianCode(CodeFactory factory, Object instance, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成spring-impl.xml--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/intf/service/spring/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);
			String entityLowerCaseSimpleName = EntityUtil.getEntityLowerCaseSimpleName(instance);
			root.put("serviceProject", serviceProject);
			root.put("entityName", entityName);
			root.put("entitySimpleName", entitySimpleName);
			root.put("entityLowerCaseSimpleName", entityLowerCaseSimpleName);
			String templateName = "spring-hessian.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/intf/service/spring/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + "spring-hessian.xml";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成spring-impl.xml--------结束--------");
	}
}