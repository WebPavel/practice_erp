package cn.com.zv2.auth.department.service;

import cn.com.zv2.auth.department.entity.Department;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lb
 * @date 2019/9/19 2:16
 */
@Transactional
public interface DepartmentService extends BaseService<Department> {
}
