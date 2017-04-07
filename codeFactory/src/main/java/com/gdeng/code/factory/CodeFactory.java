package com.gdeng.code.factory;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.gdeng.code.common.Constant;
import com.gdeng.code.out.customer.intf.dto.IntfDTOWriter;
import com.gdeng.code.out.customer.intf.service.IntfServiceWriter;
import com.gdeng.code.out.customer.intf.service.impl.IntfServiceImplWriter;
import com.gdeng.code.out.provider.intf.service.WebToolServiceWriter;
import com.gdeng.code.out.provider.service.impl.WebServiceImplWriter;
import com.gdeng.code.out.provider.web.controller.ControllerWriter;
import com.gdeng.code.out.provider.web.javascript.JavaScriptWriter;
import com.gdeng.code.out.provider.web.jsp.JspWriter;
import com.gdeng.code.util.EntityUtil;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class CodeFactory implements Constant {
	private static final Logger log = Logger.getLogger(CodeFactory.class);
	private static Configuration cfg;

	public static Configuration getCfg() {
		return cfg;
	}

	public static void init() throws Exception {
		cfg = new Configuration();
		ClassTemplateLoader ctl = new ClassTemplateLoader(CodeFactory.class.getClass(), "");
		ClassTemplateLoader ct2 = new ClassTemplateLoader(CodeFactory.class.getClass(), "template");
		TemplateLoader[] loaders = new TemplateLoader[] { ctl, ct2 };
		MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
		cfg.setTemplateLoader(mtl);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
	}

	public static void init(String tempaltePath) throws Exception {
		cfg = new Configuration();
		File file = new File(tempaltePath);
		cfg.setDirectoryForTemplateLoading(file);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
	}

	private static void generateWebCode(Object instance, CodeFactory factory, String webProjectName, String serviceProject) {
		if (StringUtils.isEmpty(webProjectName) || instance == null) {
			return;
		}
		log.info("--谷登科技JAVA代码生成工具----生成Web项目代码--------开始--------");
		try {
			WebToolServiceWriter.genServiceCode(factory, instance, webProjectName, serviceProject);
			WebServiceImplWriter.genServiceImplCode(factory, instance, webProjectName, serviceProject);
			ControllerWriter.genControllerCode(factory, instance, webProjectName, serviceProject);
			JspWriter.genCode(factory, instance, webProjectName, serviceProject);
			JavaScriptWriter.genCode(factory, instance, webProjectName, serviceProject);
		} catch (Exception e) {
			log.error("生成Web项目代码失败:" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成Web项目代码--------结束--------");
	}

	/**
	 * 生成接口层代码
	 * 
	 * @param entity
	 *            实体类
	 * @param factory
	 * @author lidong
	 */
	private static void generateIntfCode(Object instance, CodeFactory factory, String serviceProject) {
		if (StringUtils.isEmpty(serviceProject) || instance == null) {
			return;
		}
		log.info("--谷登科技JAVA代码生成工具----生成接口层代码--------开始--------");
		try {
			IntfDTOWriter.genDTOCode(factory, instance, serviceProject);
			IntfServiceWriter.genServiceCode(factory, instance, serviceProject);
			IntfServiceImplWriter.genServiceImplCode(factory, instance, serviceProject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--谷登科技JAVA代码生成工具----生成接口层代码--------结束--------");
	}

	/**
	 * 生成代码
	 * 
	 * @param serviceProject
	 *            服务层项目名
	 * @param webProjectName
	 *            web层调用项目名
	 * @param instance
	 * @author lidong
	 */
	public static void generateCode(String serviceProject, String webProjectName, Object instance) {
		log.info("欢迎使用：谷登科技JAVA代码生成工具");
		log.info("实体名称：" + instance.getClass().getName());
		long startTime = System.currentTimeMillis();
		log.info("--谷登科技JAVA代码生成工具----生成所有代码--------开始--------");
		try {
			CodeFactory factory = new CodeFactory();
			if (StringUtils.isEmpty(serviceProject)) {
				serviceProject = EntityUtil.getModuleName(instance);
			}
			generateIntfCode(instance, factory, serviceProject);
			generateWebCode(instance, factory, webProjectName, serviceProject);
		} catch (Exception e) {
			log.error("生成服务层代码失败:" + e.getLocalizedMessage());
		}
		log.info("--谷登科技JAVA代码生成工具----生成所有代码--------结束--------");

		long endTime = System.currentTimeMillis();
		log.info("感谢您使用：谷登科技JAVA代码生成工具。。。");
		log.info("生成Service、Web所有代码共耗时：" + ((endTime - startTime) / 1000.0D) + "秒。");
		log.info("请刷新项目，找到GD_SRC_OutPut文件夹，所有代码均放在里面");
	}
}