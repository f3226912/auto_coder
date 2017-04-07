package com.gdeng.code.out.provider.web.jsp;

import org.apache.log4j.Logger;

import com.gdeng.code.common.Constant;
import com.gdeng.code.factory.CodeFactory;

public class JspWriter implements Constant {
	private static final Logger log = Logger.getLogger(JspWriter.class);

	/**
	 * 生成jsp页面
	 * 
	 * @param factory
	 * @param instance
	 * @param webProjectName
	 * @param serviceProject
	 * @throws Exception
	 */
	public static void genCode(CodeFactory factory, Object instance, String webProjectName, String serviceProject) throws Exception {
		log.info("--谷登科技JAVA代码生成工具----生成JSP--------开始--------");
		MainJspWriter.genCode(factory, instance, webProjectName, serviceProject);
		 SaveJspWriter.genCode(factory, instance, webProjectName, serviceProject);
		 EditJspWriter.genCode(factory, instance, webProjectName, serviceProject);
		 ViewJspWriter.genCode(factory, instance, webProjectName, serviceProject);
		log.info("--谷登科技JAVA代码生成工具----生成JSP--------结束--------");
	}

}