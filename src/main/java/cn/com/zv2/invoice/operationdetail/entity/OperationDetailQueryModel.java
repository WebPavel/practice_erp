package cn.com.zv2.invoice.operationdetail.entity;

import cn.com.zv2.util.base.BaseQueryModel;
import cn.com.zv2.util.format.DateUtils;

public class OperationDetailQueryModel extends OperationDetail implements BaseQueryModel {
    private Long toGmtOperate;
    private Integer toQuantity;

    private String toGmtOperateView;

    public Long getToGmtOperate() {
        return toGmtOperate;
    }

    public void setToGmtOperate(Long toGmtOperate) {
        this.toGmtOperate = toGmtOperate;
        this.toGmtOperateView = DateUtils.formatDate(toGmtOperate);
    }

    public Integer getToQuantity() {
        return toQuantity;
    }

    public void setToQuantity(Integer toQuantity) {
        this.toQuantity = toQuantity;
    }

    public String getToGmtOperateView() {
        return toGmtOperateView;
    }

    public void setToGmtOperateView(String toGmtOperateView) {
        this.toGmtOperateView = toGmtOperateView;
        this.toGmtOperate = DateUtils.parse(toGmtOperateView);
    }
}
