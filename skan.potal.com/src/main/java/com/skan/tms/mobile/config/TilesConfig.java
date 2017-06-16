package com.skan.tms.mobile.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * 
 * <pre>
 * Class Name  : TileConfig.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 7. 25.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2016. 7. 25.
 * @version 
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Configuration
public class TilesConfig {
	
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {"WEB-INF/tiles/tiles.xml"
												,"WEB-INF/tiles/tilesPC.xml"
												,"WEB-INF/tiles/tilesMobile.xml"});
		
		configurer.setCheckRefresh(true);
		return  configurer;
	}
	
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		Properties props = new Properties();
		tilesViewResolver.setAttributes(props);
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	
	/**
	 * 디바이스에 따른 서브픽스 설정
	 * @return
	 */
	@Bean
	public LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver() {
		LiteDeviceDelegatingViewResolver resolver = new LiteDeviceDelegatingViewResolver(this.tilesViewResolver());
		resolver.setNormalSuffix(".mobile");
		resolver.setMobileSuffix(".mobile");
		resolver.setTabletSuffix(".mobile");
		resolver.setEnableFallback(true);
		return resolver;
	}

}
