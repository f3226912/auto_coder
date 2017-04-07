package com.gdeng.code.out.customer.intf.service;

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

public class IntfServiceWriter implements Constant {
	private static final Logger log = Logger.getLogger(IntfServiceWriter.class);

	public static void genServiceCode(CodeFactory factory, Object instance, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成Service--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/intf/service/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);

			root.put("serviceProject", serviceProject);
			root.put("entityName", entityName);
			root.put("entitySimpleName", entitySimpleName);
			String templateName = "Service.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/intf/service/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + entitySimpleName + "Service.java";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
			genBaseServiceCode(factory, instance, serviceProject);
		} catch (Exception e) {
			log.error("生成DTO失败:" + e);
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成Service--------结束--------");
	}

	private static void genBaseServiceCode(CodeFactory factory, Object instance, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成BaseService--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/intf/service/");
			Map root = new HashMap();
			root.put("serviceProject", serviceProject);
			String templateName = "baseService.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/intf/service/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + "BaseService.java";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
		} catch (Exception e) {
			log.error("生成DTO失败:" + e);
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成BaseService--------结束--------");
	}
}