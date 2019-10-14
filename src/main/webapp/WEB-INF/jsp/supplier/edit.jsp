<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="/css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(function () {
        $("#commit").click(function () {
            $("form:first").submit();
        });
    });
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">供应商管理</span>
        </div>
    </div>
    <div class="content-text">
        <div class="square-order">
            <s:form action="supplier_updateIfPresent" method="post">
                <s:hidden name="supplier.id"/>
                <div style="border:1px solid #cecece;">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr bgcolor="#FFFFFF">
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">供应商:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="supplier.name" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">地址:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="supplier.address" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">联系电话:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="supplier.telephone" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">联系人:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="supplier.contact" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">送货方式:</td>
                            <td width="82%" colspan="3">
                                <s:select name="supplier.delivered" list="@cn.com.zv2.invoice.supplier.entity.Supplier@deliveredMap" cssStyle="width: 190px"></s:select>
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
                                <td><a href="#"><img src="/image/order_tuo.gif" border="0"/></a></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </s:form>
        </div><!--"square-order"end-->
    </div><!--"content-text"end-->
    <div class="content-bbg"><img src="/image/content_bbg.jpg"/></div>
</div>
