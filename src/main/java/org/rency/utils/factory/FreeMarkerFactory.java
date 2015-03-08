package org.rency.utils.factory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import org.rency.utils.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreeMarkerFactory {

	private static final Logger logger = LoggerFactory.getLogger(FreeMarkerFactory.class);
	private static final Configuration cfg = new Configuration();
	
	/**
	 * @desc 生成Java文件
	 * @date 2015年1月27日 下午4:01:56
	 * @param ftlPath FreeMarker模板路径
	 * @param ftlName FreeMarker模板名称
	 * @param params 参数
	 * @return
	 * @throws CoreException
	 */
	public static String generateJavaFile(String ftlPath,String ftlName,Map<String, Object> params) throws CoreException{
		logger.info("Generate Java File With FreeMarker Start...");
		FileOutputStream fos = null;
		Writer out = null;
		try{
			if(!params.containsKey("package") || !params.containsKey("className")){
				throw new IllegalArgumentException("Property 'package' or 'className' must not be null.");
			}
			String packageName = String.valueOf(params.get("package"));
			String[] sections = packageName.split("\\.");
			StringBuilder filePath = new StringBuilder();
			for(String sec : sections){
				filePath.append(sec);
				filePath.append(File.separator);
			}
			String javaFileName = params.get("className")+".java";
			//Java文件完整路径
			String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()+File.separator;
			String javaFullPath =rootPath +filePath.toString()+javaFileName;
			
			cfg.setDirectoryForTemplateLoading(new File(ftlPath));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template template = cfg.getTemplate(ftlName);
			logger.debug("load freemarker success."+ftlPath+File.separator+ftlName);
			
			File javaFile = new File(javaFullPath);
			if(!javaFile.getParentFile().exists()){
				javaFile.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(javaFile);
			out = new OutputStreamWriter(fos);
			template.process(params, out);
			out.flush();
			out.close();
			logger.info("Generate Java File With FreeMarker Finish."+javaFullPath);
			return javaFullPath;
		}catch(Exception e){
			logger.error("generate java file with freemarker failed.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}
	}
	
	public static String generateHtmlFile() throws CoreException{
		return "";
	}
}