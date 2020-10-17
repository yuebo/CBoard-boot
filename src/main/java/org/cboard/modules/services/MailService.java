package org.cboard.modules.services;

import org.cboard.modules.pojo.DashboardJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by yfyuan on 2017/2/10.
 *
 * @author WangKun
 * @date 2019/04/12
 */
@Service
public class MailService {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    public void sendDashboard(DashboardJob job) throws Exception {
        LOG.info("try to send mail");
    }
}
