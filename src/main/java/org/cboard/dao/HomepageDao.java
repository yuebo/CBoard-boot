package org.cboard.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by february on 2018/12/20.
 */
@Repository
public interface HomepageDao {

    int saveHomepage(@Param("boardId") Long boardId, @Param("userId") String userId);

    int resetHomepage(String userId);

    Long selectHomepage(String userId);
}
