package com.dongbu.farm.common.utils;

import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 프로퍼티 전용 유틸
 * @author AnSangKil
 *
 */
public class PropertyUtils {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private static final String DEFAULT_PROPERTY_PATH = "config//";
	
	
	/**
	 * 외부  *.Properties 파일을 읽어와 Properties 객체에 담는 역활 수행    
	 * @param propertyName	: 실제 파일 이름
	 * @return
	 * @throws Exception
	 * @author AnSangKil
	 * 
	 */
	public Properties getProperty(String propertyName) throws Exception {

		ClassLoader cl = getClass().getClassLoader(); //cl = ClassLoader.getSystemClassLoader();

		InputStream in;
		
		String name = "";
		
		if(StringUtils.isNull(propertyName).equals("")){
			name = DEFAULT_PROPERTY_PATH + "env.properties";
		}else{
			name = DEFAULT_PROPERTY_PATH + name;
		}
		

		log.debug("propertyName ::" + name);
		
		
		if (cl != null) {
			
			in = cl.getResourceAsStream(name);
			
		} else {
			
			in = ClassLoader.getSystemResourceAsStream(name);
			
		}

		Properties mProperties = new Properties();
		
		// 만일 InputStream이 null이면 configuration 파일이 없다.
		if (in == null) {
			throw new Exception("configuration file " + name + " was not found.");
		} else {

			try {
				mProperties = new Properties();
				mProperties.load(in);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ex) {
					}
				}
			}
		}

		return mProperties;
	}

}
