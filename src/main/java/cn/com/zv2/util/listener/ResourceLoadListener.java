package cn.com.zv2.util.listener;

import cn.com.zv2.auth.resource.entity.Resource;
import cn.com.zv2.auth.resource.service.ResourceService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * @author lb
 * @date 2019/10/7 6:33
 */
public class ResourceLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        ResourceService resourceService = (ResourceService) webApplicationContext.getBean("resourceService");
        List<Resource> resourceList = resourceService.list();
        StringBuilder url = new StringBuilder();
        for (Resource resource : resourceList) {
            url.append(resource.getUrl());
            url.append(",");
        }
        servletContext.setAttribute("resourceURL", url.toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
