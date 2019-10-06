<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=resourceIds]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=resourceIds]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });
		});
	});
</script>
<div class="content-right">
	<div class="content-right-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<s:form action="role_updateIfPresent" method="post">
				<s:hidden name="role.id"/>
				<div style="border:1px solid #cecece;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr bgcolor="#FFFFFF">
						<td>&nbsp;</td>
					  </tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr  bgcolor="#FFFFFF">
						  <td width="18%" height="30" align="center">角色名称</td>
						  <td width="32%">
							<s:textfield name="role.name" size="25"/>
						  </td>
						  <td width="18%" align="center">角色编码</td>
						  <td width="32%">
							  <s:textfield name="role.code" size="25"/>
						  </td>
						</tr>
						<tr bgcolor="#FFFFFF">
						  <td colspan="4">&nbsp;</td>
						</tr>
						<tr bgcolor="#FFFFFF">
						  <td width="18%" height="30" align="center">资源名称</td>
						  <td width="82%" colspan="3">
							<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="reverse">反选
						  </td>
						</tr>
						<tr bgcolor="#FFFFFF">
						  <td width="18%" height="30" align="center">&nbsp;</td>
						  <td width="82%" colspan="3">
							<s:checkboxlist name="resourceIds" list="resourceList" listKey="id" listValue="name"></s:checkboxlist>
						  </td>
						</tr>
						 <tr bgcolor="#FFFFFF">
						  <td width="18%" height="30" align="center">菜单名称</td>
						  <td width="82%" colspan="3">
							<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="reverse">反选
						  </td>
						</tr>
						<tr bgcolor="#FFFFFF">
						  <td width="18%" height="30" align="center">&nbsp;</td>
						  <td width="82%" colspan="3">
							<input type="checkbox"/>基础维护
							<input type="checkbox"/>部门维护
							<input type="checkbox"/>员工维护
							<input type="checkbox"/>.....
						  </td>
						</tr>
						<tr bgcolor="#FFFFFF">
						  <td colspan="4">&nbsp;</td>
						</tr>
					</table>
				</div>
				<div class="order-bottom">
					<div style="margin:1px auto auto 1px;">
						<table width="100%"  border="0" cellpadding="0" cellspacing="0">
						  <tr>
							<td>
								<a href="javascript:document.forms[0].submit()"><img src="/image/order_tuo.gif" border="0" /></a>
							</td>
							<td>&nbsp;</td>
							<td><a href="#"><img src="/image/order_tuo.gif" border="0" /></a></td>
							<td>&nbsp;</td>
							<td><a href="#"><img src="/image/order_tuo.gif" border="0" /></a></td>
						  </tr>
						</table>
					</div>
				</div>
			</s:form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="/image/content_bbg.jpg" /></div>
</div>
