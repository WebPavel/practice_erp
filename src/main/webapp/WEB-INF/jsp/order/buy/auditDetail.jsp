<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<div class="content-right">
	<div class="content-right-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货审核</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-order-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
					<tr>
						<td height="30">供应商:</td>
						<td class="order_show_msg"><s:property value="order.supplier.name"/></td>
						<td>申请时间:</td>
						<td class="order_show_msg"><s:property value="order.gmtCreateView"/></td>
						<td>申请人:</td>
						<td class="order_show_msg"><s:property value="order.applicant.name"/></td>
						<td>订单号:</td>
						<td class="order_show_msg" colspan="2"><s:property value="order.sn"/></td>
					</tr>
					<tr>
						<td height="30">订单类别:</td>
						<td class="order_show_msg"><s:property value="order.typeView"/></td>
						<td>订单状态:</td>
						<td class="order_show_msg"><s:property value="order.statusView"/></td>
						<td>商品总量:</td>
						<td class="order_show_msg"><s:property value="order.amount"/></td>
						<td>订单总计:</td>
						<td class="order_show_msg"><s:property value="order.totalView"/> 元</td>
					</tr>
				</table>
			</div>
			<!--"square-order-top"end-->
			<div class="square-order">
				<center style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:'黑体';">&nbsp;&nbsp;&nbsp;&nbsp;订&nbsp;&nbsp;单&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center" style="background:url(/image/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品类别</td>
						<td width="20%">商品名称</td>
						<td width="20%">购买数量</td>
						<td width="20%">进价</td>
						<td width="20%">小计</td>
					</tr>
					<s:iterator value="order.orderDetails">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30"><s:property value="product.category.name"/></td>
							<td><s:property value="product.name"/></td>
							<td><s:property value="quantity"/></td>
							<td align="right"><s:property value="priceView"/> 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td align="right"><s:property value="subtotalView"/> 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</tr>
					</s:iterator>
					<tr align="right">
						<td height="30" width="80%" colspan="4">总计&nbsp;&nbsp;</td>
						<td width="20%"><s:property value="order.totalView"/> 元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
				</table>
				<br/>
				<table width="100%">
					<tr align="center">
						<td width="50%">
							<s:a action="order_buyAuditApprove" cssStyle="color:#0f0;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(/image/btn_bg.jpg)">
								<s:param name="order.id" value="order.id"/>
								通&nbsp;&nbsp;过
							</s:a>
						</td>
						<td width="50%">
							<s:a action="order_buyAuditReject" cssStyle="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(/image/btn_bg.jpg)">
								<s:param name="order.id" value="order.id"/>
								驳&nbsp;&nbsp;回
							</s:a>
						</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
