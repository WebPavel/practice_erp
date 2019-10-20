<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/18
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>login</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
    <script>
        $(function() {
            $("#commit").click(function() {
                $("form:first").submit();
            });
        });
        function swapImage(srcObj,image_src){
            srcObj.src=image_src;
        }
    </script>
</head>
<body>
<div class="container-login">
    <div class="login-pic">
        <div class="login-text">
            <s:actionerror/>
            <s:form action="employee_login" method="post">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="20%" height="30px">用户名：</td>
                        <td colspan="2">
                            <s:textfield name="employee.username" size="18"/>
                        </td>
                    </tr>
                    <tr>
                        <td height="30px">密码：</td>
                        <td colspan="2">
                            <s:password name="employee.password" size="18"/>
                        </td>
                    </tr>
                    <tr>
                        <td height="30px">验证码：</td>
                        <td width="40%">
                            <input type="text" size="9" />
                        </td>
                        <td width="32%">
                            <img src="${pageContext.request.contextPath}/image/test.gif" />
                        </td>
                    </tr>
                    <tr>
                        <td height="30px">&nbsp;</td>
                        <td colspan="2">
                            <a href="javascript:void(0)" id="commit">
                                <img src="${pageContext.request.contextPath}/image/login_bg.gif"
                                     name="Image1" width="42px"
                                     height="22px" border="0"
                                     onmouseover="swapImage(this,'${pageContext.request.contextPath}/image/login_bg_03.gif')"
                                     onmouseout="swapImage(this,'${pageContext.request.contextPath}/image/login_bg_03.gif')" />
                            </a>
                            <a href="#">
                                <img src="${pageContext.request.contextPath}/image/cancel_bg_03.gif"
                                     name="Image2" width="42px"
                                     height="22px" border="0"
                                     onmouseover="swapImage(this,'${pageContext.request.contextPath}/image/cancel_bg_03.gif')"
                                     onmouseout="swapImage(this,'${pageContext.request.contextPath}/image/cancel_bg_03.gif')" />
                            </a>
                        </td>
                    </tr>
                </table>
            </s:form>
        </div>
    </div>
</div>
</body>
</html>
