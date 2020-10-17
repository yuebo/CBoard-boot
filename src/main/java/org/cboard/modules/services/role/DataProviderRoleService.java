package org.cboard.modules.services.role;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.cboard.config.PropertiesConfig;
import org.cboard.modules.dao.DatasetDao;
import org.cboard.modules.dao.DatasourceDao;
import org.cboard.modules.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

/**
 * Created by yfyuan on 2016/12/23.
 */
@Repository
@Aspect
@Order(2)
public class DataProviderRoleService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DatasourceDao datasourceDao;

    @Autowired
    private DatasetDao datasetDao;

    @Around("execution(* org.cboard.modules.services.DataProviderService.getDimensionValues(..)) ||" +
            "execution(* org.cboard.modules.services.DataProviderService.getColumns(..)) ||" +
            "execution(* org.cboard.modules.services.DataProviderService.queryAggData(..)) ||" +
            "execution(* org.cboard.modules.services.DataProviderService.viewAggDataQuery(..))")
    public Object query(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long datasourceId = (Long) proceedingJoinPoint.getArgs()[0];
        Long datasetId = (Long) proceedingJoinPoint.getArgs()[2];
        String userid = authenticationService.getCurrentUser().getUserId();
        if (datasetId != null) {
            if (datasetDao.checkDatasetRole(userid, datasetId, RolePermission.PATTERN_READ) > 0) {
                return proceedingJoinPoint.proceed();
            }
        } else {
            if (datasourceDao.checkDatasourceRole(userid, datasourceId, RolePermission.PATTERN_READ) > 0) {
                return proceedingJoinPoint.proceed();
            }
        }
        return null;
    }

}
