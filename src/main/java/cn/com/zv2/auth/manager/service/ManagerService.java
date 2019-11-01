package cn.com.zv2.auth.manager.service;

import cn.com.zv2.auth.manager.entity.Manager;
import cn.com.zv2.core.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ManagerService extends BaseService<Manager> {

}
