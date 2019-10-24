<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    $(function () {
        $("#query").click(function () {
            $("[name=pageNum]").val(1);
            $("form:first").submit();
        });
    });
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">仓库操作明细</span>
        </div>
    </div>
    <div class="content-text">
        <s:form action="operationDetail_list.action" method="post">
            <div class="square-order-top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
                    <tr>
                        <td height="30">仓库名称:</td>
                        <td><s:textfield name="warehouseName" size="18"/></td>
                        <td>操作类型:</td>
                        <td>
                            <s:select name="operationDetailQueryModel.type" list="@cn.com.zv2.invoice.operationdetail.entity.OperationDetail@typeMap" headerKey="-1" headerValue="---请选择---"></s:select>
                        </td>
                        <td>操作时间:</td>
                        <td>
                            <input name="operationDetailQueryModel.gmtOperateView" id="d4311" class="Wdate" type="text" size="14" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" value="${operationDetailQueryModel.gmtOperateView}" readonly="readonly"/>
                        </td>
                        <td>到</td>
                        <td>
                            <input name="operationDetailQueryModel.toGmtOperateView" id="d4312" class="Wdate" type="text" size="14" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" value="${operationDetailQueryModel.toGmtOperateView}" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td height="30">操作人:</td>
                        <td><s:textfield name="operatorName" size="18"/></td>
                        <td>商品名称:</td>
                        <td><s:textfield name="productName" size="18"/></td>
                        <td>数量:</td>
                        <td><s:textfield name="operationDetailQueryModel.quantity" size="18"/></td>
                        <td>到:</td>
                        <td><s:textfield name="operationDetailQueryModel.toQuantity" size="18"/></td>
                        <td width="70">
                            <a id="query"><img src="${pageContext.request.contextPath}/image/can_b_01.gif" border="0"/></a>
                        </td>
                    </tr>
                </table>
            </div>
            <!--"square-order-top"end-->
            <div class="square-order">
                <s:if test="#operationDetailList.size() == 0">
                    <center>
                        <span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
                    </center>
                </s:if>
                <s:else>
                    <table width="100%" border="1" cellpadding="0" cellspacing="0">
                        <tr align="center" style="background:url(${pageContext.request.contextPath}/image/table_bg.gif) repeat-x;">
                            <td width="16%">仓库名称</td>
                            <td width="16%">操作类型</td>
                            <td width="16%">操作时间</td>
                            <td width="16%">操作人</td>
                            <td width="20%">商品名称</td>
                            <td width="16%">数量</td>
                        </tr>
                        <s:iterator value="operationDetailList">
                            <tr align="center" bgcolor="#FFFFFF">
                                <td><s:property value="warehouse.name"/></td>
                                <td><s:property value="typeView"/></td>
                                <td><s:property value="gmtOperateView"/></td>
                                <td><s:property value="operator.name"/></td>
                                <td><s:property value="product.name"/></td>
                                <td><s:property value="quantity"/> <s:property value="product.unit"/></td>
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
