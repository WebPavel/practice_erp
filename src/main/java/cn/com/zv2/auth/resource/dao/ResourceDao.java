package cn.com.zv2.auth.resource.dao;

import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.util.base.BaseDao;

import java.util.List;

public interface ResourceDao extends BaseDao<Resource> {

    List<Resource> listByEmployeeId(Long id);
}
