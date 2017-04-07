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

public class WebServiceImplWriter implements Constant {
	private static final Logger log = Logger.getLogger(WebServiceImplWriter.class);

	public static void genServiceImplCode(CodeFactory factory, Object instance, String webProjectName, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成ToolServiceImpl--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/clazz/web/service/impl/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);
			String entityLowerCaseSimpleName = EntityUtil.getEntityLowerCaseSimpleName(instance);
			root.put("webProjectName", webProjectName);
			root.put("serviceProject", serviceProject);
			root.put("entityName", entityName);
			root.put("entitySimpleName", entitySimpleName);
			root.put("entityLowerCaseSimpleName", entityLowerCaseSimpleName);
			String templateName = "ToolServiceImpl.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/web/service/impl/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + entitySimpleName + "ToolServiceImpl.java";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
			GdPropertiesWriter.genGdPropertiesCode(factory, instance, webProjectName, serviceProject);
			GdPropertiesWriter.genGdSettingCode(factory, instance, webProjectName, serviceProject);
			WebSpringXmlWriter.genServiceImplCode(factory, instance, webProjectName, serviceProject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成ToolServiceImpl--------结束--------");
	}

}