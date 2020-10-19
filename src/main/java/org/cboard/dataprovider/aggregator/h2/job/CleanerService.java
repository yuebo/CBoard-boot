package org.cboard.dataprovider.aggregator.h2.job;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zyong on 2017/9/18.
 */
@Component
public class CleanerService implements DisposableBean {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    @Qualifier("h2DataSource")
    private BasicDataSource jdbcDataSource;

    @Override
    public void destroy() throws Exception {
        cleanDB();
    }

    protected void cleanDB() {
        try (Connection conn = jdbcDataSource.getConnection();
             Statement statmt = conn.createStatement();) {
            String resetDB = "DROP ALL OBJECTS DELETE FILES";
            LOG.info("Execute: {}", resetDB);
            statmt.execute(resetDB);
        } catch (SQLException e) {
            LOG.error("", e);
        }
    }

}
