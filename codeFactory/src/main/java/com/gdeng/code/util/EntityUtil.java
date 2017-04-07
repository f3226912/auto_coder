package com.gdeng.code.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;

public class EntityUtil {

	/**
	 * 获取实体类的名称
	 * 
	 * @param instance
	 *            实体的实例化对象
	 * @return
	 * @author lidong
	 */
	public static String getEntityClassName(Object instance) {
		return instance.getClass().getSimpleName();
	}

	/**
	 * 获取实体类的简称，省略“Entity”之后的名称，用于拼接成DTO,Service等其他相关的名称
	 * 
	 * @param instance
	 *            实体的实例化对象
	 * @return
	 * @author lidong
	 */
	public static String getEntitySimpleClassName(Object instance) {
		String entityName = getEntityClassName(instance);
		if (entityName.endsWith("Entity")) {
			int lastIndex = entityName.lastIndexOf("Entity");
			return entityName.substring(0, lastIndex);
		}
		return entityName;
	}

	/**
	 * 获取首字母小写的实体类名称
	 * 
	 * @param entity
	 * @return
	 */
	public static String getEntityLowerCaseName(Object entity) {
		String entityName = getEntityClassName(entity);
		String lowerCaseName = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
		return lowerCaseName;
	}

	/**
	 * 获取首字母小写的实体类简称
	 * 
	 * @param entity
	 * @return
	 */
	public static String getEntityLowerCaseSimpleName(Object entity) {
		String entityName = getEntityClassName(entity);
		entityName = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
		if (entityName.endsWith("Entity")) {
			int lastIndex = entityName.lastIndexOf("Entity");
			entityName = entityName.substring(0, lastIndex);
			return entityName;
		}
		return entityName;
	}

	/**
	 * 获取实体类的get方法对应的字段以及对应的表字段名
	 * 
	 * @return
	 * @author lidong
	 */
	public static Map<String, String> getFieldNameAndColumnName(Class entityClass) {
		Field[] fields = entityClass.getDeclaredFields();
		Map<String, String> map = new HashMap<String, String>();
		for (Field f : fields) {
			String name = f.getName();
			Annotation[] annotations = f.getAnnotations();
			if (annotations.length <= 0) {
				String getMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
				try {
					Method method = entityClass.getDeclaredMethod(getMethodName, new Class[0]);
					annotations = method.getAnnotations();
				} catch (Exception localException) {
				}
			}
			Annotation[] arrayOfAnnotation1;
			int localException = (arrayOfAnnotation1 = annotations).length;
			for (int getMethodName = 0; getMethodName < localException; ++getMethodName) {
				Annotation anno = arrayOfAnnotation1[getMethodName];
				if ((anno.toString().contains("@javax.persistence.Column"))) {
					Column t = (Column) anno;
					String columnName = t.name();
					map.put(name, columnName);
				}
			}
		}
		return map;
	}

	/**
	 * 获取实体类的主键名称
	 * 
	 * @param entityClass
	 * @return
	 * @author lidong
	 */
	public static String getPKName(Class entityClass) {
		Field[] fields = entityClass.getDeclaredFields();
		for (Field f : fields) {
			Annotation[] annotations = f.getAnnotations();
			if (annotations.length <= 0) {
				String name = f.getName();
				String getMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
				try {
					Method method = entityClass.getDeclaredMethod(getMethodName, new Class[0]);
					annotations = method.getAnnotations();
				} catch (Exception localException) {
				}
			}
			Annotation[] arrayOfAnnotation1;
			int localException = (arrayOfAnnotation1 = annotations).length;
			for (int getMethodName = 0; getMethodName < localException; ++getMethodName) {
				Annotation anno = arrayOfAnnotation1[getMethodName];
				if ((anno.toString().contains("@javax.persistence.Id"))) {
					return f.getName();
				}
			}
		}
		return null;
	}

	/**
	 * 获取实体类的表名
	 * 
	 * @param entityClass
	 * @return
	 * @author lidong
	 */
	public static String getEntityTableName(Class entityClass) {
		String tableName = null;
		Annotation[] annotations = entityClass.getAnnotations();
		for (Annotation anno : annotations) {
			if (!(anno.toString().contains("@javax.persistence.Entity")))
				continue;
			Entity t = (Entity) anno;
			tableName = t.name();
		}
		return tableName;
	}

	/**
	 * 获取类的模块名称
	 * 
	 * @param entity
	 * @return
	 */
	public static String getModuleName(Object entity) {
		String pakageName = entity.getClass().getPackage().getName();
		String[] pakages = pakageName.split("\\.");
		return pakages[pakages.length - 2];
	}

	public static String getGetMethodName(String fieldName) {
		String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return getMethodName;
	}

	public static String getSetMethodName(String fieldName) {
		String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return setMethodName;
	}
}