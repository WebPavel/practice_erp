package cn.com.zv2.invoice.product.service;

import cn.com.zv2.invoice.product.entity.Product;
import cn.com.zv2.util.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductService extends BaseService<Product> {

    List<Product> listByCategory(Long categoryId);

    /**
     * 商品热度维护（定时器任务作业时间：2:00:00）
     */
    void updateProductPopularity();

    /**
     * 商品库存预警（定时器任务作业时间：9点到11点，每30分钟一次）
     */
    List<Object[]> listWarnProduct();
}
