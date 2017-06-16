/**
 * 
 */
package com.skan.security.config;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Description : 

 * @author skan
 * @since 2016. 5. 19.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
public class CryptoConfig {
	
	public static List<Config> configs = new ArrayList<>();
	
	static {
		new CryptoConfig(Config.DEFAULT_SECURITY_KEY, Config.DEFAULT_SALT);
		new CryptoConfig(Config.DEFAULT_SECURITY_KEY+"_2016", Config.DEFAULT_SALT);
		new CryptoConfig(Config.DEFAULT_SECURITY_KEY+"_2017", Config.DEFAULT_SALT);
	}
	
	public CryptoConfig(String secretKey , int defaultSalt) {
		Config config = new Config();
		config.setSaltLength(defaultSalt);
		config.setSecretKey(secretKey);
		configs.add(config);
	}
	
	/**
	 * 기본  configs [collenction] 에서 랜덤하게 환경설정 모델을 선택하여 사용한다. 
	 * @return
	 */
	public static Config getConfig(){
		
		SecureRandom secureRandom = new SecureRandom();
		int pos = secureRandom.nextInt(configs.size());
		return configs.get(pos);
	}
	
	public static Config getConfig(String secretKey){
		for (Config config : configs) {
			if(config.getSecretKey().equals(secretKey)){
				return config;
			}
		}
		return null;
	}
	
	/**
	 * 암호화 모델을 직접 주입 ,  Collection 추가하여 Random 하게 사용 할 수 있다.
	 * @param config
	 */
	public void setConfig(Config config){
		configs.add(config);
	}
	
	//TEST CODE
	public static void main(String[] args) {
		Config config = CryptoConfig.getConfig();
		System.out.println(config.getSaltLength());
	}
	
}
