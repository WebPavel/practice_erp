<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
<title>进销存系统-主页</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
</head>
<body>
	<div class="container">
		<div class="head">
			<div class="head-left">
				<span style="font-weight:bold; color:#1f4906">欢迎您：</span><br />
				<span style="color:#4a940d"><s:property value="#session.loginEmployee.name" /></span>
			</div>
			<div class="head-right">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="32%">
							<a href="employee_doChangePwd.action" target="main">
								<img src="${pageContext.request.contextPath}/image/head-l.gif"	border="0" />
							</a>
						</td>
						<td width="26%">
							<a href="employee_logout.action">
								<img src="${pageContext.request.contextPath}/image/head-m.gif"	border="0" />
							</a>
						</td>
						<td width="7%">&nbsp;</td>
						<td width="35%">
							<a href="#"><img src="${pageContext.request.contextPath}/image/head-r.gif" border="0" /></a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--"head"end-->

		<div class="content">
			<div class="left">
				<div style="margin-left:2px;">
					<img src="${pageContext.request.contextPath}/image/left-top.gif" width="162" height="25" />
				</div>
				<div class="left-bottom">
					<%@ include file="/WEB-INF/jsp/tools/menu.jsp"%>
				</div>
				<!--"left-bottom"end-->
			</div>
			<!--"left"end-->

			<iframe id="frame-content" src="${pageContext.request.contextPath}/content.action"
				style="width: 848px;float: right;height: 530px" scrolling="no"
				name="main" frameborder="0">
			</iframe>
			<!--"content-right"end-->
		</div>
		<!--"content"end-->
		<div class="footer">
			<div style="margin-top:5px;">
				<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td width="82%"><img src="${pageContext.request.contextPath}/image/icon_01.gif" />&nbsp;
							<a class="company" href="zv2.com.cn">随风而逝信息技术有限公司&copy;2019</a>
						</td>
						<td width="18%" valign="middle"><img src="${pageContext.request.contextPath}/image/icon_02.gif" />&nbsp;
							<a class="company" href="#">如有疑问请与技术人员联系</a>
						</td>
					</tr>
				</table>
			</div>

		</div>
		<!--"footer"end-->
	</div>
	<!--"container"end-->
	<%@ include file="/WEB-INF/jsp/tools/mask.jsp"%>
</body>
</html>
