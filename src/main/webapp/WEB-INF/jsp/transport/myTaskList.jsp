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
			<span class="page_title">任务查询</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="transport_myTaskList" method="post">
			<div class="square-order-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
					<tr>
						<td width="20%">供应商:</td>
						<td width="20%"><s:select name="supplierId" list="supplierList" listKey="id" listValue="name" headerKey="-1" headerValue="----请选择----" cssClass="width"></s:select></td>
						<td>发货方式:</td>
						<td>
							<s:select name="supplierDelivered" list="@cn.com.zv2.invoice.supplier.entity.Supplier@deliveredMap" headerKey="-1" headerValue="---请-选-择---" cssStyle="width:115px"></s:select>
						</td>
						<td width="10%">
							<a id="query"><img src="/image/can_b_01.gif" border="0" /></a>
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
						<tr align="center" style="background:url(/image/table_bg.gif) repeat-x;">
							<td width="8%" height="30">订单类别</td>
							<td width="21%">供应商</td>
							<td width="7%">发货方式</td>
							<td width="6%">联系人</td>
							<td width="12%">联系方式</td>
							<td width="30%">地址</td>
							<td width="8%">状态</td>
							<td width="6%">详情</td>
						</tr>
						<s:iterator value="orderList">
							<tr align="center" bgcolor="#FFFFFF">
								<td height="30">${typeView }</td>
								<td>${supplier.name }</td>
								<td>${supplier.deliveredView }</td>
								<td>${supplier.contact }</td>
								<td>${supplier.telephone }</td>
								<td align="left">&nbsp;${supplier.address }</td>
								<td>${statusView }</td>
								<td>
									<img src="/image/icon_03.gif" />
									<s:a action="transport_myTaskDetail">
										<s:param name="order.id" value="id"/>
										详情
									</s:a>
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
