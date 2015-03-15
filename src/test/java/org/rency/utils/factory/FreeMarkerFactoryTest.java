package org.rency.utils.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.rency.utils.exceptions.CoreException;

public class FreeMarkerFactoryTest {

	@Test
	public void testGenerateJavaFile() throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("package", "com.rency.dpf.beans");
		params.put("className", "Hello");
		
		List<Map<String,Object>> attrs = new ArrayList<Map<String, Object>>();
		params.put("attrs", attrs);
		
		Map<String, Object> attr = new HashMap<String,Object>();
		attr.put("desc", "姓名");
		attr.put("type", "String");
		attr.put("field", "name");
		attrs.add(attr);
		
		Map<String, Object> age = new HashMap<String,Object>();
		age.put("desc", "年龄");
		age.put("type", "int");
		age.put("field", "age");
		attrs.add(age);
		String ftlPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(ftlPath);
		String javaPath = FreeMarkerFactory.generateJavaFile("G:\\workspace\\DPF-Utils\\src\\test\\resources", "bean.ftl", params);
		System.out.println("生成Java文件路径："+javaPath);
		File javaFile = new File(javaPath);
		javaFile.delete();
	}

}
