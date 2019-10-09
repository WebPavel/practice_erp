package cn.com.zv2.core.web;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.*;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author lb
 * @date 2019/10/10 0:05
 */
public class BaseActionV2 extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware, RequestAware, SessionAware, ApplicationAware {

    public static final String LIST = "list";
    public static final String REDIRECT_LIST = "redirectList";
    public static final String EDIT = "edit";

    public Integer pageNum = 1;
    public Integer pageSize = 5;
    public Integer totalPage;
    public Integer totalRow;

    public String getActionName() {
        String actionName = this.getClass().getSimpleName();
        String prefix = actionName.substring(0, actionName.length() - 6);
        return prefix.substring(0, 1).toLowerCase() + prefix.substring(1);
    }

    protected void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
        this.totalPage = (totalRow + pageSize - 1) / pageSize;
    }

    protected HttpServletRequest httpServletRequest;
    protected HttpServletResponse httpServletResponse;
    protected ServletContext servletContext;

    protected Map<String, Object> requestMap;
    protected Map<String, Object> sessionMap;
    protected Map<String, Object> applicationMap;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.httpServletResponse = response;
    }

    @Override
    public void setServletContext(ServletContext context) {
        this.servletContext = context;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.requestMap = request;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.sessionMap = session;
    }

    @Override
    public void setApplication(Map<String, Object> application) {
        this.applicationMap = application;
    }

    public void put(String key, Object value) {
        requestMap.put(key, value);
    }

    public void putSession(String key, Object value) {
        sessionMap.put(key, value);
    }

    public void putApplication(String key, Object value) {
        applicationMap.put(key, value);
    }

    public Object get(String key) {
        return requestMap.get(key);
    }

    public Object getFromSession(String key) {
        return sessionMap.get(key);
    }

    public Object getFromApplication(String key) {
        return applicationMap.get(key);
    }

    public Object get(String key, Object defaultValue) {
        Object result = requestMap.get(key);
        return result == null ? defaultValue : result;
    }

    public Object getFromSession(String key, Object defaultValue) {
        Object result = sessionMap.get(key);
        return result == null ? defaultValue : result;
    }

    public Object getFromApplication(String key, Object defaultValue) {
        Object result = applicationMap.get(key);
        return result == null ? defaultValue : result;
    }

}
