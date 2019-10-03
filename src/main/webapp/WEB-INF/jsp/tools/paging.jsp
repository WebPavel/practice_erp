<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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