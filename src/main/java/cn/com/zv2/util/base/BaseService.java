package cn.com.zv2.util.base;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author lb
 * @date 2019/9/21 19:40
 */
@Transactional
// 切入点相当于execution(cn.com.zv2.util.base.BaseService.*(..))
public interface BaseService<T> {
    void save(T model);
    void update(T model);
    void delete(T model);
    T get(Serializable id);
    List<T> list();
    List<T> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize);
    Integer count(BaseQueryModel baseQueryModel);
}
