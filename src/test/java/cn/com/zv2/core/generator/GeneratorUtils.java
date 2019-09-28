package cn.com.zv2.core.generator;

import cn.com.zv2.auth.employee.entity.Employee;

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
                    bufferedWriter.write("        <property name=\"" + fields[i].getName() + "\" />");
                    bufferedWriter.newLine();
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

    public static void main(String[] args) throws IOException {
        new GeneratorUtils(Employee.class);
        System.out.println("struts.xml不推荐自动生成");
        System.out.println("xxx.hbm.xml未添加关联关系");
        System.out.println("QueryModel未添加自定义范围查询条件");
        System.out.println("DaoImpl未对自定义查询条件属性进行设置");
    }
}
