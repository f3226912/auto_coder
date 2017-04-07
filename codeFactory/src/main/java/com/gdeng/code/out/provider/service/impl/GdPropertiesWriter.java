package com.gdeng.code.out.provider.service.impl;

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

class GdPropertiesWriter implements Constant {
	private static final Logger log = Logger.getLogger(GdPropertiesWriter.class);

	public static void genGdPropertiesCode(CodeFactory factory, Object instance, String webProjectName, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成GdProperties.java--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/web/service/impl/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);
			String entityLowerCaseSimpleName = EntityUtil.getEntityLowerCaseSimpleName(instance);
			root.put("serviceProject", serviceProject);
			root.put("entityName", entityName);
			root.put("entitySimpleName", entitySimpleName);
			root.put("entityLowerCaseSimpleName", entityLowerCaseSimpleName);
			String templateName = "GdProperties.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/web/service/impl/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + "GdProperties.java";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成GdProperties.java--------结束--------");
	}

	public static void genGdSettingCode(CodeFactory factory, Object instance, String webProjectName, String serviceProject) {
		log.info("--谷登科技JAVA代码生成工具----生成gd-setting.properties--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/web/service/impl/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);
			String entityLowerCaseSimpleName = EntityUtil.getEntityLowerCaseSimpleName(instance);
			StringBuilder sb = new StringBuilder();
			sb.append("gd.");
			sb.append(entityLowerCaseSimpleName);
			sb.append("Service.url=${maven.gd.");
			sb.append(serviceProject);
			sb.append(".url}service/");
			sb.append(entityLowerCaseSimpleName);
			sb.append("Service.hs");
			root.put("setting", sb.toString());
			String templateName = "gd-setting.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/web/service/impl/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + "gd-setting.properties";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成gd-setting.properties--------结束--------");

	}

}