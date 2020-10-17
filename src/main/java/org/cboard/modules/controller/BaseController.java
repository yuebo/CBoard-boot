package org.cboard.modules.controller;

import org.apache.commons.lang3.StringUtils;
import org.cboard.modules.dto.CBoardActionLog;
import org.cboard.modules.dto.User;
import org.cboard.modules.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * Created by zyong on 2017/9/28.
 */
public class BaseController {

    Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected AuthenticationService authenticationService;

    protected String getCurrentUserId(){
       return authenticationService.getCurrentUser().getUserId();
    }
}
