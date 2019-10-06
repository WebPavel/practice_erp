package cn.com.zv2.auth.resource.service;

import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ResourceService extends BaseService<Resource> {

}
