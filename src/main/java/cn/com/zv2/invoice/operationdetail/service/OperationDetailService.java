package cn.com.zv2.invoice.operationdetail.service;

import cn.com.zv2.invoice.operationdetail.entity.OperationDetail;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OperationDetailService extends BaseService<OperationDetail> {

}
