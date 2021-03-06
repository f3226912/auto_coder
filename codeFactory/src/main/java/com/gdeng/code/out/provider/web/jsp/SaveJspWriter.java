package com.gdeng.code.out.provider.web.jsp;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gdeng.code.common.Constant;
import com.gdeng.code.factory.CodeFactory;
import com.gdeng.code.util.EntityUtil;
import com.gdeng.code.util.FileUtil;

import freemarker.template.Template;

public class SaveJspWriter implements Constant {
	private static final Logger log = Logger.getLogger(SaveJspWriter.class);

	public static void genCode(CodeFactory factory, Object instance, String webProjectName, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成save.jsp--------开始--------");
		try {
			CodeFactory.init("src/main/resources/template/jsp/");
			Map root = new HashMap();
			String entityName = EntityUtil.getEntityClassName(instance);
			String entitySimpleName = EntityUtil.getEntitySimpleClassName(instance);
			String entityLowerCaseSimpleName = EntityUtil.getEntityLowerCaseSimpleName(instance);
			root.put("webProjectName", webProjectName);
			String bigWebProjectName = webProjectName.substring(0, 1).toUpperCase() + webProjectName.substring(1);
			root.put("bigWebProjectName", bigWebProjectName);
			root.put("serviceProject", serviceProject);
			root.put("entityName", entityName);
			root.put("entitySimpleName", entitySimpleName);
			root.put("entityLowerCaseSimpleName", entityLowerCaseSimpleName);

			String pkName2 = EntityUtil.getPKName(instance.getClass());
			String pkName = null;
			String pkClumn = null;
			Map<String, String> map = EntityUtil.getFieldNameAndColumnName(instance.getClass());
			List<String> fields = new ArrayList<String>();
			List<String> clumns = new ArrayList<String>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				fields.add(entry.getKey());
				clumns.add(entry.getValue());
			}
			for (Map.Entry<String, String> entry : map.entrySet()) {
				pkName = entry.getKey();
				pkClumn = entry.getValue();
				if (pkName.equals(pkName2)) {
					break;
				}
			}
			root.put("pkName", pkName);
			root.put("pkClumn", pkClumn);
			root.put("fields", fields);
			root.put("clumns", clumns);
			root.put("basePath", "${CONTEXT}");
			String templateName = "Save.ftl";
			Template t = CodeFactory.getCfg().getTemplate(templateName);
			String actionFileOutPath = "GD_SRC_OutPut/web/jsp/" + entityLowerCaseSimpleName + "/";
			FileUtil.createDirs(actionFileOutPath, true);
			String outPutFileName = actionFileOutPath + "save.jsp";
			Writer out1 = new OutputStreamWriter(new FileOutputStream(outPutFileName), "UTF-8");
			t.process(root, out1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成save.jsp--------结束--------");
	}

}