package org.cboard.config;

import org.cboard.cache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


/**
 * @author WangKun
 * @create 2018-07-30
 * @desc
 **/
@EnableCaching
public class CacheConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);
//    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheAlias("jvmAggregator");
        LOGGER.info("ehCacheManager init ok");
        return ehCacheManager;
    }
}
