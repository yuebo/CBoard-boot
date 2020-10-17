package org.cboard.modules.services;

import org.cboard.config.PropertiesConfig;
import org.cboard.modules.dao.RoleDao;
import org.cboard.modules.pojo.DashboardRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yfyuan on 2016/12/14.
 */
@Repository
public class RoleService {

    public static final String RES_BOARD = "board";

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AuthenticationService authenticationService;

    public List<Long> getResRole(String resType) {
        String userid = authenticationService.getCurrentUser().getUserId();
        return roleDao.getRoleResByResIds(userid, resType);
    }

    public List<DashboardRole> getCurrentRoleList(){
        String userid = authenticationService.getCurrentUser().getUserId();
        if (userid.equals(propertiesConfig.getAdminId())) {
            return roleDao.getRoleListAll();
        }
        return roleDao.getCurrentRoleList(userid);
    }
}
