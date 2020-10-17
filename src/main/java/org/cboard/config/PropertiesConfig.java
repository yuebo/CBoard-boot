package org.cboard.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangKun
 * @create 2018-07-31
 * @desc
 **/
@ConfigurationProperties(prefix = "cboard")
@Data
public class PropertiesConfig {
    private String h2Url="jdbc:h2:~/H2Data/cboard;AUTO_SERVER=TRUE;LOG=0;UNDO_LOG=0";
    private String h2UserName="sa";
    private String h2DatabaseName="cboard";
    private String h2Quarz="0 1 0 * * ?";
    private String adminId= "1";
}
