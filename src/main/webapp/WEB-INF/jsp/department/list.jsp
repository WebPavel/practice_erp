<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
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
        top.$('hide-action').value = 'department_delete.action?department.id='+id;
    }
</script>
<div class="content-right">
	<div class="content-right-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">部门管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="department_list.action" method="post">
			<div class="square-order-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:'黑体';">
					<tr>
						<td width="68" height="30">&nbsp;&nbsp;&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">部门名称:</td>
						<td width="142"><s:textfield name="departmentQueryModel.name" size="18"/></td>
						<td width="60">电话:</td>
						<td width="149"><s:textfield name="departmentQueryModel.telephone" size="18"/></td>
						<td width="70">
							<a id="query"><img src="${pageContext.request.contextPath}/image/can_b_01.gif" border="0" /></a>
						</td>
						<td width="70">
							<a href="department_edit.action"><img src="${pageContext.request.contextPath}/image/can_b_02.gif" border="0" /></a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-order-top"end-->
			<div class="square-order">
				<s:if test="#departmentList.size() == 0">
					<center>
						<span style="font-size:20px;color:#96D34A;font-weight:bold">没有查找到满足条件的数据！</span>
					</center>
				</s:if>
				<s:else>
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr align="center" style="background:url(${pageContext.request.contextPath}/image/table_bg.gif) repeat-x;">
							<td width="13%" height="30">编号</td>
							<td width="13%">部门名称</td>
							<td width="16%">电话</td>
							<td width="16%">操作</td>
						</tr>
						<s:iterator value="departmentList">
							<tr align="center" bgcolor="#FFFFFF">
								<td width="13%" height="30"><s:property value="id"/></td>
								<td><s:property value="name"/></td>
								<td><s:property value="telephone"/></td>
								<td>
									<img src="${pageContext.request.contextPath}/image/icon_03.gif" />
									<span style="line-height:12px; text-align:center;">
										<s:a action="department_edit" cssClass="edit">
											<s:param name="department.id" value="id"/>
											修改
										</s:a>
									</span>
									<img src="${pageContext.request.contextPath}/image/icon_04.gif" />
									<span style="line-height:12px; text-align:center;">
										<a href="javascript:void(0)" class="edit" onclick="confirm('是否删除当前部门？', <s:property value="id"/>)">删除</a>
									</span>
								</td>
							</tr>
						</s:iterator>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="48%">每页<s:textfield name="pageSize" size="5"/>条记录</td>
							<td width="18%">共${totalRow}条记录
							<td width="6%">
								<a id="first" class="sye">首&nbsp;&nbsp;页</a>
							</td>
							<td width="6%">
								<a id="previous" class="sye">上一页</a>
							</td>
							<td width="6%">
								<a id="next" class="sye">下一页</a>
							</td>
							<td width="6%">
								<a id="last" class="sye">末&nbsp;&nbsp;页</a>
							</td>
							<td width="12%">当前第<span style="color:red;">${pageNum}</span>/${totalPage}页</td>
						</tr>
					</table>
					<s:hidden name="pageNum"/>
					<script type="text/javascript">
						var totalPage = ${totalPage};
						var pageNum = ${pageNum};
						if (totalPage == 1) {
                            $("#first").css("display", "none");
                            $("#previous").css("display", "none");
                            $("#next").css("display", "none");
                            $("#last").css("display", "none");
						} else if (pageNum == 1) {
                            $("#first").css("display", "none");
                            $("#previous").css("display", "none");
                            $("#next").css("display", "inline");
                            $("#last").css("display", "inline");
						} else if (pageNum == totalPage) {
                            $("#first").css("display", "inline");
                            $("#previous").css("display", "inline");
                            $("#next").css("display", "none");
                            $("#last").css("display", "none");
                        } else {
                            $("#first").css("display", "inline");
                            $("#previous").css("display", "inline");
                            $("#next").css("display", "inline");
                            $("#last").css("display", "inline");
						}


						$("#first").click(function () {
                            $("[name=pageNum]").val(1);
							$("form:first").submit();
                        });
                        $("#previous").click(function () {
                            $("[name=pageNum]").val($("[name=pageNum]").val()-1);
                            $("form:first").submit();
                        });
                        $("#next").click(function () {
                            $("[name=pageNum]").val($("[name=pageNum]").val()*1+1);
                            $("form:first").submit();
                        });
                        $("#last").click(function () {
                            $("[name=pageNum]").val(totalPage);
                            $("form:first").submit();
                        });
                        $("[name=pageSize]").change(function () {
                            $("form:first").submit();
                        });
					</script>
				</s:else>
			</div>
		</s:form>
	</div>
	<div class="content-bbg"></div>
</div>
