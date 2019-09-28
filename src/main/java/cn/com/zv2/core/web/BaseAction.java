package cn.com.zv2.core.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lb
 * @date 2019/9/21 19:38
 */
public class BaseAction extends ActionSupport {

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

    protected void put(String name, Object object) {
        ActionContext.getContext().put(name, object);
    }

    protected Object get(String name) {
        return ActionContext.getContext().get(name);
    }

    protected void putSession(String name, Object object) {
        ActionContext.getContext().getSession().put(name, object);
    }

    protected Object getSession(String name) {
        return ActionContext.getContext().getSession().get(name);
    }

    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }
}
