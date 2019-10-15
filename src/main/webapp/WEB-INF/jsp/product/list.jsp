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
        // 快捷查询
        $(".unit").click(function () {
            var unit = $(this).html();
            $("[name='productQueryModel.unit']").val(unit);
            $("[name=pageNum]").val(1);
            $("form:first").submit();
        });
    });
    function confirm(tip, id) {
        top.lock.show();
        top.$('context').style.display = 'block';
        top.$('context-text').innerHTML = tip;
        top.$('hide-action').value = 'product_delete.action?product.id=' + id;
    }
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">商品管理</span>
        </div>
    </div>
    <div class="content-text">
        <s:form action="product_list.action" method="post">
            <div class="square-order-top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
                    <tr>
                        <td width="25%">商品名称:</td>
                        <td width="25%"><s:textfield name="productQueryModel.name" size="18"/></td>
                        <td width="20%">产地:</td>
                        <td width="30%"><s:textfield name="productQueryModel.origin" size="18"/></td>
                    </tr>
                    <tr>
                        <td width="25%">生产厂家:</td>
                        <td width="35%"><s:textfield name="productQueryModel.producer" size="18"/></td>
                        <td width="20%">单位:</td>
                        <td width="30%"><s:textfield name="productQueryModel.unit" size="18"/></td>
                    </tr>
                    <tr>
                        <td width="16%">进价从:</td>
                        <td width="16%"><s:textfield name="productQueryModel.bid" size="18"/></td>
                        <td width="2%">到:</td>
                        <td width="16%"><s:textfield name="productQueryModel.toBid" size="18"/></td>
                        <td width="16%">售价从:</td>
                        <td width="16%"><s:textfield name="productQueryModel.price" size="18"/></td>
                        <td width="2%">到:</td>
                        <td width="16%"><s:textfield name="productQueryModel.toPrice" size="18"/></td>
                    </tr>
                    <tr>
                        <td width="30%">供应商:</td>
                        <td width="30%">
                            <s:select name="supplierId" list="supplierList" listKey="id" listValue="name" headerKey="-1" headerValue="----请选择----" cssClass="width"></s:select>
                        </td>
                        <td width="70">
                            <a id="query"><img src="${pageContext.request.contextPath}/image/can_b_01.gif" border="0"/></a>
                        </td>
                        <td width="70">
                            <a href="product_edit.action"><img src="${pageContext.request.contextPath}/image/can_b_02.gif" border="0"/></a>
                        </td>
                    </tr>
                </table>
            </div>
            <!--"square-order-top"end-->
            <div class="square-order">
                <s:if test="#productList.size() == 0">
                    <center>
                        <span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
                    </center>
                </s:if>
                <s:else>
                    <table width="100%" border="1" cellpadding="0" cellspacing="0">
                        <tr align="center" style="background:url(${pageContext.request.contextPath}/image/table_bg.gif) repeat-x;">
                            <td width="12%">商品名称</td>
                            <td width="12%">产地</td>
                            <td width="12%">生产厂家</td>
                            <td width="6%">单位</td>
                            <td width="12%">进价</td>
                            <td width="12%">售价</td>
                            <td width="18%">供应商</td>
                            <td width="16%">操作</td>
                        </tr>
                        <s:iterator value="productList">
                            <tr align="center" bgcolor="#FFFFFF">
                                <td><s:property value="name"/></td>
                                <td><s:property value="origin"/></td>
                                <td><s:property value="producer"/></td>
                                <td><a href="javascript:void(0)" class="unit"><s:property value="unit"/></a></td>
                                <td><s:property value="bidView"/>&nbsp;元&nbsp;</td>
                                <td><s:property value="priceView"/>&nbsp;元&nbsp;</td>
                                <td><s:property value="category.supplier.name"/></td>
                                <td>
                                    <img src="${pageContext.request.contextPath}/image/icon_03.gif"/>
                                    <span style="line-height:12px; text-align:center;">
                                        <s:a action="product_edit" cssClass="edit">
                                            <s:param name="product.id" value="id"/>
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
