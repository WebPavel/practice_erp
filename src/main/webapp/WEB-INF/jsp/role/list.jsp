<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(function() {
        $("#query").click(function() {
            $("[name=pageNum]").val(1);
            $("form:first").submit();
        });
    });
    function confirm(tip, id) {
        top.lock.show();
        top.$('context').style.display = 'block';
        top.$('context-text').innerHTML = tip;
        top.$('hide-action').value = 'role_delete.action?role.id='+id;
    }
</script>
<div class="content-right">
	<div class="content-right-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="role_list" method="post">
			<div class="square-order-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
					<tr bgcolor="#FFFFFF">
						<td width="12%" height="30">角色名称</td>
						<td width="28%"><s:textfield name="roleQueryModel.name" size="25" /></td>
						<td width="12%">角色编码</td>
						<td width="28"><s:textfield name="roleQueryModel.code" size="25" /></td>
						<td width="10%">
							<a id="query"><img src="/image/can_b_01.gif" border="0" /></a>
						</td>
						<td width="10%">
							<a href="role_edit.action"><img src="/image/can_b_02.gif" border="0" /></a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-order-top"end-->
			<div class="square-order">
				<s:if test="#roleList.size() == 0">
					<center>
						<span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
					</center>
				</s:if>
				<s:else>
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr align="center" style="background:url(/image/table_bg.gif) repeat-x;">
							<td width="40%" height="30">角色名称</td>
							<td width="40%">角色编码</td>
							<td width="20%">操作</td>
						</tr>
						<s:iterator value="roleList">
							<tr align="center" bgcolor="#FFFFFF">
								<td height="30"><s:property value="name" /></td>
								<td><s:property value="code" /></td>
								<td>
									<img src="/image/icon_03.gif" />
									<span style="line-height:12px; text-align:center;">
									<s:a action="role_edit.action" cssClass="edit">
										<s:param name="role.id" value="id"/>
										修改
									</s:a>
								</span>
									<img src="/image/icon_04.gif" />
									<span style="line-height:12px; text-align:center;">
									<a href="javascript:void(0)" class="edit" onclick="confirm('是否删除该项数据?', <s:property value="id" />)">删除</a>
								</span>
								</td>
							</tr>
						</s:iterator>
						<%@ include file="/WEB-INF/jsp/tools/paging.jsp"%>
					</table>
					</s:else>
			</div>
		</s:form>
	</div>
	<div class="content-bbg"></div>
</div>
