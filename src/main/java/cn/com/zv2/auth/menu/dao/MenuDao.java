package cn.com.zv2.auth.menu.dao;

import cn.com.zv2.auth.menu.entity.Menu;
import cn.com.zv2.util.base.BaseDao;

import java.util.List;

public interface MenuDao extends BaseDao<Menu> {

    List<Menu> listByParentIdIsOneOrZero();

    List<Menu> listLevel1MenuByEmployeeId(Long employeeId);

    List<Menu> listByEmployeeIdAndParentId(Long employeeId, Long parentId);
}
