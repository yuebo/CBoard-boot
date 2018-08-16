package org.cboard.dao;

import org.apache.ibatis.annotations.Param;
import org.cboard.pojo.DashboardWidget;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yfyuan on 2016/8/122.
 */
@Repository
public interface WidgetDao {

    List<String> getCategoryList();

    List<DashboardWidget> getAllWidgetList();

    List<DashboardWidget> getWidgetList(String userId);

    List<DashboardWidget> getWidgetListAdmin(String userId);

    int save(DashboardWidget dashboardWidget);

    long countExistWidgetName(Map<String, Object> map);

    int update(DashboardWidget dashboardWidget);

    int delete(@Param("id") Long id, @Param("userId") String userId);

    DashboardWidget getWidget(Long id);

    long checkWidgetRole(@Param("userId") String userId, @Param("widgetId") Long widgetId, @Param("permissionPattern") String permissionPattern);
}
