<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<div class="content-right">
	<div class="content-right-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">修改密码</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<s:form action="employee_changePwd.action" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr bgcolor="#FFFFFF">
				      <td align="center">原始密码</td>
				      <td colspan="3">
						  <s:password name="employee.password" size="25"/>
						  <s:actionerror/>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
					<tr bgcolor="#FFFFFF">
				      <td align="center">新密码</td>
				       <td colspan="3">
				      	<s:password name="newPassword" size="25"/>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  	<td colspan="4">&nbsp;</td>
					</tr>
					<tr bgcolor="#FFFFFF">
				      	<td align="center">确认密码</td>
						<td colspan="3">
				      		<input type="password" size="25"/>
				      	</td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  	<td colspan="4">&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="order-bottom">
				<div style="margin:1px auto auto 1px;">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
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
