package ${package}

public class ${className}{
	<#list attrs as attr>
	/**
	 * ${attr.desc}
	 */
	private ${attr.type} ${attr.field};
	</#list>
	
	<#list  attrs as attr>
	public void set${attr.field?cap_first}(${attr.type} ${attr.field}){
		this.${attr.field}=${attr.field};
	}
	
	public ${attr.type} get${attr.field?cap_first}{
		return this.${attr.field}
	}
	
	</#list>
}