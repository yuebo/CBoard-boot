package org.cboard.modules.controller;

import java.util.HashMap;
import java.util.Map;

import org.cboard.config.PropertiesConfig;
import org.cboard.modules.services.BoardService;
import org.cboard.modules.services.HomepageService;
import org.cboard.modules.services.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by february on 2018/12/20.
 */
@RestController
@RequestMapping("/homepage")
public class HomepageController extends BaseController {
    
    @Autowired
    private HomepageService homepageService;
    
    @Autowired
    private BoardService boardService;
    
    @Autowired
    private PropertiesConfig propertiesConfig;

    @RequestMapping(value = "/saveHomepage")
    public ServiceStatus saveHomepage(@RequestParam(name = "boardId", required = true) Long boardId) {
        String userId = getCurrentUserId();
        return homepageService.saveHomepage(boardId, userId);
    }
    
    @RequestMapping(value = "/resetHomepage")
    public ServiceStatus resetHomepage() {
        String userId = getCurrentUserId();
        return homepageService.resetHomepage(userId);
    }
    
    @RequestMapping(value = "/selectHomepage")
    public Long selectHomepage() {
        String userId = getCurrentUserId();
        return homepageService.selectHomepage(userId);
    }
    
    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    public Map<String, ?> loginPage() {           
        String userId = getCurrentUserId();
        // 优先查找当前用户设置的首页
        Long boardId = homepageService.selectHomepage(userId);
        if(boardId == null) {
            // 如当前用户未设置首页，查找由管理员设置的系统首页
            boardId = homepageService.selectHomepage(propertiesConfig.getAdminId());
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        if(boardId == null) {
            // 如当前用户和管理员均未设置首页，将使用默认首页
            result.put("url", "");
            result.put("templateUrl", "org/cboard/view/cboard/homepage.html");
            result.put("controller", "homepageCtrl");
        } else {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", boardId);
            result.put("url", "");
            result.put("params", params);
            result.put("templateUrl", "org/cboard/view/dashboard/view.html");
            result.put("controller", "dashboardViewCtrl");
        }
        return result;
    }
}
