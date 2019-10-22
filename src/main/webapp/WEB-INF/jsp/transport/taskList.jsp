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
			<span class="page_title">商品运输管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="transport_taskList" method="post">
			<div class="square-order-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
					<tr>
						<td>申请时间：</td>
						<td>
							<input name="orderQueryModel.gmtCreateView" id="d4311" class="Wdate" type="text" size="14" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" value="${orderQueryModel.gmtCreateView}" readonly="readonly"/>
						</td>
						<td>到</td>
						<td>
							<input name="orderQueryModel.toGmtCreateView" id="d4312" class="Wdate" type="text" size="14" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" value="${orderQueryModel.toGmtCreateView}" readonly="readonly"/>
						</td>
						<td width="20%">供应商:</td>
						<td width="20%"><s:select name="supplierId" list="supplierList" listKey="id" listValue="name" headerKey="-1" headerValue="----请选择----" cssClass="width"></s:select></td>

						<td>申请人：</td>
						<td>
							<s:textfield name="applicantName" size="14"/>
						</td>
						<td>&nbsp;</td>
						<td>
							<a id="query"><img src="/image/can_b_01.gif" border="0" /></a>
						</td>
					</tr>
					<tr>
						<td>审核时间:</td>
						<td>
							<input name="orderQueryModel.gmtAuditView" id="d4321" class="Wdate" type="text" size="14" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\')||\'2020-10-01\'}'})" value="${orderQueryModel.gmtAuditView}" readonly="readonly"/>
						</td>
						<td>到</td>
						<td>
							<input name="orderQueryModel.toGmtAuditView" id="d4322" class="Wdate" type="text" size="14" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\')}',maxDate:'2020-10-01'})" value="${orderQueryModel.toGmtAuditView}" readonly="readonly"/>
						</td>
						<td>发货方式:</td>
						<td>
							<s:select name="supplierDelivered" list="@cn.com.zv2.invoice.supplier.entity.Supplier@deliveredMap" headerKey="-1" headerValue="---请-选-择---" cssStyle="width:115px"></s:select>
						</td>
						<td>审核人:</td>
						<td><s:textfield name="auditorName" size="10"/></td>
						<td>跟单人:</td>
						<td><s:textfield name="merchandiserName" size="10"/></td>
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
							<td width="10%" height="30">订单类别</td>
							<td width="13%">申请时间</td>
							<td width="8%">申请人</td>
							<td width="13%">审核时间</td>
							<td width="8%">审核人</td>
							<td width="25%">供应商</td>
							<td width="13%">发货方式</td>
							<td width="10%">跟单人</td>
						</tr>
						<s:iterator value="orderList">
							<tr align="center" bgcolor="#FFFFFF">
								<td height="30">${typeView}</td>
								<td>${gmtCreateView }</td>
								<td>${applicant.name }</td>
								<td>${gmtAuditView }</td>
								<td>${auditor.name }</td>
								<td>${supplier.name }</td>
								<td>${supplier.deliveredView }</td>
								<td>
								<s:if test="status == @cn.com.zv2.invoice.order.entity.Order@ORDER_STATUS_OF_BUY_APPROVE">
									<img src="/image/icon_03.gif" />
									<span style="line-height:12px; text-align:center;">
										<s:a action="transport_taskDetail" cssClass="edit">
											<s:param name="order.id" value="id" />
											任务指派
										</s:a>
									</span>
								</s:if>
								<s:else>
									${merchandiser.name}
								</s:else>
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
