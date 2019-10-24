package cn.com.zv2.invoice.operationdetail.dao.impl;

import cn.com.zv2.invoice.operationdetail.dao.OperationDetailDao;
import cn.com.zv2.invoice.operationdetail.entity.OperationDetail;
import cn.com.zv2.invoice.operationdetail.entity.OperationDetailQueryModel;
import cn.com.zv2.util.base.BaseDaoImpl;
import cn.com.zv2.util.base.BaseQueryModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class OperationDetailDaoImpl extends BaseDaoImpl<OperationDetail> implements OperationDetailDao {

    public static final long lastMillisOfDay = 24*60*60*1000 - 1;

    @Override
    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {
        OperationDetailQueryModel operationDetailQueryModel = (OperationDetailQueryModel) baseQueryModel;
        if (operationDetailQueryModel.getWarehouse() != null &&
                operationDetailQueryModel.getWarehouse().getName() != null &&
                operationDetailQueryModel.getWarehouse().getName().trim().length() > 0) {
            detachedCriteria.createAlias("warehouse", "warehouse");
            detachedCriteria.add(Restrictions.like("warehouse.name", "%" + operationDetailQueryModel.getWarehouse().getName() + "%"));
        }
        if (operationDetailQueryModel.getOperator() != null &&
                operationDetailQueryModel.getOperator().getName() != null &&
                operationDetailQueryModel.getOperator().getName().trim().length() > 0) {
            detachedCriteria.createAlias("operator", "operator");
            detachedCriteria.add(Restrictions.like("operator.name", "%" + operationDetailQueryModel.getOperator().getName() + "%"));
        }
        if (operationDetailQueryModel.getProduct() != null &&
                operationDetailQueryModel.getProduct().getName() != null &&
                operationDetailQueryModel.getProduct().getName().trim().length() > 0) {
            detachedCriteria.createAlias("product", "product");
            detachedCriteria.add(Restrictions.like("product.name", "%" + operationDetailQueryModel.getProduct().getName() + "%"));
        }
        if (operationDetailQueryModel.getType() != null && operationDetailQueryModel.getType() != -1) {
            detachedCriteria.add(Restrictions.eq("type", operationDetailQueryModel.getType()));
        }
        if (operationDetailQueryModel.getQuantity() != null && operationDetailQueryModel.getQuantity() > 0) {
            detachedCriteria.add(Restrictions.ge("quantity", operationDetailQueryModel.getQuantity()));
        }
        if (operationDetailQueryModel.getToQuantity() != null && operationDetailQueryModel.getToQuantity() > 0) {
            detachedCriteria.add(Restrictions.le("quantity", operationDetailQueryModel.getToQuantity()));
        }
        if (operationDetailQueryModel.getGmtOperate() != null) {
            detachedCriteria.add(Restrictions.ge("gmtOperate", operationDetailQueryModel.getGmtOperate()));
        }
        if (operationDetailQueryModel.getToGmtOperate() != null) {
            detachedCriteria.add(Restrictions.le("gmtOperate", operationDetailQueryModel.getToGmtOperate() + lastMillisOfDay));
        }
    }
}
