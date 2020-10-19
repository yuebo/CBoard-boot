package org.cboard.modules.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.cboard.config.PropertiesConfig;
import org.cboard.modules.dao.*;
import org.cboard.modules.dto.*;
import org.cboard.modules.pojo.*;
import org.cboard.modules.services.AdminSerivce;
import org.cboard.modules.services.DatasourceService;
import org.cboard.modules.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by yfyuan on 2016/12/2.
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminSerivce adminSerivce;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private BoardDao boardDao;
    @Autowired
    private DatasetDao datasetDao;
    @Autowired
    private DatasourceDao datasourceDao;
    @Autowired
    private WidgetDao widgetDao;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DatasourceService datasourceService;


    @RequestMapping(value = "/saveNewUser")
    public String saveNewUser(@RequestParam(name = "user") String user) {
        JSONObject jsonObject = JSONObject.parseObject(user);
        return adminSerivce.addUser(UUID.randomUUID().toString(), jsonObject.getString("loginName"), jsonObject.getString("userName"), jsonObject.getString("userPassword"));
    }

    @RequestMapping(value = "/updateUser")
    public String updateUser(@RequestParam(name = "user") String user) {
        JSONObject jsonObject = JSONObject.parseObject(user);
        return adminSerivce.updateUser(jsonObject.getString("userId"), jsonObject.getString("loginName"), jsonObject.getString("userName"), jsonObject.getString("userPassword"));
    }

    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam(name = "userId") String userId) {
        return adminSerivce.deleteUser(userId);
    }

    @RequestMapping(value = "/getUserList")
    public List<DashboardUser> getUserList() {
        List<DashboardUser> list = userDao.getUserList();
        return list;
    }

    @RequestMapping(value = "/saveRole")
    public String saveRole(@RequestParam(name = "role") String role) {
        JSONObject jsonObject = JSONObject.parseObject(role);
        return adminSerivce.addRole(UUID.randomUUID().toString(), jsonObject.getString("roleName"), jsonObject.getString("userId"));
    }

    @RequestMapping(value = "/updateRole")
    public String updateRole(@RequestParam(name = "role") String role) {
        JSONObject jsonObject = JSONObject.parseObject(role);
        return adminSerivce.updateRole(jsonObject.getString("roleId"), jsonObject.getString("roleName"), jsonObject.getString("userId"));
    }

    @RequestMapping(value = "/deleteRole")
    public String deleteRole(@RequestParam(name = "roleId") String roleId) {
        return adminSerivce.deleteRole(roleId);
    }

    @RequestMapping(value = "/getRoleList")
    public List<DashboardRole> getRoleList() {
        List<DashboardRole> list = roleDao.getRoleList(getCurrentUserId());
        return list;
    }

    @RequestMapping(value = "/getRoleListAll")
    public List<DashboardRole> getRoleListAll() {
        List<DashboardRole> list = roleDao.getRoleListAll();
        return list;
    }

    @RequestMapping(value = "/updateUserRole")
    public String updateUserRole(@RequestParam(name = "userIdArr") String userIdArr, @RequestParam(name = "roleIdArr") String roleIdArr) {
        return adminSerivce.updateUserRole(
                JSONArray.parseArray(userIdArr).toArray(new String[]{}),
                JSONArray.parseArray(roleIdArr).toArray(new String[]{}),
                getCurrentUserId()
        );
    }

    @RequestMapping(value = "/deleteUserRole")
    public String deleteUserRole(@RequestParam(name = "userIdArr") String userIdArr, @RequestParam(name = "roleIdArr") String roleIdArr) {
        return adminSerivce.deleteUserRoles(
                JSONArray.parseArray(userIdArr).toArray(new String[]{}),
                JSONArray.parseArray(roleIdArr).toArray(new String[]{}),
                getCurrentUserId()
        );
    }

    @RequestMapping(value = "/getUserRoleList")
    public List<DashboardUserRole> getUserRoleList() {
        List<DashboardUserRole> list = userDao.getUserRoleList();
        return list;
    }

    @RequestMapping(value = "/getRoleResList")
    public List<ViewDashboardRoleRes> getRoleResList() {
        List<DashboardRoleRes> list = roleDao.getRoleResList();
        return list.stream().map(ViewDashboardRoleRes::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/updateRoleRes")
    public String updateRoleRes(@RequestParam(name = "roleIdArr") String roleIdArr, @RequestParam(name = "resIdArr") String resIdArr) {
        return adminSerivce.updateRoleRes(JSONArray.parseArray(roleIdArr).toArray(new String[]{}), resIdArr);
    }

    @RequestMapping(value = "/updateRoleResUser")
    public String updateRoleResUser(@RequestParam(name = "roleIdArr") String roleIdArr, @RequestParam(name = "resIdArr") String resIdArr) {
        return adminSerivce.updateRoleResUser(JSONArray.parseArray(roleIdArr).toArray(new String[]{}), resIdArr);
    }

    @RequestMapping(value = "/isAdmin")
    public boolean isAdmin() {
        return propertiesConfig.getAdminId().equals(getCurrentUserId());
    }

    @RequestMapping(value = "/isConfig")
    public boolean isConfig(@RequestParam(name = "type") String type) {
        if (getCurrentUserId().equals(propertiesConfig.getAdminId())) {
            return true;
        } else if (type.equals("widget")) {
            List<Long> menuIdList = menuDao.getMenuIdByUserRole(getCurrentUserId());
            if (menuIdList.contains(1L) && menuIdList.contains(4L)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/getDatasetList")
    public List<ViewDashboardDataset> getDatasetList() {
        List<DashboardDataset> list = datasetDao.getDatasetListAdmin(getCurrentUserId());
        return Lists.transform(list, ViewDashboardDataset.TO);
    }

    @RequestMapping(value = "/getDatasetListUser")
    public List<ViewDashboardDataset> getDatasetListUser() {
        List<DashboardDataset> list = adminSerivce.getDatasetList(getCurrentUserId());
        return Lists.transform(list, ViewDashboardDataset.TO);
    }


    @RequestMapping(value = "/getBoardList")
    public List<ViewDashboardBoard> getBoardList() {
        List<DashboardBoard> list = boardDao.getBoardListAdmin(getCurrentUserId());
        return Lists.transform(list, ViewDashboardBoard.TO);
    }

    @RequestMapping(value = "/getBoardListUser")
    public List<ViewDashboardBoard> getBoardListUser() {
        List<DashboardBoard> list = adminSerivce.getBoardList(getCurrentUserId());
        return Lists.transform(list, ViewDashboardBoard.TO);
    }

    @RequestMapping(value = "/getWidgetList")
    public List<ViewDashboardWidget> getWidgetList() {
        List<DashboardWidget> list = widgetDao.getWidgetListAdmin(getCurrentUserId());
        return Lists.transform(list, ViewDashboardWidget.TO);
    }

    @RequestMapping(value = "/getWidgetListUser")
    public List<ViewDashboardWidget> getWidgetListUser() {
        List<DashboardWidget> list = adminSerivce.getWidgetList(getCurrentUserId());
        return Lists.transform(list, ViewDashboardWidget.TO);
    }

    @RequestMapping(value = "/getDatasourceList")
    public List<ViewDashboardDatasource> getDatasourceList() {
        return datasourceService.getViewDatasourceList(() -> datasourceDao.getDatasourceListAdmin(getCurrentUserId()));
    }

    @RequestMapping(value = "/getMenuList")
    public List<DashboardMenu> getMenuList() {
        if (propertiesConfig.getAdminId().equals(getCurrentUserId())) {
            return menuService.getMenuList();
        } else {
            List<Long> menuId = menuDao.getMenuIdByRoleAdmin(getCurrentUserId());
            return menuService.getMenuList().stream().filter(e -> menuId.stream().anyMatch(id -> id.equals(e.getMenuId()))).collect(Collectors.toList());
        }
    }
}
