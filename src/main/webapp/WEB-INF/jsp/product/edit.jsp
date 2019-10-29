<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="/css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(function () {
        $("#commit").click(function () {
            $("form:first").submit();
        });
        $("#supplierListId").change(function () {
            var supplierId = $(this).val();
            $.post("category_ajaxListBySupplier.action",{"supplierId":supplierId},function(data) {
                    var categoryList = data.categoryList;
                    $("#categoryListId").empty();
                    for (var i=0;i<categoryList.length;i++) {
                        var category = categoryList[i];
                        $option = $("<option value='"+category.id+"'>"+category.name+"</option>");
                        $("#categoryListId").append($option);
                    }
                });
        });
    });
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">商品管理</span>
        </div>
    </div>
    <div class="content-text">
        <div class="square-order">
            <s:form action="product_updateIfPresent" method="post">
                <s:hidden name="product.id"/>
                <div style="border:1px solid #cecece;">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr bgcolor="#FFFFFF">
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">供应商:</td>
                            <td width="32%">
                                <s:select id="supplierListId" name="supplierId" list="supplierList" listKey="id" listValue="name" cssStyle="width: 190px"></s:select>
                            </td>
                            <td width="18%" height="30" align="center">商品类别:</td>
                            <td width="32%">
                                <s:select id="categoryListId" name="categoryId" list="categoryList" listKey="id" listValue="name" cssStyle="width: 190px"></s:select>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">商品名称:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="product.name" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">产地:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="product.origin" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">生产厂家:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="product.producer" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">单位:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="product.unit" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">进价:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="product.bid" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">售价:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="product.price" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">最高预警值:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="product.ula" size="25"/>
                            </td>
                        </tr>
                        <tr bgcolor="#FFFFFF">
                            <td width="18%" height="30" align="center">最低预警值:</td>
                            <td width="82%" colspan="3">
                                <s:textfield name="product.lla" size="25"/>
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
