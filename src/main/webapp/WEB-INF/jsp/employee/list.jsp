<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
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
        top.$('hide-action').value = 'employee_delete.action?employee.id='+id;
    }
</script>
<div class="content-right">
	<div class="content-right-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="employee_list.action" method="post">
			<div class="square-order-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
					<tr>
						<td height="30">用户名</td>
						<td><s:textfield name="employeeQueryModel.username" size="14"/></td>
						<td>真实姓名</td>
						<td><s:textfield name="employeeQueryModel.name" size="14"/></td>
						<td>电话</td>
						<td><s:textfield name="employeeQueryModel.telephone" size="14"/></td>
						<td>性别</td>
						<td>
							<s:select name="employeeQueryModel.gender" list="@cn.com.zv2.auth.employee.entity.Employee@genderMap" headerKey="-1" headerValue="----请选择----" cssClass="width"></s:select>
						</td>
							
						<td width="70"><a href="employee_edit.action"><img src="/image/can_b_02.gif" border="0" /></a></td>
					</tr>
					<tr>
						<td  height="30">电子邮件</td>
						<td><s:textfield name="employeeQueryModel.email" size="14"/></td>
						<td>出生日期</td>
						<td>
							<input name="employeeQueryModel.birthdayView" id="d4311" class="Wdate" type="text" size="14" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" value="${employeeQueryModel.birthdayView}" readonly="readonly"/>
						</td>
						<td>出生日期</td>
						<td>
							<input name="employeeQueryModel.birthday2View" id="d4312" class="Wdate" type="text" size="14" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" value="${employeeQueryModel.birthday2View}" readonly="readonly"/>
						</td>
						<td>部门名称</td>
						<td>
							<s:select name="employeeQueryModel.departmentId" list="departmentList" listKey="id" listValue="name" headerKey="-1" headerValue="----请选择----" cssClass="width"></s:select>
							<!-- employeeQueryModel对象的department属性的id属性 -->
						</td>
						<td><a id="query"><img src="${pageContext.request.contextPath}/image/can_b_01.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-order-top"end-->
			<div class="square-order">
                <s:if test="#employeeList.size() == 0">
                    <center>
                        <span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
                    </center>
                </s:if>
                <s:else>
                    <table width="100%" border="1" cellpadding="0" cellspacing="0">
                        <tr align="center" style="background:url(/image/table_bg.gif) repeat-x;">
                            <td width="10%" height="30">用户名</td>
                            <td width="10%">真实姓名</td>
                            <td width="5%">性别</td>
                            <td width="12%">出生日期</td>
                            <td width="10%">电话</td>
                            <td width="12%">电子邮件</td>
                            <td width="9%">所属部门</td>
                            <td width="16%">最后登录时间</td>
                            <td width="16%">操作</td>
                        </tr>
                        <s:iterator value="employeeList">
                            <tr align="center" bgcolor="#FFFFFF">
                                <td width="13%" height="30"><s:property value="username"/></td>
                                <td><s:property value="name"/></td>
                                <td><s:property value="genderView"/></td>
                                <td><s:property value="birthdayView"/></td>
                                <td><s:property value="telephone"/></td>
                                <td><s:property value="email"/></td>
                                <td><s:property value="department.name"/></td>
                                <td><s:property value="gmtLastLoginView"/></td>
                                <td>
                                    <img src="/image/icon_03.gif" />
                                    <span style="line-height:12px; text-align:center;">
                                        <s:a action="employee_edit" cssClass="edit">
                                            <s:param name="employee.id" value="id"/>
                                            修改
                                        </s:a>
                                    </span>
                                    <img src="/image/icon_04.gif" />
                                    <span style="line-height:12px; text-align:center;">
                                        <a href="javascript:void(0)" class="edit" onclick="confirm('是否删除该项数据？', <s:property value="id"/>)">删除</a>
                                    </span>
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
