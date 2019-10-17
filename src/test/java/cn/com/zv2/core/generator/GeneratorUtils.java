package cn.com.zv2.core.generator;

import cn.com.zv2.invoice.category.entity.Category;
import cn.com.zv2.invoice.order.entity.Order;
import cn.com.zv2.invoice.product.entity.Product;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lb
 * @date 2019/9/21 2:38
 */
public class GeneratorUtils {

    public static final String line_separator = "\r\n";

    private Class clazz;
    private String uccClassName;
    private String lccClassName;
    private String modelPackageName;
    private String modulePackageName;
    private String moduleDirPath;
    public GeneratorUtils(Class clazz) throws IOException {
        /**
         * 生成文件清单列表
         * -1.数据初始化
         * 0.创建目录
         * 1.QueryModel
         * 2.xxx.hbm.xml
         * 3.Dao
         * 4.DaoImpl
         * 5.Service
         * 6.ServiceImpl
         * 7.Action
         * 8.applicationContext.xml
         * 9.struts.xml
         */
        this.clazz = clazz;
        init();
        generateDirectory();
        generateQueryModel();
        generateHbmXml();
        generateDao();
        generateDaoImpl();
        generateService();
        generateServiceImpl();
        generateAction();
        generateApplicationContextXml();
        modifyStrutsXml();
        generateListPage();
        generateEditPage();
    }

    private void init() {
        uccClassName = clazz.getSimpleName();
        System.out.println(uccClassName);
        lccClassName = uccClassName.substring(0, 1).toLowerCase() + uccClassName.substring(1);
        System.out.println(lccClassName);
        modelPackageName = clazz.getPackage().getName();
        System.out.println("modelPackageName=" + modelPackageName);
        modulePackageName = modelPackageName.substring(0, modelPackageName.length() - 7);
        System.out.println("modulePackageName=" + modulePackageName);
        moduleDirPath = modulePackageName.replace(".", "/");
        System.out.println("moduleDirPath=" + moduleDirPath);
    }

    private static void preGenerateDirectory() {
        File cssDir = new File("src/main/webapp/css");
        cssDir.mkdirs();
        File jsDir = new File("src/main/webapp/js");
        jsDir.mkdirs();
        File imageDir = new File("src/main/webapp/image");
        imageDir.mkdirs();
    }

    private void generateDirectory() {
        /**
         * dao
         * dao/impl
         * service
         * service/impl
         * web
         */
        File daoDir = new File("src/main/java/" + moduleDirPath + "/dao");
        daoDir.mkdirs();
        File daoImplDir = new File("src/main/java/" + moduleDirPath + "/dao/impl");
        daoImplDir.mkdirs();
        File serviceDir = new File("src/main/java/" + moduleDirPath + "/service");
        serviceDir.mkdirs();
        File serviceImplDir = new File("src/main/java/" + moduleDirPath + "/service/impl");
        serviceImplDir.mkdirs();
        File webDir = new File("src/main/java/" + moduleDirPath + "/web");
        webDir.mkdirs();
        File entityDirOfResources = new File("src/main/resources/" + moduleDirPath + "/entity");
        entityDirOfResources.mkdirs();
        File pageDir = new File("src/main/webapp/WEB-INF/jsp/" + lccClassName);
        pageDir.mkdirs();
    }

    private void generateQueryModel() throws IOException {
        File file = new File("src/main/java/" + moduleDirPath + "/entity/" + uccClassName + "QueryModel.java");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("package " + modelPackageName + ";");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("import cn.com.zv2.util.base.BaseQueryModel;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("public class " + uccClassName + "QueryModel extends " + uccClassName + " implements BaseQueryModel {");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("}");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void generateHbmXml() throws IOException {
        File file = new File("src/main/resources/" + moduleDirPath + "/entity/" + uccClassName + ".hbm.xml");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("<!DOCTYPE hibernate-mapping PUBLIC");
        bufferedWriter.newLine();
        bufferedWriter.write("        \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"");
        bufferedWriter.newLine();
        bufferedWriter.write("        \"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">");
        bufferedWriter.newLine();
        bufferedWriter.write("<hibernate-mapping>");
        bufferedWriter.newLine();
        bufferedWriter.write("    <class name=\"" + clazz.getName() + "\" table=\"" + lccClassName + "\">");
        bufferedWriter.newLine();
        bufferedWriter.write("        <id name=\"id\">");
        bufferedWriter.newLine();
        bufferedWriter.write("            <generator class=\"native\" />");
        bufferedWriter.newLine();
        bufferedWriter.write("        </id>");
        bufferedWriter.newLine();


        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getModifiers() == Modifier.PRIVATE && !"id".equals(fields[i].getName())) {
                if (String.class.equals(fields[i].getType()) ||
                        String.class.equals(fields[i].getType()) ||
                        Integer.class.equals(fields[i].getType()) ||
                        Long.class.equals(fields[i].getType()) ||
                        Double.class.equals(fields[i].getType()) ||
                        Date.class.equals(fields[i].getType()) ||
                        BigDecimal.class.equals(fields[i].getType())) {
                    if (!fields[i].getName().endsWith("View")) {
                        bufferedWriter.write("        <property name=\"" + fields[i].getName() + "\" />");
                        bufferedWriter.newLine();
                    }
                }
            }
        }
        bufferedWriter.write("    </class>");
        bufferedWriter.newLine();
        bufferedWriter.write("</hibernate-mapping>");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void generateDao() throws IOException {
        File file = new File("src/main/java/" + moduleDirPath + "/dao/" + uccClassName + "Dao.java");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("package " + modulePackageName + ".dao;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("import " + clazz.getName() + ";");
        bufferedWriter.newLine();
        bufferedWriter.write("import cn.com.zv2.util.base.BaseDao;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("public interface " + uccClassName + "Dao extends BaseDao<" + uccClassName + "> {");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("}");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void generateDaoImpl() throws IOException {
        File file = new File("src/main/java/" + moduleDirPath + "/dao/impl/" + uccClassName + "DaoImpl.java");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("package " + modulePackageName + ".dao.impl;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("import " + modulePackageName + ".dao." + uccClassName + "Dao;");
        bufferedWriter.newLine();
        bufferedWriter.write("import " + clazz.getName() + ";");
        bufferedWriter.newLine();
        bufferedWriter.write("import " + clazz.getName() + "QueryModel;");
        bufferedWriter.newLine();
        bufferedWriter.write("import cn.com.zv2.util.base.BaseDaoImpl;");
        bufferedWriter.newLine();
        bufferedWriter.write("import cn.com.zv2.util.base.BaseQueryModel;");
        bufferedWriter.newLine();
        bufferedWriter.write("import org.hibernate.criterion.DetachedCriteria;");
        bufferedWriter.newLine();
        bufferedWriter.write("import org.hibernate.criterion.Restrictions;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("public class " + uccClassName + "DaoImpl extends BaseDaoImpl<" + uccClassName + "> implements " + uccClassName + "Dao {");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    @Override");
        bufferedWriter.newLine();
        bufferedWriter.write("    public void doQbc(DetachedCriteria detachedCriteria, BaseQueryModel baseQueryModel) {");
        bufferedWriter.newLine();
        bufferedWriter.write("        " + uccClassName + "QueryModel " + lccClassName + "QueryModel = (" + uccClassName + "QueryModel) baseQueryModel;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.write("}");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void generateService() throws IOException {
        File file = new File("src/main/java/" + moduleDirPath + "/service/" + uccClassName + "Service.java");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("package " + modulePackageName + ".service;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("import " + clazz.getName() + ";");
        bufferedWriter.newLine();
        bufferedWriter.write("import cn.com.zv2.util.base.BaseService;");
        bufferedWriter.newLine();
        bufferedWriter.write("import org.springframework.transaction.annotation.Transactional;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("@Transactional");
        bufferedWriter.newLine();
        bufferedWriter.write("public interface " + uccClassName + "Service extends BaseService<" + uccClassName + "> {");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("}");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void generateServiceImpl() throws IOException {
        File file = new File("src/main/java/" + moduleDirPath + "/service/impl/" + uccClassName + "ServiceImpl.java");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("package " + modulePackageName + ".service.impl;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("import " + modulePackageName + ".dao." + uccClassName + "Dao;");
        bufferedWriter.newLine();
        bufferedWriter.write("import " + clazz.getName() + ";");
        bufferedWriter.newLine();
        bufferedWriter.write("import " + modulePackageName + ".service." + uccClassName + "Service;");
        bufferedWriter.newLine();
        bufferedWriter.write("import cn.com.zv2.util.base.BaseQueryModel;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("import java.io.Serializable;");
        bufferedWriter.newLine();
        bufferedWriter.write("import java.util.List;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("public class " + uccClassName + "ServiceImpl implements " + uccClassName + "Service {");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    private " + uccClassName + "Dao " + lccClassName + "Dao;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    public void set" + uccClassName + "Dao(" + uccClassName + "Dao " + lccClassName + "Dao) {");
        bufferedWriter.newLine();
        bufferedWriter.write("        this." + lccClassName + "Dao = " + lccClassName + "Dao;");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    @Override");
        bufferedWriter.newLine();
        bufferedWriter.write("    public void save(" + uccClassName + " " + lccClassName + ") {");
        bufferedWriter.newLine();
        bufferedWriter.write("        " + lccClassName + "Dao.save(" + lccClassName + ");");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    @Override");
        bufferedWriter.newLine();
        bufferedWriter.write("    public void update(" + uccClassName + " " + lccClassName + ") {");
        bufferedWriter.newLine();
        bufferedWriter.write("        " + lccClassName + "Dao.update(" + lccClassName + ");");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    @Override");
        bufferedWriter.newLine();
        bufferedWriter.write("    public void delete(" + uccClassName + " " + lccClassName + ") {");
        bufferedWriter.newLine();
        bufferedWriter.write("        " + lccClassName + "Dao.delete(" + lccClassName + ");");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    @Override");
        bufferedWriter.newLine();
        bufferedWriter.write("    public " + uccClassName + " get(Serializable id) {");
        bufferedWriter.newLine();
        bufferedWriter.write("        return " + lccClassName + "Dao.get(id);");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    @Override");
        bufferedWriter.newLine();
        bufferedWriter.write("    public List<" + uccClassName + "> list() {");
        bufferedWriter.newLine();
        bufferedWriter.write("        return " + lccClassName + "Dao.list();");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    @Override");
        bufferedWriter.newLine();
        bufferedWriter.write("    public List<" + uccClassName + "> list(BaseQueryModel baseQueryModel, Integer pageNum, Integer pageSize) {");
        bufferedWriter.newLine();
        bufferedWriter.write("        return " + lccClassName + "Dao.list(baseQueryModel, pageNum, pageSize);");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    @Override");
        bufferedWriter.newLine();
        bufferedWriter.write("    public Integer count(BaseQueryModel baseQueryModel) {");
        bufferedWriter.newLine();
        bufferedWriter.write("        return " + lccClassName + "Dao.count(baseQueryModel);");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("}");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void generateAction() throws IOException {
        File file = new File("src/main/java/" + moduleDirPath + "/web/" + uccClassName + "Action.java");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("package " + modulePackageName + ".web;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("import " + clazz.getName() + ";");
        bufferedWriter.newLine();
        bufferedWriter.write("import " + modelPackageName + "." + uccClassName + "QueryModel;");
        bufferedWriter.newLine();
        bufferedWriter.write("import " + modulePackageName + ".service." + uccClassName + "Service;");
        bufferedWriter.newLine();
        bufferedWriter.write("import cn.com.zv2.util.base.BaseAction;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("import java.util.List;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("public class " + uccClassName + "Action extends BaseAction {");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    public " + uccClassName + " " + lccClassName + " = new " + uccClassName + "();");
        bufferedWriter.newLine();
        bufferedWriter.write("    public " + uccClassName + "QueryModel " + lccClassName + "QueryModel = new " + uccClassName + "QueryModel();");
        bufferedWriter.newLine();
        bufferedWriter.write("    private " + uccClassName + "Service " + lccClassName + "Service;");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    public void set" + uccClassName + "Service(" + uccClassName + "Service " + lccClassName + "Service) {");
        bufferedWriter.newLine();
        bufferedWriter.write("        this." + lccClassName + "Service = " + lccClassName + "Service;");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    public String list() {");
        bufferedWriter.newLine();
        bufferedWriter.write("        setTotalRow(" + lccClassName + "Service.count(" + lccClassName + "QueryModel));");
        bufferedWriter.newLine();
        bufferedWriter.write("        List<" + uccClassName + "> " + lccClassName + "List = " + lccClassName + "Service.list(" + lccClassName + "QueryModel, pageNum, pageSize);");
        bufferedWriter.newLine();
        bufferedWriter.write("        put(\"" + lccClassName + "List\", " + lccClassName + "List);");
        bufferedWriter.newLine();
        bufferedWriter.write("        return LIST;");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    public String edit() {");
        bufferedWriter.newLine();
        bufferedWriter.write("        if (" + lccClassName + ".getId() != null) {");
        bufferedWriter.newLine();
        bufferedWriter.write("            " + lccClassName + " = " + lccClassName + "Service.get(" + lccClassName + ".getId());");
        bufferedWriter.newLine();
        bufferedWriter.write("        }");
        bufferedWriter.newLine();
        bufferedWriter.write("        return EDIT;");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    public String updateIfPresent() {");
        bufferedWriter.newLine();
        bufferedWriter.write("        if (" + lccClassName + ".getId() == null) {");
        bufferedWriter.newLine();
        bufferedWriter.write("            " + lccClassName + "Service.save(" + lccClassName + ");");
        bufferedWriter.newLine();
        bufferedWriter.write("        } else {");
        bufferedWriter.newLine();
        bufferedWriter.write("            " + lccClassName + "Service.update(" + lccClassName + ");");
        bufferedWriter.newLine();
        bufferedWriter.write("        }");
        bufferedWriter.newLine();
        bufferedWriter.write("        return REDIRECT_LIST;");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("    public String delete() {");
        bufferedWriter.newLine();
        bufferedWriter.write("        " + lccClassName + "Service.delete(" + lccClassName + ");");
        bufferedWriter.newLine();
        bufferedWriter.write("        return REDIRECT_LIST;");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.write("}");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void generateApplicationContextXml() throws IOException {
        File file = new File("src/main/resources/" + moduleDirPath + "/applicationContext-" + lccClassName + ".xml");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bufferedWriter.newLine();
        bufferedWriter.write("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
        bufferedWriter.newLine();
        bufferedWriter.write("       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        bufferedWriter.newLine();
        bufferedWriter.write("       xsi:schemaLocation=\"");
        bufferedWriter.newLine();
        bufferedWriter.write("http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\">");
        bufferedWriter.newLine();
        bufferedWriter.write("    <!--Action-->");
        bufferedWriter.newLine();
        bufferedWriter.write("    <bean id=\"" + lccClassName + "Action\" class=\"" + modulePackageName + ".web." + uccClassName + "Action\" scope=\"prototype\">");
        bufferedWriter.newLine();
        bufferedWriter.write("        <property name=\"" + lccClassName + "Service\" ref=\"" + lccClassName + "Service\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("    </bean>");
        bufferedWriter.newLine();
        bufferedWriter.write("    <!--Service-->");
        bufferedWriter.newLine();
        bufferedWriter.write("    <bean id=\"" + lccClassName + "Service\" class=\"" + modulePackageName + ".service.impl." + uccClassName + "ServiceImpl\">");
        bufferedWriter.newLine();
        bufferedWriter.write("        <property name=\"" + lccClassName + "Dao\" ref=\"" + lccClassName + "Dao\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("    </bean>");
        bufferedWriter.newLine();
        bufferedWriter.write("    <!--Dao-->");
        bufferedWriter.newLine();
        bufferedWriter.write("    <bean id=\"" + lccClassName + "Dao\" class=\"" + modulePackageName + ".dao.impl." + uccClassName + "DaoImpl\">");
        bufferedWriter.newLine();
        bufferedWriter.write("        <property name=\"sessionFactory\" ref=\"sessionFactory\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("    </bean>");
        bufferedWriter.newLine();
        bufferedWriter.write("</beans>");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void modifyStrutsXml() throws IOException {
        File file = new File("src/main/resources/struts.xml");
        long fileLength = file.length();
        byte[] buf = new byte[new Long(fileLength).intValue()];
        InputStream inputStream = new FileInputStream(file);
        inputStream.read(buf);
        inputStream.close();
        String content = new String(buf);
        int idx = content.lastIndexOf("    </package>");
        String writtenContent = "        <!--" + uccClassName + "-->" + line_separator + "        <action name=\"" + lccClassName + "_*\" class=\"" + lccClassName + "Action\" method=\"{1}\">" + line_separator + "        </action>" + line_separator + line_separator + "";
        StringBuilder stringBuilder = new StringBuilder(content);
        stringBuilder.insert(idx, writtenContent);
        System.out.println(stringBuilder);
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(stringBuilder.toString().getBytes());
        outputStream.close();
    }

    private void generateListPage() throws IOException {
        File file = new File("src/main/webapp/WEB-INF/jsp/" + lccClassName + "/list.jsp");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\" isELIgnored=\"false\" %>");
        bufferedWriter.newLine();
        bufferedWriter.write("<%@ taglib prefix=\"s\" uri=\"/struts-tags\" %>");
        bufferedWriter.newLine();
        bufferedWriter.write("<link href=\"${pageContext.request.contextPath}/css/index.css\" rel=\"stylesheet\" type=\"text/css\">");
        bufferedWriter.newLine();
        bufferedWriter.write("<script type=\"text/javascript\" src=\"${pageContext.request.contextPath}/js/jquery-1.12.4.js\"></script>");
        bufferedWriter.newLine();
        bufferedWriter.write("<script type=\"text/javascript\">");
        bufferedWriter.newLine();
        bufferedWriter.write("    $(function () {");
        bufferedWriter.newLine();
        bufferedWriter.write("        $(\"#query\").click(function () {");
        bufferedWriter.newLine();
        bufferedWriter.write("            $(\"[name=pageNum]\").val(1);");
        bufferedWriter.newLine();
        bufferedWriter.write("            $(\"form:first\").submit();");
        bufferedWriter.newLine();
        bufferedWriter.write("        });");
        bufferedWriter.newLine();
        bufferedWriter.write("    });");
        bufferedWriter.newLine();
        bufferedWriter.write("    function confirm(tip, id) {");
        bufferedWriter.newLine();
        bufferedWriter.write("        top.lock.show();");
        bufferedWriter.newLine();
        bufferedWriter.write("        top.$('context').style.display = 'block';");
        bufferedWriter.newLine();
        bufferedWriter.write("        top.$('context-text').innerHTML = tip;");
        bufferedWriter.newLine();
        bufferedWriter.write("        top.$('hide-action').value = '" + lccClassName + "_delete.action?" + lccClassName + ".id=' + id;");
        bufferedWriter.newLine();
        bufferedWriter.write("    }");
        bufferedWriter.newLine();
        bufferedWriter.write("</script>");
        bufferedWriter.newLine();
        bufferedWriter.write("<div class=\"content-right\">");
        bufferedWriter.newLine();
        bufferedWriter.write("    <div class=\"content-right-pic_w\">");
        bufferedWriter.newLine();
        bufferedWriter.write("        <div style=\"margin:8px auto auto 12px;margin-top:6px\">");
        bufferedWriter.newLine();
        bufferedWriter.write("            <span class=\"page_title\">管理</span>");
        bufferedWriter.newLine();
        bufferedWriter.write("        </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("    </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("    <div class=\"content-text\">");
        bufferedWriter.newLine();
        bufferedWriter.write("        <s:form action=\"" + lccClassName + "_list.action\" method=\"post\">");
        bufferedWriter.newLine();
        bufferedWriter.write("            <div class=\"square-order-top\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:14px; font-weight:bold; font-family:'黑体';\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                    <tr>");
        bufferedWriter.newLine();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if ("id".equals(fieldName)) {
                continue;
            }
            bufferedWriter.write("                        <td width=\"\">:</td>");
            bufferedWriter.newLine();
            bufferedWriter.write("                        <td width=\"\"><s:textfield name=\"" + lccClassName + "QueryModel." + fieldName + "\" size=\"18\"/></td>");
            bufferedWriter.newLine();
        }
        bufferedWriter.write("                        <td width=\"70\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                            <a id=\"query\"><img src=\"${pageContext.request.contextPath}/image/can_b_01.gif\" border=\"0\"/></a>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        </td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        <td width=\"70\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                            <a href=\"" + lccClassName + "_edit.action\"><img src=\"${pageContext.request.contextPath}/image/can_b_02.gif\" border=\"0\"/></a>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        </td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                    </tr>");
        bufferedWriter.newLine();
        bufferedWriter.write("                </table>");
        bufferedWriter.newLine();
        bufferedWriter.write("            </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("            <!--\"square-order-top\"end-->");
        bufferedWriter.newLine();
        bufferedWriter.write("            <div class=\"square-order\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                <s:if test=\"#" + lccClassName + "List.size() == 0\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                    <center>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        <span style=\"font-size:20px;color:#96D34A;font-weight:bold\">没有查找到满足条件的数据！</span>");
        bufferedWriter.newLine();
        bufferedWriter.write("                    </center>");
        bufferedWriter.newLine();
        bufferedWriter.write("                </s:if>");
        bufferedWriter.newLine();
        bufferedWriter.write("                <s:else>");
        bufferedWriter.newLine();
        bufferedWriter.write("                    <table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                        <tr align=\"center\" style=\"background:url(${pageContext.request.contextPath}/image/table_bg.gif) repeat-x;\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                            <td width=\"16%\">操作</td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        </tr>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        <s:iterator value=\"" + lccClassName + "List\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                            <tr align=\"center\" bgcolor=\"#FFFFFF\">");
        bufferedWriter.newLine();
        for (Field field : fields) {
            String fieldName = field.getName();
            if ("id".equals(fieldName)) {
                continue;
            }
            bufferedWriter.write("                                <td><s:property value=\"" + fieldName + "\"/></td>");
            bufferedWriter.newLine();
        }
        bufferedWriter.write("                                <td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                    <img src=\"${pageContext.request.contextPath}/image/icon_03.gif\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                    <span style=\"line-height:12px; text-align:center;\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                                        <s:a action=\"" + lccClassName + "_edit\" cssClass=\"edit\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                                            <s:param name=\"" + lccClassName + ".id\" value=\"id\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                            修改");
        bufferedWriter.newLine();
        bufferedWriter.write("                                        </s:a>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                    </span>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                    <img src=\"${pageContext.request.contextPath}/image/icon_04.gif\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                    <span style=\"line-height:12px; text-align:center;\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                                        <a href=\"javascript:void(0)\" class=\"edit\" onclick=\"confirm('是否删除该项数据？', <s:property value=\"id\"/>)\">删除</a>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                    </span>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                </td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                            </tr>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        </s:iterator>");
        bufferedWriter.newLine();
        bufferedWriter.write("                    </table>");
        bufferedWriter.newLine();
        bufferedWriter.write("                    <%@ include file=\"/WEB-INF/jsp/tools/paging.jsp\" %>");
        bufferedWriter.newLine();
        bufferedWriter.write("                </s:else>");
        bufferedWriter.newLine();
        bufferedWriter.write("            </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("        </s:form>");
        bufferedWriter.newLine();
        bufferedWriter.write("    </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("    <div class=\"content-bbg\"></div>");
        bufferedWriter.newLine();
        bufferedWriter.write("</div>");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void generateEditPage() throws IOException {
        File file = new File("src/main/webapp/WEB-INF/jsp/" + lccClassName + "/edit.jsp");
        if (file.exists()) {
            return;
        }
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\" isELIgnored=\"false\" %>");
        bufferedWriter.newLine();
        bufferedWriter.write("<%@ taglib prefix=\"s\" uri=\"/struts-tags\" %>");
        bufferedWriter.newLine();
        bufferedWriter.write("<link href=\"/css/index.css\" rel=\"stylesheet\" type=\"text/css\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("<script type=\"text/javascript\" src=\"/js/jquery-1.12.4.js\"></script>");
        bufferedWriter.newLine();
        bufferedWriter.write("<script type=\"text/javascript\">");
        bufferedWriter.newLine();
        bufferedWriter.write("    $(function () {");
        bufferedWriter.newLine();
        bufferedWriter.write("        $(\"#commit\").click(function () {");
        bufferedWriter.newLine();
        bufferedWriter.write("            $(\"form:first\").submit();");
        bufferedWriter.newLine();
        bufferedWriter.write("        });");
        bufferedWriter.newLine();
        bufferedWriter.write("    });");
        bufferedWriter.newLine();
        bufferedWriter.write("</script>");
        bufferedWriter.newLine();
        bufferedWriter.write("<div class=\"content-right\">");
        bufferedWriter.newLine();
        bufferedWriter.write("    <div class=\"content-right-pic_w\">");
        bufferedWriter.newLine();
        bufferedWriter.write("        <div style=\"margin:8px auto auto 12px;margin-top:6px\">");
        bufferedWriter.newLine();
        bufferedWriter.write("            <span class=\"page_title\">管理</span>");
        bufferedWriter.newLine();
        bufferedWriter.write("        </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("    </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("    <div class=\"content-text\">");
        bufferedWriter.newLine();
        bufferedWriter.write("        <div class=\"square-order\">");
        bufferedWriter.newLine();
        bufferedWriter.write("            <s:form action=\"" + lccClassName + "_updateIfPresent\" method=\"post\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                <s:hidden name=\"" + lccClassName + ".id\"/>");
        bufferedWriter.newLine();
        bufferedWriter.write("                <div style=\"border:1px solid #cecece;\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                        <tr bgcolor=\"#FFFFFF\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                            <td>&nbsp;</td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        </tr>");
        bufferedWriter.newLine();
        bufferedWriter.write("                    </table>");
        bufferedWriter.newLine();
        bufferedWriter.write("                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
        bufferedWriter.newLine();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if ("id".equals(fieldName)) {
                continue;
            }
            bufferedWriter.write("                        <tr bgcolor=\"#FFFFFF\">");
            bufferedWriter.newLine();
            bufferedWriter.write("                            <td width=\"18%\" height=\"30\" align=\"center\">:</td>");
            bufferedWriter.newLine();
            bufferedWriter.write("                            <td width=\"82%\" colspan=\"3\">");
            bufferedWriter.newLine();
            bufferedWriter.write("                                <s:textfield name=\"" + lccClassName + "." + fieldName + "\" size=\"25\"/>");
            bufferedWriter.newLine();
            bufferedWriter.write("                            </td>");
            bufferedWriter.newLine();
            bufferedWriter.write("                        </tr>");
            bufferedWriter.newLine();
        }
        bufferedWriter.write("                        <tr bgcolor=\"#FFFFFF\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                            <td colspan=\"4\">&nbsp;</td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        </tr>");
        bufferedWriter.newLine();
        bufferedWriter.write("                    </table>");
        bufferedWriter.newLine();
        bufferedWriter.write("                </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("                <div class=\"order-bottom\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                    <div style=\"margin:1px auto auto 1px;\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
        bufferedWriter.newLine();
        bufferedWriter.write("                            <tr>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                <td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                    <a href=\"javascript:void(0)\" id=\"commit\"><img src=\"/image/order_tuo.gif\" border=\"0\"/></a>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                </td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                <td>&nbsp;</td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                <td><a href=\"#\"><img src=\"/image/order_tuo.gif\" border=\"0\"/></a></td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                <td>&nbsp;</td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                                <td><a href=\"#\"><img src=\"/image/order_tuo.gif\" border=\"0\"/></a></td>");
        bufferedWriter.newLine();
        bufferedWriter.write("                            </tr>");
        bufferedWriter.newLine();
        bufferedWriter.write("                        </table>");
        bufferedWriter.newLine();
        bufferedWriter.write("                    </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("                </div>");
        bufferedWriter.newLine();
        bufferedWriter.write("            </s:form>");
        bufferedWriter.newLine();
        bufferedWriter.write("        </div><!--\"square-order\"end-->");
        bufferedWriter.newLine();
        bufferedWriter.write("    </div><!--\"content-text\"end-->");
        bufferedWriter.newLine();
        bufferedWriter.write("    <div class=\"content-bbg\"><img src=\"/image/content_bbg.jpg\"/></div>");
        bufferedWriter.newLine();
        bufferedWriter.write("</div>");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        preGenerateDirectory();
        new GeneratorUtils(Order.class);
        System.out.println("struts.xml不推荐自动生成");
        System.out.println("xxx.hbm.xml未添加关联关系");
        System.out.println("QueryModel未添加自定义范围查询条件");
        System.out.println("DaoImpl未对自定义查询条件属性进行设置");
    }
}
