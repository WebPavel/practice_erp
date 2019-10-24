<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
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
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="order_warehousingList" method="post">
			<div class="square-order-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
					<tr>
						<td>订单号：</td>
						<td>
							<s:textfield name="orderQueryModel.sn" size="14"/>
						</td>
						<td>跟单人：</td>
						<td>
							<s:textfield name="merchandiserName" size="14"/>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;&nbsp;
							<a id="query">
								<img src="/image/can_b_01.gif" border="0" />
							</a>
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
							<td width="40%" height="30">订单号</td>
							<td width="20%">跟单人</td>
							<td width="20%">商品种类</td>
							<td width="20%">入库</td>
						</tr>
						<s:iterator value="orderList">
							<tr align="center" bgcolor="#FFFFFF">
								<td width="13%" height="30"><s:property value="sn"/></td>
								<td><s:property value="merchandiser.name"/></td>
								<td><s:property value="orderDetails.size()"/></td>
								<td>
									<s:if test="status == @cn.com.zv2.invoice.order.entity.Order@ORDER_STATUS_OF_BUY_WAREHOUSING">
										<img src="/image/icon_03.gif">
										<s:a action="order_warehousingDetail" cssClass="edit">
											<s:param name="order.id" value="id"/>
											入库
										</s:a>
									</s:if>
									<s:else>
										<span style="color: blue"><s:property value="statusView"/></span>
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
