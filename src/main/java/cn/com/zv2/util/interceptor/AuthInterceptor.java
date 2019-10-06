package cn.com.zv2.util.interceptor;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.auth.resource.service.ResourceService;
import cn.com.zv2.util.exception.ApplicationException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import java.util.List;

/**
 * @author lb
 * @date 2019/10/7 4:42
 */
public class AuthInterceptor extends AbstractInterceptor {

    private ResourceService resourceService;

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String actionName = invocation.getProxy().getAction().getClass().getName();
        String methodName = invocation.getProxy().getMethod();
        String requestURL = actionName + "." + methodName;
        String resourceURL = (String) ServletActionContext.getServletContext().getAttribute("resourceURL");
        if (!resourceURL.contains(requestURL)) {
            return invocation.invoke();
        }
        Employee currEmployee = (Employee) ActionContext.getContext().getSession().get(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME);
        // 登录用户可操作资源基本不变，登录后获取其可操作资源路径放入登录用户信息中
        if (currEmployee.getResourceURL().contains(requestURL)) {
            return invocation.invoke();
        }
        throw new ApplicationException("403 Forbidden");
    }

    @Deprecated
    public String doIntercept(ActionInvocation invocation) throws Exception {
        // 1.获取本次操作
        // 2.获取所有需要拦截的操作
        // 3.判断当前操作是否在需要拦截操作列表中
        // 4.必须保障当前用户已登录(可不校验)
        // 5.获取登录用户信息
        // 6.获取当前用户所有可执行操作
        // 7.判断当前操作是否可执行
        String actionName = invocation.getProxy().getAction().getClass().getName();
        String methodName = invocation.getProxy().getMethod();
        String requestURL = actionName + "." + methodName;
        List<Resource> resourceList = resourceService.list();
        StringBuilder url = new StringBuilder();
        for (Resource resource : resourceList) {
            url.append(resource.getUrl());
            url.append(",");
        }
        if (url.indexOf(requestURL) < 0) {
            return invocation.invoke();
        }
        // resource need to be authorized
        Employee currEmployee = (Employee) ActionContext.getContext().getSession().get(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME);
//        if (currEmployee == null) {
//            return Action.LOGIN;
//        }
        List<Resource> resourceListOfEmployee = resourceService.listByEmployee(currEmployee.getId());
        for (Resource resource : resourceListOfEmployee) {
            if (resource.getUrl().equals(requestURL)) {
                return invocation.invoke();
            }
        }
        throw new ApplicationException("403 Forbidden");
    }

}
