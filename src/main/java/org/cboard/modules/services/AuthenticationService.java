package org.cboard.modules.services;

import org.cboard.modules.dto.User;

/**
 * Created by yfyuan on 2016/9/29.
 */
public interface AuthenticationService {

    User getCurrentUser();
}
