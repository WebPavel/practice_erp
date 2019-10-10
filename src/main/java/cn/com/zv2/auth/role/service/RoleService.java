package cn.com.zv2.auth.role.service;

import cn.com.zv2.auth.role.entity.Role;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleService extends BaseService<Role> {

    void save(Role role, Long[] resourceIds, Long[] menuIds);

    void update(Role role, Long[] resourceIds, Long[] menuIds);
}
