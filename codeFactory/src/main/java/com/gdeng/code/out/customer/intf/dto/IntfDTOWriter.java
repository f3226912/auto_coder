package com.gdeng.code.out.customer.intf.dto;

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

public class IntfDTOWriter implements Constant {
	private static final Logger log = Logger.getLogger(IntfDTOWriter.class);

	public static void genDTOCode(CodeFactory factory, Object instance, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成DTO--------开始--------");
		String entitySimpleName = null;
		try {
			CodeFactory.init("src/main/resources/template/clazz/intf/dto/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);
			root.put("serviceProject", serviceProject);
			root.put("entityName", entityName);
			root.put("entitySimpleName", entitySimpleName);
			String templateName = "DTO.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/intf/dto/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + entitySimpleName + "DTO.java";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
		} catch (Exception e) {
			log.error("生成DTO失败:" + e);
			e.printStackTrace();
		}
		log.info(String.format("--谷登科技JAVA代码生成工具----生成%s--------结束--------", entitySimpleName + "DTO.java"));
	}
}