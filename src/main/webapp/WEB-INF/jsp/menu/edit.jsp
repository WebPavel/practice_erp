<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="/css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(function () {
        $("#commit").click(function () {
            $("form:first").submit();
        });
        $("#all").click(function() {
            $("[name=roleIds]").prop("checked", $(this).prop("checked"));
        });
        $("#reverse").click(function() {
            $("[name=roleIds]").each(function() {
                $(this).prop("checked", !$(this).prop("checked"));
            });
            checkSelect();
        });
        $("[name=roleIds]").click(function() {
            checkSelect();
        });
        function checkSelect() {
            var checked = true;
            $("[name=roleIds]").each(function() {
                checked = checked && $(this).prop("checked");
            });
            $("#all").prop("checked", checked);
        }
    });
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">菜单管理</span>
        </div>
    </div>
    <div class="content-text">
        <div class="square-order">
            <s:form action="menu_updateIfPresent" method="post">
                <s:hidden name="menu.id"/>
                <div style="border:1px solid #cecece;">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr bgcolor="#FFFFFF">
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">菜单名称:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="menu.name" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">URL:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="menu.url" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">所属菜单:</td>
                            <td width="82%" colspan="3">
                                <s:select name="parentId" list="parentList" listKey="id" listValue="name" cssStyle="width: 190px"></s:select>
                            </td>
                        </tr>
                        <tr  bgcolor="#FFFFFF">
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        <tr  bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">角色名称</td>
                            <td width="82%" colspan="3">
                                <input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="checkbox" id="reverse">反选
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">&nbsp;</td>
                            <td width="82%" colspan="3">
                                <s:checkboxlist name="roleIds" list="roleList" listKey="id" listValue="name"></s:checkboxlist>
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
                                    <a href="javascript:void(0)" id="commit"><img src="/image/order_tuo.gif" border="0"/></a>
                                </td>
                                <td>&nbsp;</td>
                                <td><a href="#"><img src="/image/order_tuo.gif" border="0"/></a></td>
                                <td>&nbsp;</td>
                                <td><a href="javascript:history.back()"><img src="/image/order_tuo.gif" border="0"/></a></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </s:form>
        </div><!--"square-order"end-->
    </div><!--"content-text"end-->
    <div class="content-bbg"><img src="/image/content_bbg.jpg"/></div>
</div>
