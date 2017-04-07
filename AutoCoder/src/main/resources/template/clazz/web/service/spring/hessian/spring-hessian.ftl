<bean name="/service/${entityLowerCaseSimpleName}Service.hs" class="org.springframework.remoting.caucho.HessianServiceExporter">
	<property name="service" ref="${entityLowerCaseSimpleName}Service" />
	<property name="serviceInterface" value="com.gudeng.commerce.gd.${serviceProject}.service.${entitySimpleName}Service" />
</bean>