package org.cboard;

import org.cboard.config.CacheConfig;
import org.cboard.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ServletComponentScan
@Import({DataSourceConfig.class, CacheConfig.class})
public class CboardApplication {
    public static ConfigurableApplicationContext run;

    public static void main(String[] args) {
        run = SpringApplication.run(CboardApplication.class, args);
    }
}
