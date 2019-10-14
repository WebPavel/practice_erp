<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(function () {
        $("#query").click(function () {
            $("[name=pageNum]").val(1);
            $("form:first").submit();
        });
    });
    function confirm(tip, id) {
        top.lock.show();
        top.$('context').style.display = 'block';
        top.$('context-text').innerHTML = tip;
        top.$('hide-action').value = 'category_delete.action?category.id=' + id;
    }
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">商品类别管理</span>
        </div>
    </div>
    <div class="content-text">
        <s:form action="category_list.action" method="post">
            <div class="square-order-top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
                    <tr>
                        <td width="20%" height="30">类别名称:</td>
                        <td width="25%"><s:textfield name="categoryQueryModel.name" size="18"/></td>
                        <td width="20%">供应商:</td>
                        <td width="20%"><s:select name="supplierId" list="supplierList" listKey="id" listValue="name" headerKey="-1" headerValue="----请选择----" cssClass="width"></s:select></td>
                        <td width="70">
                            <a id="query"><img src="${pageContext.request.contextPath}/image/can_b_01.gif" border="0"/></a>
                        </td>
                        <td width="70">
                            <a href="category_edit.action"><img src="${pageContext.request.contextPath}/image/can_b_02.gif" border="0"/></a>
                        </td>
                    </tr>
                </table>
            </div>
            <!--"square-order-top"end-->
            <div class="square-order">
                <s:if test="#categoryList.size() == 0">
                    <center>
                        <span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
                    </center>
                </s:if>
                <s:else>
                    <table width="100%" border="1" cellpadding="0" cellspacing="0">
                        <tr align="center" style="background:url(${pageContext.request.contextPath}/image/table_bg.gif) repeat-x;">
                            <td width="30%" height="30">类别名称</td>
                            <td width="30%">供应商</td>
                            <td width="40%">操作</td>
                        </tr>
                        <s:iterator value="categoryList">
                            <tr align="center" bgcolor="#FFFFFF">
                                <td><s:property value="name"/></td>
                                <td><s:property value="supplier.name"/></td>
                                <td>
                                    <img src="${pageContext.request.contextPath}/image/icon_03.gif"/>
                                    <span style="line-height:12px; text-align:center;">
                                        <s:a action="category_edit" cssClass="edit">
                                            <s:param name="category.id" value="id"/>
                                            修改
                                        </s:a>
                                    </span>
                                    <img src="${pageContext.request.contextPath}/image/icon_04.gif"/>
                                    <span style="line-height:12px; text-align:center;">
                                        <a href="javascript:void(0)" class="edit" onclick="confirm('是否删除该项数据？', <s:property value="id"/>)">删除</a>
                                    </span>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                    <%@ include file="/WEB-INF/jsp/tools/paging.jsp" %>
                </s:else>
            </div>
        </s:form>
    </div>
    <div class="content-bbg"></div>
</div>
