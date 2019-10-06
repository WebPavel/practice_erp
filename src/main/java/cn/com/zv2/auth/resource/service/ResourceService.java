package cn.com.zv2.auth.resource.service;

import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ResourceService extends BaseService<Resource> {

    /**
     * 获取指定用户所有可操作资源列表
     * @param id 用户ID
     * @return
     */
    List<Resource> listByEmployee(Long id);
}
