package cn.com.zv2.util.interceptor;

import cn.com.zv2.auth.employee.entity.Employee;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author lb
 * @date 2019/9/19 0:28
 */
public class LoginInterceptor extends AbstractInterceptor {
    private static final String ALLOWED_URL = "cn.com.zv2.auth.employee.web.EmployeeAction.login";
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>");
        String actionName = invocation.getProxy().getAction().getClass().getName();
        String methodName = invocation.getProxy().getMethod();
        String qualifiedName = actionName + "." + methodName;
        System.out.println(qualifiedName);
        if (ALLOWED_URL.equals(qualifiedName)) {
            return invocation.invoke();
        }
        Employee loginEmployee = (Employee) ActionContext.getContext().getSession().get(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME);
        if (loginEmployee == null) {
            return Action.LOGIN;
        }
        return invocation.invoke();
    }
}
