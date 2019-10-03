package cn.com.zv2.util.interceptor;

import cn.com.zv2.util.exception.ApplicationException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lb
 * @date 2019/10/3 21:01
 */
public class ExceptionInterceptor extends AbstractInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            return invocation.invoke();
        } catch (ApplicationException e) {
            logger.error(e.getMessage(), e);
            ActionSupport actionSupport = (ActionSupport) invocation.getAction();
            String text = actionSupport.getText(e.getMessage());
            actionSupport.addActionError(text);
            return Action.ERROR;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
//            ActionSupport actionSupport = (ActionSupport) invocation.getAction();
//            actionSupport.addActionError("对不起，服务器维护中");
//            return Action.ERROR;
            // 记录日志
            // 发送日志到developer邮箱
            // 报警
            e.printStackTrace();
            return invocation.invoke();
        }
    }
}
