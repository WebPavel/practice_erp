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
    function confirm(tip, id) {
        top.lock.show();
        top.$('context').style.display = 'block';
        top.$('context-text').innerHTML = tip;
        top.$('hide-action').value = 'order_delete.action?order.id=' + id;
    }
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">采购订单管理</span>
        </div>
    </div>
    <div class="content-text">
        <s:form action="order_buyList.action" method="post">
            <div class="square-order-top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
                    <tr>
                        <td>订单状态：</td>
                        <td>
                            <s:select name="orderQueryModel.status" list="@cn.com.zv2.invoice.order.entity.Order@buyStatusMap" headerKey="-1" headerValue="----请选择----" cssClass="width"/>
                        </td>
                        <td>申请人：</td>
                        <td>
                            <s:textfield name="applicantName" size="14"/>
                        </td>
                        <td>总量：</td>
                        <td>
                            <s:textfield name="orderQueryModel.amount" size="14"/>
                        </td>
                        <td>到：</td>
                        <td>
                            <s:textfield name="orderQueryModel.toAmount" size="14"/>
                        </td>
                        <td width="70">
                            <a id="query"><img src="${pageContext.request.contextPath}/image/can_b_01.gif" border="0"/></a>
                        </td>
                    </tr>
                    <tr>
                        <td>申请时间：</td>
                        <td>
                            <input name="orderQueryModel.gmtCreateView" id="d4311" class="Wdate" type="text" size="14" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" value="${orderQueryModel.gmtCreateView}" readonly="readonly"/>
                        </td>
                        <td>到</td>
                        <td>
                            <input name="orderQueryModel.toGmtCreateView" id="d4312" class="Wdate" type="text" size="14" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" value="${orderQueryModel.toGmtCreateView}" readonly="readonly"/>
                        </td>
                        <td>总计：</td>
                        <td>
                            <s:textfield name="orderQueryModel.total" size="14"/>
                        </td>
                        <td>到：</td>
                        <td>
                            <s:textfield name="orderQueryModel.toTotal" size="14"/>
                        </td>
                        <td width="70">
                            <a href="order_buyEdit.action"><img src="${pageContext.request.contextPath}/image/can_b_02.gif" border="0"/></a>
                        </td>
                    </tr>
                </table>
            </div>
            <!--"square-order-top"end-->
            <div class="square-order">
                <s:if test="#orderList.size() == 0">
                    <center>
                        <span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
                    </center>
                </s:if>
                <s:else>
                    <table width="100%" border="1" cellpadding="0" cellspacing="0">
                        <tr align="center" style="background:url(${pageContext.request.contextPath}/image/table_bg.gif) repeat-x;">
                            <td width="15%">订单号</td>
                            <td width="19%">供应商</td>
                            <td width="10%">申请人</td>
                            <td width="20%">申请时间</td>
                            <td width="10%">订单商品总量</td>
                            <td width="12%">订单总计</td>
                            <td width="5%">详情</td>
                            <td width="9%">订单状态</td>
                        </tr>
                        <s:iterator value="orderList">
                            <tr align="center" bgcolor="#FFFFFF">
                                <td width="13%" height="30"><s:property value="sn"/></td>
                                <td><s:property value="supplier.name"/></td>
                                <td><s:property value="applicant.name"/></td>
                                <td><s:property value="gmtCreateView"/></td>
                                <td><s:property value="amount"/></td>
                                <td><s:property value="totalView"/></td>
                                <td>
                                    <s:a action="order_buyDetail" cssClass="edit">
                                        <s:param name="order.id" value="id"/>
                                        详情
                                    </s:a>
                                </td>
                                <td><s:property value="statusView"/></td>
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
