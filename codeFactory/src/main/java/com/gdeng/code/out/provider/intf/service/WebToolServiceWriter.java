package com.gdeng.code.out.provider.intf.service;

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

public class WebToolServiceWriter implements Constant {
	private static final Logger log = Logger.getLogger(WebToolServiceWriter.class);

	public static void genServiceCode(CodeFactory factory, Object instance, String webProjectName,String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成Web-ToolService--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/web/service/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);
			root.put("webProjectName", webProjectName);
			root.put("serviceProject", serviceProject);
			root.put("entityName", entityName);
			root.put("entitySimpleName", entitySimpleName);
			String templateName = "ToolService.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/web/service/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + entitySimpleName + "ToolService.java";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
			genBaseServiceCode(factory, instance, webProjectName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成Web-ToolService--------结束--------");
	}

	private static void genBaseServiceCode(CodeFactory factory, Object instance, String webProjectName) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成Web-ToolBaseService--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/web/service/");
			Map root = new HashMap();
			root.put("webProjectName", webProjectName);
			String templateName = "baseToolService.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/web/service/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + "BaseToolService.java";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成Web-ToolBaseService--------结束--------");
	}
}