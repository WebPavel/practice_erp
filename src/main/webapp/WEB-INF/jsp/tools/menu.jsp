<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/treeview/jquery.treeview.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/treeview/screen.css" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/treeview/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/treeview/jquery.treeview.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/treeview/jquery.treeview.edit.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/treeview/jquery.treeview.async.js" type="text/javascript"></script>
<ul id="black" class="filetree"></ul>
<script type="text/javascript">
    $("#black").treeview({
        collapsed: true,
        url: "${pageContext.request.contextPath}/menu_showMenu.action"
    })
</script>
