package gudeng.AutoCoder;

import com.gdeng.code.factory.CodeFactory;
import com.gudeng.commerce.gd.supplier.entity.ProductCategoryEntity;

/**
 * 谷登科技，代码生成器
 * <p>
 * 服务层： 生成实体类的DTO,interface，interfaceImpl,spring-impl.xml配置，sqlMap_XXX.xml,hessian配置
 * <p>
 * web层：interface，interfaceImpl,spring-impl.xml配置块，gd-setting.properties配置块,GdProperties.java代码块，controller,javascript,jsp
 * 
 * @author lidong
 * 
 */
public class AutoCodeFactory {
	/**
	 * 使用步骤
	 * <p>
	 * 1 创建实体类对象，请使用 AutoBean 自动生成
	 * <p>
	 * 2 指定服务层模块名称 (该名称会作为包名的一部分)
	 * <p>
	 * 3 指定web项目名称 (该名称会作为包名的一部分)
	 * <p>
	 * 4 调用方法CodeFactory.generateCode
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ProductCategoryEntity entity = new ProductCategoryEntity();
		String serviceProject = "supplier";
		String webProjectName = "admin";
		CodeFactory.generateCode(serviceProject, webProjectName, entity);
	}
}
