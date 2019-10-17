<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="/css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
    $(function () {
        $("#commit").click(function () {
            $("form:first").submit();
        });
        $("#supplier").change(function () {
            var supplierId = $(this).val();
            $.post("order_ajaxListCategoryAndProduct.action", {"supplierId":supplierId}, function (data) {
                var categoryList = data.categoryList;
                $(".category").empty();
                for (var i=0;i<categoryList.length;i++) {
                    var $category_option = $("<option value='"+categoryList[i].id+"'>"+categoryList[i].name+"</option>");
                    $(".category").append($category_option);
                }
                var productList = data.productList;
                $(".product").empty();
                for (var j=0;j<productList.length;j++) {
                    var $product_option = $("<option value='"+productList[j].id+"'>"+productList[j].name+"</option>");
                    $(".product").append($product_option);
                }
                var price = data.product.bidView;
                // 修改数量为1
                $(".quantity").val(1);
                // 修改单价
                $(".price").val(price);
                // 修改小计
                $(".subtotal").html(price+" 元");
                $(".total").html(price+" 元");
            });
        });
        $(".category").change(function () {
            var categoryId = $(this).val();
            $.post("order_ajaxListProductByCategory.action", {"categoryId":categoryId}, function (data) {
                var productList = data.productList;
                $(".product").empty();
                for (var j=0;j<productList.length;j++) {
                    var $product_option = $("<option value='"+productList[j].id+"'>"+productList[j].name+"</option>");
                    $(".product").append($product_option);
                }
                var price = data.product.bidView;
                // 修改数量为1
                $(".quantity").val(1);
                // 修改单价
                $(".price").val(price);
                // 修改小计
                $(".subtotal").html(price+" 元");
                $(".total").html(price+" 元");
            });
        });
        // 修改商品
        $(".product").change(function () {
            var productId = $(this).val();
            $.post("order_ajaxGetProductPrice.action", {"productId":productId}, function (data) {
                var price = data.bidView;
                // 修改数量为1
                $(".quantity").val(1);
                // 修改单价
                $(".price").val(price);
                // 修改小计
                $(".subtotal").html(price+" 元");
                $(".total").html(price+" 元");
            });
        });
    });
</script>
<div class="content-right">
    <div class="content-right-pic_w">
        <div style="margin:8px auto auto 12px;margin-top:6px">
            <span class="page_title">采购订单管理</span>
        </div>
    </div>
    <div class="content-text">
        <s:form action="order_buySave" method="POST">
            <div class="square-order-top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:'黑体';">
                    <tr>
                        <td width="68px" height="30">供应商：</td>
                        <td width="648px">
                            <s:select id="supplier" list="supplierList" listKey="id" listValue="name" cssStyle="width:190px"></s:select>
                        </td>
                        <td height="30">
                            <a id="add"><img src="/image/can_b_02.gif" border="0" /></a>
                        </td>
                    </tr>
                </table>
            </div>
            <!--"square-order-top"end-->
            <div class="square-order">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr align="center" style="background:url(/image/table_bg.gif) repeat-x;">
                        <td width="25%" height="30">商品类别</td>
                        <td width="25%">商品名称</td>
                        <td width="10%">采购数量</td>
                        <td width="15%">单价</td>
                        <td width="15%">合计</td>
                        <td width="10%">操作</td>
                    </tr>
                    <tr align="center" bgcolor="#FFFFFF">
                        <td height="30">
                            <s:select cssClass="category" list="categoryList" listKey="id" listValue="name" cssStyle="width:200px"></s:select>
                        </td>
                        <td>
                            <s:select cssClass="product" list="productList" listKey="id" listValue="name" cssStyle="width:200px"></s:select>
                        </td>
                        <td>
                            <input name="quantity" class="quantity" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/>
                        </td>
                        <td>
                            <input name="price" class="price" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="${productList[0].bidView}"/>&nbsp;元
                        </td>
                        <td class="subtotal" align="right">${productList[0].bidView}&nbsp;元</td>
                        <td>
                            <a class="deleteBtn delete edit" value="4"><img src="/image/icon_04.gif" />删除</a>
                        </td>
                    </tr>
                    <tr id="finalTr" align="center" style="background:url(/image/table_bg.gif) repeat-x;">
                        <td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
                        <td class="total" width="16%" align="right">${productList[0].bidView}&nbsp;元</td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
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
            </div><!--"square-order"end-->
        </s:form>
    </div><!--"content-text"end-->
    <div class="content-bbg"><img src="/image/content_bbg.jpg"/></div>
</div>
